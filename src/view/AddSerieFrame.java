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
	
	public AddSerieFrame(){
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
				int returnVal = chooser.showDialog(getContentPane(),"");
				chooser.setVisible(true);
				
				
				
			}
		});
		btnParcourirUnDossier.setBounds(148, 127, 152, 23);
		getContentPane().add(btnParcourirUnDossier);
		
		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				nomSerie = textField.getText();
				List<Series> series = controller.trouverSerieTVDB(nomSerie);
				JDialog boiteDialogue = new JDialog();
				
				
				boiteDialogue.setVisible(true);
				boiteDialogue.setSize(new Dimension(200,300));
				JTable maTable = new JTable(new DefaultTableModel(series.size(), 1));
				DefaultTableModel model = (DefaultTableModel) maTable.getModel();
				for (Series series2 : series) {
					model.addRow(new String[]{series2.getSeriesName()});
				}
				boiteDialogue.add(maTable);
				maTable.setVisible(true);
				
				
				
				
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
