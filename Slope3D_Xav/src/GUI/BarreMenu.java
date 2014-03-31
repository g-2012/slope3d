package GUI;

import java.awt.Dimension;

import javax.swing.JMenuBar;

public class BarreMenu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6646521212364175899L;
	/* Cette classe correspond à la barre de menu qui sera affichée dans la fenêtre du programme. */
	
	/*
	 * Attributs
	 */
	private MenuFichier mFichier;
	private MenuReglage mReglages;
	private Fenetre parent;
	
	/*
	 * Constructeur.
	 */
	public BarreMenu(Dimension d, Fenetre parent) {
		super();
		this.parent = parent;
		this.setPreferredSize(d);
		
		/* 
		 * Menu relatif à l'ouverture de fichiers et à la fermeture du programme.
		 */
		mFichier = new MenuFichier(this);
		this.add(mFichier);
		
		/* 
		 * Menu donnant accès au paramétrage de la visualisation.
		 */
		mReglages = new MenuReglage(this);
		this.add(mReglages);
	}
	
	/*
	 * Getters
	 */
	public MenuReglage getMenuReglage() {
		return mReglages;
	}
	
	public MenuFichier getMenuFichier() {
		return mFichier;
	}
	
	public Fenetre getFenetreMere() {
		return parent;
	}
}
