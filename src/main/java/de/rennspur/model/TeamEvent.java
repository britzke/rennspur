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
 * The persistent class for the TEAM_EVENTS database table.
 * 
 */
@Entity
@Table(name="TEAM_EVENTS")
@NamedQuery(name="TeamEvent.findAll", query="SELECT t FROM TeamEvent t")
public class TeamEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TeamEventPK id;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="EVENTS_ID")
	private Event event;

	public TeamEvent() {
	}

	public TeamEventPK getId() {
		return this.id;
	}

	public void setId(TeamEventPK id) {
		this.id = id;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}