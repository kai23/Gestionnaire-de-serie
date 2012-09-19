package view;

import javax.swing.JOptionPane;

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
	public class SeasonField {
		public SeasonField(JRadioButton title, JList episodes) {
			this.title = title;
			this.episodes = episodes;
		}
		public JRadioButton title;
		public JList episodes;
	}

	private ArrayList<SeasonField> seasons;
	//private JList listEpisodes;

    public PanelMain() {
    	super();
    	setLayout(new BorderLayout());

		seasons = new ArrayList<SeasonField>();

    	//Recuperer les infos de la serie
		//JLabel infoBox = new JLabel(serieCtrl.getInfo(););
		JLabel infoBox = new JLabel("Les Mutants");

    	//Creation des scrollbar
		JPanel panelScrollpane = new JPanel();
		JScrollPane scrollpane = new JScrollPane(panelScrollpane);

		panelScrollpane.setLayout(new BoxLayout(panelScrollpane, BoxLayout.PAGE_AXIS));
		for (int i = 1; i <= 100; i++) {
			JPanel panelSeason = new JPanel(new BorderLayout());
			panelScrollpane.add(panelSeason);

			JRadioButton season = new JRadioButton("Saison " + i);
			season.setIcon(new Icon() {
					public void paintIcon(Component c, Graphics g, int x, int y) {}
					public int getIconWidth() { return 0; }
					public int getIconHeight() { return 0; }
				});
			season.addActionListener(this);
    	
			panelSeason.add(season, BorderLayout.PAGE_START);

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
			seasons.add(new SeasonField(season, listEpisodes));
		}
    	
    	//Ajout des composants
    	add(infoBox, BorderLayout.PAGE_START);
    	add(scrollpane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		for (SeasonField season : seasons) {
			if (e.getSource() == season.title)
				season.episodes.setVisible(!season.episodes.isVisible());
		}
	}

	public void showInfoSerie(String serieName) {
		JOptionPane.showMessageDialog(null, serieName, "test", JOptionPane.INFORMATION_MESSAGE);		
	}
}
