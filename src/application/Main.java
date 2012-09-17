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
		
		
		// Le chemin qui est à récupérer via JFileChooser
		String chemin = "/home/";
		
		// Notre controleur de série
		SerieController controller = new SerieController();

		// On récupère la liste des fichiers du dossier
		ArrayList<String> listeFichiers = controller.listDirectory(chemin);
		
		// On affiche notre liste
		System.out.println(listeFichiers);
		
	}

}
