package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TEAM_MEMBERS database table.
 * 
 */
@Entity
@Table(name="TEAM_MEMBERS")
@NamedQuery(name="TeamMember.findAll", query="SELECT t FROM TeamMember t")
public class TeamMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	private String surname;

	//bi-directional many-to-one association to Club
	@ManyToOne
	@JoinColumn(name="CLUBS_ID")
	private Club club;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="TEAMS_ID")
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

}