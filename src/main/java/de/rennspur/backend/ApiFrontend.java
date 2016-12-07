/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  Leo Winter
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
package de.rennspur.backend;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.rennspur.model.Club;
import de.rennspur.model.Event;
import de.rennspur.model.Race;
import de.rennspur.model.Team;
import de.rennspur.model.TeamMember;
import de.rennspur.model.TeamPosition;

/**
 * The ApiFrontend provides the Api endpoint for the webfrontend.
 * 
 * @author Leo Winter
 * @param <FrontendData>
 */
@Path("/APId")

public class ApiFrontend<FrontendData> {
	@Inject
	private EntityManagerFactory emf;

	@GET
	@Path("/FrontendFull")
	@Produces(MediaType.APPLICATION_JSON)

	public FrontendData getFrontendDataInJSON() {
		// TODO - Output of every needed value.
		Club club = new Club();
		Event event = new Event();
		Race race = new Race();
		Team team = new Team();
		TeamMember teammember = new TeamMember();
		TeamPosition teamposition = new TeamPosition();

		team.getName();
		club.getAbreviation();

		// TODO - return the result to the client
		return null;
	}
	
	@POST	
	@Path("/FrontendUpdate")
	@Produces(MediaType.APPLICATION_XML)
	
	/**
	 * Returns a specific amount of the latest Positions of a team
	 * 
	 * @param teamid
	 *            ID of the wanted team
	 * @param positionsCount
	 * @return
	 */

	public List<TeamPosition> getLatestTeamPositions(@FormParam("id") int teamid) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createNamedQuery("TeamPosition.findLatestPositions");
		query.setParameter("id", teamid);
		//query.setParameter("limit", limit);
		@SuppressWarnings("unchecked")
		List<TeamPosition> positions = query.getResultList();
		return positions;

	}
	
}