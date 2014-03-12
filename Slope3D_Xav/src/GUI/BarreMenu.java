package GUI;

import java.awt.Dimension;

import javax.swing.JMenuBar;

public class BarreMenu extends JMenuBar {
	/* Cette classe correspond à la barre de menu qui sera affichée dans la fenêtre du programme. */
	private MenuFichier mFichier;
	
	public BarreMenu(Dimension d) {
		super();
		this.setPreferredSize(d);
		
		mFichier = new MenuFichier();
		this.add(mFichier);
		
	}

}
