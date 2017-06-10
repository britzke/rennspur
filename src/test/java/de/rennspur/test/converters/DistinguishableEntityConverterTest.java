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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import de.rennspur.converters.DistinguishableEntityConverter;
import de.rennspur.model.Club;

/**
 * Tests the {@link DistinguishableEntityConverter}
 *
 * @author burghard.britzke, bubi@charmides.in-berlin.de
 */
@RunWith(MockitoJUnitRunner.class)
public class DistinguishableEntityConverterTest {

	@InjectMocks
	private DistinguishableEntityConverter proband = new DistinguishableEntityConverter() {

		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1,
				String arg2) throws ConverterException {
			return null;
		}
	};

	/**
	 * Test method for
	 * {@link de.rennspur.converters.DistinguishableEntityConverter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public void testGetAsString() {
		String result = proband.getAsString(null, null, null);
		assertEquals("must return \"\" for a null object.", "", result);
		Club value = new Club();
		value.setId(5);
		result = proband.getAsString(null, null, value);
		assertEquals("must return the ID as String", "5", result);
	}
}