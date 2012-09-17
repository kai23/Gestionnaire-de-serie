package controlleur;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class SerieController {

	private int dircount;
	private int filecount;

	/**
	 * @param args
	 */

	public ArrayList<String> listDirectory(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();
		ArrayList<String> listeFichier = new ArrayList<>();

		// On récupère la liste de fichiers dans l'arraylist
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				// String fichier = files[i].toString();
				// String ext = fichier.substring(fichier.lastIndexOf("."));
				if (files[i].isDirectory() == false) {
					listeFichier.add(files[i].toString());
				}
				if (files[i].isDirectory() == true) {
					this.listDirectory(files[i].getAbsolutePath());
				}
			}
		}
		return listeFichier;

	}

}
