/*
 *  This file is part of Renspur.
 *
 *  Copyright (C) 2017  burghard.britzke bubi@charmides.in-berlin.de
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import de.rennspur.beans.RaceBean;
import de.rennspur.beans.SelectedEventBean;
import de.rennspur.model.Event;
import de.rennspur.model.Race;

/**
 * Unit test for the unit {@link de.rennspur.beans.RaceBean}
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@RunWith(MockitoJUnitRunner.class)
public class RaceBeanTest {
	@Mock
	private EntityManager entityManager;
	@Mock
	private Query query;
	@Mock
	private EntityTransaction transaction;
	@Mock
	private SelectedEventBean selectedEventBean;
	@Mock
	private ActionEvent actionEvent;
	@Mock
	List<Race> races;
	@Mock
	private Event selectedEvent;

	@InjectMocks
	RaceBean proband;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		when(selectedEventBean.getEvent()).thenReturn(selectedEvent);
		when(entityManager.getTransaction()).thenReturn(transaction);
	}

	/**
	 * Test method for {@link de.rennspur.beans.RaceBean#init()}. Tests, if the
	 * init() method throws an {@link IllegalArgumentException}, if there is no
	 * selectedEvent yet.
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testInitWithoutSelctedEvent() {
		when(selectedEventBean.getEvent()).thenReturn(null);
		thrown.expect(IllegalArgumentException.class);
		proband.init();
	}

	/**
	 * Test method for {@link de.rennspur.beans.RaceBean#init()}. Tests, if the
	 * list of races is been initialized after init().
	 */
	@Test
	public void testInitWithSelectedEvent() {
		when(entityManager.createNamedQuery("Race.findRacesByEvent"))
				.thenReturn(query);
		when(query.getResultList()).thenReturn(races);

		proband.init();

		assertEquals(
				"The RaceBean's races of races must be the races, which the EntityManager returns for the query 'Race.findRaceByEvent'",
				proband.getRaces(), races);
	}

	/**
	 * Test method for {@link de.rennspur.beans.RaceBean#add()}. Tests if a new
	 * Race is added to the database, to the selectedEventBean and to
	 * RacesBean::races.
	 */
	@Test
	public void testAdd() {

		when(selectedEventBean.getEvent()).thenReturn(selectedEvent);

		proband.add(actionEvent);

		verify(races).add(any());
		verify(selectedEvent).addRace(any());
		verify(transaction).commit();
	}

	/**
	 * Test method for {@link de.rennspur.beans.RaceBean#removeLast()}. Tests if
	 * the race with the highest number is removed from the database, the
	 * selectedEventBean.
	 */
	@Test
	public void testRemoveLastWithEmptyRaces() {

		proband.removeLast(actionEvent);

		verify(selectedEventBean, times(0)).getEvent();
	}

	@Test
	public void testRemoveLastWithNonEmptyRaces() {
		when(races.size()).thenReturn(1);

		proband.removeLast(actionEvent);
		verify(races).remove(any());
	}
}
