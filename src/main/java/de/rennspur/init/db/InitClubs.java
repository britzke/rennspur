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
package de.rennspur.init.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.rennspur.model.Club;
import de.rennspur.model.Clubs;

/**
 * Initializes the database table for the entity {@link Club} with the contents
 * of an XML file of german sailing clubs.
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 * 
 */
public class InitClubs {

	/**
	 * Haupteinstiegspunkt in das Programm. Die {@link Club} Datensätze werden
	 * aus einer XML-Datei gelesen und in die Datenbank gespeichert.
	 * 
	 * @param args
	 *            Dateiname der einzulesenden Datei.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1) {
			System.out.println("usage: java InitClubs <xml-input-filename>");
		} else {
			try {
				JAXBContext jaxbContext = JAXBContext
						.newInstance("de.rennspur.model");
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				unmarshaller.setEventHandler(
						new UnmarschallerValidationEventHandler());
				Clubs clubs = (Clubs) unmarshaller
						.unmarshal(new FileInputStream(args[0]));

				EntityManagerFactory emf = Persistence
						.createEntityManagerFactory("rennspur-db");
				EntityManager em = emf.createEntityManager();
				EntityTransaction et = em.getTransaction();
				et.begin();
				for (Club club : clubs.getClub()) {
					club = em.merge(club);
				}
				et.commit();
				em.close();
				System.out.println(clubs.getClub().size() + " Clubs imported");
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
}