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

import de.rennspur.model.Event;
import de.rennspur.model.Race;

/**
 * The RaceBean is an application scoped bean, which provides the actual race
 * for each event, which is currently running
 * 
 * @author burghard.britzke mailto:bubi@charmides.in-berlin.de
 */
@RequestScoped
@Named
public class RaceBean {

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private EventBean eventBean;
	
	private List<Race> races;
	private Race selectedRace;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		Query q = entityManager.createNamedQuery("Race.findRacesByEventId");
		q.setParameter("event", eventBean.getSelectedEvent());
		races = q.getResultList();
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

			Race race = new Race();

			Query q = entityManager.createNamedQuery("Event.findEventByID");
			q.setParameter("id", eventBean);
						
			race.setEvent((Event) q.getSingleResult());

			entityManager.merge(race);
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
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	/**
	 * @return the eventBean
	 */
	public EventBean getEventBean() {
		return eventBean;
	}
	/**
	 * @param eventBean the eventBean to set
	 */
	public void setEventBean(EventBean eventBean) {
		this.eventBean = eventBean;
	}
	/**
	 * @return the races
	 */
	public List<Race> getRaces() {
		System.out.println("RaceBean::getRaces(): "+races);
		return races;
	}
	/**
	 * @param races the races to set
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
	 * @param selectedRace the selectedRace to set
	 */
	public void setSelectedRace(Race selectedRace) {
		this.selectedRace = selectedRace;
	}

}
