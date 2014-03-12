package GUI;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import Utils.Constantes;

public class MenuReglage extends JMenu {
	/** Menu permettant d'acc�der aux r�glages de la visualisation.
	 * Ces r�glages incluent :
	 *	- Le r�glage des objets affich�s (MNT et/ou courbes de niveau)
	 * 	- Le r�glage du type de Cam�ra :
	 * 		� Vue de dessus, d�pla�able uniquement par translation
	 * 		� Cam�ra en orbite pr�r�gl�e, vitesse de rotation param�trable
	 *		� Cam�ra personnalis�e d�pla�able par rotation et translation
	 *	- Le r�glage des plages de couleurs affect�es aux diff�rentes pentes :
	 *		� Mode automatique : nuance de gris de valeur proportionnelle � la pente.
	 *		� Mode utilisateur : l'utilisateur choisit des plages de valeurs et y associe des couleurs
	 */
	
	/* 
	 * Composants du menu
	 */
	// Cases � cocher pour le choix des objets affich�s
	JCheckBoxMenuItem chkObjMNT, chkObjIsoligne;
	// Groupes de boutons radio pour s'assurer que seule une option soit s�lectionnable par groupe.
	ButtonGroup grpCamera, grpCouleurs; 
	// Boutons radio
	JRadioButtonMenuItem radCamOrtho, radCamOrbite, radCamPerso, // pour les r�glages cam�ra
	radCouAuto, radCouPerso; // pour les r�glages couleurs
	
	/*
	 * Param�tres : valeurs des choix
	 */
	private byte choixObj, choixCam;
	private boolean choixCou;


	/*
	 * Constructeur
	 */
	public MenuReglage() {
		super("Reglages");

		// Menu de s�lection des objets affich�s
		JLabel titreObj = new JLabel("  Objets affich�s  ", CENTER);
		titreObj.setHorizontalTextPosition(CENTER);
		this.add(titreObj);
		chkObjMNT = new JCheckBoxMenuItem("MNT", true);
		chkObjIsoligne = new JCheckBoxMenuItem("Isolignes");
		this.add(chkObjMNT);
		this.add(chkObjIsoligne);


		this.addSeparator();


		// Menu de s�lection du type de vue
		JLabel titreCam = new JLabel("  Type de cam�ra  ", CENTER);
		titreCam.setHorizontalTextPosition(CENTER);
		this.add(titreCam);
		grpCamera = new ButtonGroup();
		radCamOrtho = new JRadioButtonMenuItem("Vue de dessus", true);
		radCamOrbite = new JRadioButtonMenuItem("En orbite");
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
		radCouPerso = new JRadioButtonMenuItem("Personnalis�");
		// Groupement des boutons
		grpCouleurs.add(radCouAuto);
		grpCouleurs.add(radCouPerso);
		// Ajout des boutons
		this.add(radCouAuto);
		this.add(radCouPerso);
		
		// Initialisation des valeurs de choix
		choixObj = Constantes.OBJ_MNT;
		choixCam = Constantes.CAM_DESSUS;
		choixCou = Constantes.COU_AUTO;


	}
	
	
	/*
	 * Getters associ�s aux valeurs des choix
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

}
