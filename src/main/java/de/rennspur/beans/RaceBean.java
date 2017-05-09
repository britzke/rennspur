/*
 *  This file is part of Renspur.
 *
 *  Copyright (C) 2016  burghard.britzke bubi@charmides.in-berlin.de
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
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.model.Event;
import de.rennspur.model.Race;

/**
 * The RaceBean is a request scoped bean, which provides the list of races and
 * the actual race for the selected event, which is currently running
 *
 * @author burghard.britzke mailto:bubi@charmides.in-berlin.de
 */
@RequestScoped
@Named
public class RaceBean {

	@Inject
	private EntityManager entityManager;

	@Inject
	private SelectedEventBean selectedEventBean;

	private List<Race> races;

	/**
	 * Initializes the list of races with all races of the selected event.
	 * Merges the selected {@link Event}.
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (selectedEventBean.getEvent() == null) {
			throw new IllegalArgumentException(
					"RaceBean cannot be instantiated without a selected event");
		}
		Query q = entityManager.createNamedQuery("Race.findRacesByEvent");
		q.setParameter("event", selectedEventBean.getEvent());
		races = q.getResultList();
		// make the selected event be managed again
		selectedEventBean
				.setEvent(entityManager.merge(selectedEventBean.getEvent()));
	}

	/**
	 * Initializes the selectedRaceBean to a new {@link Race} and navigates to
	 * race.xhtml in order to enable the user to enter data for a race.
	 *
	 * @return
	 */
	public void add(ActionEvent actionEvent) {
		Race race = new Race();
		race.setEvent(selectedEventBean.getEvent());
		race.setNumber(races.size() + 1);
		races.add(race);
		selectedEventBean.getEvent().addRace(race);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		selectedEventBean
				.setEvent(entityManager.merge(selectedEventBean.getEvent()));
		transaction.commit();
	}

	/**
	 * Action method to remove a race from the races, managed by this bean.
	 *
	 * @return The event, which has been removed.
	 */
	public void removeLast(ActionEvent actionEvent) {
		int lastIndex = races.size() - 1;
		if (lastIndex >= 0) {
			Race lastRace = races.get(lastIndex);
			Event event = selectedEventBean.getEvent();
			EntityTransaction t = entityManager.getTransaction();
			t.begin();
			entityManager.remove(lastRace);
			t.commit();
			event.removeRace(lastRace);
			races.remove(lastRace);
		}
	}

	/**
	 * Handler for a double click on a row.
	 *
	 * @param event
	 *            The primefaces SelectEvent for the selected race row.
	 */
	public void onDblselect(SelectEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("race.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	/**
	 * @return the selectedEventBean
	 */
	public SelectedEventBean getSelectedEventBean() {
		return selectedEventBean;
	}

	/**
	 * @param selectedEventBean
	 *            the selectedEventBean to set
	 */
	public void setSelectedEventBean(SelectedEventBean selectedEventBean) {
		this.selectedEventBean = selectedEventBean;
	}

	/**
	 * @return the races
	 */
	public List<Race> getRaces() {
		return races;
	}

	/**
	 * @param races
	 *            the races to set
	 */
	public void setRaces(List<Race> races) {
		this.races = races;
	}
}