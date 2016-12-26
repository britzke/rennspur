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
 *  along with Rennspur.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.rennspur.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TEAM_EVENTS database table.
 * 
 */
@Embeddable
public class TeamEventPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TEAMS_ID", insertable=true, updatable=true)
	private int teamsId;

	@Column(name="EVENTS_ID", insertable=false, updatable=false)
	private int eventsId;

	public TeamEventPK() {
	}
	public int getTeamsId() {
		return this.teamsId;
	}
	public void setTeamsId(int teamsId) {
		this.teamsId = teamsId;
	}
	public int getEventsId() {
		return this.eventsId;
	}
	public void setEventsId(int eventsId) {
		this.eventsId = eventsId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TeamEventPK)) {
			return false;
		}
		TeamEventPK castOther = (TeamEventPK)other;
		return 
			(this.teamsId == castOther.teamsId)
			&& (this.eventsId == castOther.eventsId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.teamsId;
		hash = hash * prime + this.eventsId;
		
		return hash;
	}
}