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

package de.rennspur.backend;

@Path("/backend")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ApiGPS {

	/**
	 * Receives the Post from the GPS-component.
	 * @return c String with content of JSON.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String getJSON(@PathParam("id") int id) {
		string c = "" + id;
		return c;
	}

	/**
	 * Parses the received JSON File into single arguments which are then uploaded
	 * to the Database.
	 * @param json The JSON to parse.
	 */
	public void parseJson(MediaType.APPLICATION_JSON json) {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("c:\\test.json"));

			JSONObject jsonObject = (JSONObject) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}