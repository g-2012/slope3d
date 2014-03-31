package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import structures.Grille;
import Utils.FilesUtils;

/* 
 * Menu relatif à l'ouverture d'un nouveau fichier MNT ou à la fermeture du programme. 
 */

public class MenuFichier extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3121268353771202760L;
	
	/*
	 * Attributs.
	 */
	private JMenuItem ouvrir;
	private JMenuItem quitter;
	private String fichier;
	private String extension;
	private Grille grille;
	private Panneau3D panEnv;
	private BarreMenu parent;
	
	/*
	 * Constructeur.
	 */
	public MenuFichier(BarreMenu bar) {
		super("Fichier");
		
		parent = bar;
		/* 
		 * Création de l'item "Ouvrir" qui permet d'ouvrir un nouveau MNT.
		 */
		ouvrir = new JMenuItem("Ouvrir");
		ouvrir.addActionListener(new ActionListener() { // Actions engendréees par le clic sur l'item "Ouvrir".
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("/")); // Répertoire par défaut : racine du lecteur sur lequel est installé l'IDE / le programme.
				/*
				 * Configuration des filtres par extension
				 */
				FileNameExtensionFilter filterAsc = new FileNameExtensionFilter("Fichiers ASCII", "asc");
				chooser.setFileFilter(filterAsc);
				FileNameExtensionFilter filterXYZ = new FileNameExtensionFilter("MNT format XYZ", "xyz");
				chooser.addChoosableFileFilter(filterXYZ);
				FileNameExtensionFilter filterTout = new FileNameExtensionFilter("Tous les types supportés", "asc","xyz");
				chooser.addChoosableFileFilter(filterTout);
				chooser.setFileHidingEnabled(true); // Les fichiers et dossiers cachés ne sont pas visibles dans la liste.
				chooser.setAcceptAllFileFilterUsed(false); // Suppression du filtre affichant tous les types de fichiers.
				chooser.setMultiSelectionEnabled(false); // Sélection multiple de fichiers désactivée.
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Les dossiers ne peuvent pas être sélectionnés.
				
				
				int result = chooser.showOpenDialog(ouvrir); // Valeur du choix fait par l'utilisateur.
				if (result == JFileChooser.APPROVE_OPTION){ // En cas de validation (bouton "Ouvrir").
					fichier = chooser.getSelectedFile().getAbsoluteFile().toURI().toString().substring(6); // Récupère le chemin d'accès du fichier choisi.
					
					extension = fichier.substring(fichier.length()-3); // Récupère son extension
					System.out.println("Vous avez ouvert le fichier : "+fichier);
					System.out.println("Il s'agit d'un fichier de type "+extension);
					
					// Appel des méthodes de mise à jour de l'affichage pour prendre en compte le nouveau fichier ouvert.
					if (extension.equalsIgnoreCase("asc")) {
						grille = FilesUtils.loadMNTAsc(fichier);						
					}
					else if (extension.equalsIgnoreCase("xyz")) {
						grille = FilesUtils.loadMNTxyz2(fichier);						
					}
					panEnv.setGrille(grille);
					panEnv.revalidate();
					parent.getFenetreMere().revalidate();
				}
				else if (result == JFileChooser.CANCEL_OPTION){ // En cas d'annulation.
					chooser.cancelSelection();
					System.out.println("Ouverture annulée.");
				}	
			}
		});
		
		
		/* 
		 * Création de l'item "Quitter" qui permet de fermer le programme.
		 */
		quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() { // Action engendrée par le clic sur l'item "Quitter"
			public void actionPerformed(ActionEvent event) {
				System.exit(0); // Fermeture du programme.
			}
		});
		
		
		/* 
		 * Ajout des éléments du menu dans ce dernier
		*/
		this.add(ouvrir);
		this.addSeparator();
		this.add(quitter);
	}
	
	/*
	 * Chien de chasse
	 */
	public void setPanEnv(Panneau3D pan){
		panEnv = pan;
		if(panEnv.equals(null)){
			System.out.println("Problème de chargement du panneau");
		}
	}
}
