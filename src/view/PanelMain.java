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
import javax.swing.JTable;

import controlleur.SerieController;

import model.Episode;
import model.Season;
import model.Serie;

public class PanelMain extends JPanel implements ActionListener {
	
	/* ATTRIBUTS */
	//Widgets
	private JLabel wInfoBox = new JLabel();
	private ArrayList<SeasonField> wSeasons;
	private JPanel panelScrollpane;
	public class SeasonField {
		public JRadioButton title;
		public JTable episodes;
		public SeasonField(JRadioButton title, JTable episodes) {
			this.title = title;
			this.episodes = episodes;
		}
	}
	
	private Serie serie;
	private SerieController ctrl;

	/**
	 * Constructor which creates the panel of seasons and episodes
	 * @param ctrl
	 */
    public PanelMain(SerieController ctrl) {
    	super();
    	//Initialisation du controlleur
    	this.ctrl = ctrl;
    	//Initialisation de la série
    	setSerie(getSerie());
    	
    	/***** AFFICHAGE *****/
    	setLayout(new BorderLayout());
		wSeasons = new ArrayList<SeasonField>();
		panelScrollpane = new JPanel(); //Creation de la scrollbar
		JScrollPane scrollpane = new JScrollPane(panelScrollpane);
		panelScrollpane.setLayout(new BoxLayout(panelScrollpane, BoxLayout.PAGE_AXIS));		
    	add(wInfoBox, BorderLayout.PAGE_START);
    	add(scrollpane, BorderLayout.CENTER);
	}

    /* GETTEURS ET SETTEURS */
    /**
     * Serie getter
     * @return serie
     */
	public Serie getSerie() {
		return serie;
	}
	/**
	 * Serie setter
	 * @param serie
	 */
	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	/**
	 * Update of the panel when change
	 */
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
	
			//Recuperation des episodes
			/*DefaultListModel<String> listModel = new DefaultListModel<String>();
			for (Episode episode : season.getAllEpisodes()) {
				listModel.addElement(episode.getName());
			}
			JList listEpisodes = new JList(listModel);*/
			
			//Composition de la table
			String[] columnNames = {"N°", "Title", "Length", "Seen"};
			Object[][] data = new Object[season.getAllEpisodes().size()][2];
			//Pour chaque episode
			int i = 0; 
			for(Episode episode : season.getAllEpisodes()){
				data[i][0] = episode.getNum();
				data[i][1] = episode.getName();
			//	data[i][2] = episodes.getLength();
			//	data[i][3] = episodes.isWatched();				
				i++;
			}
			wSeasons.add(new SeasonField(seasonLabel, new JTable(data, columnNames)));
		}
	
		this.updateUI();
	}
	
	/**
	 * Listener
	 * @param : actionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		for (SeasonField season : wSeasons) {
			if (e.getSource() == season.title){
				season.episodes.setVisible(!season.episodes.isVisible());
				System.out.println(season.episodes);
			}
		}
	}

	
	/**
	 * Function allowing the transfer of informations from one panel to the other
	 * @param nameSerie
	 */
	public void transferInfo(String nameSerie) {
		setSerie(ctrl.getSerieByName(nameSerie));
	}
	
}
