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
package de.rennspur.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.rennspur.model.Country;

/**
 * A CountryBean is an application scoped bean, to provide a list of
 * {@link Country} objects with all countries listed in ISO-3166.
 *
 * @author burghard.britzke, bubi@charmides.in-berlin.de
 */
@ApplicationScoped
@Named
public class CountryBean {

	@Inject
	private EntityManager entityManager;

	private List<Country> countries;

	/**
	 * Initialises the countries list from the database with all countries.
	 */
	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		Query q = entityManager.createNamedQuery("Country.findAll");
		countries = q.getResultList();
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @return the countries
	 */
	public List<Country> getCountries() {
		return countries;
	}

	/**
	 * @param countries
	 *            the countries to set
	 */
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}