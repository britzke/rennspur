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
package de.rennspur.init.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.rennspur.model.Countries;
import de.rennspur.model.Country;

/**
 * Initializes the database table for the entity {@link Country} with the
 * contents of an XML file of countries with their ISO3166 codes.
 *
 * @author burghard.britzke bubi@charmides.in-berlin.de
 */
public class InitCountries {

	/**
	 * Main entry point for this programm. The {@link Country} Objects will be
	 * unmarschalled out of a XML file and be stored into the database. If
	 * records with the same name exists, the content of the XML element is
	 * merged into the existing country.
	 *
	 * @param args
	 *            Filename of the xml input file.
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 2) {
			System.out.println(
					"usage: java InitCountries <xml-input-filename> <true|false (update only)>");
		} else {
			try {
				boolean updateOnly = Boolean.valueOf(args[1]);
				JAXBContext jaxbContext = JAXBContext
						.newInstance("de.rennspur.model");
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				unmarshaller.setEventHandler(
						new UnmarschallerValidationEventHandler());
				Countries countries = (Countries) unmarshaller
						.unmarshal(new FileInputStream(args[0]));

				EntityManagerFactory emf = Persistence
						.createEntityManagerFactory("rennspur-db-local");
				EntityManager em = emf.createEntityManager();

				EntityTransaction et = em.getTransaction();
				et.begin();
				Query q = em.createNamedQuery("Country.findAll");
				@SuppressWarnings("unchecked")
				List<Country> existingCountries = (List<Country>) q
						.getResultList();
				for (Country country : countries.getCountry()) {
					boolean countryExists = false;
					for (Country existingCountry : existingCountries) {
						if (existingCountry.getName()
								.equals(country.getName())) {
							countryExists = true;
							if (country.getA2Code() != null) {
								existingCountry.setA2Code(country.getA2Code());
							}
							if (country.getA3Code() != null) {
								existingCountry.setA3Code(country.getA3Code());
							}
							if (country.getCode() != null) {
								existingCountry.setCode(country.getCode());
							}
							if (country.getSince() != null) {
								existingCountry.setSince(country.getSince());
							}
							if (country.getMnaCode() != null) {
								existingCountry.setMnaCode(country.getMnaCode());
							}
							if (country.getMnaGroup() != null) {
								existingCountry.setMnaGroup(country.getMnaGroup());
							}
						}
					}
					if (!countryExists) {
						if (updateOnly) {
							System.out.println("No Match found for "+country.getName());
						} else {
							existingCountries.add(em.merge(country));
						}
					}
				}
				et.commit();
				em.close();
				System.out.println(
						countries.getCountry().size() + " Countries imported");
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
	}
}