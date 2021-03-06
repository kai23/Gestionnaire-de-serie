package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.XMLOutputter;
import org.jdom2.filter.*;

public class Episode {
	/**
	 * Attribute
	 */
	private String name;
	private String uri;
	private String num;
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
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
	Episode(String name, String uri, String num, Season season) {
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

	public String toString() {
		return "" + num + " " + name + " (" + uri + ")";
	}

	public Element store() {
		Element episodeElement = new Element("Episode");
		episodeElement.setAttribute(new Attribute("numberE", num));
		episodeElement.setText(name);

		return episodeElement;
	}
}
