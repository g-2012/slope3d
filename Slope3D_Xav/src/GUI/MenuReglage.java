package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import Utils.Constantes;

public class MenuReglage extends JMenu {
	/** Menu permettant d'accéder aux réglages de la visualisation.
	 * Ces réglages incluent :
	 *	- Le réglage des objets affichés (MNT et/ou courbes de niveau)
	 * 	- Le réglage du type de Caméra :
	 * 		° Vue de dessus, déplaçable uniquement par translation
	 * 		° Caméra en orbite préréglée, vitesse de rotation paramétrable
	 *		° Caméra personnalisée déplaçable par rotation et translation
	 *	- Le réglage des plages de couleurs affectées aux différentes pentes :
	 *		° Mode automatique : nuance de gris de valeur proportionnelle à la pente.
	 *		° Mode utilisateur : l'utilisateur choisit les couleurs associées à des plages de valeurs (10° d'amplitude)
	 */
	
	/* 
	 * Composants du menu
	 */
	// Cases à cocher pour le choix des objets affichés
	JCheckBoxMenuItem chkObjMNT, chkObjIsoligne;
	// Groupes de boutons radio pour s'assurer que seule une option soit sélectionnable par groupe.
	ButtonGroup grpCamera, grpCouleurs; 
	// Boutons radio
	JRadioButtonMenuItem radCamOrtho, radCamOrbite, radCamPerso, // pour les réglages caméra
	radCouAuto, radCouPerso; // pour les réglages couleurs
	
	/*
	 * Paramètres : valeurs des choix
	 */
	private byte choixObj, choixCam;
	private boolean choixCou;
	
	/*
	 * Paramètres : couleurs choisies pour la coloration personnalisée, en fonction de la valeur de la pente
	 */
	private Color 	coul0, // [0°, 10°[
					coul1, // [10°, 20°[
					coul2, // [20°, 30°[
					coul3, // [30°, 40°[
					coul4, // [40°, 50°[
					coul5, // [50°, 60°[
					coul6, // [60°, 70°[
					coul7, // [70°, 80°[
					coul8; // [80°, 90°]


	/*
	 * Constructeur
	 */
	public MenuReglage() {
		super("Reglages");

		// Menu de sélection des objets affichés
		JLabel titreObj = new JLabel("  Objets affichés  ", CENTER);
		titreObj.setHorizontalTextPosition(CENTER);
		this.add(titreObj);
		chkObjMNT = new JCheckBoxMenuItem("MNT", true);
		chkObjIsoligne = new JCheckBoxMenuItem("Isolignes");
		this.add(chkObjMNT);
		this.add(chkObjIsoligne);


		this.addSeparator();


		// Menu de sélection du type de vue
		JLabel titreCam = new JLabel("  Type de caméra  ", CENTER);
		titreCam.setHorizontalTextPosition(CENTER);
		this.add(titreCam);
		grpCamera = new ButtonGroup();
		radCamOrtho = new JRadioButtonMenuItem("Vue de dessus");
		radCamOrbite = new JRadioButtonMenuItem("En orbite", true);
		radCamPerso = new JRadioButtonMenuItem("Navigation 3D");
		// Groupement des boutons
		grpCamera.add(radCamOrtho);
		grpCamera.add(radCamOrbite);
		grpCamera.add(radCamPerso);
		// Ajout des boutons
		this.add(radCamOrtho);
		this.add(radCamOrbite);
		this.add(radCamPerso);


		this.addSeparator();


		// Menu de choix des couleurs du MNT
		JLabel titreCou = new JLabel("  Couleurs du MNT  ", CENTER);
		titreCou.setHorizontalTextPosition(CENTER);
		this.add(titreCou);
		grpCouleurs = new ButtonGroup();
		radCouAuto = new JRadioButtonMenuItem("Auto", true);
		radCouPerso = new JRadioButtonMenuItem("Personnalisé");
		// Groupement des boutons
		grpCouleurs.add(radCouAuto);
		grpCouleurs.add(radCouPerso);
		// Ajout des boutons
		this.add(radCouAuto);
		this.add(radCouPerso);
		
		// Initialisation des valeurs de choix
		choixObj = Constantes.OBJ_MNT;
		choixCam = Constantes.CAM_ORBITE;
		choixCou = Constantes.COU_AUTO;
		
		// Initialisation des couleurs par défaut
		coul0 = new Color(0, 0, 0);
		coul1 = new Color(30, 30, 30);
		coul2 = new Color(60, 60, 60);
		coul3 = new Color(90, 90, 90);
		coul4 = new Color(120, 120, 120);
		coul5 = new Color(150, 150, 150);
		coul6 = new Color(180, 180, 180);
		coul7 = new Color(210, 210, 210);
		coul8 = new Color(240, 240, 240);

	}
	
	
	/*
	 * Getters associés aux valeurs des choix
	 */
	public byte getChoixObj() {
		return choixObj;
	}
	
	public byte getChoixCam() {
		return choixCam;
	}
	
	public boolean getChoixCou() {
		return choixCou;
	}
	
	public Color[] getCouleurs(){
		Color[] tabCouleurs = {coul0, coul1, coul2, coul3, coul4, coul5, coul6, coul7, coul8};
		return tabCouleurs;
	}

}
