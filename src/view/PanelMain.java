package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ScrollPaneLayout;

public class PanelMain extends JPanel {
    public PanelMain() {
    	super();
    	setLayout(new BorderLayout());
    	//Recuperer les infos de la série
    	JLabel infoBox = new JLabel("Ici, nous aurons les informations de la série");
    	//JLabel infoBox = new JLabel(serieCtrl.getInfo(););
    	
    	
    	//Creation panel saisons
    	JPanel panelSeasons = new JPanel();
    	panelSeasons.setLayout(new ScrollPaneLayout());
    	panelSeasons.setBackground(Color.cyan);
    	
    	//Ajout des composants
    	add(infoBox, BorderLayout.PAGE_START);
    	add(panelSeasons, BorderLayout.CENTER);
    	
    	
    	
    	
		
	}
}
