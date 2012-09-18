package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.TextArea;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMain extends JPanel {
    public PanelMain() {
    	super();
    	setLayout(new BorderLayout());
    	//Recuperer les infos de la série
    	JLabel infoBox = new JLabel("Ici, nous aurons les informations de la série");
    	//JLabel infoBox = new JLabel(serieCtrl.getInfo(););
    	
    	
    	//Ajout des composants
    	add(infoBox, BorderLayout.PAGE_START);
    	add(new JComboBox());
    	
    	
		
	}
}
