package view;


import java.awt.Dimension;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener, KeyListener{
	private JToolBar toolbar;
	private JButton boutonAjout;
	private JButton boutonParam;
	private JTextField boxSearch;;
	
	/**
	 * Constructeur par defaut de Window
	 * Affiche une toolbar et deux panel
	 */
	public Window() {
		super("Ma fenêtre");
		
		/*Mise en page de la fenetre*/
		setLayout(new BorderLayout());
		Container pane = getContentPane();
		setSize(500, 500);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		/* Toolbar */
		toolbar = new JToolBar();
		ImageIcon add = new ImageIcon("./src/images/ajouter.png");
		boutonAjout = new JButton(add);
		ImageIcon settings = new ImageIcon("./src/images/settings.png");
		boutonParam = new JButton(settings);
		boxSearch = new JTextField("Recherche...");	
		toolbar.add(boutonAjout);
		toolbar.add(boutonParam);
		toolbar.add(boxSearch);
		boutonAjout.addActionListener(this);
		boutonParam.addActionListener(this);
		boxSearch.addKeyListener(this);

		/* Panel de droite et de gauche */
		PanelMain infoSerie = new PanelMain();
		pane.add(toolbar, BorderLayout.PAGE_START);
		PanelSerie panelSeries = new PanelSerie(infoSerie);
		pane.add(new PanelSerie(infoSerie), BorderLayout.LINE_START);
		pane.add(infoSerie, BorderLayout.CENTER);

		//Apparence visible
		setVisible(true);
	}

	@Override
	/**
	 * Listener sur les boutons de ToolBar
	 * @param : un evenement action
	 */
	public void actionPerformed(ActionEvent e) {
		/* Bouton ajout */
		if(e.getSource() == boutonAjout){
			JOptionPane.showMessageDialog(this, "Add", "Add", JOptionPane.WARNING_MESSAGE);
		}else{
			/* Bouton Parametres */
			if(e.getSource() == boutonParam){
				SettingsFrame mesParams = new SettingsFrame();
			} 
		}	
	}
	
	/**
	 * Listener des touches de claviers
	 * @param : un evenement touche
	 */
	public void keyPressed(KeyEvent e) 
	{	
		/* Touche 'ENTER' */
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			//Valeur sur laquelle il faut executer la recherche.
			String saisie = new String(boxSearch.getText());
			JOptionPane.showMessageDialog(this, saisie, saisie, JOptionPane.WARNING_MESSAGE);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
