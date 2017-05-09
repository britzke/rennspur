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

package de.rennspur.test.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import de.rennspur.model.Waypoint;

/**
 * @author bubi
 *
 */
public class EventTest {
	@InjectMocks
	Waypoint proband;

	@Mock
	List<Waypoint> waypoints;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.rennspur.model.Event#addRace(de.rennspur.model.Race)}.
	 */
	@Test
	public void testAddRace() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.rennspur.model.Event#removeRace(de.rennspur.model.Race)}.
	 */
	@Test
	public void testRemoveRace() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.rennspur.model.Event#addWaypoint(de.rennspur.model.Waypoint)}.
	 */
	@Test
	public void testAddWaypoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.rennspur.model.Event#removeWaypoint(de.rennspur.model.Waypoint)}.
	 */
	@Test
	public void testRemoveWaypoint() {
		fail("Not yet implemented");
	}

	/**
	 * The method for
	 * {@link de.rennspur.model.Event#Event(de.rennspur.model.Event)}.
	 */
	@Test
	public void testEvent() {
		assertEquals(waypoints.size(), 0);
	}

}
