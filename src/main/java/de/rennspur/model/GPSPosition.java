package de.rennspur.model;

import java.util.ArrayList;
import java.util.List;

public class GPSPosition {
	String hash;
	List<Position> positions;

	public GPSPosition(){
		positions = new ArrayList<>();
		hash = "23794zui23rdfi32";
	}
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
}
