package model;

import java.util.ArrayList;

public class Season {
	private int num;
	private Serie serie;
	private ArrayList<Episode> episodes;

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
	public ArrayList<Episode> getEpisodes() {
		return episodes;
	}
	public void setEpisodes(ArrayList<Episode> episodes) {
		this.episodes = episodes;
	}
}
