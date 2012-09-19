package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import controlleur.SerieController;

import model.Episode;
import model.Season;
import model.Serie;

public class PanelMain extends JPanel implements ActionListener {
	
	/* ATTRIBUTS */
	//Widgets
	private JLabel wInfoBox = new JLabel("Les Mutants");
	public class SeasonField {
		public SeasonField(JRadioButton title, JList episodes) {
			this.title = title;
			this.episodes = episodes;
		}
		public JRadioButton title;
		public JList episodes;
	}
	private Serie serie;
	private ArrayList<SeasonField> wSeasons;
	private JPanel panelScrollpane;
	private SerieController ctrl;

    public PanelMain(SerieController ctrl) {
    	super();
    	this.ctrl = ctrl;
    	setLayout(new BorderLayout());
    	setSerie(getSerie());
		wSeasons = new ArrayList<SeasonField>();

    	//Creation des scrollbar
		panelScrollpane = new JPanel();
		JScrollPane scrollpane = new JScrollPane(panelScrollpane);

		panelScrollpane.setLayout(new BoxLayout(panelScrollpane, BoxLayout.PAGE_AXIS));
				
		/*for (int i = 1; i <= serie.getAllSeason().size(); i++) {
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
			wSeasons.add(new SeasonField(season, listEpisodes));
		}
    	*/
    	//Ajout des composants
    	add(wInfoBox, BorderLayout.PAGE_START);
    	add(scrollpane, BorderLayout.CENTER);
	}

    /* GETTEURS ET SETTEURS */
	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	
	public void updateView(){
		
		this.wInfoBox.setText(this.serie.getName());
		panelScrollpane.removeAll();
		JPanel panelSeason = new JPanel(new BorderLayout());
		panelScrollpane.add(panelSeason);

		for (Season season : serie.getAllSeason()) {
			
			JRadioButton seasonLabel = new JRadioButton("Saison " + season.getNum());
			seasonLabel.setIcon(new Icon() {
					public void paintIcon(Component c, Graphics g, int x, int y) {}
					public int getIconWidth() { return 0; }
					public int getIconHeight() { return 0; }
				});
			seasonLabel.addActionListener(this);
		
			panelSeason.add(seasonLabel, BorderLayout.PAGE_START);
	
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			for (Episode episode : season.getAllEpisodes()) {
				listModel.addElement(episode.getName());
		}
		JList listEpisodes = new JList(listModel);

		panelSeason.add(listEpisodes, BorderLayout.CENTER);
		wSeasons.add(new SeasonField(seasonLabel, listEpisodes));
	}
	
		this.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		for (SeasonField season : wSeasons) {
			if (e.getSource() == season.title)
				season.episodes.setVisible(!season.episodes.isVisible());
		}
	}

	public void showInfoSerie(String serieName) {
		JOptionPane.showMessageDialog(null, serieName, "test", JOptionPane.INFORMATION_MESSAGE);		
	}


	public void transferInfo(String nameSerie) {
		setSerie(ctrl.getSerieByName(nameSerie));
	}
	
}
