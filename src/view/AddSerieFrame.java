package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.moviejukebox.thetvdb.model.Series;

import controlleur.SerieController;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;

import java.awt.Window.Type;
import java.util.Locale;

class AddSerieFrame extends JFrame {
	private SerieController controller = new SerieController();
	private JTextField textField;
	private String nomSerie;

	public AddSerieFrame() {
		setSize(new Dimension(200, 500));
		setLocale(Locale.FRANCE);
		setTitle("Ajouter une série");
		getContentPane().setLayout(null);
		setVisible(true);
		setSize(500, 200);

		JLabel lblEntrerLeNom = new JLabel("Entrer le nom de la série");
		lblEntrerLeNom.setBounds(10, 25, 182, 14);
		getContentPane().add(lblEntrerLeNom);

		textField = new JTextField();
		textField.setToolTipText("Le nom de la série");
		textField.setBounds(202, 22, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblOu = new JLabel("OU");
		lblOu.setBounds(207, 84, 46, 14);
		getContentPane().add(lblOu);

		JButton btnParcourirUnDossier = new JButton("Parcourir un dossier");
		btnParcourirUnDossier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(new File("."));
				int returnVal = chooser.showDialog(getContentPane(), "");
				chooser.setVisible(true);

			}
		});
		btnParcourirUnDossier.setBounds(148, 127, 152, 23);
		getContentPane().add(btnParcourirUnDossier);

		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Quelques variables
				nomSerie = textField.getText();
				final List<Series> series = controller.trouverSerieTVDB(nomSerie);
				final JDialog boiteDialogue = new JDialog();

				// On crée la boite de dialogue
				boiteDialogue.setContentPane(new Container());
				boiteDialogue.setLayout(new BorderLayout());
				boiteDialogue.setVisible(true);
				boiteDialogue.setSize(new Dimension(200, 300));
				
				// Buton ok
				JButton bOk = new JButton("OK");
				
				// Notre JTable
				@SuppressWarnings("serial")
				final JTable maTable = new JTable(new DefaultTableModel(0, 1) {
					public boolean isCellEditable(int iRowIndex,
							int iColumnIndex) {
						return false;
					}
				});
				
				// On prépare la JTable
				DefaultTableModel model = (DefaultTableModel) maTable.getModel();
				for (Series series2 : series) {
					model.addRow(new String[] { series2.getSeriesName() });
				}
				
				// On prépare la boite de dialogue
				boiteDialogue.add(maTable, BorderLayout.NORTH);
				boiteDialogue.add(bOk, BorderLayout.SOUTH);
				
				// On affiche le tout
				maTable.setVisible(true);
				bOk.setVisible(true);
				
				
				bOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int choisi = maTable.getSelectedRow();
						System.out.println(choisi);
						Series serie = series.get(choisi);
						boiteDialogue.dispose();
						
					}
				});
				
			dispose();	
			}
		});
		btnGo.setBounds(300, 21, 58, 23);
		getContentPane().add(btnGo);
				
	}

	public String getNomSerie() {
		return nomSerie;
	}

	public void setNomSerie(String nomSerie) {
		this.nomSerie = nomSerie;
	}
}
