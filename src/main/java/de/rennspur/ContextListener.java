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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.rennspur;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class
 * ApplicaitonStartupListener
 *
 * @author burghard.britzke
 */
@WebListener
public class ContextListener implements ServletContextListener {

	@Inject
	private EntityManagerFactory emf;

	/**
	 * Do nothing.
	 * 
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
	}

	/**
	 * Connect to the Database. If database is not populated with tables
	 * populates them.
	 * 
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// create an entity manager to ensure that the database and the correct
		// tables exists
		EntityManager em = emf.createEntityManager();
		em.close();
	}
}
