package view;

import javax.swing.JButton;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ScrollPaneLayout;

public class PanelMain extends JPanel {
    public PanelMain() {
    	super();
    	setLayout(new BorderLayout());
    	
    	//Recuperer les infos de la serie
    	JLabel infoBox = new JLabel("Ici, nous aurons les informations de la serie");
    	//JLabel infoBox = new JLabel(serieCtrl.getInfo(););
    	    	
    	
    	//Creation panel saisons
    	//JPanel panelSeasons = new JPanel(new ScrollPaneLayout());
    	//panelSeasons.setBackground(Color.cyan);
    	//Creation des scrollbar
		JPanel panelScrollpane = new JPanel();
		panelScrollpane.setBackground(Color.pink);
		
		JScrollPane scrollpane = new JScrollPane(panelScrollpane);
		scrollpane.setBackground(Color.cyan);

		panelScrollpane.setLayout(new BoxLayout(panelScrollpane, BoxLayout.PAGE_AXIS));
		panelScrollpane.add(new JButton("Saison 1"));
		panelScrollpane.add(new JButton("Saison 2"));
		panelScrollpane.add(new JButton("Saison 3"));				
		
    	
    	//Ajout des composants
    	add(infoBox, BorderLayout.PAGE_START);
    	add(scrollpane, BorderLayout.CENTER);
	}
}
