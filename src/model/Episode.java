package model;

public class Episode {
	private String name;
	private String uri;
	private int num;
	private boolean isWatched;
	private Season season;
	private Serie serie;
	
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
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	
	public Episode(String name, String uri, int num, boolean isWatched,
			Season season, Serie serie) {
		super();
		this.name = name;
		this.uri = uri;
		this.num = num;
		this.isWatched = isWatched;
		this.season = season;
		this.serie = serie;
	}
	
	
}
