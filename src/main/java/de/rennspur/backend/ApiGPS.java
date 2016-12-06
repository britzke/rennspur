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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import de.rennspur.model.Club;
import de.rennspur.model.TeamPosition;

/**
 * ApiGPS objects are the service endpoints to handle the incomming GPS-Data
 * from the GPS-Clients.
 * 
 * @author ruben.maurer, burghard.britzke,leon.schlender
 */
@Path("/gps-service")
public class ApiGPS {
	@Inject
	private EntityManagerFactory emf;

	/**
	 * Demonstrates an objects short way via an injected bean to the database
	 * and with data back via servlet to the client as xml.
	 * 
	 * @return A list of clubs.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Club> getClubs() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createNamedQuery("Club.findAll");
		@SuppressWarnings("unchecked")
		List<Club> clubs = query.getResultList();
		System.out.println("ApiGPS::getClub()");
		return clubs;
	}

	/**
	 * Handles the Post from the GPS-component.
	 * 
	 * @param jsonString
	 *            The received JSON-object as a String.
	 * 
	 * @return ok String with the message of success.
	 */
	@POST
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces(MediaType.TEXT_PLAIN)
	public String getGPSDataInJSON(String jsonString) {

		/**
		 * Test Output.
		 */
		System.out.println(jsonString);
		System.out.println(emf);

		/**
		 * Initializing Variables.
		 */
		ArrayList<TeamPosition> positionList = new ArrayList<TeamPosition>();

		/**
		 * Converting the Input String into a JSONObject
		 */
		JSONObject json = new JSONObject(jsonString);

		/**
		 * Extracting the key and name value off the boat.
		 */
		String key = (String) json.get("key");
		String name = (String) json.get("name");

		/**
		 * Test Output.
		 */
		System.out.println(key + " : " + name);

		/**
		 * Extracting the position-array from the JSONObject.
		 */
		JSONArray lineItems = json.getJSONArray("lineItems");

		/**
		 * Itterating through the extracted arry from the JSONObject, to get
		 * every position.
		 */
		for (Object o : lineItems) {

			JSONObject jsonLineItem = (JSONObject) o;

			String longitude = jsonLineItem.getString("key");
			String latitude = jsonLineItem.getString("value");
			String time = jsonLineItem.getString("value");

			/**
			 * Creating a new TeampositionObject and filling it with the
			 * extracted Data from the JSONObject.
			 */
			TeamPosition newPosition = new TeamPosition();
			newPosition.setId(Integer.parseInt(key));
			newPosition.setLatitude(Double.parseDouble(latitude));
			newPosition.setLongitude(Double.parseDouble(longitude));
			newPosition.setTime(convertStringToTimestamp(time));

			/**
			 * Adding the new now filled TeamPosition object into the Arraylist.
			 */
			positionList.add(newPosition);

			/**
			 * Test Output.
			 */
			System.out.println(longitude + " : " + latitude + " : " + time);
		}

		return "ok";
	}

	/**
	 * Small utility-method to convert a String into the Timestamp format.
	 * 
	 * @param str_date
	 * @return Timestamp
	 */
	public static Timestamp convertStringToTimestamp(String str_date) {
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = (Date) formatter.parse(str_date);
			Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}
}