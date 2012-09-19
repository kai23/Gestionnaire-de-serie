package model;


import java.io.*;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Serie {
	/**
	 * Attribute
	 */
	private int id;
	private String name;
	private ArrayList<Season> seasons;
	private Episode currentEpisode;
	private String description;

	/**
	 * Setter/Getter
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Constructor
	 */

	public Serie() {
		this.seasons = new ArrayList<Season>();
	}

	public Serie(int id, String name) {
		this.id = id;
		this.name = name;
		this.seasons = new ArrayList<Season>();
	}

	public Serie(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.seasons = new ArrayList<Season>();
	}

	public Serie(int id, String name, ArrayList<Season> seasons,
			Episode currentEpisode, String description) {
		super();
		this.id = id;
		this.name = name;
		this.seasons = seasons;
		this.currentEpisode = currentEpisode;
		this.description = description;
	}

	/**
	 * Methods
	 */
	public void addSeason(Season season) {
		seasons.add(season);
	}
	
	public Season addSeason(int num) {
		if (getSeason(num) != null)
			return null;

		Season season = new Season(this, num);
		seasons.add(season);

		return season;
	}

	public Season getSeason(int num) {
		for (Season season : seasons) {
			if (season.getNum() == num)
				return season;
		}

		return null;
	}

	public void deleteSeason(Season season) {
		seasons.remove(season);
	}

	public ArrayList<Season> getAllSeason() {
		return seasons;
	}

	public String toString() {
		String ret = name + " " + id + "\n";
		for (Season season : seasons) {
			ret = ret + "\t" + season + "\n";
		}
		return ret;
	}
	
	public static ArrayList<Serie> getAllSeries(String file) {
		ArrayList<Serie> series = new ArrayList<Serie>();

		Document document = null;

		SAXBuilder sxb = new SAXBuilder();
		try {
			// On crée un nouveau document JDOM avec en argument le fichier
			// XML
			// Le parsing est terminé ;)
			try {
				document = sxb.build(new File(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Ne trouve pas le fichier !");
			}
		} catch (JDOMException e) {
		}

		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		Element racine = document.getRootElement();
		// On crée une List contenant tous les noeuds "Series" de l'Element
		// racine
		List listageSerie = racine.getChildren("Serie");

		// On crée un Iterator sur notre liste de série
		Iterator i = listageSerie.iterator();
		while (i.hasNext()) {
			// On parcourt toutes les séries
			Element serieCourant = (Element) i.next();

			Serie serie = new Serie(Integer.parseInt(serieCourant.getAttributeValue("id")),
									serieCourant.getAttributeValue("name"));
									
			
			List seasonNodes = serieCourant.getChildren("Saison");
			for (Object seasonObject : seasonNodes) {
				Element seasonNode = (Element)seasonObject;

				Season season = new Season(serie,
										   Integer.parseInt(seasonNode.getAttributeValue("numberS")));

				List episodeNodes = seasonNode.getChildren("Episode");
				for (Object episodeObject : episodeNodes) {
					Element episodeNode = (Element)episodeObject;

					season.addEpisode(episodeNode.getValue(), "/",
									  Integer.parseInt(episodeNode.getAttributeValue("numberE")));
					
				}
				serie.addSeason(season);				
			}
			series.add(serie);
			System.out.println(serie);
				//series.add(serieCourant.getAttributeValue("name"));

		}
		System.out.println(series);
		return series;
	}

	public boolean storeSerie() {
		String xml = "BaseDeDonneeSerie.xml";
		
		// TODO Faire une fonction écrivant à la fin du fichier XML
		
		return false;
	}

	public static int getLastId() {
		// TODO Faire une fonction qui retourne le dernier ID de série
		return 0;
	}
}
