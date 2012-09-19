package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlleur.SerieController;

public class PanelSerie extends JPanel implements ListSelectionListener{
	private static final long serialVersionUID = 1L;
	private PanelMain infoSerie;
	private DefaultListModel<String> listModel;

	public PanelSerie(PanelMain infoSerie){
		super();
		this.infoSerie = infoSerie;
		setLayout(new BorderLayout());

		//Recuperation des noms de series
		SerieController ctrl = new SerieController();
		ArrayList<String> mesSeries = ctrl.getAllSerieName();
			
		//Passage par un defaultListModel
		listModel = new DefaultListModel<>();
		JList listSerie = new JList(listModel);
		for(String maSerie : mesSeries) {
			addSerie(maSerie);
		}
		
		//Creation et remplissage de la JList
		listSerie.addListSelectionListener(this);
		
		//Ajout des components
		add(listSerie);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//Recuperer le nom de la serie choisie
		String nameSerie = (String) (((JList) e.getSource()).getSelectedValue());

		infoSerie.transferInfo(nameSerie);
		infoSerie.updateView();
		
	}

	private void addSerie(String name) {
		listModel.addElement(name);
	}
}
