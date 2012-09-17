package model;

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
	public Serie(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Serie(int id, String name, String description) {
		Serie(id, name);
		this.description = description;
	}

	/**
	 * Methods
	 */
	public Season addSeason(int num) {
		if (getSeason(num) != null)
			return null;
		
		season = new Season(this, num);
		seasons.add(season);
		
		return season;
	}
	public Season getSeason(int num) {
		Iterator<Season> it = seasons.iterator();
		while (it.hasNext()) {
			Season season = it.next();
			if (season.getNum() == num)
				return season;
		}
		return null;
	}
	public void deleteSeason(Season season) {
		season.setSerie(null);
		seasons.remove(season);
	}
}
