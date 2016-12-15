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

import java.util.List;

/**
 * The persistent class for the TEAMS database table.
 * 
 * burghard.britzke bubi@charmides.in-berlin.de
 */
@Entity
@Table(name = "TEAMS")
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(name = "HANDYCAP_FAKTOR", nullable = false)
	private int handycapFaktor;

	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false)
	private String country;

	// bi-directional many-to-one association to Club
	@ManyToOne
	@JoinColumn(name = "CLUBS_ID")
	private Club club;

	@NotNull
	@Column(nullable = false)
	private String email;

	@NotNull
	@Column(nullable = false)
	private String hash;

	// bi-directional many-to-one association to TeamMember
	@OneToMany(mappedBy = "team")
	private List<TeamMember> members;

	// bi-directional many-to-one association to TeamPosition
	@OneToMany(mappedBy = "team")
	private List<TeamPosition> positions;

	public Team() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHandycapFaktor() {
		return this.handycapFaktor;
	}

	public void setHandycapFaktor(int handycapFaktor) {
		this.handycapFaktor = handycapFaktor;
	}

	/**
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String land) {
		this.country = land;
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash
	 *            the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return The members.
	 */
	public List<TeamMember> getMembers() {
		return this.members;
	}

	/**
	 * @param teamMembers
	 *            The members to set.
	 */
	public void setMembers(List<TeamMember> teamMembers) {
		this.members = teamMembers;
	}

	/**
	 * Adds a member to the team.
	 * 
	 * @param member
	 *            The member to add.
	 * @return The added member.
	 */
	public TeamMember addMember(TeamMember member) {
		getMembers().add(member);
		member.setTeam(this);

		return member;
	}

	/**
	 * Removes a member from the team.
	 * 
	 * @param member
	 *            The member to remove.
	 * @return The removed member.
	 */
	public TeamMember removeMember(TeamMember member) {
		getMembers().remove(member);
		member.setTeam(null);

		return member;
	}

	/**
	 * @return
	 */
	public List<TeamPosition> getPositions() {
		return this.positions;
	}

	/**
	 * @param teamPositions
	 */
	public void setPositions(List<TeamPosition> teamPositions) {
		this.positions = teamPositions;
	}

	/**
	 * Adds a position to the team.
	 * 
	 * @param position
	 *            The position to add.
	 * @return The position added.
	 */
	public TeamPosition addPosition(TeamPosition position) {
		getPositions().add(position);
		position.setTeam(this);

		return position;
	}

	/**
	 * Removes the specified position from the team.
	 * 
	 * @param position
	 *            The position to remove
	 * @return The removed position
	 */
	public TeamPosition removePosition(TeamPosition position) {
		getPositions().remove(position);
		position.setTeam(null);

		return position;
	}

	/**
	 * Converts the Team to a human readable string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "<Team (id=" + getId() + ", name=" + name + ", club="
				+ club.getName() + ", email=" + email + ", members="
				+ (members != null ? members.size() : 0) + "positions="
				+ (positions != null ? positions.size() : 0) + ")>";
	}

}