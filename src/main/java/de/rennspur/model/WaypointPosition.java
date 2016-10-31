package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the WAYPOINT_POSITIONS database table.
 * 
 */
@Entity
@Table(name="WAYPOINT_POSITIONS")
@NamedQuery(name="WaypointPosition.findAll", query="SELECT w FROM WaypointPosition w")
public class WaypointPosition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double latitude;

	private double longitude;

	private Timestamp time;

	//bi-directional many-to-one association to Waypoint
	@ManyToOne
	@JoinColumn(name="WAYPOINTS_ID")
	private Waypoint waypoint;

	public WaypointPosition() {
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

	public Waypoint getWaypoint() {
		return this.waypoint;
	}

	public void setWaypoint(Waypoint waypoint) {
		this.waypoint = waypoint;
	}

}