package model;

import java.util.ArrayList;

public class Serie {
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

	/**
	 * Methods
	 */
	public boolean addSeason(Season season) {
		if (getSeason(season.getNum()) == null)
			return seasons.add(season);
		else
			return false;
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
		seasons.remove(season);
	}
}
