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
import java.util.ArrayList;

public class Season {
	/**
	 * Attribute
	 */
	private int num;
	private Serie serie;
	private ArrayList<Episode> episodes;

	/**
	 * Setter/Getter
	 */
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	/**
	 * Constructor
	 */
	Season(Serie serie, int num) {
		this.serie = serie;
		this.num = num;
		this.episodes = new ArrayList<Episode>();
	}

	public Season() {
		this.episodes = new ArrayList<Episode>();
	}

	/**
	 * Methods
	 */
	public Episode addEpisode(String name, String uri, int num) {
		if (getEpisode(num) != null)
			return null;
		Episode episode = new Episode(name, uri, num, this);
		episodes.add(episode);

		return episode;
	}

	public Episode getEpisode(int num) {
		for (Episode episode : episodes) {
			if (episode.getNum() == num)
				return episode;
		}

		return null;
	}

	public void deleteEpisode(Episode episode) {
		episodes.remove(episode);
	}

	public String toString() {
		String ret = "Saison " + num + "\n";
		for (Episode episode : episodes) {
			ret = ret + "\t\t" + episode + "\n";
		}
		return ret;
	}

	public ArrayList<String> getAllEpisodes() {
		ArrayList<String> episodes = new ArrayList<>();
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
							"BaseDeDonneeSerie.xml"));
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
						/*System.out.println("Episode "
								+ episodeCourant.getAttributeValue("numberE")
								+ ": " + episodeCourant.getValue());*/
						episodes.add(episodeCourant.getAttributeValue("numberE"));
					}
				}
				
			}
			System.out.println(episodes);
			return episodes;
		}
	}

}
