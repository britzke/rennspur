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

package de.rennspur.test.beans;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import de.rennspur.beans.CountryBean;
import de.rennspur.model.Country;

/**
 * Test for the {@link CountryBean}.
 * 
 * @author burghard.britzke, bubi@charmides.in-berlin.de
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryBeanTest {

	@InjectMocks
	private CountryBean proband;

	@Mock
	private EntityManager em;
	@Mock
	private Query q;
	@Mock
	private List<Country> countries;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		when(em.createNamedQuery("Country.findAll")).thenReturn(q);
		when(q.getResultList()).thenReturn(countries);
	}

	/**
	 * Test method for {@link de.rennspur.beans.CountryBean#init()}.
	 */
	@Test
	public void testInit() {
		proband.init();

		assertNotNull("Field 'countries' must be initialized.", proband.getCountries());
	}
}
