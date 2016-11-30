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

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EVENTS database table.
 * 
 */
@Entity
@Table(name="EVENTS")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	private Boolean handicap;
	
	@Column(name = "START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	//bi-directional many-to-one association to Club
	@ManyToOne
	@JoinColumn(name="CLUBS_ID")
	private Club club;

	//bi-directional many-to-one association to Race
	@OneToMany(mappedBy="event")
	private List<Race> races;

	//bi-directional many-to-one association to Waypoint
	@OneToMany(mappedBy="event")
	private List<Waypoint> waypoints;

	public Event() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the handicap
	 */
	public Boolean getHandicap() {
		return handicap;
	}

	/**
	 * @param handicap the handicap to set
	 */
	public void setHandicap(Boolean handicap) {
		this.handicap = handicap;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public List<Race> getRaces() {
		return this.races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	public Race addRace(Race race) {
		getRaces().add(race);
		race.setEvent(this);

		return race;
	}

	public Race removeRace(Race race) {
		getRaces().remove(race);
		race.setEvent(null);

		return race;
	}

	public List<Waypoint> getWaypoints() {
		return this.waypoints;
	}

	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public Waypoint addWaypoint(Waypoint waypoint) {
		getWaypoints().add(waypoint);
		waypoint.setEvent(this);

		return waypoint;
	}

	public Waypoint removeWaypoint(Waypoint waypoint) {
		getWaypoints().remove(waypoint);
		waypoint.setEvent(null);

		return waypoint;
	}

}