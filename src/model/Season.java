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

	public ArrayList<Episode> getAllEpisodes() {
		return episodes;
	}

}
