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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the EVENTS database table.
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name = "EVENTS")
@NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 255)
	@NotNull
	private String name;

	@Column(nullable = false, length = 1)
	@NotNull
	private Boolean handicap;

	@Column(name = "START_DATE", nullable = false)
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	private double latitude;

	private double longitude;

	private double resolution;

	private double rotation;

	// bi-directional many-to-one association to Club
	@NotNull
	@ManyToOne
	@JoinColumn(name = "CLUBS_ID", nullable = false)
	private Club club;

	// bi-directional many-to-many association to Team
	@ManyToMany(mappedBy = "events", cascade = { CascadeType.ALL })
	private List<Team> teams;

	@XmlTransient
	// bi-directional many-to-one association to Race
	@OneToMany(mappedBy = "event")
	private List<Race> races;

	@XmlTransient
	// bi-directional many-to-one association to Waypoint
	@OneToMany(mappedBy = "event")
	private List<Waypoint> waypoints;

	public Event() {
		teams = new ArrayList<Team>();
		races = new ArrayList<Race>();
		waypoints = new ArrayList<Waypoint>();
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
	 * @param handicap
	 *            the handicap to set
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
	 * @param startDate
	 *            the startDate to set
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
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the resolution
	 */
	public double getResolution() {
		return resolution;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(double resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the rotation
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
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

	/**
	 * Converts the event to a human readable string.
	 *
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<Event (id=" + getId() + ", name=" + name + ", handicap="
				+ handicap + ", club=" + club != null ?
					"null"	: club.getAbbreviation() + ", races="
								+ (races != null ? races.size() : 0)
								+ ", waypoints="
								+ (waypoints != null ? waypoints.size() : 0)
								+ ", teams="
								+ (teams != null ? teams.size() : 0) + ")>";
	}
}