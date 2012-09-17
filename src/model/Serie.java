package model;

import java.util.ArrayList;





public class Serie {
	
	private int id;
	private String name;
	private ArrayList<Season> seasons;
	private Episode currentEpisode;
	private String description;
	
	
	public Serie(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
}
