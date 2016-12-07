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

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Position is a class to be extended by the entity classes
 * TeamMember and TeamPostion.
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@MappedSuperclass
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double latitude;

	private double longitude;

	private Timestamp time;

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
}
