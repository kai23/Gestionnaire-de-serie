package controlleur;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SerieControllerTest {

	protected SerieController controller;
	protected String episode;
	protected String saison;
	
	@Before
	public void setUp() {
		controller = new SerieController();
		
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void testTrouverNomSerie() {
		ArrayList<String> test1 = controller.trouverSaisonEpisode("malcolm103.avi");
		assertEquals("01", test1.get(0));
		assertEquals("03", test1.get(1));
	}
	
	@Test
	public void testTrouverNomSerie2(){
		ArrayList<String> test1 = controller.trouverSaisonEpisode("malcolm0103.avi");
		assertEquals("01", test1.get(0));
		assertEquals("03", test1.get(1));
	}
	
	@Test
	public void testTrouverNomSerie3(){
		ArrayList<String> actual = controller.trouverSaisonEpisode("malcolm1003.avi");
		assertEquals("10", actual.get(0));
		assertEquals("03", actual.get(1));
	}
	
	@Test
	public void testTrouverNomSerie4() {
		ArrayList<String> actual = controller.trouverSaisonEpisode("malcolm S01 E03.avi");
		assertEquals("01", actual.get(0));
		assertEquals("03", actual.get(1));
	}
	
	@Test
	public void testTrouverNomSerie5() {
		ArrayList<String> actual = controller.trouverSaisonEpisode("malcolm13.avi");
		
		assertEquals("01", actual.get(0));
		assertEquals("03", actual.get(1));
	}
	
	@Test
	public void testTrouverNomSerie6() {
		ArrayList<String> actual = controller.trouverSaisonEpisode("malcolm - S1E3.avi");
		assertEquals("01", actual.get(0));
		assertEquals("03", actual.get(1));
	}
	
	@Test void testRenommage() {
		
	}
	
	
	
	
}
