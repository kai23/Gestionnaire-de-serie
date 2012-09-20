package model;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.XMLOutputter;
import org.jdom2.filter.*;

import java.util.Iterator;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class MaCollection {
	//Attributs
	private ArrayList<Serie> series;
	
	/**
	 * Constructeur par défaut
	 */
	public MaCollection() {
		series = new ArrayList<Serie>();
	}

	/**
	 * Getter of series
	 * @return ArrayList
	 */
	public ArrayList<Serie> getSeries() {
		return series;
	}

	/**
	 * Setter of series
	 * @param series
	 */
	public void setSeries(ArrayList<Serie> series) {
		this.series = series;
	}
	
	/**
	 * Fonction permettant de recuperer une série à partir de son identifiant
	 * @param id
	 * @return serie || null
	 */
	public Serie getSerieById(String id){
		for(int i = 0; i <series.size(); i++){
			if(series.get(i).getId().equals(id))
				return series.get(i);
		}
		return null;
	}
	
	/**
	 * Fonction permettant de recuperer une série à partir de son titre
	 * @param name
	 * @return serie || null
	 */
	public Serie getSerieByName(String name){
		for(int i = 0; i <series.size(); i++){
			if(series.get(i).getName().equals(name))
				return series.get(i);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public void loadSeries(String file) {
		Document document = null;

		SAXBuilder sxb = new SAXBuilder();
		try {
			// On crée un nouveau document JDOM avec en argument le fichier
			// XML
			// Le parsing est terminé ;)
			try {
				document = sxb.build(new File(file));
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Ne trouve pas le fichier !");
			}
		}
		catch (JDOMException e) {
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

			Serie serie = new Serie(serieCourant.getAttributeValue("id"), serieCourant.getAttributeValue("name"));

			List seasonNodes = serieCourant.getChildren("Saison");
			for (Object seasonObject : seasonNodes) {
				Element seasonNode = (Element) seasonObject;

				Season season = new Season(serie, seasonNode.getAttributeValue("numberS"));

				List episodeNodes = seasonNode.getChildren("Episode");
				for (Object episodeObject : episodeNodes) {
					Element episodeNode = (Element) episodeObject;

					season.addEpisode(episodeNode.getValue(), "/", episodeNode.getAttributeValue("numberE"));

				}
				serie.addSeason(season);
			}
			series.add(serie);
			System.out.println(serie);
			// series.add(serieCourant.getAttributeValue("name"));

		}
	}

	public void storeSeries(String url) {
				// On crée l'élement Série
		try {
			SAXBuilder sxb = new SAXBuilder();
			//Document document = sxb.build(new File(url));
			Element racine = new Element("BaseDeDonneeSerie");
			Document document = new Document(racine);

			for (Serie serie : this.series) {
				racine.addContent(serie.store());
			}

			System.out.println(racine);

			XMLOutputter sortie = new XMLOutputter(org.jdom2.output.Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(url));
		} catch (Exception e) {
			System.out.println("Impossible d'ouvrir le fichier : " + e.getMessage());
		}
	}
}
