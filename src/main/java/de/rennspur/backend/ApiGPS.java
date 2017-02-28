/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  maurer.ruben, maurerruben97@gmail.com
 *  					burghard.britzke bubi@charmides.in-berlin.de
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

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.rennspur.model.Club;
import de.rennspur.model.GPSPositionsTransfer;
import de.rennspur.model.Position;
import de.rennspur.model.Race;
import de.rennspur.model.Team;
import de.rennspur.model.TeamPosition;
import de.rennspur.model.Waypoint;
import de.rennspur.model.WaypointPosition;

/**
 * ApiGPS objects are the service endpoints to handle the incomming GPS-Data
 * from the GPS-Clients.
 * 
 * @author ruben.maurer, burghard.britzke,leon.schlender
 */
@Path("/gps-service")
public class ApiGPS {
	@Inject
	private EntityManagerFactory emf;

	/**
	 * Demonstrates an objects short way via an injected bean to the database
	 * and with data back via servlet to the client as xml.
	 * 
	 * @return A list of clubs.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Club> getClubs() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createNamedQuery("Club.findAll");
		@SuppressWarnings("unchecked")
		List<Club> clubs = query.getResultList();
		return clubs;
	}

	/**
	 * Handles the Post from the GPS-component.
	 * 
	 * @param positions
	 *            The last positions of a team during a race.
	 * 
	 * @return ok String with the message of success.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postTeamPositions(GPSPositionsTransfer positionsTransfer) {
		try {
			EntityManager em = emf.createEntityManager();
			Query q = em.createNamedQuery("Team.getTeamByHash");
			q.setParameter("hash", positionsTransfer.getHash());
			Team team = (Team) q.getSingleResult();

			if (team != null) { // Team with hash exists
				// TODO retrieve actual race information
				q = em.createNamedQuery("Race.findRaceByID");
				q.setParameter("id", 1);
				Race race = (Race) q.getSingleResult();
				EntityTransaction et = em.getTransaction();
				et.begin();
				for (Position position : positionsTransfer.getPositions()) {
					TeamPosition newTeamPosition = new TeamPosition();
					newTeamPosition.setLatitude(position.getLatitude());
					newTeamPosition.setLongitude(position.getLongitude());
					newTeamPosition.setTime(position.getTime());
					newTeamPosition.setRace(race);
					newTeamPosition.setTeam(team);
					newTeamPosition = em.merge(newTeamPosition);
				}
				et.commit();
				em.close();

				return "ok";
			} else {
				throw new ErrorHandling("");
			}
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new ErrorHandling("");
			// return e.getMessage();
		}
	}

	/**
	 * Endpoint to post a position for a waypoint.
	 * 
	 * @param waypoint
	 *            The waypoint with minimum one position.
	 */
	@Path("/waypoint")
	@POST
	@RolesAllowed("wegpunktsetzer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void postWaypoint(Waypoint recievedWaypoint) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("Waypoint.getWaypointById");
		q.setParameter("id", recievedWaypoint.getId());
		Waypoint waypoint = (Waypoint) q.getSingleResult();

		q = em.createNamedQuery("Race.findRaceByID");
		q.setParameter("id", 1);
		Race race = (Race) q.getSingleResult();

		EntityTransaction et = em.getTransaction();
		et.begin();
		for (WaypointPosition p : recievedWaypoint.getWaypointPositions()
				.toArray(new WaypointPosition[recievedWaypoint.getWaypointPositions().size()])) {
			if (p.getTime() != null) {
				p.setRace(race);
				p.setWaypoint(waypoint);
				waypoint.getWaypointPositions().add(p);
			}
		}
		waypoint.setRace(race);
		et.commit();
		em.close();
	}
}