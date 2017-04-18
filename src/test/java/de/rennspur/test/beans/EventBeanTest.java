/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2017  Maximilian Lietzmann,
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
package de.rennspur.test.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.primefaces.event.SelectEvent;

import de.rennspur.beans.EventBean;
import de.rennspur.model.Event;

/**
 * Tests for UnitEventBean.
 * 
 * @author maXLeev
 */
@RunWith(MockitoJUnitRunner.class)
public class EventBeanTest {
	@Mock
	EntityManager em;
	@Mock
	Query q;
	@Mock
	EntityTransaction transaction;

	@InjectMocks
	EventBean proband;

	List<Event> list;

	/**
	 * Sets up the Mocks to get a list of events.
	 * 
	 * @throws Exception
	 *             on unexpected events.
	 */
	@Before
	public void setUp() throws Exception {
		when(em.createNamedQuery("Event.findAll")).thenReturn(q);
		when(em.getTransaction()).thenReturn(transaction);
		when(q.getResultList()).thenReturn(list = new ArrayList<Event>());
	}

	/**
	 * Test method for {@link de.rennspur.beans.EventBean#init(void)}. Tests if
	 * the list of events is filled with the same list, an EntityManager returns
	 * for the query "Event.findAll".
	 */
	@Test
	public void testInit() {
		proband.init();
		
		assertEquals(
				"The EventBean's list of events must be the list, which the EntityManager returns for the query 'Event.findAll'",
				proband.getEvents(), list);
	}

	/**
	 * Test method for {@link de.rennspur.beans.EventBean#addEvent(Event)}.
	 * Tests if the event is added to the list of events in this bean and if the
	 * event is merged to the list of entities managed by the entityManager.
	 */
	@Test
	public void testAddEvent() {
		Event event = new Event();
		proband.init();

		Event mergedEvent = proband.addEvent(event);

		verify(em).merge(event);
		verify(transaction).commit();
		assertTrue(
				"The event must be added to the list of events managed by this bean",
				proband.getEvents().contains(mergedEvent));
	}

	/**
	 * Test method for {@link de.rennspur.beans.EventBean#removeEvent(Event)}.
	 * Tests if the event is removed from the list of events managed by this
	 * bean and if it is removed from the database.
	 */
	@Test
	public void testRemoveEvent() {
		Event event = new Event();
		proband.init();

		Event mergedEvent = proband.removeEvent(event);

		verify(em).remove(event);
		verify(transaction).commit();
		assertFalse(
				"The event must not to be in the list of events managed by this bean, when removed",
				proband.getEvents().contains(mergedEvent));
	}

	/**
	 * Test method for {@link de.rennspur.beans.EventBean#onRowSelect(SelectEvent)}.
	 */
	@Test
	public void testOnRowSelect() {
		// TODO implement the test if onRowSelect() is beeing used.
	}
}
