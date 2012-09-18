package controlleur;



import org.apache.log4j.*;

import utils.Clavier;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import model.Serie;
import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Episode;
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
	
	
	public void renameEpisode(String nomSerie) {
		String apiKey = "6FB2F69F85316497";
		TheTVDB tvdb = new TheTVDB(apiKey);
		List<Series> serieRecherchee = tvdb.searchSeries(nomSerie, "fr");
		try {
			
			System.out.println("Saisir la série choisie : ");
			int i = 0;
			for (Series series : serieRecherchee) {
				System.out.println(i +" "+ series.getSeriesName());
				i++;
			}
			i=0;
			System.out.println("Saisir la série choisie : ");
			int choix = Clavier.lireInt();
			Series serie = serieRecherchee.get(choix);
			System.out.println("Merci de choisir une saison");
			int saison = Clavier.lireInt();
			System.out.println("Voici les épisodes correspondants : ");
			List<Episode> episodes = tvdb.getSeasonEpisodes(serie.getId(), saison, "fr");
			for (Episode episode : episodes) {
				System.out.println(i+" "+episode.getEpisodeName());
				i++;
			}
			System.out.println("Choisir un épisode");
			int episodeChoisit = Clavier.lireInt();
			Episode episode = episodes.get(episodeChoisit);
			System.out.println("Voici l'overview");
			System.out.println(episode.getOverview());
			
			
		} catch (NullPointerException e) {
			System.out.println("Aucune série n'a été trouvée !");
		} catch (IndexOutOfBoundsException e2) {
			System.out.println("Cette série n'existe pas");
		}
	}
	

}
