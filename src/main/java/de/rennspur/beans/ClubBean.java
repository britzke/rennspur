package de.rennspur.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.backend.ApiGPS;
import de.rennspur.model.Club;

@ApplicationScoped
@Named
public class ClubBean {

	List<Club> clubs;
	Club selectedClub;
	
	@PostConstruct
	public void init(){
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Club.findAll");
		@SuppressWarnings("unchecked")
		List<Club> clubs = q.getResultList();
		this.clubs = clubs;
	}

	@Inject
	EntityManagerFactory emf;
	/**
	 * Inserts a club into the database.
	 */
	public void insertNewClub(String abbreviation, String dsv_number, String name, String url) {
		try {
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();

			Club club = new Club();
			club.setAbreviation(abbreviation);
			club.setDsvNumber(dsv_number);
			club.setName(name);
			club.setUrl(url);

			//em.getEntityManagerFactory().getCache().evictAll();
			//em.refresh(em.find(Club.class, 1));
			
			em.merge(club);
			et.commit();
			em.close();

		} catch (NoResultException e) {
			e.printStackTrace();
		}
	}
	
	public void onRowSelect(SelectEvent club) {
		try {
			System.out.println("CLUB : " + club);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("club.xhtml?id=" + selectedClub.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the clubs
	 */
	public List<Club> getClubs() {
		return clubs;
	}

	/**
	 * @param clubs the clubs to set
	 */
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

	/**
	 * @return the selectedClub
	 */
	public Club getSelectedClub() {
		return selectedClub;
	}

	/**
	 * @param selectedClub the selectedClub to set
	 */
	public void setSelectedClub(Club selectedClub) {
		this.selectedClub = selectedClub;
	}
}
