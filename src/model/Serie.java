package model;

import java.util.Iterator;

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
	public boolean addSeason(Season season)
		throws SeasonExistsException {
		if (getSeason(season.getNum()) != null)
			throw new SeasonExistsException(season.getNum());
		return seasons.add(season);
			
	}
	public Season getSeason(int num)
	throws SeasonNotExistsException {
		Iterator<Season> it = seasons.iterator();
		while (it.hasNext()) {
			Season season = it.next();
			if (season.getNum() == num)
				return season;
		}
		throw new SeasonNotExistsException(num);
	}
	public void deleteSeason(Season season) {
		seasons.remove(season);
	}
}
