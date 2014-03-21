package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class DialogueCouleurs extends JDialog {
	/*
	 * Attributs
	 */
	Color[] couleurs;
	JButton bValider, bAnnuler;
	
	
	
	/*
	 * Constructeur
	 */
	public DialogueCouleurs(MenuReglage reglages){
		super();
		this.setTitle("Choix des couleurs associées aux pentes");
		this.setSize(new Dimension(410,430));
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		int largeur = this.getWidth()-10;
		int hauteur = this.getHeight()-30;
		System.out.println(largeur+"*"+hauteur);
		
		/*
		 * Création des sous-composants
		 */
		JLabel lTitrePerso = new JLabel("COLORATION PERSONNALISEE");
			lTitrePerso.setPreferredSize(new Dimension(largeur, 30));
			lTitrePerso.setHorizontalAlignment(JLabel.CENTER);
		JPanel panCouleurs = new JPanel();
			panCouleurs.setPreferredSize(new Dimension(largeur,220));
		
		JSeparator sep1 = new JSeparator(SwingConstants.HORIZONTAL);
			sep1.setPreferredSize(new Dimension(largeur,1));
		JLabel lTitreAuto = new JLabel("COLORATION AUTOMATIQUE");
			lTitreAuto.setPreferredSize(new Dimension (largeur, 30));
			lTitreAuto.setHorizontalAlignment(JLabel.CENTER);
		JLabel lTexteAuto = new JLabel("  Niveaux de gris évoluant proportionnellement à la valeur de la pente,");
			lTexteAuto.setPreferredSize(new Dimension (largeur, 15));
		JLabel lTexteAuto2 = new JLabel("  du noir (triangle horizontal) au blanc (triangle vertical).");
			lTexteAuto2.setPreferredSize(new Dimension (largeur, 20));
			lTexteAuto2.setVerticalAlignment(JLabel.TOP);
		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
			sep2.setPreferredSize(new Dimension(largeur,1));
		JPanel panBoutons = new JPanel();
			panBoutons.setPreferredSize(new Dimension(largeur, 88));
			panBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 29));
				bValider = new JButton("Valider");
				bValider.setSize(110, 30);
				bAnnuler = new JButton("Annuler");
				bAnnuler.setSize(110, 30);
			panBoutons.add(bValider);
			panBoutons.add(bAnnuler);
				
		
		
		
		this.add(lTitrePerso);
		this.add(panCouleurs);
		this.add(sep1);
		this.add(lTitreAuto);
		this.add(lTexteAuto);
		this.add(lTexteAuto2);
		this.add(sep2);
		this.add(panBoutons);
		
		
	}

}
