package view;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JMenu;
import java.awt.List;

public class Window<E> extends JFrame{

	public Window(){
		
		//Panel gauche - SERIES
		PanelSeries panelDroite = new PanelSeries();
				
		//Panel droite - INFOS
		PanelInfo panelGauche = new PanelInfo();
		
		
		//Frame principale
		JFrame maFrame = new JFrame();
		maFrame.setVisible(true);
		maFrame.add(panelDroite);

		
		/*List listSeries = new List();
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 29, 167, 330);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollBar scrollBar = new JScrollBar();
		panel.add(scrollBar, BorderLayout.EAST);
		

		panel.add(listSeries, BorderLayout.CENTER);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(241, 29, 411, 330);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollBar scrollBar_1 = new JScrollBar();
		panel_1.add(scrollBar_1, BorderLayout.EAST);
		
		List list_1 = new List();
		panel_1.add(list_1, BorderLayout.CENTER);*/

	}
}
