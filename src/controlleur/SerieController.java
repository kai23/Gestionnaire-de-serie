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

import model.Season;
import model.Serie;
import com.moviejukebox.thetvdb.TheTVDB;
import com.moviejukebox.thetvdb.model.Episode;
import com.moviejukebox.thetvdb.model.Series;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class SerieController {

	ArrayList<File> listeFichier = new ArrayList<>();
	UtilitaireSerie utilitaireSerie = new UtilitaireSerie();
	String apiKey = "6FB2F69F85316497";
	Series serie;
	ArrayList<Serie> maCollection = null;
	
	//Constructeur
	
	
	/**
	 * Chargement des series
	 */
	public void loadSeries (String url){
		maCollection = Serie.getAllSeries(url);
	}

	/**
	 * Fonction permettant de lister les différents fichiers des repertoires
	 * 
	 * @param dir
	 *            : Le repertoire choisi
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
	 * 
	 * @return un tableau avec les nom des séries
	 */

	public ArrayList<String> getAllSerieName() {
		ArrayList<String> listeNomSerie = new ArrayList<String>();
		for (Serie serie : maCollection) {
			listeNomSerie.add(serie.getName());			
		}
		return listeNomSerie;
	}

	/**
	 * Fonction permettant de renommer un fichier
	 * 
	 * @param fichier_a_renommer
	 *            : le fichier... à renommer !
	 */
	public void renameEpisode(ArrayList<File> listeFichier) {

		ArrayList<String> fichierRenommes = new ArrayList<>();

		for (File fichier_a_renommer : listeFichier) {
			// On récupère le nom du fichier à renommer
			String nomSerie = fichier_a_renommer.getName();

			// On récupère l'extension
			String ext = fichier_a_renommer.getName().substring(
					fichier_a_renommer.getName().lastIndexOf("."),
					fichier_a_renommer.getName().length());

			/* On cherche le nom de la série grace à notre super utilitaire ! */
			ArrayList<String> episodeTrouve = this.trouverInfosSerie(nomSerie);

			// Y'a plus qu'à ajouter au le renomage !
			String renommage = episodeTrouve.get(0) + " S"
					+ episodeTrouve.get(1) + "E" + episodeTrouve.get(2) + " - "
					+ episodeTrouve.get(3) + ext;

			String nouveau_nom = fichier_a_renommer.getParent() + "/"
					+ renommage;

			System.out.println(nomSerie + ext + "   ===>    " + renommage);
			fichierRenommes.add(nouveau_nom);
			// fichier_a_renommer.renameTo(new File(nouveau_nom));

		}

		System.out.println("Renommer l'ensemble ? O/N");
		String choix = Clavier.lireString();
		if (choix.equals("O") || choix.equals("o")) {
			int i = 0;
			for (File fichier_a_renommer : listeFichier) {
				fichier_a_renommer.renameTo(new File(fichierRenommes.get(i)));
				i++;
			}
			System.out.println("Renommage réussi !");
		}
		else {
			for (File fichier_a_renommer : listeFichier) {
				// On récupère le nom du fichier à renommer
				String nomSerie = fichier_a_renommer.getName();

				// On récupère l'extension
				String ext = fichier_a_renommer.getName().substring(
						fichier_a_renommer.getName().lastIndexOf("."),
						fichier_a_renommer.getName().length());

				/* On cherche le nom de la série grace à notre super utilitaire ! */
				ArrayList<String> episodeTrouve = this.trouverInfosSerie(nomSerie);

				// Y'a plus qu'à ajouter au le renomage !
				String renommage = episodeTrouve.get(0) + " S"
						+ episodeTrouve.get(1) + "E" + episodeTrouve.get(2) + " - "
						+ episodeTrouve.get(3) + ext;

				String nouveau_nom = fichier_a_renommer.getParent() + "/"
						+ renommage;

				
				System.out.println(nomSerie + ext + "   ===>    " + renommage + " ? O/N");
				
				if (Clavier.lireString().equals("O")) {
					fichier_a_renommer.renameTo(new File(nouveau_nom));
				}
				

			}
		}

	}

	/**
	 * Fonctioner permettant de trouver le nom d'une série
	 * 
	 * @param nomSerie
	 *            : le nom "brut" de la série à renommer
	 * @return Un tableau contenant :
	 *         <ul>
	 *         <li>Le nom de la série</li>
	 *         <li>Le numéro de la saison</li>
	 *         <li>Le numéro de l'épisode</li>
	 *         <li>Le nom de l'épisode</li>
	 *         </ul>
	 */
	public ArrayList<String> trouverInfosSerie(String nomSerie) {

		// Les variables dont on aura besoin
		TheTVDB tvdb = new TheTVDB(apiKey);
		List<Series> serieRecherchee = null;
		Episode episode = new Episode();
		String nouveauNom;
		String numEpisode;
		String saison;

		ArrayList<String> SaisonEpisode = trouverSaisonEpisode(nomSerie);
		saison = SaisonEpisode.get(0);
		numEpisode = SaisonEpisode.get(1);

		if (this.serie == null) {

			// On commence à faire les recherche par un explode sur la
			// ponctuation
			String[] tokens = nomSerie.split("\\p{Punct}");
			nouveauNom = tokens[0];

			// System.out.println(nouveauNom);
			serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
			if (serieRecherchee.size() > 10) {
				System.out
						.println("Beaucoup trop de résultats ! merci de saisir le nom :");
				nouveauNom = Clavier.lireString();
				serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
			}
			if (serieRecherchee.isEmpty()) {
				tokens = nomSerie.split("\\d");
				nouveauNom = tokens[0];
				serieRecherchee = tvdb.searchSeries(nouveauNom, "fr");
				if (serieRecherchee.isEmpty()) {
					System.out
							.println("Désolé, je ne trouve pas la série recherchée, merci de la saisir à la main");
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
			// On conserve la série
			this.serie = serie;
		}
		// On cherches les épisodes de la série
		List<Episode> episodes = tvdb.getSeasonEpisodes(this.serie.getId(),
				Integer.parseInt(saison), "fr");

		// On récupère l'épisode recherché
		episode = episodes.get(Integer.parseInt(numEpisode) - 1);

		// On crée l'arraylist avec les différents objets
		ArrayList<String> listeAttributs = new ArrayList<String>();
		listeAttributs.add(this.serie.getSeriesName());
		listeAttributs.add(saison);
		listeAttributs.add(numEpisode);
		listeAttributs.add(episode.getEpisodeName());

		return listeAttributs;

	}

	/**
	 * Fonction permettant de récupérer l'ID d'une serie par son nom
	 * 
	 * @param nomSerie
	 *            : le nom de la série
	 * @return l'ID de la série
	 */
	public int getSerieIDbyName(String nomSerie) {
		int idSerie = 0;

		return idSerie;
	}

	/**
	 * Fonction permettant d'extraire la saison et l'épisode d'une série
	 * 
	 * @param nomSerieBrut
	 *            le nom de la série
	 * @return la forme "S01E02"
	 */
	public ArrayList<String> trouverSaisonEpisode(String nomSerieBrut) {
		// Première recherche à chaud de la saison *ça brule :D*
		Scanner scanner = new Scanner(nomSerieBrut);
		String saison;
		String numEpisode;
		String saisonTemp;
		ArrayList<String> SaisonEpisode = new ArrayList<>();

		// On cherche le nombre de chiffres dans le nom
		int nombreChiffre = 0;
		while (scanner.findInLine("\\d") != null) {
			nombreChiffre++;
		}

		if (nombreChiffre == 3) {

			scanner = new Scanner(nomSerieBrut);

			// On cherche la saison
			saisonTemp = scanner.findInLine("\\d");
			saison = "0".concat(saisonTemp);

			// On cherche l'épisode
			String chiffre3 = scanner.findInLine("\\d");
			String chiffre4 = scanner.findInLine("\\d");
			if (chiffre4 != null) {
				chiffre3 = chiffre3.concat(chiffre4);
			}

			// On récupère le numéro de l'épisode sous forme de int
			numEpisode = chiffre3;

		}

		else if (nombreChiffre == 4) {
			scanner = new Scanner(nomSerieBrut);
			// On cherche la saison
			String chiffre1 = scanner.findInLine("\\d");
			String chiffre2 = scanner.findInLine("\\d");
			saison = chiffre1.concat(chiffre2);

			// On cherche l'épisode
			String chiffre3 = scanner.findInLine("\\d");
			String chiffre4 = scanner.findInLine("\\d");
			numEpisode = chiffre3.concat(chiffre4);

		}

		else if (nombreChiffre == 2) {
			scanner = new Scanner(nomSerieBrut);
			saison = "0" + scanner.findInLine("\\d");
			numEpisode = "0" + scanner.findInLine("\\d");
		}

		// Le chiffre est supérieur à 4 ou inférieur à 2
		else {
			System.out.println("Aucune saison ou épisode n'a pu être trouvé");
			System.out.println("Saison : ");
			saison = Clavier.lireString();

			System.out.println("Episode :");
			numEpisode = Clavier.lireString();
		}

		SaisonEpisode.add(saison);
		SaisonEpisode.add(numEpisode);

		return SaisonEpisode;
	}
	
	public List<Series> trouverSerieTVDB(String nomSerie) {
		// Les variables dont on aura besoin
		TheTVDB tvdb = new TheTVDB(apiKey);
		List<Series> serieRecherchee = tvdb.searchSeries(nomSerie, "fr");
		return serieRecherchee;
	}
	public Serie getSerieByName(String name) {
		for (Serie serie : maCollection) {
			if (serie.getName().equals(name)) {
				return serie;
			}
		}
		return null;
	}

	/**
	 * Fonction permettant d'enregistrer dans le XML une série prise sur TvDB
	 * @param serie2
	 * @return
	 */
	public boolean storeSerieXML(Serie serie) {
		if (serie.storeSerie()) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Fonction permettant de récupérer les saisons d'une série sur TvDB
	 * @param serie
	 * @return
	 */
	public ArrayList<Season> getNumberOfSeason(Series serie) {
		TheTVDB tvdb = new TheTVDB(apiKey);
		List<Episode> allEpisode = tvdb.getAllEpisodes(serie.getSeriesId(), "fr");
		int i = 0;
		for (Episode episode : allEpisode) {
			i = episode.getSeasonNumber();
		}
		Serie serieNousSerie = new Serie(serie.getSeriesId(), serie.getSeriesName(), serie.getOverview());
		ArrayList<Season> saisons = new ArrayList<>();
		for (int j = 0; j <= i; j++) {
			saisons.add(new Season(serieNousSerie, ""+j));
		}
		return saisons;
		
		
	}
}

