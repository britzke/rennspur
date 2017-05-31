/*
 *  This file is part of Renspur.
 *
 *  Copyright (C) 2017  burghard.britzke bubi@charmides.in-berlin.de
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
package de.rennspur.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.rennspur.model.Club;

/**
 * A session scoped bean to manage the selected club (persist, remove)
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@SessionScoped
@Named
public class SelectedClubBean implements Serializable {
	private static final long serialVersionUID = 1705301752L;

	private Club club;
	private List<Club> filteredClubs;

	/**
	 * @return the club
	 */
	public Club getClub() {
		return club;
	}

	/**
	 * @param club
	 *            the club to set
	 */
	public void setClub(Club selectedClub) {
		this.club = selectedClub;
	}

	/**
	 * @return the filteredClubs
	 */
	public List<Club> getFilteredClubs() {
		return filteredClubs;
	}

	/**
	 * @param filteredClubs the filteredClubs to set
	 */
	public void setFilteredClubs(List<Club> filteredClubs) {
		this.filteredClubs = filteredClubs;
	}
}
