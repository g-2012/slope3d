package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import Utils.Constantes;

public class Fenetre extends JFrame {
	/**
	 * 
	 */
	/*
	 * Classe d'affichage de la fenêtre où s'éxécute le programme.
	 */

	private static final long serialVersionUID = -1856636070468607309L;

	/*
	 * Attributs de la classe.
	 */
	private String TITRE = "Slope3D : affichage de MNT coloré selon la pente"; // Titre
																				// de
																				// la
																				// fenêtre.
	private Panneau3D panEnv; // Le panneau d'environnement 3D qui affichera la
								// représentation graphique du MNT.
	private PanneauControle panCtrl; // Panneau qui permet de paramétrer la vue
	private BarreMenu bandeauMenu; // Barre de menu
	private JMenu mIsolignes; // Menu permettant d'ouvrir dans une fenêtre
								// séparée la carte des isolignes en 2D
	private JMenuItem iIso2D; // Bouton assoicié à ce menu.

	/*
	 * Constructeur.
	 */
	public Fenetre() {
		super(); // Crée une instance de JFrame.
		/*
		 * Localisation, particulièrement pour la langue des textes par défaut
		 * des boutons.
		 */
		this.setLocale(Locale.FRANCE);
		Constantes.langueFR();

		/*
		 * Extraction des dimensions de l'écran sur lequel s'exécute le
		 * programme.
		 */
		Toolkit tk = this.getToolkit(); // Chargement d'une boîte à outils pour
										// extraire les dimensions de l'écran.
		Dimension dimEcran = tk.getScreenSize(); // Dimensions de l'écran actif.

		/*
		 * Paramétrage de la fenêtre.
		 */
		Dimension dimFenetre = new Dimension(dimEcran.width * 9 / 10,
				dimEcran.height * 9 / 10);
		this.setPreferredSize(dimFenetre); // La fenêtre occupe 9/10 de l'écran.
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout()); // Choix de la mise en page des
											// composants.

		/*
		 * Création et ajout des composants.
		 */
		bandeauMenu = new BarreMenu(new Dimension(dimFenetre.width, 20), this);
		this.getContentPane().add(bandeauMenu, BorderLayout.NORTH);

		panEnv = new Panneau3D(bandeauMenu.getMenuReglage(), new Dimension(
				dimFenetre.width - 250, dimFenetre.height - 25 - 20)); // Création
																		// du
																		// panneau
																		// contenant
																		// l'environnement
																		// 3D.
		this.getContentPane().add(panEnv, BorderLayout.CENTER);
		bandeauMenu.getMenuFichier().setPanEnv(panEnv);
		// Affichage des informations sur la grille extraite du fichier MNT dans
		// la console.
		System.out.println(panEnv.getGrille());

		mIsolignes = new JMenu("Isolignes");
		iIso2D = new JMenuItem("Afficher les Isolignes 2D");
		iIso2D.addActionListener(new ActionListener() { // Actions engendrées
														// par le clic sur
														// l'item iIso2D.
			public void actionPerformed(ActionEvent event) {
				System.out
						.println("Ouverture du plan des isolignes 2D en cours...");
				SimpleFrame frame = new SimpleFrame(panEnv.getSegments(),
						panEnv.getGrille());
				frame.setVisible(true);
			}
		});
		mIsolignes.add(iIso2D);
		bandeauMenu.add(mIsolignes);

		panCtrl = new PanneauControle(bandeauMenu.getMenuReglage(),
				new Dimension(250, dimFenetre.height - 25));
		this.getContentPane().add(panCtrl, BorderLayout.EAST);

		this.setTitle(TITRE);
		this.pack();
		this.setVisible(true);
	}

	/*
	 * Getters
	 */
	public Panneau3D getPanEnv() {
		return panEnv;
	}

	public BarreMenu getMenu() {
		return bandeauMenu;
	}

	public PanneauControle getControles() {
		return panCtrl;
	}
}

/* A ajouter au main pour fermer l'animateur en même temps que la fenêtre */
// this.addWindowListener(new WindowAdapter() {
// @Override
// public void windowClosing(WindowEvent e) {
// // Use a dedicate thread to run the stop() to ensure that the
// // animator stops before program exits.
// new Thread() {
// @Override
// public void run() {
// if (((Panneau3D) panEnv).getAnimateur().isStarted()) ((Panneau3D)
// panEnv).getAnimateur().stop();
// System.exit(0);
// }
// }.start();
// }
// });