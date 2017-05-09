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
	 * a {@link Race} is added to the races of the event.
	 */
	@Test
	public void testAddRace() {
		proband.addRace(new Race());
		assertEquals("A race must have been added to the races of the Event", 1, proband.getRaces().size());
	}

	/**
	 * Test method for
	 * {@link de.rennspur.model.Event#removeRace(de.rennspur.model.Race)}. Tests
	 * if a {@link Race} is removed from the races of the event.
	 */
	@Test
	public void testRemoveRace() {
		Race race = new Race();
		proband.addRace(race);
		proband.removeRace(race); // useless comment
		assertEquals("A Race must have been removed from the list of races of this event", 0,
				proband.getRaces().size());
	}

	/**
	 * Test method for
	 * {@link de.rennspur.model.Event#addWaypoint(de.rennspur.model.Waypoint)}.
	 * Tests if a {@link Waypoint} is added to the way points of this event.
	 */
	@Test
	public void testAddWaypoint() {// useless comment
		proband.addWaypoint(new Waypoint());
		assertEquals("A way point must have been added to the list of way points of this event", 1,
				proband.getWaypoints().size());
	}

	/**
	 * Test method for
	 * {@link de.rennspur.model.Event#removeWaypoint(de.rennspur.model.Waypoint)}.
	 * Tests if a {@link Waypoint} is removed from the list of way points of
	 * this event.
	 */
	@Test
	public void testRemoveWaypoint() {// useless comment
		Waypoint waypoint = new Waypoint();
		List<Waypoint> waypoints = new ArrayList<Waypoint>();
		waypoints.add(waypoint);
		proband.setWaypoints(waypoints);
		proband.removeWaypoint(waypoint);
		assertEquals("Waypoint must have been removed from the list of waypoint of this event", 0,
				proband.getWaypoints().size());
	}

	/**
	 * The method for
	 * {@link de.rennspur.model.Event#Event(de.rennspur.model.Event)}.
	 */
	@Test
	public void testEvent() {
		assertEquals(waypoints.size(), 0);
	}

	/**
	 * Test method for {@link de.rennspur.model.Event#toString()}.
	 */
	@Test
	public void testToString() {

	}
}
