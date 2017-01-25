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
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.primefaces.event.SelectEvent;

import de.rennspur.model.Club;
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
	int number;
	int event_id;

	/**
	 * Add an event to the events managed by this bean.
	 * 
	 * @param event
	 *            The event, which is to be added.
	 * @return The event, which has been added.
	 */
	public void addRace() {
		try {
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();

			Race race = new Race();
			race.setNumber(number);

			Query q = em.createNamedQuery("Event.findRaceByID");
			q.setParameter("id", 1);

			em.merge(race);
			et.commit();
			em.close();

		} catch (NoResultException e) {
			e.printStackTrace();
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

}
