package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelSerie extends JPanel implements ListSelectionListener{
	private static final long serialVersionUID = 1L;

	public PanelSerie(){
		super();
		setLayout(new BorderLayout());

		//Recuperation des noms de series
		ArrayList<String> mesSeries = new ArrayList<String>();
		/*SerieController ctrl = new SerieController();
		mesSeries = ctrl.getAllSerieName();*/
		
		//Arraylist temporaire
		mesSeries.add("Malcom");
		mesSeries.add("TBBT");
		mesSeries.add("HIMYM");
		mesSeries.add("The Simpsons");
		
			
		//Passage par un defaultListModel
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(int i = 0; i <mesSeries.size(); i++){
			listModel.addElement(mesSeries.get(i));
		}
		
		//Creation et remplissage de la JList
		JList listSerie = new JList(listModel);
		listSerie.addListSelectionListener(this);
		
		//Ajout des components
		add(listSerie);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String nameSerie = (String) (((JList) e.getSource()).getSelectedValue());
		JOptionPane.showMessageDialog(this, nameSerie, "LISTENER", JOptionPane.WARNING_MESSAGE);
		
	}
}
