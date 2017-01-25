package de.rennspur.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.rennspur.model.Club;
import de.rennspur.model.Team;

@ApplicationScoped
@Named
public class TeamBean {
	
	String name;
	String country;
	String email;
	String hash;
	int handicap;
	int clubid;
	
	@Inject
	EntityManagerFactory emf;

	/**
	 * Inserts a team into the database.
	 */
	public void insertNewTeam() {
		try {
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			Team team = new Team();
			team.setCountry(getCountry());
			team.setEmail(getEmail());
			team.setHandicapFactor(getHandicap());
			team.setHash(getHash());
			
			Query q = em.createNamedQuery("Club.findClubByID");
			q.setParameter("id", clubid);
			
			//team.setMembers(); //todo
			team.setClub((Club) q.getSingleResult());	
			team.setName(getName());
			
			em.merge(team);
			et.commit();
			em.close();

		} catch (NoResultException e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}
	
	public String getHash() {
		return hash;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHandicap() {
		return handicap;
	}

	public void setHandycap(int handycap) {
		this.handicap = handycap;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public int getId() {
		return clubid;
	}

	public void setId(int id) {
		this.clubid = id;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	public int getClubid() {
		return clubid;
	}

	public void setClubid(int clubid) {
		this.clubid = clubid;
	}

}
