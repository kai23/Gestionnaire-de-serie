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
		//test du store 
		
		SerieController controller = new SerieController();
		Serie serie = new Serie();
		Season saison1 = new Season(serie, "1");
		Season saison2 = new Season(serie,"2");
		ArrayList<Season> saisons = new ArrayList<>();
		saisons.add(saison1);
		saisons.add(saison2);
		serie.setDescription("blabla");
		serie.setId("120");
		serie.setName("Flo à Miami");
		serie.setSeasons(saisons);
		serie.setFolder("/home/kai");
		if (controller.storeSerieXML(serie)) {
			System.out.println("C'est store !");
		}
		
	}

}
