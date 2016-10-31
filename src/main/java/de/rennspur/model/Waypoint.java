package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the WAYPOINTS database table.
 * 
 */
@Entity
@Table(name="WAYPOINTS")
@NamedQuery(name="Waypoint.findAll", query="SELECT w FROM Waypoint w")
public class Waypoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String kennung;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="EVENTS_ID")
	private Event event;

	//bi-directional many-to-one association to Race
	@ManyToOne
	@JoinColumn(name="RACES_ID")
	private Race race;

	//bi-directional many-to-one association to WaypointPosition
	@OneToMany(mappedBy="waypoint")
	private List<WaypointPosition> waypointPositions;

	public Waypoint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKennung() {
		return this.kennung;
	}

	public void setKennung(String kennung) {
		this.kennung = kennung;
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

	public WaypointPosition addWaypointPosition(WaypointPosition waypointPosition) {
		getWaypointPositions().add(waypointPosition);
		waypointPosition.setWaypoint(this);

		return waypointPosition;
	}

	public WaypointPosition removeWaypointPosition(WaypointPosition waypointPosition) {
		getWaypointPositions().remove(waypointPosition);
		waypointPosition.setWaypoint(null);

		return waypointPosition;
	}

}