package fr.doranco.jaxws.etudiant;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.doranco.jaxws.webservice.EtudiantService;
import fr.doranco.jaxws.webservice.EtudiantService_Service;

public class EtudiantWebServiceSynchroneTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before Class");
		EtudiantService_Service service = new EtudiantService_Service();
		EtudiantService port = service.getEtudiantPort();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After Class");
	}

	@Before
	public void setUpBeforeMethod() throws Exception {
		System.out.println("Before Method");
	}

	@After
	public void tearDownAfterMethod() throws Exception {
		System.out.println("After Method");
	}


	@Test
	public void testEtudiant() {

		fail("Not yet implemented");
	}

}
