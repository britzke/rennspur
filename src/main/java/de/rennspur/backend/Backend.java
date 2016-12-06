/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  leon.schlender
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

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import de.rennspur.model.Team;
import de.rennspur.model.TeamMember;
import de.rennspur.model.TeamPosition;

/**
 * Recieves, sends and processes data from the Database
 * 
 * @author e4_schlender
 */
@RequestScoped
public class Backend {

	/**
	 * A list for all Teams.
	 */
	private HashSet<Team> Teams = new HashSet<Team>();
	@Inject
	private EntityManagerFactory factory;

	public Backend() {

		super();
	}
	@PostConstruct
	public void init() {

	}

	/**
	 * Returns all Members of a team.
	 * 
	 * @param team
	 *            ID of the team
	 */
	public List<TeamMember> getMembers(int team) {
		// factory = Persistence.createEntityManagerFactory("MEMBERS");
		EntityManager em = factory.createEntityManager();

		Query query = em.createNativeQuery("select * from teams where id=:id");
		query.setParameter("id", team);
		@SuppressWarnings("unchecked")
		List<TeamMember> members = query.getResultList();

		return members;
	}

	/**
	 * Returns a specific amount of the latest Positions of a team
	 * 
	 * @param teamid
	 *            ID of the wanted team
	 * @param positionsCount
	 * @return
	 */

	public List<TeamPosition> getLatestMemberPositions(int teamid, Integer positionsCount) {
		/*
		 * factory = Persistence.createEntityManagerFactory("POSITIONS");
		 * EntityManager em = factory.createEntityManager();
		 */
		List<TeamPosition> getTeilnehmerPositionen = null;

		return getTeilnehmerPositionen;

	}

	/**
	 * Saves a new Position into the Database
	 * 
	 * @param pos
	 *            Position
	 * @param key
	 *            Token of the Team
	 * @param date
	 *            Date of the Position
	 */

	private void saveGPSPosition(TeamPosition pos, String key, Date date) {

	}

	/**
	 * Waits for new POST Requests from the GPS
	 */
	public void getPost() {

	}

	/**
	 * Description of the method setRaceNumber.
	 * 
	 * @param raceNumber
	 *            the chosen race number
	 */
	public void setRaceNumber(int raceNumber) {

	}

	/**
	 * Returns Teams.
	 * 
	 * @return Teams
	 */
	public HashSet<Team> getTeams() {
		return this.Teams;
	}

}
