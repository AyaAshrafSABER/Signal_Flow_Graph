package main;

import java.awt.EventQueue;

import view.StartWindow;

public class Main {
	/**
	 * Launch the application.
	 */
	static StartWindow frame = new StartWindow();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
