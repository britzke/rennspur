/*
 *  This file is part of Rennspur.
 *
 *  Copyright (C) 2017  burghard.britzke, bubi@charmides.in-berlin.de
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

import de.rennspur.model.DistinguishableEntity;

/**
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
public class DistinguishableEntityTest {

	private class TestableDistinguishableEntity extends DistinguishableEntity {

		private static final long serialVersionUID = 1705161917L;
	}

	private TestableDistinguishableEntity proband;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		proband = new TestableDistinguishableEntity();
	}

	/**
	 * Test method for
	 * {@link de.rennspur.model.DistinguishableEntity#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		proband.setId(15);
		int hashCode = proband.hashCode();
		assertEquals("The hashCode must match the formula.",
				"TestableDistinguishableEntity".hashCode() + 15, hashCode);
	}

	/**
	 * Test method for
	 * {@link de.rennspur.model.DistinguishableEntity#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		proband.setId(10);
		TestableDistinguishableEntity equal = new TestableDistinguishableEntity();
		equal.setId(10);

		boolean result = proband.equals(equal);

		assertTrue("Must be equal if assignable and ids equal", result);

	}

	/**
	 * Test method for
	 * {@link de.rennspur.model.DistinguishableEntity#toString()}.
	 */
	@Test
	public void testToString() {

		String result = proband.toString();

		assertEquals("toString() must show the class name and the value of the id", "TestableDistinguishableEntity[id=0]", result);
	}
}