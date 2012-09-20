package model;

import java.io.*;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.XMLOutputter;
import org.jdom2.filter.*;

import java.text.Format;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Serie
{
	/**
	 * Attribute
	 */
	private String id;
	private String name;
	private ArrayList<Season> seasons;
	private Episode currentEpisode;
	private String description;
	private String folder;

	// Pour le XML
	org.jdom2.Document document;
	Element racine;
	String xml = "BaseDeDonneeSerie.xml";

	public String getFolder()
	{
		return folder;
	}

	public void setFolder(String folder)
	{
		this.folder = folder;
	}

	/**
	 * Setter/Getter
	 */
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Constructor
	 */

	public Serie()
	{
		this.seasons = new ArrayList<Season>();
	}

	public Serie(String id, String name)
	{
		this.id = id;
		this.name = name;
		this.seasons = new ArrayList<Season>();
	}

	public Serie(String id, String name, String description)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.seasons = new ArrayList<Season>();
	}

	public Serie(String id, String name, ArrayList<Season> seasons, Episode currentEpisode, String description)
	{
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
	public void addSeason(Season season)
	{
		seasons.add(season);
	}

	public Season addSeason(String num)
	{
		if (getSeason(num) != null) return null;

		Season season = new Season(this, num);
		seasons.add(season);

		return season;
	}

	public Season getSeason(String num)
	{
		for (Season season : seasons) {
			if (season.getNum() == num) return season;
		}

		return null;
	}

	public void deleteSeason(Season season)
	{
		seasons.remove(season);
	}

	public ArrayList<Season> getAllSeason()
	{
		return seasons;
	}

	public String toString()
	{
		String ret = name + " " + id + "\n";
		for (Season season : seasons) {
			ret = ret + "\t" + season + "\n";
		}
		return ret;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////:

	/**
	 * Fonction permettant de lire un fichier XML
	 * 
	 * @param fichier
	 * @throws Exception
	 */
	void lireFichier(String fichier) throws Exception
	{
		SAXBuilder sxb = new SAXBuilder();
		document = sxb.build(new File(fichier));
		racine = document.getRootElement();
	}

	/**
	 * Fonction permettant d'enregistrer un fichier XML
	 * 
	 * @return true ou false, si ça a réussi ou pas
	 */
	public Element store()
	{
		Element serie = new Element("Serie");

		serie.setAttribute(new Attribute("name", this.getName()));
		serie.setAttribute(new Attribute("id", this.id));
		serie.setAttribute(new Attribute("lang", "fr"));
		if (this.folder != null)
			serie.setAttribute(new Attribute("folder", this.folder));

		for (Season season : this.seasons) {
			serie.addContent(season.store());
		}

		return serie;
	}

	public ArrayList<Season> getSeasons()
	{
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons)
	{
		this.seasons = seasons;
	}

	public Episode getCurrentEpisode()
	{
		return currentEpisode;
	}

	public void setCurrentEpisode(Episode currentEpisode)
	{
		this.currentEpisode = currentEpisode;
	}

	public static int getLastId()
	{
		// TODO Faire une fonction qui retourne le dernier ID de série
		return 0;
	}

	// On enregsitre notre nouvelle arborescence dans le fichier
	// d'origine dans un format classique.
	public void enregistreFichier(String fichier) throws Exception
	{
		XMLOutputter sortie = new XMLOutputter(org.jdom2.output.Format.getPrettyFormat());
		sortie.output(this.document, new FileOutputStream(fichier));
	}
}
