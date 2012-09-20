package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import controlleur.SerieController;

import model.Episode;
import model.Season;
import model.Serie;

public class PanelMain extends JPanel implements ActionListener {

	/* ATTRIBUTS */
	// Widgets
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
	 * 
	 * @param ctrl
	 */
	public PanelMain(SerieController ctrl) {
		super();
		// Initialisation du controlleur
		this.ctrl = ctrl;
		// Initialisation de la série
		setSerie(getSerie());

		/***** AFFICHAGE *****/
		setLayout(new BorderLayout());
		wSeasons = new ArrayList<SeasonField>();
		panelScrollpane = new JPanel(); // Creation de la scrollbar
		JScrollPane scrollpane = new JScrollPane(panelScrollpane);
		panelScrollpane.setLayout(new BoxLayout(panelScrollpane,
				BoxLayout.PAGE_AXIS));
		add(wInfoBox, BorderLayout.PAGE_START);
		add(scrollpane, BorderLayout.CENTER);
	}

	/* GETTEURS ET SETTEURS */
	/**
	 * Serie getter
	 * 
	 * @return serie
	 */
	public Serie getSerie() {
		return serie;
	}

	/**
	 * Serie setter
	 * 
	 * @param serie
	 */
	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	/**
	 * Update of the panel when change
	 */
	public void updateView() {
		if (serie == null) {
			JLabel errImg = new JLabel(new ImageIcon("./src/images/erreur.png")); 
			JLabel errText = new JLabel("Cette serie n'existe pas dans votre collection");
			JPanel panelErreur = new JPanel();
			panelErreur.add(errImg);
			panelErreur.add(errText);
			panelErreur.setBounds(30, 30, 100, 200);
			panelScrollpane.add(panelErreur, BorderLayout.CENTER);
			panelErreur.setBounds(30, 30, 100, 200);
		} else {
			this.wInfoBox.setText(this.serie.getName());
			panelScrollpane.removeAll();
			wSeasons.clear();

			for (Season season : serie.getAllSeason()) {
				JPanel panelSeason = new JPanel(new BorderLayout());
				panelScrollpane.add(panelSeason);
				JRadioButton seasonLabel = new JRadioButton("Saison "
						+ season.getNum());
				seasonLabel.setIcon(new Icon() {
					public void paintIcon(Component c, Graphics g, int x, int y) {
					}

					public int getIconWidth() {
						return 0;
					}

					public int getIconHeight() {
						return 0;
					}
				});
				seasonLabel.addActionListener(this);
				panelSeason.add(seasonLabel, BorderLayout.PAGE_START);

				// Recuperation des episodes
				/*
				 * DefaultListModel<String> listModel = new
				 * DefaultListModel<String>(); for (Episode episode :
				 * season.getAllEpisodes()) {
				 * listModel.addElement(episode.getName()); } JList listEpisodes
				 * = new JList(listModel);
				 */

				// Composition de la table
				String[] columnNames = { "N°", "Title", "Length", "Seen" };
				Object[][] data = new Object[season.getAllEpisodes().size()][4];
				// Pour chaque episode
				int i = 0;
				for (Episode episode : season.getAllEpisodes()) {
					data[i][0] = episode.getNum();
					data[i][1] = episode.getName();
					System.out.println((String) data[i][1]);
					// data[i][2] = episodes.getLength();
					if (episode.isWatched())
						data[i][3] = "Watched";
					else {

					}
					i++;
				}
				JTable table = new JTable(data, columnNames);
				TableColumn col0 = table.getColumnModel().getColumn(0);
				col0.setPreferredWidth((int) (panelScrollpane.getWidth() * 0.05));
				TableColumn col1 = table.getColumnModel().getColumn(1);
				col1.setPreferredWidth((int) (panelScrollpane.getWidth() * 0.45));
				TableColumn col2 = table.getColumnModel().getColumn(2);
				col2.setPreferredWidth((int) (panelScrollpane.getWidth() * 0.35));
				TableColumn col3 = table.getColumnModel().getColumn(3);
				col3.setPreferredWidth((int) (panelScrollpane.getWidth() * 0.05));
				col3.setCellRenderer(new ButtonRenderer());
				panelSeason.add(table);
				wSeasons.add(new SeasonField(seasonLabel, table));
			}
		}
		this.updateUI();
	}

	/**
	 * Listener
	 * 
	 * @param : actionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		for (SeasonField season : wSeasons) {
			if (e.getSource() == season.title) {
				season.episodes.setVisible(!season.episodes.isVisible());
			}
		}
	}

	/**
	 * Function allowing the transfer of informations from one panel to the
	 * other
	 * 
	 * @param nameSerie
	 */
	public void transferInfo(String nameSerie) {
		setSerie(ctrl.getSerieByName(nameSerie));
	}

}
