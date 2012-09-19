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

	public void updateView() {
		this.wInfoBox.setText(this.serie.getName());
		panelScrollpane.removeAll();
		wSeasons.clear();

		for (Season season : serie.getAllSeason()) {
			JPanel panelSeason = new JPanel(new BorderLayout());
			panelScrollpane.add(panelSeason);
			
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
