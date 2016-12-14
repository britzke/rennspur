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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.sql.Timestamp;

/**
 * The persistent class for the TEAM_POSITIONS database table.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeamPosition", propOrder = {

})

@Entity
@Table(name = "TEAM_POSITIONS")
@NamedQueries({ @NamedQuery(name = "TeamPosition.findAll", query = "SELECT t FROM TeamPosition t"),
		@NamedQuery(name = "TeamPosition.findLatestPositions", query = "SELECT tp FROM TeamPosition tp inner join tp.team t WHERE t.id=:id"), })

public class TeamPosition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@XmlElement(required = true)
	private double latitude;

	@XmlElement(required = true)
	private double longitude;

	@XmlElement(required = true)
	private Timestamp time;

	// bi-directional many-to-one association to Race
	@XmlElement(required = true)
	@ManyToOne
	@JoinColumn(name = "RACES_ID")
	private Race race;

	// bi-directional many-to-one association to Team
	@XmlElement(required = true)
	@ManyToOne
	@JoinColumn(name = "TEAMS_ID")
	private Team team;

	public TeamPosition() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Race getRace() {
		return this.race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public String toString() {
		return "<TeamPosition(id="+id+", latitude="+latitude+", longitude="+longitude+", race="+race+", team="+team+")>";
	}

}