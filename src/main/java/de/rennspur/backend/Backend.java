/*
 *  This file is part of Renspur.
 *  
 *  Copyright (C) 2016  leon.s
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

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import de.rennspur.model.*;


/**
 * Empfängt, sendet und vearbeitet Daten Sendet an das Frontend Empfängt vom GPS
 * Veareitet in eine Datenbank
 * 
 * @author e4_schlender
 */
public class Backend {
	/**
	 * a hash.
	 */
	public HashSet<WaypointTransmitter> waypointTransmitters = new HashSet<WaypointTransmitter>();

	/**
	 * Description of the property property4.
	 */
	public String property4 = "";

	/**
	 * Description of the property manschaften.
	 */
	private String manschaften = "";

	/**
	 * Description of the property teilnehmerPositionen.
	 */
	private HashSet<TeamPosition> teilnehmerPositonen = new HashSet<TeamPosition>();

	/**
	 * Description of the property mannschaften.
	 */
	private HashSet<Team> mannschaften = new HashSet<Team>();

	/**
	 * Description of the property race.
	 */
	public Race race = null;

	/**
	 * The constructor.
	 */
	public Backend() {
		
		super();
	}

	/**
	 * Description of the method getMitglieder.
	 * 
	 * @param manschaft
	 */
	public void getMitglieder(Team manschaft) {
		
	}

	/**
	 * Description of the method addTeilnehmerPositionen.
	 * 
	 * @param teilnehmerPosition
	 */
	public void addTeilnehmerPositionen(TeamPosition teilnehmerPosition) {
	
	}

	/**
	 * Description of the method addWegepunktPositon.
	 * 
	 * @param wegepunktPosition
	 */
	public void addWegepunktPositon(Object wegepunktPosition) {
		
	}

	
	/*
	 * Liefert die Positionen der Teilnehmer zum letzten, dem Backend bekannten
	 * Zeitpunkt, der vor der angegeben Zeit liegt.
	 * 
	 * @param zeit
	 * @param anzahlPositionen
	 * @return
	 */
	
	public List<TeamPosition> getTeilnehmerPositionen(Date zeit, Integer anzahlPositionen) {
		// Start of user code for method getTeilnehmerPositionen
		List getTeilnehmerPositionen = null;
		return getTeilnehmerPositionen;
		// End of user code
	}

	/**
	 * Description of the method saveGPSPosition.
	 * 
	 * @param pos
	 * @param key
	 * @param zeit
	 */
	private void saveGPSPosition(Position pos, String key, Date zeit) {
		
	}

	/**
	 * Description of the method getMannschaft.
	 * 
	 * @param key
	 */
	private void getMannschaft(String key) {
		
	}

	/**
	 * Description of the method addDatabaseEntry.
	 */
	private void addDatabaseEntry() {
		
	}

	/**
	 * Description of the method updateDatabaseENtry.
	 */
	private void updateDatabaseENtry() {
		
	}

	/**
	 * Description of the method deleteDatabaseEntry.
	 */
	private void deleteDatabaseEntry() {
	
	}

	/**
	 * Description of the method init.
	 */
	private void init() {
		
	}

	/**
	 * wartet auf Post anfragen von GPS, je nachdem wie in JaxRS genutzt
	 */
	public void getPost() {
		
	}

	/**
	 * Description of the method setRaceNumber.
	 * 
	 * @param raceNumber
	 */
	public void setRaceNumber(int raceNumber) {
		
	}
	/**
	 * Returns waypointTransmitters.
	 * 
	 * @return waypointTransmitters
	 */
	public HashSet<WaypointTransmitter> getWaypointTransmitters() {
		return this.waypointTransmitters;
	}

	/**
	 * Returns property4.
	 * 
	 * @return property4
	 */
	public String getProperty4() {
		return this.property4;
	}

	/**
	 * Sets a value to attribute property4.
	 * 
	 * @param newProperty4
	 */
	public void setProperty4(String newProperty4) {
		this.property4 = newProperty4;
	}

	/**
	 * Returns manschaften.
	 * 
	 * @return manschaften
	 */
	public String getManschaften() {
		return this.manschaften;
	}

	/**
	 * Sets a value to attribute manschaften.
	 * 
	 * @param newManschaften
	 */
	public void setManschaften(String newManschaften) {
		this.manschaften = newManschaften;
	}

	/**
	 * Returns teilnehmerPositonen.
	 * 
	 * @return teilnehmerPositonen
	 */
	public HashSet<TeamPosition> getTeilnehmerPositonen() {
		return this.teilnehmerPositonen;
	}

	/**
	 * Returns mannschaften.
	 * 
	 * @return mannschaften
	 */
	public HashSet<Team> getMannschaften() {
		return this.mannschaften;
	}

	/**
	 * Returns race.
	 * 
	 * @return race
	 */
	public Race getRace() {
		return this.race;
	}

	/**
	 * Sets a value to attribute race.
	 * 
	 * @param newRace
	 */
	public void setRace(Race newRace) {
		this.race = newRace;
	}

}
