package model;

import java.util.ArrayList;

public class Serie {
	public Serie(int id, String name = "") {
		this.id = id;
		this.name = name;
	}
	
	private int id;
	private String name;
	private ArrayList<Season> seasons;
	private Episode currentEpisode;
	private String description;
}
