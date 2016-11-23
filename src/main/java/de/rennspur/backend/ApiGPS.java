/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  maurer.ruben, maurerruben97@gmail.com
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

@Path("/backend")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ApiGPS {

	/**
	 * Handles the Post from the GPS-component.
	 * 
	 * @param jsonString
	 *            The received JSON-obeject as a String.
	 * 
	 * @return ok String with the message of success.
	 */

	@POST
	@Consumes({ MediaType.TEXT_PLAIN })
	// @Produces({ MediaType.TEXT_PLAIN })
	@Path("/backend")
	public String getGPSDataInJSON(String jsonString) {

		/**
		 * 	Convert JSON-String to Java JSONObject.
		 */
		JSONObject jsonObj = new JSONObject(jsonString);

		/**
		 * Extract data from the Java JSONObject.
		 */
		String key = jsonObj.get("key");
		ArrayList<TeamPosition> newPos = jsonObj.get("status");

		return "ok";
	}
}