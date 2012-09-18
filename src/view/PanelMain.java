package view;

import java.awt.event.ActionEvent;

import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.util.ArrayList;

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

public class PanelMain extends JPanel implements ActionListener {
	private JRadioButton infoBox;
	
    public PanelMain() {
    	super();
    	setLayout(new BorderLayout());
    	
    	//Recuperer les infos de la serie
		infoBox = new JRadioButton("<h1>Les Mutants</h1>");
		infoBox.setIcon(new Icon() {
				public void paintIcon(Component c, Graphics g, int x, int y) {}
				public int getIconWidth() { return 0; }
				public int getIconHeight() { return 0; }
			});
    	//JLabel infoBox = new JLabel(serieCtrl.getInfo(););

		infoBox.addActionListener(this);
    	
    	//Creation des scrollbar
		JPanel panelScrollpane = new JPanel();
		JScrollPane scrollpane = new JScrollPane(panelScrollpane);

		panelScrollpane.setLayout(new BoxLayout(panelScrollpane, BoxLayout.PAGE_AXIS));
		for (int i = 1; i <= 100; i++) {
			JPanel panelSeason = new JPanel(new BorderLayout());
			panelScrollpane.add(panelSeason);

			JLabel anonyme = new JLabel("Saison "+i);
			panelSeason.add(anonyme, BorderLayout.PAGE_START);

			ArrayList<String> episodes = new ArrayList<String>();
			episodes.add("1 - Les mutants juniors");
			episodes.add("2 - Le retours des mutants juniors");
			episodes.add("3 - La fin des mutants juniors");
			episodes.add("4 - Les mutants séniors");
			episodes.add("5 - Le retours des mutants séniors");
			episodes.add("6 - La fin des mutants séniors");

			DefaultListModel<String> listModel = new DefaultListModel<String>();
			for (String episode : episodes) {
				listModel.addElement(episode);
			}
			JList listEpisodes = new JList(listModel);

			panelSeason.add(listEpisodes, BorderLayout.CENTER);
		}
    	
    	//Ajout des composants
    	add(infoBox, BorderLayout.PAGE_START);
    	add(scrollpane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoBox) {
			
		}
	}
}
