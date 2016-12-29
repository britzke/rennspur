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
 *  along with Rennspur. If not, see <http://www.gnu.org/licenses/>.
 */
package de.rennspur.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the TEAM_POSITIONS database table.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name = "TEAM_POSITIONS")
@NamedQueries({
		@NamedQuery(name = "TeamPosition.findAll", query = "SELECT t FROM TeamPosition t"),
		@NamedQuery(name = "TeamPosition.findLatestPositions", query = "SELECT tp FROM TeamPosition tp inner join tp.team t WHERE t.id=:id"), })

public class TeamPosition extends Position {
	private static final long serialVersionUID = 1L;
	
	// bi-directional many-to-one association to Race
	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "RACES_ID", nullable = false)
	private Race race;

	// bi-directional many-to-one association to Team
	@NotNull
	@ManyToOne
	@JoinColumn(name = "TEAMS_ID", nullable = false)
	private Team team;

	public TeamPosition() {
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

	/**
	 * Converts the TeamPosition to a human readable string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<TeamPosition (id=" + getId() + ", latitude=" + getLatitude()
				+ ", longitude=" + getLongitude() + ", race=" + race.getNumber()
				+ ", team=" + team.getName() + ")>";
	}
}