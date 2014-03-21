package GUI;

import java.awt.Dimension;

import javax.swing.JMenuBar;

public class BarreMenu extends JMenuBar {
	/* Cette classe correspond à la barre de menu qui sera affichée dans la fenêtre du programme. */
	private MenuFichier mFichier;
	private MenuReglage mReglages;
	private Fenetre parent;
	
	public BarreMenu(Dimension d, Fenetre parent) {
		super();
		this.parent = parent;
		this.setPreferredSize(d);
		
		/* Menu relatif à l'ouverture de fichiers et à la fermeture du programme */
		mFichier = new MenuFichier();
		this.add(mFichier);
		
		/* Menu donnant accès au paramétrage de la visualisation */
		mReglages = new MenuReglage(this);
		this.add(mReglages);
		
	}
	
	/*
	 * getters
	 */
	public MenuReglage getMenuReglage() {
		return mReglages;
	}
	
	public Fenetre getFenetreMere() {
		return parent;
	}

}
