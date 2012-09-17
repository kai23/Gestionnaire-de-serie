package application;

import controlleur.SerieController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 
		 * Faire tous les tests ici !
		 * 
		 */
		
		SerieController controller = new SerieController();
		controller.listDirectory("/root");
	
	}

}
