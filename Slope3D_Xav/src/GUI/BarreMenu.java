package GUI;

import java.awt.Dimension;

import javax.swing.JMenuBar;

public class BarreMenu extends JMenuBar {
	/* Cette classe correspond à la barre de menu qui sera affichée dans la fenêtre du programme. */
	private MenuFichier mFichier;
	private MenuReglage mReglages;
	
	public BarreMenu(Dimension d) {
		super();
		this.setPreferredSize(d);
		
		/* Menu relatif à l'ouverture de fichiers et à la fermeture du programme */
		mFichier = new MenuFichier();
		this.add(mFichier);
		
		/* Menu donnant accès au paramétrage de la visualisation */
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
