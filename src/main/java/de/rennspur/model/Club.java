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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import java.util.List;


/**
 * <p>Java-Klasse für XML anonymous complex type.
 * The persistent class for the CLUBS database table.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kürzel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dsvNummer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@Entity
@Table(name="CLUBS")
@NamedQuery(name="Club.findAll", query="SELECT c FROM Club c")
public class Club implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@XmlElement(required = true)
	@Column(name="DSV_NUMMER")
	private String dsvNummer;

	@XmlElement(required = true)
	private String kürzel;

	@XmlElement(required = true)
	private String name;

	@XmlElement(required = true)
	private String url;
	
	@XmlTransient
	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="club")
	private List<Event> events;

	@XmlTransient
	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="club")
	private List<Team> teams;

	@XmlTransient
	//bi-directional many-to-one association to TeamMember
	@OneToMany(mappedBy="club")
	private List<TeamMember> teamMembers;

	public Club() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDsvNummer() {
		return this.dsvNummer;
	}

	public void setDsvNummer(String dsvNummer) {
		this.dsvNummer = dsvNummer;
	}

	public String getKürzel() {
		return this.kürzel;
	}

	public void setKürzel(String kürzel) {
		this.kürzel = kürzel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setClub(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setClub(null);

		return event;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		getTeams().add(team);
		team.setClub(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setClub(null);

		return team;
	}

	public List<TeamMember> getTeamMembers() {
		return this.teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public TeamMember addTeamMember(TeamMember teamMember) {
		getTeamMembers().add(teamMember);
		teamMember.setClub(this);

		return teamMember;
	}

	public TeamMember removeTeamMember(TeamMember teamMember) {
		getTeamMembers().remove(teamMember);
		teamMember.setClub(null);

		return teamMember;
	}

}