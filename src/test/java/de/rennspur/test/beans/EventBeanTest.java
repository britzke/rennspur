package de.rennspur.test.beans;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import de.rennspur.beans.EventBean;
import de.rennspur.model.Event;
/**
 * Tests for UnitEventBean.
 * 
 * @author maXLeev
 */
@RunWith(MockitoJUnitRunner.class)
public class EventBeanTest {
	
	@InjectMocks
	EventBean proband;
	
	@Mock
	EntityManagerFactory emf;
	@Mock
	EntityManager em;
	@Mock
	Query q;

	List<Event> list;
	
	@Before
	public void setUp() throws Exception{
		when(emf.createEntityManager()).thenReturn(em);
		when(em.createNamedQuery("Event.findAll")).thenReturn(q);
		when(q.getResultList()).thenReturn(list = new ArrayList<Event>());
		
	}
	
	@Test
	public void testInit(){
		proband.init();
		assertEquals(proband.getEvents(), list);		
	}
	
}
