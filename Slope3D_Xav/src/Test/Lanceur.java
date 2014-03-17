package Test;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import GUI.*;

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
		
		JFrame fen2 = new JFrame();
		fen2.setSize(250, 600);
		PanneauControle pan = new PanneauControle(fen.getMenu().getMenuReglage(), new Dimension(250,575));
		pan.setVisible(true);
		fen2.add(pan);
		fen2.setVisible(true);
	}

}
