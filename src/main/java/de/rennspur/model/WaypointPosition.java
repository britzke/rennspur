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
package de.rennspur.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the WAYPOINT_POSITIONS database table.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@Entity
@Table(name = "WAYPOINT_POSITIONS")
@NamedQuery(name = "WaypointPosition.findAll", query = "SELECT w FROM WaypointPosition w")
public class WaypointPosition extends Position {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Waypoint
	@ManyToOne
	@JoinColumn(name = "WAYPOINTS_ID")
	private Waypoint waypoint;

	// bi-directional many-to-one association to Waypoint
	@ManyToOne
	@JoinColumn(name = "RACES_ID")
	private Race race;

	public WaypointPosition() {
	}

	public Waypoint getWaypoint() {
		return this.waypoint;
	}

	public void setWaypoint(Waypoint waypoint) {
		this.waypoint = waypoint;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public void setRace(Race race) {
		this.race = race;
	}

	/**
	 * Converts the WaypointPosition to a human readable string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<WaypointPosition (id=" + getId() + ", latitude=" + getLatitude() + ", longitude=" + getLongitude()
				+ ", race=" + race + ", waypoint=" + waypoint + ")>";
	}

}