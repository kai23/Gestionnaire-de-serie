package model;

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
	public Season(Serie serie, int num) {
		this.serie = serie;
		this.num = num;
	}

	/**
	 * Methods
	 */
	public Episode addEpisode(int num) {
		if (getEpisode(num) != null)
			return null;
		
		episode = new Episode(serie, this, num);
		episodes.add(episode);
		
		return episode;
	}
	public Episode getEpisode(int num) {
		Iterator<Episode> it = episodes.iterator();
		while (it.hasNext()) {
			Episode episode = it.next();
			if (episode.getNum() == num)
				return episode;
		}
		return null;
	}
	public void deleteEpisode(Episode episode) {
		episode.setSeason(null);
		episodes.remove(episode);
	}

}
