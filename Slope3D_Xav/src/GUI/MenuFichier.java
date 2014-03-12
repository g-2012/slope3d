package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MenuFichier extends JMenu {
/* Menu relatif � l'ouverture d'un nouveau fichier MNT ou � la fermeture du programme */
	
	
	private JMenuItem ouvrir;
	private JMenuItem quitter;
	
	public MenuFichier() {
		super("Fichier");
		
		/* Cr�ation de l'item "Ouvrir" qui permet d'ouvrir un nouveau MNT */
		ouvrir = new JMenuItem("Ouvrir");
		
		
		/* Cr�ation de l'item "Quitter" qui permet de fermer le programme */
		quitter = new JMenuItem("Quitter");
		
		
		/* Ajout des �l�ments du menu dans ce dernier */
		this.add(ouvrir);
		this.addSeparator();
		this.add(quitter);
	}

}
