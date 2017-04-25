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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.rennspur.annotations.SelectedEvent;
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
	@SelectedEvent
	private Event selectedEvent;

	private List<Race> races;
	private Race selectedRace;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (selectedEvent == null) {
			throw new IllegalArgumentException(
					"RaceBean cannot be instantiated without a selected event");
		}
		Query q = entityManager.createNamedQuery("Race.findRacesByEvent");
		q.setParameter("event", selectedEvent);
		races = q.getResultList();
		// make the selected event be managed again
		selectedEvent = entityManager.merge(selectedEvent);
	}

	/**
	 * Add an event to the events managed by this bean.
	 * 
	 * @param event
	 *            The event, which is to be added.
	 * @return The event, which has been added.
	 */
	public void addRace() {
		try {
			EntityTransaction et = entityManager.getTransaction();
			et.begin();

			entityManager.merge(selectedRace);
			et.commit();

		} catch (NoResultException e) {
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
	 * @return the selectedEvent
	 */
	public Event getSelectedEvent() {
		return selectedEvent;
	}

	/**
	 * @return the races
	 */
	public List<Race> getRaces() {
		System.out.println("RaceBean::getRaces(): " + races);
		return races;
	}

	/**
	 * @param races
	 *            the races to set
	 */
	public void setRaces(List<Race> races) {
		this.races = races;
	}

	/**
	 * @return the selectedRace
	 */
	public Race getSelectedRace() {
		return selectedRace;
	}

	/**
	 * @param selectedRace
	 *            the selectedRace to set
	 */
	public void setSelectedRace(Race selectedRace) {
		this.selectedRace = selectedRace;
	}

}
