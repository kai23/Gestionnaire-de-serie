package controlleur;

import org.apache.log4j.*;

import utils.Clavier;
import utils.UtilitaireSerie;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.filechooser.FileNameExtensionFilter;

import model.Serie;
import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Episode;
import com.moviejukebox.thetvdb.model.Series;

public class SerieController {

	ArrayList<File> listeFichier = new ArrayList<>();
	UtilitaireSerie utilitaireSerie = new UtilitaireSerie();
	String apiKey = "6FB2F69F85316497";


	/**
	 * Fonction permettant de lister les différents fichiers des repertoires
	 * @param dir : Le repertoire choisi
	 * @return un tableau de fichiers
	 */
	public ArrayList<File> listDirectory(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();

		// On récupère la liste de fichiers dans l'arraylist
		if (files != null) {
			for (int i = 0; i < files.length; i++) {

				// On vérifie si c'est pas un repertoire
				if (files[i].isDirectory() == false && !files[i].isHidden()) {
					listeFichier.add(files[i]);
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
	
	/**
	 * Fonction permettant de récupérer tous les noms de série
	 * @return un tableau avec les nom des séries
	 */
	
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

	/**
	 * Fonction permettant de renommer un fichier
	 * @param fichier_a_renommer : le fichier... à renommer !
	 */
	public void renameEpisode(File fichier_a_renommer) {

		// On récupère le nom du fichier à renommer
		String nomSerie = fichier_a_renommer.getName();
		
		// On récupère l'extension
		String ext = fichier_a_renommer.getName().substring(fichier_a_renommer.getName().lastIndexOf("."), fichier_a_renommer.getName().length());
		
		
		/* On cherche le nom de la série grace à notre super utilitaire ! */
		ArrayList<Object> episodeTrouve = this.trouverNomSerie(nomSerie);

		// Y'a plus qu'a faire le renomage !
		System.out.println(
				"Renommer pour \"" + 
						episodeTrouve.get(0) + 
						" S" + episodeTrouve.get(1) +
						"E" + episodeTrouve.get(2)+ 
						" - " + episodeTrouve.get(3)+"\" ? O/N ");
		String choixRenommage = Clavier.lireString();
		if (choixRenommage.equals("O") || choixRenommage.equals("o")) {
			String renommage = episodeTrouve.get(0) + 
					" S" + episodeTrouve.get(1) +
					"E" + episodeTrouve.get(2)+ 
					" - " + episodeTrouve.get(3)+
					ext;
			
			
			
			String nouveau_nom = fichier_a_renommer.getParent()+"/"+renommage;
			
			
			
			fichier_a_renommer.renameTo(new File(nouveau_nom));
		}

	}

	/**
	 * Fonctioner permettant de trouver le nom d'une série
	 * @param nomSerie : le nom "brut" de la série à renommer
	 * @return Un tableau contenant :
	 * <ul>
	 * 	<li> Le nom de la série </li>
	 * 	<li> Le numéro de la saison </li>
	 * 	<li> Le numéro de l'épisode </li>
	 * 	<li> Le nom de l'épisode </li>
	 * </ul>
	 */
	public ArrayList<Object> trouverNomSerie(String nomSerie) {

		// Les variables dont on aura besoin
		TheTVDB tvdb = new TheTVDB(apiKey);
		List<Series> serieRecherchee = null;
		Episode episode = new Episode();
		String nouveauNom;
		int numEpisode;
		String saisonTemp;
		int saison;

		// Première recherche à chaud de la saison *ça brule :D*
		Scanner scanner = new Scanner(nomSerie);
		String chiffre1 = scanner.findInLine("\\d");
		try {
			if (chiffre1.equals("0")) {
				chiffre1 = scanner.findInLine("\\d");
			} else {
				chiffre1 = chiffre1.concat(scanner.findInLine("\\d"));
			}
			
			saisonTemp = chiffre1;
			if (saisonTemp.length() == 1) {
				saisonTemp = "0".concat(saisonTemp);
			}
			
			// On caste la saison
			saison = Integer.parseInt(saisonTemp);
			// Puis celle de l'épisode
			String chiffre3 = scanner.findInLine("\\d");
			String chiffre4 = scanner.findInLine("\\d");
			if (chiffre4 != null) {
				chiffre3 = chiffre3.concat(chiffre4);
			}
			
			// On récupère le numéro de l'épisode sous forme de int
			numEpisode = Integer.parseInt(chiffre3);
			System.out.println("Trouvé : Saison " + saison + " Episode " + numEpisode);

			
		}
		catch (NullPointerException e) {
			System.out.println("Aucune saison ou épisode n'a pu être trouvé");
			System.out.println("Saison : ");
			saison = Clavier.lireInt();
			
			System.out.println("Episode :");
			numEpisode = Clavier.lireInt();
		}
			
		// On commence à faire les recherche par un explode sur la ponctuation
		String[] tokens = nomSerie.split("\\p{Punct}");
		nouveauNom = tokens[0];

		// System.out.println(nouveauNom);
		serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
		if (serieRecherchee.size() > 10) {
			System.out.println("Beaucoup trop de résultats ! merci de saisir le nom :");
			nouveauNom = Clavier.lireString();
			serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
		}
		if (serieRecherchee.isEmpty()) {
			tokens = nomSerie.split("\\d");
			nouveauNom = tokens[0];
			serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
			if (serieRecherchee.isEmpty()) {
				System.out.println("Désolé, je ne trouve pas la série recherchée, merci de la saisir à la main");
				nouveauNom = Clavier.lireString();
				serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
			}
		}
		int i = 0;
		for (Series series : serieRecherchee) {
			System.out.println(i + " " + series.getSeriesName());
			i++;
		}

		int choix = Clavier.lireInt();
		Series serie = serieRecherchee.get(choix);

		// On cherches les épisodes de la série
		List<Episode> episodes = tvdb.getSeasonEpisodes(serie.getId(), saison, "fr");

		// On récupère l'épisode recherché
		episode = episodes.get(numEpisode- 1);

		// On crée l'arraylist avec les différents objets
		ArrayList<Object> listeAttributs = new ArrayList<Object>();
		listeAttributs.add(serie.getSeriesName());
		listeAttributs.add(saison);
		listeAttributs.add(numEpisode);
		listeAttributs.add(episode.getEpisodeName());
		
		return listeAttributs;

	}

	/**
	 * Fonction permettant de récupérer l'ID d'une serie par son nom
	 * @param nomSerie : le nom de la série
	 * @return l'ID de la série
	 */
	public int getSerieIDbyName(String nomSerie) {
		int idSerie = 0;

		return idSerie;
	}
	
	
	public String trouverSaisonEpisode(String nomSerieBrut) {
				// Première recherche à chaud de la saison *ça brule :D*
				Scanner scanner = new Scanner(nomSerieBrut);
				String chiffre1 = scanner.findInLine("\\d");
				int saison;
				int numEpisode;
				try {
					if (chiffre1.equals("0")) {
						chiffre1 = scanner.findInLine("\\d");
					} else {
						chiffre1 = chiffre1.concat(scanner.findInLine("\\d"));
					}
					
					String saisonTemp = chiffre1;
					if (saisonTemp.length() == 1) {
						saisonTemp = "0".concat(saisonTemp);
					}
					
					// On caste la saison
					saison = Integer.parseInt(saisonTemp);
					// Puis celle de l'épisode
					String chiffre3 = scanner.findInLine("\\d");
					String chiffre4 = scanner.findInLine("\\d");
					if (chiffre4 != null) {
						chiffre3 = chiffre3.concat(chiffre4);
					}
					
					// On récupère le numéro de l'épisode sous forme de int
					numEpisode = Integer.parseInt(chiffre3);
					System.out.println("Trouvé : Saison " + saison + " Episode " + numEpisode);
					nomSerieBrut = "S"+saison+"E"+numEpisode;
					
				}
				catch (NullPointerException e) {
					System.out.println("Aucune saison ou épisode n'a pu être trouvé");
					System.out.println("Saison : ");
					saison = Clavier.lireInt();
					
					System.out.println("Episode :");
					numEpisode = Clavier.lireInt();
				}
		
		return nomSerieBrut;
	}

}
