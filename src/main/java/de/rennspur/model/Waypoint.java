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

import org.eclipse.persistence.jpa.config.Cascade;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)

/**
 * The persistent class for the WAYPOINTS database table.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@Entity
@Table(name = "WAYPOINTS")
@NamedQueries({
@NamedQuery(name = "Waypoint.findAll", query = "SELECT w FROM Waypoint w"),
@NamedQuery(name = "Waypoint.getWaypointById", query = "SELECT w FROM Waypoint w where w.id = :id") })

public class Waypoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false)
	private String name;

	@XmlTransient
	// bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name = "EVENTS_ID")
	private Event event;

	@XmlTransient
	// bi-directional many-to-one association to Race
	@NotNull
	@ManyToOne
	@JoinColumn(name = "RACES_ID", nullable = false)
	private Race race;

	// bi-directional many-to-one association to WaypointPosition
	@OneToMany(mappedBy = "waypoint", cascade = CascadeType.PERSIST)
	private List<WaypointPosition> waypointPositions;

	public Waypoint() {
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

	public void setName(String kennung) {
		this.name = kennung;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Race getRace() {
		return this.race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public List<WaypointPosition> getWaypointPositions() {
		return this.waypointPositions;
	}

	public void setWaypointPositions(List<WaypointPosition> waypointPositions) {
		this.waypointPositions = waypointPositions;
	}

	public WaypointPosition addWaypointPosition(
			WaypointPosition waypointPosition) {
		getWaypointPositions().add(waypointPosition);
		waypointPosition.setWaypoint(this);

		return waypointPosition;
	}

	public WaypointPosition removeWaypointPosition(
			WaypointPosition waypointPosition) {
		getWaypointPositions().remove(waypointPosition);
		waypointPosition.setWaypoint(null);

		return waypointPosition;
	}

	/**
	 * Converts the Waypoint to a human readable string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<Waypoint (id=" + getId() + ", name=" + name + ", event="
				+ event.getName() + ", race=" + race.getNumber() + ")>";
	}
}