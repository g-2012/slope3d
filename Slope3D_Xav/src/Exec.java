import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import GUI.Fenetre;
import GUI.Panneau3D;

public class Exec {
	/*
	 * Classe servant à l'exécution du programme.
	 */

	public static void main(String[] args) {
		/*
		 * Création de la fenêtre principale.
		 */
		final Fenetre fen = new Fenetre();
		
		/*
		 * Ecouteur qui surveille la fermeture de la fenêtre principale, donc du programme.
		 */
		fen.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 *  Utilise un processus dédié qui execute stop() pour s'assurer que l'animateur est 
				 *  arrêté avant la fermeture totale du programme.
				 */
				new Thread() {
					public void run() {
						if (((Panneau3D) fen.getPanEnv()).getAnimateur().isStarted()) ((Panneau3D) fen.getPanEnv()).getAnimateur().stop();
						System.exit(0);
					}
				}.start();
			}
		});

	}

}
