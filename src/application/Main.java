package application;

import java.util.ArrayList;

import controlleur.SerieController;
import view.*;
import model.Serie;
import model.Episode;
import model.Season;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Window window = new Window();
		
	
	Serie serie = new Serie();
	Episode episode = new Episode();
	Season season = new Season();
	
	serie.getAllSeries();
	season.getAllSeason();
	episode.getAllEpisodes();
	}

}
