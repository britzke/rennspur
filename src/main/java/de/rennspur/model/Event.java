package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
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

	private String yardstick;

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

	public String getYardstick() {
		return this.yardstick;
	}

	public void setYardstick(String yardstick) {
		this.yardstick = yardstick;
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