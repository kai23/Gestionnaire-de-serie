package controlleur;

import java.io.File;
import java.util.ArrayList;

public class SerieController {

	ArrayList<String> listeFichier = new ArrayList<>();

	/**
	 * @param args
	 */

	public ArrayList<String> listDirectory(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();
		

		// On récupère la liste de fichiers dans l'arraylist
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				
				// On vérifie si c'est pas un repertoire
				if (files[i].isDirectory() == false) {
					listeFichier.add(files[i].getName());
				}
				
				// Si c'en est un, alors on relance la fonction pour chercher récursivement
				if (files[i].isDirectory() == true) {
					this.listDirectory(files[i].getAbsolutePath());
				}
			}
		}
		

		
		return listeFichier;

	}

}
