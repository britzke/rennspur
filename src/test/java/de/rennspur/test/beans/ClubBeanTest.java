/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2017  burghard.britzke bubi@charmides.in-berlin.de
 *  					Konstantin Baltruschat
 *  					Ruben Maurer
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import de.rennspur.beans.ClubBean;
import de.rennspur.model.Club;

/**
 * Tests the ClubBean unit.
 *
 * @author britzke
 */
@RunWith(MockitoJUnitRunner.class)
public class ClubBeanTest {

	@InjectMocks
	ClubBean proband;

	@Mock
	EntityManagerFactory emf;
	@Mock
	EntityManager em;
	@Mock
	Query q;
	@Mock
	EntityTransaction et;

	/**
	 * Initializes the Mocks to return the objects needed for the test.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		when(emf.createEntityManager()).thenReturn(em);
		when(em.createNamedQuery("Club.findAll")).thenReturn(q);
		when(em.getTransaction()).thenReturn(et);
		when(em.merge(any(Club.class))).then(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Club club = (Club) invocation.getArguments()[0];
				Club newClub = new Club();
				newClub.setName(club.getName());
				newClub.setAbreviation(club.getAbreviation());
				newClub.setUrl(club.getUrl());
				newClub.setDsvNumber(club.getDsvNumber());

				/*
				 * assertEquals(proband.getName(), club.getName());
				 * assertEquals(proband.getAbbreviation(),
				 * club.getAbreviation()); assertEquals(proband.getUrl(),
				 * club.getUrl()); assertEquals(proband.getDsv_number(),
				 * club.getDsvNumber());
				 */
				return newClub;
			}
		});
	}

	/**
	 * Tests if the clubs list field is initialized from the database. Test
	 * method for {@link de.rennspur.beans.ClubBean#init()}.
	 */
	@Test
	public void testIfInitInitializesFieldClubs() {

		proband.init();
		assertNotNull("Field 'Clubs' must be initialized.", proband.getClubs());
	}

	/**
	 * Test method for {@link de.rennspur.beans.ClubBean#insertNewClub()}.
	 * 
	 * @throws SecurityException
	 *             If a SecurityManager is used and no permission to some
	 *             reflection operation is granted.
	 * @throws NoSuchMethodException
	 *             If an expected Method of the class under test is not defined
	 *             by it.
	 */
	@Test
	public void testInsertNewClub()
			throws NoSuchMethodException, SecurityException {

		Class<? extends ClubBean> probandClass = proband.getClass();
		Method insertNewClubMethod = probandClass
				.getDeclaredMethod("insertNewClub");
		assertTrue(insertNewClubMethod.getReturnType().equals(Void.TYPE));

		proband.insertNewClub();

		verify(et, atLeast(1)).commit();
	}

	/**
	 * Test method for
	 * {@link de.rennspur.beans.ClubBean#onRowSelect(org.primefaces.event.SelectEvent)}.
	 */
	@Test
	public void testOnRowSelect() {
		// TODO implement the test if the method is used.
	}
}
