/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016 Ruben Maurer
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
 *  along with Rennspur.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.rennspur.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A GPSPositionsTransfer object represents a trace, which is to be transferred from the
 * GPS sensor to the application server.
 * 
 * @author Ruben Maurer
 */
public class GPSPositionsTransfer {
	String hash;
	List<Position> positions;

	/**
	 * Creates a new GPSPositionsTransfer. Initializes the List of Positions.
	 */
	public GPSPositionsTransfer() {
		positions = new ArrayList<>();
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
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions
	 *            the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
}
