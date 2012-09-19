package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
public class Episode {
	/**
	 * Attribute
	 */
	private String name;
	private String uri;
	private int num;
	private boolean isWatched;
	private Season season;

	/**
	 * Setter/Getter
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isWatched() {
		return isWatched;
	}

	public void setWatched(boolean isWatched) {
		this.isWatched = isWatched;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Constructor
	 */
	Episode(String name, String uri, int num, Season season) {
		this.name = name;
		this.uri = uri;
		this.num = num;
		this.isWatched = false;
		this.season = season;
	}
	
	public Episode() {
	}

	/**
	 * Methods
	 */
	public ArrayList<Episode> getAllEpisodes() {
		ArrayList<Episode> episodes = new ArrayList<>();
		{
			Document document = null;
			Element racine;

			SAXBuilder sxb = new SAXBuilder();
			try {
				// On crée un nouveau document JDOM avec en argument le fichier
				// XML
				// Le parsing est terminé ;)
				try {
					document = sxb.build(new File(
							"/home/florian/Bureau/BaseDeDonneeSerie.xml"));
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
					// On crée une List contenant tous les noeuds "Episodes" de
					// l'Element
					// SaisonCourant
					List listageEpisode = saisonCourant.getChildren("Episode");
					// On crée un Iterator sur notre liste d'épisodes
					Iterator e = listageEpisode.iterator();
					while (e.hasNext()) {
						// On parcourt toutes les épisodes
						Element episodeCourant = (Element) e.next();
						// On affiche le résultat
						System.out.println("Episode "
								+ episodeCourant.getAttributeValue("numberE")
								+ ": " + episodeCourant.getValue());
					}
				}
			}
			return episodes;
		}
	}
}
