/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  Leo Winter, leon.schlender, burghard.britzke
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
package de.rennspur.backend;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.rennspur.model.Clubs;
import de.rennspur.model.Race;
import de.rennspur.model.TeamPosition;


/**
 * This is the service endpoint, which provides the Api for the web frontend.
 * 
 * @author leo.winter, leon.schlender, burghard.britzke
 * @param <FrontendData>
 */
@Path("/frontend")
public class ApiFrontend {
	@Inject
	private EntityManagerFactory emf;

	/**
	 * Service to get the full data for one race.
	 * 
	 * @return A Race
	 */
	@GET
	@Path("/race")
	@Produces(MediaType.APPLICATION_JSON)
	public Race getRace() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createNamedQuery("Race.findRaceByID");
		query.setParameter("id", 1);
		Race race = (Race) query.getSingleResult();

		em.close();
		return race;
	}

	/**
	 * Returns the latest 50 Positions of a team.
	 * 
	 * @param teamid
	 *            ID of the wanted team
	 * @param positionsCount
	 * @return The list for TeamPositions for the given team id.
	 */
	@GET
	@Path("/update/{id}")
	@Produces("application/json")
	public List<TeamPosition> getLatestTeamPositions(
			@PathParam("id") int teamid) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createNamedQuery("TeamPosition.findLatestPositions");
		query.setParameter("id", teamid).setMaxResults(50);
		@SuppressWarnings("unchecked")
		List<TeamPosition> positions = query.getResultList();
		
		em.close();
		return positions;
	}
	
	/**
	 * Returns a list of all clubs.
	 * @return The list of clubs.
	 */
	@GET
	@Path("/clubs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Clubs> getClubs() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Club.findAll");
		query.setParameter("id", 1);
		@SuppressWarnings("unchecked")
		List<Clubs> clubs = query.getResultList();
		return clubs;
	}
}