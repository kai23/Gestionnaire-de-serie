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
	}

	public Serie(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Serie(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * Methods
	 */
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

	public ArrayList<String> getAllSeason() {
		ArrayList<String> season = new ArrayList<>();
		{
			Document document = null;
			Element racine;

			SAXBuilder sxb = new SAXBuilder();
			try {
				// On crée un nouveau document JDOM avec en argument le fichier
				// XML
				// Le parsing est terminé ;)
				try {
					document = sxb.build(new File("BaseDeDonneeSerie.xml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Ne trouve pas le fichier !");
				}
			} catch (JDOMException e) {
			}

			// On initialise un nouvel élément racine avec l'élément racine du
			// document.
			racine = document.getRootElement();
			// On crée une List contenant tous les noeuds "Series" de l'Element
			// racine
			List listageSerie = racine.getChildren("Serie");

			// On crée un Iterator sur notre liste de série
			Iterator i = listageSerie.iterator();
			while (i.hasNext()) {
				// On parcourt toutes les séries
				Element serieCourant = (Element) i.next();
				// On crée une List contenant tous les noeuds "Saisons" de
				// l'Element
				// SerieCourant
				List listageSaison = serieCourant.getChildren("Saison");
				// On crée un Iterator sur notre liste de saison
				Iterator s = listageSaison.iterator();
				while (s.hasNext()) {
					// On parcourt toutes les saisons
					Element saisonCourant = (Element) s.next();
					// On affiche le résultat
					/*System.out.println("Saison "
							+ saisonCourant.getAttributeValue("numberS"));*/
					season.add(saisonCourant.getAttributeValue("numberS"));
				}
			}
			System.out.println(season);
		}
		return season;
	}
	
	public ArrayList<String> getAllSeries() {
		ArrayList<String> series = new ArrayList<>();
		{
			Document document = null;
			Element racine;

			SAXBuilder sxb = new SAXBuilder();
			try {
				// On crée un nouveau document JDOM avec en argument le fichier
				// XML
				// Le parsing est terminé ;)
				try {
					document = sxb.build(new File("BaseDeDonneeSerie.xml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Ne trouve pas le fichier !");
				}
			} catch (JDOMException e) {
			}

			// On initialise un nouvel élément racine avec l'élément racine du
			// document.
			racine = document.getRootElement();
			// On crée une List contenant tous les noeuds "Series" de l'Element
			// racine
			List listageSerie = racine.getChildren("Serie");

			// On crée un Iterator sur notre liste de série
			Iterator i = listageSerie.iterator();
			while (i.hasNext()) {
				// On parcourt toutes les séries
				Element serieCourant = (Element) i.next();
				// On affiche le résultat
				/*
				 * System.out.println("Série: " +
				 * serieCourant.getAttributeValue("name") + "   |   " +
				 * "langue: " + serieCourant.getAttributeValue("lang") +
				 * "   |   " + "Emplacement: " +
				 * serieCourant.getAttributeValue("folder"));
				 */
				series.add(serieCourant.getAttributeValue("name"));

			}
			System.out.println(series);
		}
		return series;

	}
}
