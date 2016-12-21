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
package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * The persistent class for the RACES database table.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@Entity
@Table(name = "RACES")
@NamedQuery(name = "Race.findAll", query = "SELECT r FROM Race r")
public class Race implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false)
	private int number;

	// bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name = "EVENTS_ID")
	private Event event;

	// bi-directional many-to-one association to TeamPosition
	@OneToMany(mappedBy = "race")
	private List<TeamPosition> teamPositions;

	// bi-directional many-to-one association to Waypoint
	@OneToMany(mappedBy = "race")
	private List<Waypoint> waypoints;

	public Race() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<TeamPosition> getTeamPositions() {
		return this.teamPositions;
	}

	public void setTeamPositions(List<TeamPosition> teamPositions) {
		this.teamPositions = teamPositions;
	}

	public TeamPosition addTeamPosition(TeamPosition teamPosition) {
		getTeamPositions().add(teamPosition);
		teamPosition.setRace(this);

		return teamPosition;
	}

	public TeamPosition removeTeamPosition(TeamPosition teamPosition) {
		getTeamPositions().remove(teamPosition);
		teamPosition.setRace(null);

		return teamPosition;
	}

	public List<Waypoint> getWaypoints() {
		return this.waypoints;
	}

	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public Waypoint addWaypoint(Waypoint waypoint) {
		getWaypoints().add(waypoint);
		waypoint.setRace(this);

		return waypoint;
	}

	public Waypoint removeWaypoint(Waypoint waypoint) {
		getWaypoints().remove(waypoint);
		waypoint.setRace(null);

		return waypoint;
	}

	/**
	 * Converts the Race to a human readable string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<Race (id=" + getId() + ", number=" + number + ", event="
				+ event.getName() + ", teamPositions="
				+ (teamPositions != null ? teamPositions.size() : 0)
				+ ", waypoints=" + (waypoints != null ? waypoints.size() : 0)
				+ ")>";
	}
}