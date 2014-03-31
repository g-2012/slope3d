package Test;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import GUI.Fenetre;
import GUI.Panneau3D;

public class Lanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Fenetre fen = new Fenetre();
		fen.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Use a dedicate thread to run the stop() to ensure that the
				// animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						if (((Panneau3D) fen.getPanEnv()).getAnimateur().isStarted()) ((Panneau3D) fen.getPanEnv()).getAnimateur().stop();
						System.exit(0);
					}
				}.start();
			}
		});

	}

}
