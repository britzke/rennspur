package de.rennspur.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import de.rennspur.model.Club;

@ApplicationScoped
@Named
public class ClubBean {

	String abbreviation;
	String dsv_number;
	String name;
	String url;

	@Inject
	EntityManagerFactory emf;

	/**
	 * Inserts a club into the database.
	 */
	public void insertNewClub() {
		try {
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();

			Club club = new Club();
			club.setAbreviation(abbreviation);
			club.setDsvNumber(dsv_number);
			club.setName(name);
			club.setUrl(url);

			em.merge(club);
			et.commit();
			em.close();

		} catch (NoResultException e) {
			e.printStackTrace();
		}

	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abreviation) {
		this.abbreviation = abreviation;
	}

	public String getDsv_number() {
		return dsv_number;
	}

	public void setDsv_number(String dsv_number) {
		this.dsv_number = dsv_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
