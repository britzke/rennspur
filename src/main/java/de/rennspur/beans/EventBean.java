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
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.model.Event;
import de.rennspur.model.Race;

/**
 * The EventBean is an bean, which provides a list of events and information
 * about selected event, races, teams.
 *
 * @author burghard.britzke mailto:bubi@charmides.in-berlin.de
 */
@RequestScoped
@Named
public class EventBean {

	@Inject
	private transient EntityManager entityManager;

	private List<Event> events;

	@Inject
	private SelectedEventBean selectedEventBean;

	private List<Race> eventRaces;

	/**
	 * Constructs a new EventBean. Initializes the actual races list with an
	 * empty list.
	 */
	public EventBean() {
		eventRaces = new ArrayList<Race>();
	}

	/**
	 * Initializes the list of Events with the actual list of events in the
	 * database.
	 */
	@PostConstruct
	public void init() {
		Query q = entityManager.createNamedQuery("Event.findAll");
		@SuppressWarnings("unchecked")
		List<Event> events = q.getResultList();
		this.events = events;
		if (selectedEventBean.getEvent() != null) {	// make selectedEvent be managed again
			selectedEventBean.setEvent(entityManager.merge(selectedEventBean.getEvent()));
		}
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * Initializes the selectedEventBean to a new Event and navigates to event.xhtml in
	 * order to enable the user to enter data for a new event.
	 *
	 * @return Navigation to "event.xhtml", to enable the user to insert data
	 *         into the club form.
	 */
	public String add() {
		selectedEventBean.setEvent(new Event());
		return "event.xhtml?faces-redirect=true";
	}

	/**
	 * Persists an event into the database.
	 */
	public String persist() {
		EntityTransaction et = entityManager.getTransaction();
		et.begin();
		selectedEventBean.setEvent(entityManager.merge(selectedEventBean.getEvent()));
		et.commit();
		events.add(selectedEventBean.getEvent());
		return "Events.xhtml?faces-redirect=true";
	}

	/**
	 * Removes the selectedEvent from the database and navigates to the list of
	 * events.
	 *
	 * @return "Events.xhtml?faces-redirect=true";
	 */
	public String remove() {
		EntityTransaction et = entityManager.getTransaction();
		et.begin();
		Event event = entityManager.merge(selectedEventBean.getEvent());
		entityManager.remove(event);
		et.commit();

		return "Clubs.xhtml?faces-redirect=true";
	}

	/**
	 * Remove an event from the events managed by this bean.
	 *
	 * @param event
	 *            The event, which is to be removed.
	 * @return The event, which has been removed.
	 */
	public Event removeEvent(Event event) {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		getEvents().remove(event);
		entityManager.remove(event);
		t.commit();
		return event;
	}

	/**
	 * @return the selectedEventBean
	 */
	public SelectedEventBean getSelectedEventBean() {
		return selectedEventBean;
	}

	/**
	 * @param selectedEventBean the selectedEventBean to set
	 */
	public void setSelectedEventBean(SelectedEventBean selectedEventBean) {
		this.selectedEventBean = selectedEventBean;
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
						for (Race actualRace : eventRaces) {
							if (actualRace.getEvent().getId() == event
									.getId()) {
								eventRaces.remove(actualRace);
							}
						}
						eventRaces.add(r);
						return;
					}
				}
				e.addRace(race);
				eventRaces.add(race);
				return;
			}
		}
		events.add(event);
		eventRaces.add(race);
	}

	/**
	 * Get the actual race for an event.
	 *
	 * @param event
	 *            The event for which the actual race should be gotten.
	 * @return The actual race for the given event.
	 */
	public Race getActualRace(Event event) {
		for (Race race : eventRaces) {
			if (race.getEvent().getId() == event.getId()) {
				return race;
			}
		}
		return null;
	}

	public void onDblselect(SelectEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("event.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
