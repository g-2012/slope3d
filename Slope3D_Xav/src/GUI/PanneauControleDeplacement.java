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
	 * Panneau de contr�le permettant � l'utilisateur de d�placer la cam�ra par translation selon les 3 axes du rep�re 3D,
	 * de revenir � la position par d�faut et de changer la sensibilit� des commandes
	 */
	
	/*
	 * Attributs : boutons
	 */
	JButton bAvant, bArriere, // translation avant <-> arri�re
		bGauche, bDroit, // translation gauche <-> droite
		bHaut, bBas, // translation haut <-> bas
		bVitPlus, bVitMoins, // sensibilit� des boutons / vitesse de d�placement
		bOrigine; // retour � la position par d�faut
	
	
	/*
	 * Constructeur
	 */
	public PanneauControleDeplacement(MenuReglage mReg, Dimension d) {
		super();
		this.setSize(d);
		this.setBackground(new Color(225,225,225));
		
		bHaut = new JButton("Haut");
		bOrigine = new JButton("Origine");
		bVitPlus = new JButton("Vit +");
		bBas = new JButton("Bas");
		bAvant = new JButton("Avant");
		bVitMoins = new JButton("Vit -");
		bGauche = new JButton("Gauche");
		bArriere = new JButton("Arri�re");
		bDroit = new JButton("Droite");
		
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
		}
		else {
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
		
		// Mise en page sous forme d'une grille de 4 ligne et 3 colonnes, dont les cases sont de taille identique et espac�es de 1 p�xel
		this.setLayout(new GridLayout(4, 3, 1, 1));
		// case 0,0
		this.add(new JLabel(" "));
		// case 0,1
		this.add(new JLabel("D�placement"));
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

}
