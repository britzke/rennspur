package de.rennspur.test.init.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.Test;
import org.mockito.Mock;

import de.rennspur.model.Race;
import de.rennspur.model.TeamPosition;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(SimulateTraces.class)
public class SimulateTracesTest {

	@Mock
	EntityManagerFactory emf;
	@Mock
	EntityManager em;
	@Mock
	Query q;

	@Mock
	Race r;
	@Mock
	TeamPosition tp;


	@Test
	public void Test(){
		//mockStatic();
	}
}
