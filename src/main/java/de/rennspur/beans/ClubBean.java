/*
 *  This file is part of Renspur.
 *
 *  Copyright (C) 2016  Ruben Maurer,
 *  					Leon Schlender,
 *  					burghard.britzke bubi@charmides.in-berlin.de
 *
 *  Rennspur is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Rennspur is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with Rennspur.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.model.Club;

/**
 * The ClubBean is a request scoped bean, to manage a list of clubs.
 *
 * @author Ruben Maurer, Leon Schlender, burghard.britzke
 *         bubi@charmides.in-berlin.de
 */
@RequestScoped
@Named
public class ClubBean {

	@Inject
	private EntityManager entityManager;

	private List<Club> clubs;

	@Inject
	private SelectedClubBean selectedClubBean;

	/**
	 * Initialises the clubs list from the database with all clubs.
	 */
	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		Query q = entityManager.createNamedQuery("Club.findAll");
		clubs = q.getResultList();
	}

	/**
	 * Initialises the selectedClubBean to a new Club and navigates to
	 * club.xhtml in order to enable the user to enter data for a new club.
	 *
	 * @return Navigation to "club.xhtml", to enable the user to insert data
	 *         into the club form.
	 */
	public String add() {
		selectedClubBean.setClub(new Club());
		return "club.xhtml&faces-redirect=true";
	}

	/**
	 * Persists a club into the database.
	 */
	public String persist() {
		EntityTransaction et = entityManager.getTransaction();
		et.begin();

		selectedClubBean
				.setClub(entityManager.merge(selectedClubBean.getClub()));
		et.commit();
		return "Clubs.xhtml?faces-redirect=true";
	}

	/**
	 * Removes the selectedClubBean from the database and navigates to the list
	 * of clubs.
	 *
	 * @return "Clubs.xhtml?faces-redirect=true";
	 */
	public String remove() {
		EntityTransaction et = entityManager.getTransaction();
		et.begin();
		Club club = entityManager.merge(selectedClubBean.getClub());
		entityManager.remove(club);
		selectedClubBean.setClub(null);
		et.commit();

		return "Clubs.xhtml?faces-redirect=true";
	}

	/**
	 * Handler for a double click on a row. Loads the selected club row into the
	 * club.xhtml form.
	 *
	 * @param event
	 */
	public void onRowDblselect(SelectEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("club.xhtml");
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
	 * @param clubs
	 *            the clubs to set
	 */
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

	/**
	 * @return the selectedClubBean
	 */
	public SelectedClubBean getSelectedClubBean() {
		return selectedClubBean;
	}

	/**
	 * @param selectedClubBean
	 *            the selectedClubBean to set
	 */
	public void setSelectedClubBean(SelectedClubBean selectedClub) {
		this.selectedClubBean = selectedClub;
	}
}