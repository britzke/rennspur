package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the RACES database table.
 * 
 */
@Entity
@Table(name="RACES")
@NamedQuery(name="Race.findAll", query="SELECT r FROM Race r")
public class Race implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int number;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="EVENTS_ID")
	private Event event;

	//bi-directional many-to-one association to TeamPosition
	@OneToMany(mappedBy="race")
	private List<TeamPosition> teamPositions;

	//bi-directional many-to-one association to Waypoint
	@OneToMany(mappedBy="race")
	private List<Waypoint> waypoints;

	public Race() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<TeamPosition> getTeamPositions() {
		return this.teamPositions;
	}

	public void setTeamPositions(List<TeamPosition> teamPositions) {
		this.teamPositions = teamPositions;
	}

	public TeamPosition addTeamPosition(TeamPosition teamPosition) {
		getTeamPositions().add(teamPosition);
		teamPosition.setRace(this);

		return teamPosition;
	}

	public TeamPosition removeTeamPosition(TeamPosition teamPosition) {
		getTeamPositions().remove(teamPosition);
		teamPosition.setRace(null);

		return teamPosition;
	}

	public List<Waypoint> getWaypoints() {
		return this.waypoints;
	}

	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public Waypoint addWaypoint(Waypoint waypoint) {
		getWaypoints().add(waypoint);
		waypoint.setRace(this);

		return waypoint;
	}

	public Waypoint removeWaypoint(Waypoint waypoint) {
		getWaypoints().remove(waypoint);
		waypoint.setRace(null);

		return waypoint;
	}

}