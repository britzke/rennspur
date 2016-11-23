package de.rennspur.backend;

import javax.ws.rs.GET;
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

@Path("/API")
public class ApiFrontend<FrontendData> {
	
	@GET
	@Path("/FrontendFull")
	@Produces(MediaType.APPLICATION_JSON)
	
	public FrontendData getFrontendDataInJSON() {
		Club club = new Club();
		Event event = new Event();
		Race race = new Race();
		Team team = new Team();
		TeamMember teammember = new TeamMember();
		TeamPosition teamposition = new TeamPosition();
		
		
		team.getKennung();
		club.getKürzel();
		/** 
		 * ToDo - Output of every needed value.
		 */
		return null;
		/**
		 * ToDo - return 
		 */
	}
}