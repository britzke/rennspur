package de.rennspur.beans;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.annotations.SelectedEvent;
import de.rennspur.model.Event;
import de.rennspur.model.Team;

/**
 * A Bean to manage a list of Teams for a specific Event, including CRUD
 * database operations.
 *
 * @author Leon Schlender, burghard.britzke bubi@charmides.in-berlin.de
 */
@RequestScoped
@Named
public class TeamBean {

	@Inject
	EntityManager entityManager;

	private List<Team> teams;

	@Inject
	private SelectedTeamBean selectedTeamBean;

	@Inject
	@SelectedEvent
	private Event selectedEvent;

	/**
	 * Initalizes the list of {@link Team} objects for the selected
	 * {@link Event}. If there is no selected event, the bean can not be
	 * initalized. In this case an {@link IllegalArgumentException} is thrown.
	 *
	 * @throws IllegalArgumentException
	 *             If no selected {@link Event} exists.
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (selectedEvent == null) {
			throw new IllegalArgumentException(
					"TeamBean cannot be instantiated without a selected event");
		}
		Query q = entityManager.createNamedQuery("Team.findTeamsByEvent");
		q.setParameter("event", selectedEvent);
		this.teams = q.getResultList();
		// make the selected event be managed again
		selectedEvent = entityManager.merge(selectedEvent);
	}

	/**
	 * Action routine for a command button. Initializes the selectedTeamBean
	 * with a new {@link Team} and redirects to team.xhtml in order to enable
	 * the user to enter data for a new team. The hash of the new team is
	 * initialized with a hash.
	 *
	 * @return Redirects to "team.xhtml", to enable the user to insert data into
	 *         the team form.
	 * @throws NoSuchAlgorithmException
	 */
	public String add() throws NoSuchAlgorithmException {
		// create a digest from actual date
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(new Date().toString().getBytes());
		StringBuffer hash = new StringBuffer();
		for (byte b : digest) {
			hash.append(String.format("%02x", b));
		}
		Team team = new Team(hash.toString());

		selectedTeamBean.setTeam(team);
		return "team.xhtml?faces-redirect=true";
	}

	/**
	 * Persists a team into the database.
	 *
	 * @return Empty string – ever.
	 */
	public String persist() {
		try {
			EntityTransaction et = entityManager.getTransaction();
			et.begin();
			selectedTeamBean
					.setTeam(entityManager.merge(selectedTeamBean.getTeam()));
			et.commit();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return "Teams.xhtml?faces-redirect=true";
	}

	/**
	 * Removes the selectedTeam from the database and navigates to the list of
	 * teams.
	 *
	 * @return "Teams.xhtml?faces-redirect=true" - ever!
	 */
	public String remove() {
		EntityTransaction et = entityManager.getTransaction();
		et.begin();
		selectedTeamBean
				.setTeam(entityManager.merge(selectedTeamBean.getTeam()));
		entityManager.remove(selectedTeamBean.getTeam());
		selectedTeamBean.setTeam(null);
		et.commit();

		return "Teams.xhtml?faces-redirect=true";
	}

	/**
	 * Redirects user to "team.xhtml".
	 *
	 * @param event
	 */
	public void onRowDblselect(SelectEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("team.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	/**
	 * @return the selectedTeamBean
	 */
	public SelectedTeamBean getSelectedTeamBean() {
		return selectedTeamBean;
	}

	/**
	 * @param selectedTeamBean
	 *            the selectedTeamBean to set
	 */
	public void setSelectedTeamBean(SelectedTeamBean selectedTeamBean) {
		this.selectedTeamBean = selectedTeamBean;
	}

	/**
	 * @return the selectedEvent
	 */
	public Event getSelectedEvent() {
		return selectedEvent;
	}

	/**
	 * @param selectedEvent
	 *            the selectedEvent to set
	 */
	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
