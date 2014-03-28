package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utils.FilesUtils;
import structures.Grille;


public class MenuFichier extends JMenu {
/* Menu relatif à l'ouverture d'un nouveau fichier MNT ou à la fermeture du programme */
	
	
	private JMenuItem ouvrir;
	private JMenuItem quitter;
	private String fichier;
	private String extension;
	private Grille grille;
	private Panneau3D panEnv;
	
	public MenuFichier() {
		super("Fichier");
		
		
		/* Création de l'item "Ouvrir" qui permet d'ouvrir un nouveau MNT */
		ouvrir = new JMenuItem("Ouvrir");
		ouvrir.addActionListener(new ActionListener() { // Actions engendrees par le clic sur l'item "Ouvrir".
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("/")); // Repertoire par defaut : sous-dossier de la racine du programme contenant les images.
				System.out.println(chooser.getLocale().toLanguageTag());
				//Configuration des filtres par extension
				FileNameExtensionFilter filterAsc = new FileNameExtensionFilter("Fichiers ASCII", "asc");
				chooser.setFileFilter(filterAsc);
				/*FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("Fichiers Texte", "txt");
				chooser.addChoosableFileFilter(filterTxt);*/
				FileNameExtensionFilter filterXYZ = new FileNameExtensionFilter("MNT format XYZ", "xyz");
				chooser.addChoosableFileFilter(filterXYZ);
				FileNameExtensionFilter filterTout = new FileNameExtensionFilter("Tous les types supportés", "asc","xyz");
				chooser.addChoosableFileFilter(filterTout);
				chooser.setFileHidingEnabled(true);
				chooser.setAcceptAllFileFilterUsed(false); // Suppression du filtre affichant tous les types de fichiers.
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				
				int result = chooser.showOpenDialog(ouvrir); // Valeur du choix fait par l'utilisateur.
				if (result == JFileChooser.APPROVE_OPTION){ // En cas de validation (bouton "Ouvrir").
			
					fichier = chooser.getSelectedFile().getAbsoluteFile().toURI().toString().substring(6); // Recupere le chemin d'acces du fichier choisi.
					
					extension = fichier.substring(fichier.length()-3); // Récupère son extension
					System.out.println("Vous avez ouvert le fichier : "+fichier);
					System.out.println("Il s'agit d'un fichier de type "+extension);
					
					// Appel des methodes de mise a jour de l'affichage pour prendre en compte le nouveau fichier ouvert.
					if (extension.equalsIgnoreCase("asc")) {
						grille = FilesUtils.loadMNTAsc(fichier);						
					}
					else if (extension.equalsIgnoreCase("xyz")) {
						grille = FilesUtils.loadMNTxyz2(fichier);						
					}
					panEnv.setGrille(grille);
					panEnv.revalidate();
					
					
	
				}
				else if (result == JFileChooser.CANCEL_OPTION){ // En cas d'annulation.
					chooser.cancelSelection();
					System.out.println("Ouverture annulee.");
				}
			
			
			
			}
		});
		
		
		/* Création de l'item "Quitter" qui permet de fermer le programme */
		quitter = new JMenuItem("Quitter");
		
		
		/* Ajout des éléments du menu dans ce dernier */
		this.add(ouvrir);
		this.addSeparator();
		this.add(quitter);
	}
	
	public void setPanEnv(Panneau3D pan){
		panEnv = pan;
		if(panEnv.equals(null)){
			System.out.println("probleme de chargement du panneau");
		}
	}

}
