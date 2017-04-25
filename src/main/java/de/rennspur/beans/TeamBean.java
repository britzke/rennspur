package de.rennspur.beans;

import java.io.IOException;
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

	private List<Team> teams;
	private Team selectedTeam;

	@Inject @SelectedEvent
	private Event selectedEvent;

	@Inject
	EntityManager entityManager;

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
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		try {
			System.out.println("TeamBean::onRowSelect(event=" + event + ")");
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("Team.xhtml?id=" + selectedTeam.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts a team into the database.
	 */
	public void insertNewTeam() {
		try {
			EntityTransaction et = entityManager.getTransaction();
			et.begin();
			selectedTeam = entityManager.merge(selectedTeam);
			et.commit();
		} catch (NoResultException e) {
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
	 * @return the selectedTeam
	 */
	public Team getSelectedTeam() {
		return selectedTeam;
	}

	/**
	 * @param selectedTeam
	 *            the selectedTeam to set
	 */
	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	/**
	 * @return the selectedEvent
	 */
	public Event getSelectedEvent() {
		return selectedEvent;
	}

	/**
	 * @param selectedEvent the selectedEvent to set
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
