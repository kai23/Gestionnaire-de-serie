package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlleur.SerieController;

public class DeleteSerieFrame extends JFrame{
	
	private JComboBox liste;
	private JButton confirmer;
	
	public DeleteSerieFrame(){
		super("Supprimer une série de la collection");
		setLayout(new BorderLayout());
		Container pane = getContentPane();
		setSize(new Dimension(300, 300));
		setResizable(false);
		setVisible(true);
		
		//Panel centrale
		JPanel centerPanel = new JPanel(new BorderLayout());
		liste = new JComboBox<String>(allSeries());
		confirmer = new JButton("Confirmer");
		centerPanel.add(liste, BorderLayout.CENTER);
		centerPanel.add(confirmer, BorderLayout.LINE_END);
		
		//Panel générale
		pane.add(centerPanel, BorderLayout.CENTER);
		
	}

	private String[] allSeries() {
		SerieController ctrl = new SerieController();
		String[] values = ctrl.getAllSerieName().toArray(new String[ctrl.getAllSerieName().size()]);
		return values;
	}

}
