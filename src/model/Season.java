package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;

public class Season {
	/**
	 * Attribute
	 */
	private String num;
	private Serie serie;
	private ArrayList<Episode> episodes;

	/**
	 * Setter/Getter
	 */
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
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
	public Season(Serie serie, String num) {
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
	public Episode addEpisode(String name, String uri, String num) {
		if (getEpisode(num) != null)
			return null;
		Episode episode = new Episode(name, uri, num, this);
		episodes.add(episode);

		return episode;
	}

	public Episode getEpisode(String num) {
		for (Episode episode : episodes) {
			if (episode.getNum().equals(num))
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

	public ArrayList<Episode> getAllEpisodes() {
		return episodes;
	}

}
