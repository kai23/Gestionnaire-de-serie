package view;


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
	
	public Window() {
		super("Ma fenêtre");

		setLayout(new BorderLayout());
		Container pane = getContentPane();

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

		//Ajout des blocs
		pane.add(toolbar, BorderLayout.PAGE_START);
		pane.add(new PanelSerie(), BorderLayout.LINE_START);
		pane.add(new PanelMain(), BorderLayout.CENTER);

		setSize(500, 500);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boutonAjout){
			JOptionPane.showMessageDialog(this, "Add", "Add", JOptionPane.WARNING_MESSAGE);
		}
		if(e.getSource() == boutonParam){
			JOptionPane.showMessageDialog(this, "Settings", "Settings", JOptionPane.WARNING_MESSAGE);
		} 
		
		
	}
	
	public void keyPressed(KeyEvent e) 
	{	
		//
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
