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
package de.rennspur.test.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import de.rennspur.beans.ClubBean;
import de.rennspur.converters.ClubConverter;
import de.rennspur.model.Club;

/**
 * Tests the {@link ClubConverter}.
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@RunWith(MockitoJUnitRunner.class)
public class ClubConverterTest {

	@Mock
	private ClubBean clubBean;
	@Mock
	private FacesContext facesContext;
	@Mock
	private UIComponent component;

	@InjectMocks
	private ClubConverter proband;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<Club> clubs = new ArrayList<Club>();
		Club club = new Club();
		club.setId(1);
		club.setName("test");
		clubs.add(club);
		when(clubBean.getClubs()).thenReturn(clubs);
	}

	/**
	 * Test method for
	 * {@link de.rennspur.converters.ClubConverter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)}.
	 */
	@Test(expected = ConverterException.class)
	public void testGetAsObject() {
		Club resultingClub = (Club) proband.getAsObject(facesContext, component,
				"");
		assertNull(
				"Must return null, when text representation of object is \"\" (empty string).",
				resultingClub);
		resultingClub = (Club) proband.getAsObject(facesContext, component,
				"1");
		assertEquals(
				"Resulting Club must have the name, which the ClubBean returned club has.",
				"test", resultingClub.getName());
		resultingClub = (Club) proband.getAsObject(facesContext, component,
				"2");
		assertNull(
				"Must return null, when text representation of object has non existing id.",
				resultingClub);

		proband.getAsObject(facesContext, component, "abc"); // should throw
																// ConverterException
	}
}