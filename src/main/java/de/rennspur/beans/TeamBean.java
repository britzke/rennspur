package de.rennspur.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.model.Club;
import de.rennspur.model.Team;

@RequestScoped
@Named
public class TeamBean {
	
	List<Team> team;
	private List<Team> teams;
	private Team selectedTeam;
	
	@Inject 
	private EventBean eventBean;
	
	@Inject
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Team.findTeamsByEventId");
		q.setParameter("id",eventBean.getSelectedEvent().getId());
		this.teams = q.getResultList();
	}

	public void onRowSelect(SelectEvent team) {
		try {
			System.out.println("TeamBean::onRowSelect(team="+team+")");
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
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			Team team = new Team();
			
			Query q = em.createNamedQuery("Team.findTeamByEventID");
			q.setParameter("id", selectedTeam.getId());
			
			//team.setMembers(); //todo
			team.setClub((Club) q.getSingleResult());
			
			em.merge(team);
			et.commit();
			em.close();

		} catch (NoResultException e) {
			e.printStackTrace();
		}

	}

	public List<Team> getTeams() {
		return teams;
	}

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
	 * @param selectedTeam the selectedTeam to set
	 */
	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}


	/**
	 * @return the selectedEvent
	 */
	public EventBean getEventBean() {
		return eventBean;
	}

	/**
	 * @param selectedEvent the selectedEvent to set
	 */
	public void setEventBean(EventBean eventBean) {
		this.eventBean = eventBean;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
}
