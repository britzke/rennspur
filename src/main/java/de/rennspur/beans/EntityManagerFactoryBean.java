/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  burghard.britzke, bubi@charmides.in-berlin.de
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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The EntityManagerFactoryBean provides an application scoped
 * EntityManagerFactory. Because the EntityManagerFactory itself is not
 * Serializable, it can only injected in <b>transient</b> properties, if the
 * bean into which it is injected is {@code @SessionScoped}.
 * 
 * @author burghard.britzke
 */
@ApplicationScoped
public class EntityManagerFactoryBean {

	@Produces
	private EntityManagerFactory entityManagerFactory;

	/**
	 * Creates a new EntityManagerFactoryBean.
	 */
	public EntityManagerFactoryBean() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("rennspur-db");
	}

	/**
	 * Gets the entityManagerFactory.
	 * 
	 * @return the entityManager
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		System.out
				.println("EntityManagerFactoryBean::getEntityManagerFactory()");
		return entityManagerFactory;
	}

	/**
	 * Get a fresh EntityManager.
	 * 
	 * @return A newly created entityManager
	 */
	@Produces
	public EntityManager getEntityManager() {
		System.out.println("EntityManagerFactoryBean::getEntityManager()");
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * Closes the entityManager.
	 * 
	 * @param entityManager
	 */
	public void closeEntityManager(@Disposes EntityManager entityManager) {
		System.out.println(
				"EntityManagerFactoryBean::closeEntityManager(entityManger="
						+ entityManager + ")");
		entityManager.close();
	}
}
