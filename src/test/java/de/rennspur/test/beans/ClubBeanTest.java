/**
 * 
 */
package de.rennspur.test.beans;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import de.rennspur.beans.ClubBean;
import de.rennspur.model.Club;

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
	@Mock
	EntityTransaction et;

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
		when(em.getTransaction()).thenReturn(et);
		when(em.merge(any(Club.class))).then(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Club club = (Club) invocation.getArguments()[0];
				assertEquals(proband.getName(), club.getName());
				assertEquals(proband.getAbbreviation(), club.getAbreviation());
				assertEquals(proband.getUrl(), club.getUrl());
				assertEquals(proband.getDsv_number(), club.getDsvNumber());
				return null;
			}
		});
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
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testInsertNewClub() throws NoSuchMethodException, SecurityException {
		// Check if return type is Type void
		Class<? extends ClubBean> probandClass = proband.getClass();
		Method insertNewClubMethod = probandClass.getMethod("insertNewClub");
		assertTrue(insertNewClubMethod.getReturnType().equals(Void.TYPE));

		proband.insertNewClub();

		// verify that the changes have been committed
		verify(et, atLeast(1)).commit();
	}

	/**
	 * Test method for
	 * {@link de.rennspur.beans.ClubBean#onRowSelect(org.primefaces.event.SelectEvent)}.
	 */
	@Test
	public void testOnRowSelect() {
		fail("Not yet implemented");
	}

}