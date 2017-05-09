/*
 *  This file is part of Renspur.
 *
 *  Copyright (C) 2017  burghard.britzke bubi@charmides.in-berlin.de, leo.winter
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

import org.junit.Before;
import org.junit.Test;

import de.rennspur.model.Race;

/**
 * @author bubi
 *
 */
public class EventTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link de.rennspur.model.Event#addRace(de.rennspur.model.Race)}.
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
		Race race = new Race();
		proband.addRace(race);
		proband.removeRace(race);
		assertEquals("A Race must have been removed from the list of races of this event", 0, proband.getRaces().size());
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

}
