package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TEAM_POSITIONS database table.
 * 
 */
@Entity
@Table(name="TEAM_POSITIONS")
@NamedQuery(name="TeamPosition.findAll", query="SELECT t FROM TeamPosition t")
public class TeamPosition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double latitude;

	private double longitude;

	private Timestamp time;

	//bi-directional many-to-one association to Race
	@ManyToOne
	@JoinColumn(name="RACES_ID")
	private Race race;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="TEAMS_ID")
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

}