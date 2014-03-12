package GUI;

import java.awt.Dimension;

import javax.swing.JMenuBar;

public class BarreMenu extends JMenuBar {
	/* Cette classe correspond � la barre de menu qui sera affich�e dans la fen�tre du programme. */
	private MenuFichier mFichier;
	
	public BarreMenu(Dimension d) {
		super();
		this.setPreferredSize(d);
		
		mFichier = new MenuFichier();
		this.add(mFichier);
		
	}

}
