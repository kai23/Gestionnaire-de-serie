package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlleur.SerieController;

public class PanelSerie extends JPanel implements ListSelectionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private PanelMain infoSerie;
	private DefaultListModel<String> listModel;
	private SerieController ctrl;

	public PanelSerie(PanelMain infoSerie, SerieController ctrl){
		super();
		this.ctrl = ctrl;
		this.infoSerie = infoSerie;
		setLayout(new BorderLayout());

		//Recuperation des noms de seriess
		ArrayList<String> mesSeries = ctrl.getAllSerieName();
			
		//Passage par un defaultListModel
		listModel = new DefaultListModel<>();
		JList listSerie = new JList(listModel);
		for(String maSerie : mesSeries) {
			addSerie(maSerie);
		}
		
		//Creation et remplissage de la JList
		listSerie.addListSelectionListener(this);
		listSerie.addMouseListener(this);
		
		//Ajout des components
		add(listSerie);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//Recuperer le nom de la serie choisie
		String nameSerie = (String) (((JList) e.getSource()).getSelectedValue());

		infoSerie.transferInfo(nameSerie);
		infoSerie.updateView();
		
	}
	
	public void mousePressed(MouseEvent e){
        if ( SwingUtilities.isRightMouseButton(e) )
        {
            System.out.println("mouse pressed");
        }
    }
 
 

	private void addSerie(String name) {
		listModel.addElement(name);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
