package view;

import javax.swing.JToolBar;

import java.awt.BorderLayout;

import java.awt.Container;

import javax.swing.JFrame;

public class Window extends JFrame {
	private JToolBar toolbar;
	
	public Window() {
		super("Ma fenêtre");

		setLayout(new BorderLayout());
		Container pane = getContentPane();

		toolbar = new JToolBar();
		pane.add(toolbar, BorderLayout.PAGE_START);
		pane.add(new PanelSerie(), BorderLayout.LINE_START);
		pane.add(new PanelMain(), BorderLayout.CENTER);
	}
}
