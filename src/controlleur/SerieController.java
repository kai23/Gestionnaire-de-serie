package controlleur;

import org.apache.log4j.*;

import utils.Clavier;
import utils.UtilitaireSerie;

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
	UtilitaireSerie utilitaireSerie = new UtilitaireSerie();
	String apiKey = "6FB2F69F85316497";
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
					listeFichier.add(files[i].getAbsolutePath());
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

	public void renameEpisode(File fichier_a_renommer) {

		// On récupère le nom du fichier à renommer
		String nomSerie = fichier_a_renommer.getName();

		/* On cherche le nom de la série grace à notre super utilitaire ! */
		Episode episodeTrouve = this.trouverNomSerie(nomSerie);

		// Y'a plus qu'a faire le renomage !
		// TODO

	}

	public Episode trouverNomSerie(String nomSerie) {
		
		
		// Les variables dont on aura besoin
		TheTVDB tvdb = new TheTVDB(apiKey);
		List<Series> serieRecherchee = null;
		Episode episode = new Episode();
		String nouveauNom;
		
		// On commence à faire les recherche par un explode, avec le premier
		String[] tokens = nomSerie.split("-");
		nouveauNom = tokens[0];
		serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
		
		// Ca n'a pas marché :(
		if (serieRecherchee.isEmpty()) {
			
		}
		
		// Youhou ! Du premier coup \o/ *like a boss*
		else {
			System.out.println("Merci de saisir la série : ");
		}
		
		
		// // l'utilisateur de le rentrer lui même
		// if (nomSerie == null) {
		// System.out.println("La série n'a pas été trouvée. Merci d'en saisir une : ");
		// nomSerie = Clavier.lireString();
		//
		// // On recherche la série via le nom
		// serieRecherchee = tvdb.searchSeries(nomSerie, "fr");
		//
		// }
		//
		// // Ca y est, on a un nom valide !
		// // On recherche les éventuels doublons
		// serieRecherchee = tvdb.searchSeries(nomSerie, "fr");
		// // On affiche ce qu'on a trouvé
		// int i = 0;
		// for (Series series : serieRecherchee) {
		// System.out.println(i + " " + series.getSeriesName());
		// i++;
		// }
		//
		// // Réinitialisation de i
		// i = 0;
		//
		// // Choix de la série
		// int choix = Clavier.lireInt();
		// Series serie = serieRecherchee.get(choix);
		//
		// // Choix de la saison
		// System.out.println("Merci de choisir une saison");
		// int saison = Clavier.lireInt();
		//
		// // Choix de l'épisode
		// System.out.println("Voici les épisodes correspondants : ");
		// List<Episode> episodes = tvdb.getSeasonEpisodes(serie.getId(),
		// saison,
		// "fr");
		// for (Episode episode : episodes) {
		// System.out.println(i + " " + episode.getEpisodeName());
		// i++;
		// }
		// System.out.println("Choisir un épisode");
		// int episodeChoisit = Clavier.lireInt();
		// Episode episode = episodes.get(episodeChoisit);
		return episode;
	}

}
