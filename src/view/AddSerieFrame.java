package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class AddSerieFrame extends JFrame implements ActionListener {
	
	private JTextField addedSerie; 
	private JButton ajoutButton, ouvrirButton, enregistrerButton;

	public AddSerieFrame(){
		super("Ajouter une série à la collection");
		setLayout(new BorderLayout());
		Container pane = getContentPane();
		setSize(new Dimension(200, 200));
		setVisible(true);
		
		//Panel d'ajout 
		JPanel topPanel = new JPanel(new BorderLayout());
		addedSerie = new JTextField("Ajouter une serie");
		ajoutButton = new JButton("Ajouter");
		ajoutButton.addActionListener(null);
		topPanel.add(addedSerie, BorderLayout.CENTER);
		topPanel.add(ajoutButton, BorderLayout.LINE_END);
		
		//Panel d'ouverture de dossier
		JPanel centerPanel = new JPanel(new BorderLayout());
		JLabel ouvrir = new JLabel("Ouvrir un dossier");
		ouvrirButton = new JButton("Ouvrir");
		ouvrirButton.addActionListener(this);
		centerPanel.add(ouvrir, BorderLayout.CENTER);
		centerPanel.add(ouvrirButton, BorderLayout.LINE_END);
		
		
		//Panel d'enregistrement
		JPanel bottomPanel = new JPanel(new BorderLayout());
		enregistrerButton = new JButton("Enregistrer");
		enregistrerButton.addActionListener(this);
		bottomPanel.add(enregistrerButton, BorderLayout.CENTER);
		
		
		
		pane.add(topPanel, BorderLayout.PAGE_START);
		pane.add(centerPanel, BorderLayout.CENTER);
		pane.add(bottomPanel, BorderLayout.PAGE_END);
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ajoutButton){
			
		}else if (e.getSource() == ouvrirButton){
			JFileChooser choix = new JFileChooser();
			choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int retour=choix.showOpenDialog(getParent());
			if(retour==JFileChooser.APPROVE_OPTION){
			   choix.getSelectedFile().getAbsolutePath();
			}
		}
		
	}
}
