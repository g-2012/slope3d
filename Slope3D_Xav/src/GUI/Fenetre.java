package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;



public class Fenetre extends JFrame { // Classe d'affichage de la fen�tre o� s'�x�cute le programme.
	// Attributs de la classe	
	private String TITRE = "Slope3D : affichage de MNT color� selon la pente"; // Titre  de la fen�tre.
	
	private int LARGEUR_ENV = 800; // Largeur de la zone de dessin 3D.
	private int HAUTEUR_ENV = 800; // Longueur de la zone de dessin 3D.
	
	
	private Panneau3D panEnv; // Le panneau d'environnement 3D qui affichera la repr�sentation graphique du MNT.
	private PanneauControle panCtrl; // Panneau qui permet de param�trer la vue 
		
	
	// Constructeur.
	public Fenetre() {
		super(); // Cr�e une instance de JFrame.
				
		Toolkit tk = this.getToolkit(); // Chargement d'une bo�te � outils pour extraire les dimensions de l'�cran.
		Dimension dimEcran = tk.getScreenSize(); // Dimensions de l'�cran actif.
		
		this.setPreferredSize(new Dimension(dimEcran.width*9/10 ,dimEcran.height*9/10)); // La fen�tre fait la taille de l'�cran.
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout()); // Choix de la mise en page des composants.
		
		panEnv = new Panneau3D(); // Cr�ation du panneau contenant l'environnement 3D.
		panEnv.setPreferredSize(new Dimension(this.getContentPane().getWidth()-250,this.getContentPane().getHeight()-20));
		this.getContentPane().add(panEnv, BorderLayout.CENTER);
		
		panCtrl = new PanneauControle();
		panCtrl.setPreferredSize(new Dimension(250,this.getContentPane().getHeight()));
		this.getContentPane().add(panCtrl, BorderLayout.EAST);
		
		JMenuBar bandeauMenu = new JMenuBar();
		bandeauMenu.setPreferredSize(new Dimension(this.getContentPane().getWidth(),20));
		this.getContentPane().add(bandeauMenu, BorderLayout.NORTH);
		
			
		
		/*this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// S'assure que l'animateur est bien arr�t� lorsqu'on ferme le programme
				new Thread() {
					@Override
					public void run() {
						if (panEnv.getAnimateur().isStarted()) {panEnv.getAnimateur().stop();}
						System.exit(0);
					}
				}.start();
			}
		});*/
		
		this.setTitle(TITRE);
		this.pack();
		this.setVisible(true);
		
		
		
		
	}
	
}
