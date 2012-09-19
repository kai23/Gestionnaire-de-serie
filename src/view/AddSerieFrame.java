package view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.moviejukebox.thetvdb.model.Series;

import controlleur.SerieController;
import javax.swing.JSeparator;

class AddSerieFrame extends JFrame {
	private SerieController controller = new SerieController();
	private JTextField textField;
	
	
	public AddSerieFrame(){
		getContentPane().setLayout(null);
		setVisible(true);
		setSize(500, 200);
		
		
		JLabel lblEntrerLeNom = new JLabel("Entrer le nom de la série");
		lblEntrerLeNom.setBounds(61, 25, 131, 14);
		getContentPane().add(lblEntrerLeNom);
		
		textField = new JTextField();
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
		
	}
}
