package view;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JFrame;

public class Window extends JFrame {
	private JToolBar toolbar;
	
	public Window() {
		super("Ma fenêtre");

		setLayout(new BorderLayout());
		Container pane = getContentPane();

		toolbar = new JToolBar();
		JButton btn = new JButton("Ajouter");
		toolbar.add(btn);

		pane.add(toolbar, BorderLayout.PAGE_START);
		pane.add(new PanelSerie(), BorderLayout.LINE_START);
		pane.add(new PanelMain(), BorderLayout.CENTER);

<<<<<<< HEAD
=======
		setSize(500, 500);
		setResizable(true);
>>>>>>> 1cc224339868bd00528511ce3a665a55035614e8
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
}
