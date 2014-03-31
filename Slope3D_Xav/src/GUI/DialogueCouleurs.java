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

/**
 * Boîte de dialogue dédiée au choix des couleurs de représentation des
 * différentes pentes sur le MNT.
 * 
 * @version 0.6
 * @author JIGX
 * 
 */
public class DialogueCouleurs extends JDialog {
	/**
	 * @param couleurs
	 *            Tableau des 9 couleurs personnalisées associées respectivement
	 *            aux 9 plages de valeurs des pentes.
	 */
	private static final long serialVersionUID = 1940234182836431776L;
	/*
	 * Attributs
	 */
	private Color[] couleurs;
	private JButton bValider, bAnnuler;

	/*
	 * Constructeur
	 */
	public DialogueCouleurs(MenuReglage reglages) {
		super();
		/*
		 * Pamétrage de la boîte de dialogue
		 */
		this.setTitle("Choix des couleurs associées aux pentes");
		this.setSize(new Dimension(410, 430));
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		int largeur = this.getWidth() - 10;
		int hauteur = this.getHeight() - 30;
		// Déboguage : System.out.println(largeur+"*"+hauteur);

		/*
		 * Création des composants
		 */
		/*
		 * Zone de choix des couleurs personnalisées.
		 */
		JLabel lTitrePerso = new JLabel("COLORATION PERSONNALISEE");
		lTitrePerso.setPreferredSize(new Dimension(largeur, 30));
		lTitrePerso.setHorizontalAlignment(JLabel.CENTER);
		JPanel panCouleurs = new JPanel();
		panCouleurs.setPreferredSize(new Dimension(largeur, 220));

		JSeparator sep1 = new JSeparator(SwingConstants.HORIZONTAL);
		sep1.setPreferredSize(new Dimension(largeur, 1));

		/*
		 * Description de la coloration automatique.
		 */
		JLabel lTitreAuto = new JLabel("COLORATION AUTOMATIQUE");
		lTitreAuto.setPreferredSize(new Dimension(largeur, 30));
		lTitreAuto.setHorizontalAlignment(JLabel.CENTER);
		JLabel lTexteAuto = new JLabel(
				"  Niveaux de gris évoluant proportionnellement à la valeur de la pente,");
		lTexteAuto.setPreferredSize(new Dimension(largeur, 15));
		JLabel lTexteAuto2 = new JLabel(
				"  du noir (triangle horizontal) au blanc (triangle vertical).");
		lTexteAuto2.setPreferredSize(new Dimension(largeur, 20));
		lTexteAuto2.setVerticalAlignment(JLabel.TOP);

		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
		sep2.setPreferredSize(new Dimension(largeur, 1));

		/*
		 * Boutons Valider / Annuler
		 */
		JPanel panBoutons = new JPanel();
		panBoutons.setPreferredSize(new Dimension(largeur, 88));
		panBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 29));
		bValider = new JButton("Valider");
		bValider.setSize(110, 30);
		bAnnuler = new JButton("Annuler");
		bAnnuler.setSize(110, 30);
		panBoutons.add(bValider);
		panBoutons.add(bAnnuler);

		/*
		 * Ajout des composants
		 */
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
