package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
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
	Episode(String name, String uri, int num, Season season) {
		this.name = name;
		this.uri = uri;
		this.num = num;
		this.isWatched = false;
		this.season = season;
	}
	
	public Episode() {
	}

	/**
	 * Methods
	 */
}
