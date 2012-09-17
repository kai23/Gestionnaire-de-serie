package model;

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
	public Episode(String name, String uri, int num, Season season, Serie serie) {
		this.name = name;
		this.uri = uri;
		this.num = num;
		this.isWatched = false;
		this.season = season;
		this.serie = serie;
	}
	
	/**
	 * Methods
	 */
}
