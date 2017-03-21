/**
 * 
 */
package de.rennspur.test.beans;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import de.rennspur.beans.ClubBean;

/**
 * Tests the ClubBean unit.
 *
 * @author britzke
 */
@RunWith(MockitoJUnitRunner.class)
public class ClubBeanTest {

	@InjectMocks
	ClubBean proband;

	@Mock
	EntityManagerFactory emf;
	@Mock
	EntityManager em;
	@Mock
	Query q;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// proband = new ClubBean();
		// emf = mock(EntityManagerFactory.class);
		// em = mock(EntityManager.class);
		// q = mock(Query.class);
		// Class<? extends ClubBean> probandClass = proband.getClass();
		// Field emfField = probandClass.getDeclaredField("emf");
		// emfField.setAccessible(true);
		// emfField.set(proband, emf);

		when(emf.createEntityManager()).thenReturn(em);
		when(em.createNamedQuery("Club.findAll")).thenReturn(q);
	}

	/**
	 * Tests if the clubs list field is initialized from the database. Test
	 * method for {@link de.rennspur.beans.ClubBean#init()}.
	 */
	@Test
	public void testIfInitInitializesFieldClubs() {

		proband.init();
		assertNotNull(proband.getClubs());
	}

	/**
	 * Test method for {@link de.rennspur.beans.ClubBean#insertNewClub()}.
	 */
	@Test
	public void testInsertNewClub() {
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link de.rennspur.beans.ClubBean#onRowSelect(org.primefaces.event.SelectEvent)}.
	 */
	@Test
	public void testOnRowSelect() {
		// fail("Not yet implemented");
	}

}
