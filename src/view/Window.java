package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Window extends JFrame {
	public Window() {
		super("Ma fenêtre");

		setLayout(new BorderLayout());
		add(BorderLayout.CENTER);
	}
}
