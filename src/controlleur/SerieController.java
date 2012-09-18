package controlleur;



import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import model.Serie;
import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Series;


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

				// Si c'en est un, alors on relance la fonction pour chercher
				// récursivement
				if (files[i].isDirectory() == true) {
					this.listDirectory(files[i].getAbsolutePath());
				}
			}
		}

		return listeFichier;

	}

	public ArrayList<String> getAllSerieName() {
		Serie serie = new Serie();
		ArrayList<String> listeNomSerie = new ArrayList<>();
		
		// On récupère toutes les séries
		ArrayList<Serie> listeSerie = serie.getAllSeries();

		for (Serie serie2 : listeSerie) {
			listeNomSerie.add(serie2.getName());
		}

		return listeNomSerie;
	}
	
	
	public void renameEpisode(String nomEpisode) {
		String apiKey = "6FB2F69F85316497";
		TheTVDB tvdb = new TheTVDB(apiKey);
		System.out.println(TheTVDB.getXmlMirror(apiKey));
		List<Series> serie = tvdb.searchSeries(nomEpisode, "fr");
		try {
			;
		} catch (NullPointerException e) {
			System.out.println("Pas de série de ce nom !");
		}
	}
	

}
