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

import de.rennspur.beans.CountryBean;
import de.rennspur.converters.ClubConverter;
import de.rennspur.converters.CountryConverter;
import de.rennspur.model.Country;

/**
 * Tests the {@link ClubConverter}.
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryConverterTest {

	@Mock
	private CountryBean countryBean;
	@Mock
	private FacesContext facesContext;
	@Mock
	private UIComponent component;

	@InjectMocks
	public CountryConverter proband;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<Country> countries = new ArrayList<Country>();
		Country country = new Country();
		country.setId(1);
		country.setName("test");
		countries.add(country);
		when(countryBean.getCountries()).thenReturn(countries);
	}

	/**
	 * Test method for
	 * {@link de.rennspur.converters.ClubConverter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)}.
	 */
	@Test(expected = ConverterException.class)
	public void testGetAsObject() {
		Country resultingCountry = (Country) proband.getAsObject(facesContext, component,
				"");
		assertNull(
				"Must return null, when text representation of object is \"\" (empty string).",
				resultingCountry);
		resultingCountry = (Country) proband.getAsObject(facesContext, component,
				"1");
		assertEquals(
				"Resulting Club must have the name, which the ClubBean returned club has.",
				"test", resultingCountry.getName());
		resultingCountry = (Country) proband.getAsObject(facesContext, component,
				"2");
		assertNull(
				"Must return null, when text representation of object has non existing id.",
				resultingCountry);

		proband.getAsObject(facesContext, component, "abc"); // should throw
																// ConverterException
	}
}