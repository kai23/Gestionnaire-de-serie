package controlleur;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SerieControllerTest {

	protected SerieController controller;

	@Before
	public void setUp() {
		controller = new SerieController();
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void testTrouverNomSerie() {
		String test1 = controller.trouverSaisonEpisode("malcolm103.avi");
		assertEquals("S01E03", test1);
	}
	
	@Test
	public void testTrouverNomSerie2(){
		String test1 = controller.trouverSaisonEpisode("malcolm0103.avi");
		assertEquals("S01E03", test1);
	}
	
	@Test
	public void testTrouverNomSerie3(){
		String actual = controller.trouverSaisonEpisode("malcolm1003.avi");
		assertEquals("S10E03", actual);
	}
	
	@Test
	public void testTrouverNomSerie4() {
		String actual = controller.trouverSaisonEpisode("malcolm S01 E03.avi");
		assertEquals("S01E03", actual);
	}
	
	@Test
	public void testTrouverNomSerie5() {
		String actual = controller.trouverSaisonEpisode("malcolm1003.avi");
		assertEquals("", actual);
	}
	
	
	
	
}
