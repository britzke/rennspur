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

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import de.rennspur.model.Race;
import de.rennspur.model.TeamPosition;

/**
 * Imulate incoming Traces for two boats {@link TeamPosition},
 * 
 * @author burghard.britzke bubi@charmides.in-berlin.de
 * 
 */
public class SimulateTraces {

	/**
	 * Haupteinstiegspunkt in das Programm.
	 * 
	 * @param args
	 *            Wird nicht beachtet.
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws InterruptedException {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("rennspur-db-local");
		EntityManager em = emf.createEntityManager();

		javax.persistence.Query q = em.createNamedQuery("Race.findRace");
		q.setParameter("id", 1);
		Race r = (Race) q.getSingleResult();

		TeamPosition tp;
		// [52.50100,13.20200]-[52.50200,13.18800]
		// [52.50150,13.20100]-[52.50210,13.19000]
		// [52.50200,13.20150]-[52.50215,13.19100]
		// [52.50180,13.20050]-[52.50190,13.18900]

		Random random = new Random();
		double[][] coord = { { 52.50100, 13.20200 }, { 52.50150, 13.20100 },
				{ 52.50200, 13.20150 }, { 52.50180, 13.20050 } };
		double[][] delta = {
				{ (52.50200 - 52.50100) / 200.0,
						(13.20100 - 13.18800) / 200.0 },
				{ (52.50210 - 52.50080) / 200.0,
						(13.20100 - 13.19000) / 200.0 },
				{ (52.50215 - 52.50200) / 200.0,
						(13.19100 - 13.18150) / 200.0 },
				{ (52.50190 - 52.50180) / 200.0,
						(13.18900 - 13.18500) / 200.0 } };

		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 4; j++) {
				coord[j][0] -= delta[j][0] + random.nextDouble() * 0.00005
						- 0.000025;
				coord[j][1] -= delta[j][1] + random.nextDouble() * 0.00005
						- 0.000025;
				tp = new TeamPosition();
				tp.setRace(r);
				tp.setTeam(r.getEvent().getTeams().get(j));
				tp.setTime(new Timestamp(new Date().getTime()));
				tp.setLatitude(coord[j][0]);
				tp.setLongitude(coord[j][1]);
				EntityTransaction et = em.getTransaction();
				et.begin();
				tp = em.merge(tp);
				et.commit();
			}
			Thread.sleep(1000);
		}
		em.close();
	}
}