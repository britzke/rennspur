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
package de.rennspur.test.beans;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import de.rennspur.beans.TeamBean;
import de.rennspur.model.Event;
import de.rennspur.model.Team;

/**
 * Unit test for the unit TeamBean.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@RunWith(MockitoJUnitRunner.class)
public class TeamBeanTest {
	@Mock
	private EntityManager em;
	@Mock
	private Query q;
	@Mock
	private EntityTransaction transaction;

	@Mock
	Event selectedEvent;

	@InjectMocks
	private TeamBean proband;

	private List<Team> teams;

	/**
	 * Set up the Mock objects to act like desired.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		when(em.createNamedQuery("Team.findTeamsByEvent")).thenReturn(q);
		when(q.getResultList()).thenReturn(teams = new ArrayList<Team>());
	}

	/**
	 * Test method for {@link de.rennspur.beans.TeamBean#init()}.
	 */
	@Test
	public void testInit() {
		proband.init();

		assertEquals(
				"The TeamBean's list of teams must be the list, which the EntityManager returns for the query 'Race.findRaceByEvent'",
				proband.getTeams(), teams);
	}

	/**
	 * Test method for
	 * {@link de.rennspur.beans.TeamBean#onRowSelect(org.primefaces.event.SelectEvent)}.
	 */
	@Test
	public void testOnRowSelect() {
		// TODO implement test, if method used.
	}

	/**
	 * Test method for {@link de.rennspur.beans.TeamBean#insertNewTeam()}.
	 */
	@Test
	public void testInsertNewTeam() {
		// TODO Num2 should implement the test.
	}
}
