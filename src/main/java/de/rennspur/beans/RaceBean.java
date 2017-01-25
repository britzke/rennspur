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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.model.Event;
import de.rennspur.model.Race;

/**
 * The RaceBean is an application scoped bean, which provides the actual race
 * for each event, which is currently running
 * 
 * @author burghard.britzke mailto:bubi@charmides.in-berlin.de
 */
@ApplicationScoped
@Named
public class RaceBean {
	@Inject
	EntityManagerFactory emf;

	List<Event> events;
	Race selectedRace;
	List<Race> actualRaces;

	/**
	 * Constructs a new RaceBean. Initializes the actual races list with an
	 * empty list.
	 */
	public RaceBean() {
		actualRaces = new ArrayList<Race>();
	}

	/**
	 * Initializes the list of Races with the actual list of races in the
	 * database.
	 */
	@PostConstruct
	public void init() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Race.findAll");
		@SuppressWarnings("unchecked")
		List<Race> races = q.getResultList();
		this.actualRaces = races;
	}

	/**
	 * @return the events
	 */
	public List<Race> getRaces() {
		return actualRaces;
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
	public void setselectedRace(Race selectedRace) {
		System.out.println(selectedRace);
		this.selectedRace = selectedRace;
	}

	/**
	 * Add an event to the events managed by this bean.
	 * 
	 * @param event
	 *            The event, which is to be added.
	 * @return The event, which has been added.
	 */
	public Race addRace(Race race) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		race = em.merge(race);
		getRaces().add(race);
		t.commit();
		return race;
	}

	/**
	 * Remove an race from the races managed by this bean.
	 * 
	 * @param race
	 *            The race, which is to be removed.
	 * @return The race, which has been removed.
	 */
	public Race removeRace(Race race) {
		getRaces().remove(race);
		return race;
	}

	/**
	 * Set the actual race for a given event.
	 * 
	 * @param event
	 *            The event for which the actual race should be set.
	 * @param race
	 *            The race, which is to be set as the actual Race.
	 */
	public void setActualRace(Event event, Race race) {
		for (Event e : events) {
			if (e.getId() == event.getId()) {
				for (Race r : e.getRaces()) {
					if (r.getId() == race.getId()) {
						for (Race actualRace : actualRaces) {
							if (actualRace.getEvent().getId() == event
									.getId()) {
								actualRaces.remove(actualRace);
							}
						}
						actualRaces.add(r);
						return;
					}
				}
				e.addRace(race);
				actualRaces.add(race);
				return;
			}
		}
		events.add(event);
		actualRaces.add(race);
	}

	/**
	 * Get the actual race for an event.
	 * 
	 * @param event
	 *            The event for which the actual race should be gotten.
	 * @return The actual race for the given event.
	 */
	public Race getActualRace(Event event) {
		for (Race race : actualRaces) {
			if (race.getEvent().getId() == event.getId()) {
				return race;
			}
		}
		return null;
	}

	public void onRowSelect(SelectEvent event) {
		try {
			System.out.println(event);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("event.xhtml?id=" + selectedRace.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
