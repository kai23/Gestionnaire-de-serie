package view;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

import controlleur.SerieController;

import model.Serie;

public class PanelSerie extends JPanel{
	private static final long serialVersionUID = 1L;

	public PanelSerie(){
		JPanel PanelSerie = new JPanel();
		
		//Recuperation des noms de series
		ArrayList<String> mesSeries = new ArrayList<String>();
		/*SerieController ctrl = new SerieController();
		mesSeries = ctrl.getAllSerieName();*/
		
		//Arraylist temporaire
		mesSeries.add("Malcom");
		mesSeries.add("TBBT");
		mesSeries.add("HIMYM");
		mesSeries.add("The Simpsons");
		
		//Creation et remplissage de la JList
		JList<String> listSerie = new JList<String>();
		for(int i = 0; i<mesSeries.size(); i++){
			listSerie.add(mesSeries.get(i), listSerie);
		}
		
		//Ajout des composants
		PanelSerie.add(listSerie);
		
		
	}
}
