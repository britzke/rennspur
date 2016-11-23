package de.rennspur.backend;
import java.util.HashSet;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of WaypointTransmitter.
 * 
 * @author e4_schlender
 */
public class WaypointTransmitter {
	/**
	 *  waypointTransmitters.
	 */
	public HashSet<WaypointTransmitter> waypointTransmitters = new HashSet<WaypointTransmitter>();

	/**
	 * Description of the property authKey.
	 */
	private String authKey = "";

	/**
	 * Description of the property backends.
	 */
	private Backend backends = null;

	// Start of user code (user defined attributes for WaypointTransmitter)

	// End of user code

	/**
	 * The constructor.
	 */
	public WaypointTransmitter() {
		// Start of user code constructor for WaypointTransmitter)
		super();
		// End of user code
	}

	/**
	 * Description of the method sendWaypoint.
	 */
	public void sendWaypoint() {
		// Start of user code for method sendWaypoint
		// End of user code
	}

	// Start of user code (user defined methods for WaypointTransmitter)

	// End of user code
	/**
	 * Returns waypointTransmitters.
	 * @return waypointTransmitters 
	 */
	public HashSet<WaypointTransmitter> getWaypointTransmitters() {
		return this.waypointTransmitters;
	}

	/**
	 * Returns authKey.
	 * @return authKey 
	 */
	public String getAuthKey() {
		return this.authKey;
	}

	/**
	 * Sets a value to attribute authKey. 
	 * @param newAuthKey 
	 */
	public void setAuthKey(String newAuthKey) {
		this.authKey = newAuthKey;
	}

	/**
	 * Returns backends.
	 * @return backends 
	 */
	public Backend getBackends() {
		return this.backends;
	}

	/**
	 * Sets a value to attribute backends. 
	 * @param newBackends 
	 */
	public void setBackends(Backend newBackends) {
		this.backends = newBackends;
	}

}
