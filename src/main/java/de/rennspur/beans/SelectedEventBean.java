/*
 *  This file is part of Renspur.
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
package de.rennspur.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import de.rennspur.annotations.SelectedEvent;
import de.rennspur.model.Event;

/**
 * A session bean to provide the selected {@link Event}.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
@SessionScoped
@Named
public class SelectedEventBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Produces @SelectedEvent
	private Event event;

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
}
