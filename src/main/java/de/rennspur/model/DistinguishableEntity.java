/*
 *  This file is part of Rennspur.
 *
 *  Copyright (C) 2017  burghard.britzke, bubi@charmides.in-berlin.de
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

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Objects of a Class that inherits from DistinguishableEntity can be safely
 * distinguished from each other. This is especially needed for entities that
 * are to be selectable from a Primefaces <p:selectOneMenu/> or similar.
 *
 * The implementation of this class is inspired from
 * http://stackoverflow.com/questions/17343032/implement-converters-for-entities-with-java-generics#17343582
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@MappedSuperclass
public abstract class DistinguishableEntity implements Serializable {

	private static final long serialVersionUID = 1705161027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Get the unique identifier within objects of the extending class.
	 *
	 * @return The unique identifier within the extending class.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set the identifier for the object which has to be unique within the
	 * extending class.
	 *
	 * @param id The unique identifier to be set.
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Tests if another object equals this object by comparing the ids of both
	 * objects if both objects are assignable to each other (see
	 * {@link Class#isAssignableFrom(Class)}). If the other object is null or
	 * the id of this object equals 0, the both objects are presented to be not
	 * equal.
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		return (other != null && getId() != 0
				&& other.getClass().isAssignableFrom(getClass())
				&& getClass().isAssignableFrom(other.getClass()))
						? getId() == ((DistinguishableEntity) other).getId()
						: (other == this);
	}

	/**
	 * Provides a hash code that depends from the classes hashCode and the id of
	 * the object.
	 *
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (getId() != 0)
				? (getClass().getSimpleName().hashCode() + getId())
				: super.hashCode();
	}

	/**
	 * Provides a human readable form of the object.
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
}