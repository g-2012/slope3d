package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Utils.Constantes;

public class PanneauControleDeplacement extends JPanel {
	/*
	 * Panneau de contrôle permettant à l'utilisateur de déplacer la caméra par
	 * translation selon les 3 axes du repère 3D, de revenir à la position par
	 * défaut et de changer la sensibilité des commandes
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 8356243999226995969L;

	/*
	 * Attributs : boutons
	 */
	JButton bAvant, bArriere, // translation avant <-> arrière
			bGauche, bDroit, // translation gauche <-> droite
			bHaut, bBas, // translation haut <-> bas
			bVitPlus, bVitMoins, // sensibilité des boutons / vitesse de
									// déplacement
			bOrigine; // retour à la position par défaut
	/*
	 * Autres attributs
	 */
	MenuReglage mReg;

	/*
	 * Constructeur
	 */
	public PanneauControleDeplacement(MenuReglage Reglages, Dimension d) {
		super();
		this.setPreferredSize(d);
		this.setBackground(new Color(225, 225, 225));
		this.mReg = Reglages;

		/*
		 * Création des boutons
		 */
		bHaut = new JButton("Haut");
		bOrigine = new JButton("Origine");
		bVitPlus = new JButton("Vit +");
		bBas = new JButton("Bas");
		bAvant = new JButton("Avant");
		bVitMoins = new JButton("Vit -");
		bGauche = new JButton("Gauche");
		bArriere = new JButton("Arrière");
		bDroit = new JButton("Droite");

		/*
		 * Activation ou désactivation des boutons selon la vue choisie
		 */
		if (mReg.getChoixCam() == Constantes.CAM_ORBITE) {
			bHaut.setEnabled(false);
			bOrigine.setEnabled(false);
			bVitPlus.setEnabled(false);
			bBas.setEnabled(false);
			bAvant.setEnabled(false);
			bVitMoins.setEnabled(false);
			bGauche.setEnabled(false);
			bArriere.setEnabled(false);
			bDroit.setEnabled(false);
		} else {
			bHaut.setEnabled(true);
			bOrigine.setEnabled(true);
			bVitPlus.setEnabled(true);
			bBas.setEnabled(true);
			bAvant.setEnabled(true);
			bVitMoins.setEnabled(true);
			bGauche.setEnabled(true);
			bArriere.setEnabled(true);
			bDroit.setEnabled(true);
		}

		// Mise en page sous forme d'une grille de 4 ligne et 3 colonnes, dont
		// les cases sont de taille identique et espacées de 1 pixel
		this.setLayout(new GridLayout(4, 3, 1, 1));
		// case 0,0
		this.add(new JLabel(" "));
		// case 0,1
		JPanel pTitre = new JPanel();
		pTitre.setBackground(new Color(225, 225, 225));
		JLabel tTra = new JLabel("Déplacement");
		tTra.setSize(75, 30);
		tTra.setAlignmentX(CENTER_ALIGNMENT);
		tTra.setAlignmentY(CENTER_ALIGNMENT);
		tTra.setBorder(new LineBorder(Color.GRAY, 1));
		pTitre.add(tTra);
		this.add(pTitre);
		// case 0,2
		this.add(new JLabel(" "));
		// case 1,0
		this.add(bHaut);
		// case 1,1
		this.add(bOrigine);
		// case 1,2
		this.add(bVitPlus);
		// case 2,0
		this.add(bBas);
		// case 2,1
		this.add(bAvant);
		// case 2,2
		this.add(bVitMoins);
		// case 3,0
		this.add(bGauche);
		// case 3,1
		this.add(bArriere);
		// case 3,2
		this.add(bDroit);

		this.setVisible(true);
	}

	/*
	 * Méthode de rafraichissement des boutons quand les réglages ont été
	 * modifiés
	 */
	public void rafraichitBoutons() {
		/*
		 * Activation ou désactivation des boutons selon la vue choisie
		 */
		if (mReg.getChoixCam() == Constantes.CAM_ORBITE) {
			bHaut.setEnabled(false);
			bOrigine.setEnabled(false);
			bVitPlus.setEnabled(false);
			bBas.setEnabled(false);
			bAvant.setEnabled(false);
			bVitMoins.setEnabled(false);
			bGauche.setEnabled(false);
			bArriere.setEnabled(false);
			bDroit.setEnabled(false);
		} else {
			bHaut.setEnabled(true);
			bOrigine.setEnabled(true);
			bVitPlus.setEnabled(true);
			bBas.setEnabled(true);
			bAvant.setEnabled(true);
			bVitMoins.setEnabled(true);
			bGauche.setEnabled(true);
			bArriere.setEnabled(true);
			bDroit.setEnabled(true);
		}
	}

}
