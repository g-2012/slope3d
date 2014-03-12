package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MenuFichier extends JMenu {
/* Menu relatif à l'ouverture d'un nouveau fichier MNT ou à la fermeture du programme */
	
	
	private JMenuItem ouvrir;
	private JMenuItem quitter;
	private JSeparator sep;
	
	public MenuFichier() {
		super("Fichier");
		
		/* Création de l'item "Ouvrir" qui permet d'ouvrir un nouveau MNT */
		ouvrir = new JMenuItem("Ouvrir");
		
		
		/* Création de l'item "Quitter" qui permet de fermer le programme */
		quitter = new JMenuItem("Quitter");
		
		/* Separateur à rôle purement esthétique */
		sep = new JSeparator();
		
		/* Ajout des éléments du menu dans ce dernier */
		this.add(ouvrir);
		this.add(sep);
		this.add(quitter);
	}

}
