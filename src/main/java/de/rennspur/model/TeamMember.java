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
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the TEAM_MEMBERS database table.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@Entity
@Table(name = "TEAM_MEMBERS")
@NamedQuery(name = "TeamMember.findAll", query = "SELECT t FROM TeamMember t")
public class TeamMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false)
	private String name;

	private String surname;

	// bi-directional many-to-one association to Club
	@ManyToOne
	@JoinColumn(name = "CLUBS_ID")
	private Club club;

	// bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name = "TEAMS_ID")
	private Team team;

	public TeamMember() {
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

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * Converts the TeamMember to a human readable string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<TeamMember (id=" + getId() + ", name=" + name + ", surname="
				+ surname + ", club=" + club.getAbreviation() + ", team="
				+ team.getName() + ")>";
	}

}