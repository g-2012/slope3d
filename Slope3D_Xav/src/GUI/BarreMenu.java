package GUI;

import java.awt.Dimension;

import javax.swing.JMenuBar;

public class BarreMenu extends JMenuBar {
	/* Cette classe correspond � la barre de menu qui sera affich�e dans la fen�tre du programme. */
	private MenuFichier mFichier;
	private MenuReglage mReglages;
	
	public BarreMenu(Dimension d) {
		super();
		this.setPreferredSize(d);
		
		/* Menu relatif � l'ouverture de fichiers et � la fermeture du programme */
		mFichier = new MenuFichier();
		this.add(mFichier);
		
		/* Menu donnant acc�s au param�trage de la visualisation */
		mReglages = new MenuReglage();
		this.add(mReglages);
		
	}
	
	/*
	 * getter des reglages
	 */
	public MenuReglage getMenuReglage() {
		return mReglages;
	}

}
