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

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The EntityManagerFactoryBean provides an application scoped
 * EntityManagerFactory. Because the EntityManagerFactory itself is not
 * Serializable, it can only injected in <b>transient</b> properties, if the
 * bean into which is incected is {@code @SessionScoped}.
 * 
 * @see Serializable
 * @author burghard.britzke
 */
@ApplicationScoped
public class EntityManagerFactoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

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
		return entityManagerFactory;
	}
	
//	TODO Produces unexpected IllegalStateException "Attempting to execute an operation on a closed EntityManagerFactory." must be further investigated"
//	/**
//	 * Closes the entityManagerFactory.
//	 * 
//	 * @param entityManagerFactory
//	 */
//	public void closeEntityManagerFactory(@Disposes EntityManagerFactory entityManagerFactory) {
//		entityManagerFactory.close();
//    }
}
