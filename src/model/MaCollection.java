package model;

import java.util.ArrayList;

public class MaCollection {
	//Attributs
	private ArrayList<Serie> series;
	
	/**
	 * Constructeur par défaut
	 */
	public MaCollection() {
		series = loadSeries();
	}

	/**
	 * Getter of series
	 * @return ArrayList
	 */
	public ArrayList<Serie> getSeries() {
		return series;
	}

	/**
	 * Setter of series
	 * @param series
	 */
	public void setSeries(ArrayList<Serie> series) {
		this.series = series;
	}
	
	/**
	 * Fonction permettant de recuperer une série à partir de son identifiant
	 * @param id
	 * @return serie || null
	 */
	public Serie getSerieById(String id){
		for(int i = 0; i <series.size(); i++){
			if(series.get(i).getId().equals(id))
				return series.get(i);
		}
		return null;
	}
	
	/**
	 * Fonction permettant de recuperer une série à partir de son titre
	 * @param name
	 * @return serie || null
	 */
	public Serie getSerieByName(String name){
		for(int i = 0; i <series.size(); i++){
			if(series.get(i).getName().equals(name))
				return series.get(i);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Serie> loadSeries() {
		int i = 0;
		series.get(i).
		return null;
	}
	
	
}
