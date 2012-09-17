package application;

import java.util.ArrayList;

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
		
		
		String chemin = "/home/";
		SerieController controller = new SerieController();
		ArrayList<String> listeFichiers = controller.listDirectory(chemin);
	
	}

}
