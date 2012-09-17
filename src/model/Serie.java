package model;

import java.util.ArrayList;

public class Serie {
	private int id;
	private String name;
	private ArrayList<Season> seasons;
	private Episode currentEpisode;
	private String description;
	
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
	
	public Serie(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
}
