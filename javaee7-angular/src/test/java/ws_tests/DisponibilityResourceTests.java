package ws_tests;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cortez.samples.javaee7angular.data.Disponibility;
import com.cortez.samples.javaee7angular.data.Restaurant;

public class DisponibilityResourceTests {

	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
		em = emf.createEntityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		em.close();
		emf.close();
	}

	@Test
	public void testGetDisponibility() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveDisponibility() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// Ajout restaurant
		Restaurant rest = new Restaurant();
		rest.setName("restoTest");
		em.persist(rest);
		tx.commit();
		long restId = rest.getId();
		assertNotNull("Id not null", restId);
		Restaurant restRetrouve = em.find(Restaurant.class, restId);
		assertNotNull("Restaurant from database", restRetrouve);
		assertEquals("getName", "restoTest", restRetrouve.getName());
	}

	@Test
	public void testDeleteDisponibility() {
		fail("Not yet implemented");
	}

}
