/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  leon.schlender
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
package de.rennspur.backend;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * WebApplicationException to simply set the Reponse.status to HTTP OK (200).
 * 
 * @author leon.schlender
 */
public class ErrorHandling extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Sets the Reponse.status to HTTP OK (200).
	 * @param message Additional message.
	 */
	public ErrorHandling(String message) {
		super(Response.status(Response.Status.OK).entity(message)
				.type(MediaType.TEXT_PLAIN).build());
	}
}
