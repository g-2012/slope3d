package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import Utils.Constantes;

public class MenuReglage extends JMenu implements ItemListener{
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
	private Color[] couleurs; // { [0°, 10°[ , [10°, 20°[ , [20°, 30°[ , [30°, 40°[ , [40°, 50°[ , [50°, 60°[ ,  [60°, 70°[ , [70°, 80°[ , [80°, 90°] }


	/*
	 * Constructeur
	 */
	public MenuReglage() {
		super("Reglages");

		
		// Menu de sélection des objets affichés
		JLabel titreObj = new JLabel("  Objets affichés  ", CENTER);
		titreObj.setHorizontalTextPosition(CENTER);
		this.add(titreObj);
		// Création des cases à cocher
		chkObjMNT = new JCheckBoxMenuItem("MNT", true);
		chkObjIsoligne = new JCheckBoxMenuItem("Isolignes");
		// Ajout de l'écouteur
		chkObjMNT.addItemListener(this);
		chkObjIsoligne.addItemListener(this);
		// Ajout des cases au menu
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
		// Ajout de l'écouteur
		radCamOrtho.addItemListener(this);
		radCamOrbite.addItemListener(this);
		radCamPerso.addItemListener(this);
		// Groupement des boutons
		grpCamera.add(radCamOrtho);
		grpCamera.add(radCamOrbite);
		grpCamera.add(radCamPerso);
		// Désactivation du bouton de caméra libre en attendant l'implémentation de cette caméra
		radCamPerso.setEnabled(false);
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
		// Ajout de l'écouteur
		radCouAuto.addItemListener(this);
		radCouPerso.addItemListener(this);
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
		couleurs = new Color[9];
		couleurs[0] = new Color(0, 0, 0); 		// [0°, 10°[
		couleurs[1] = new Color(30, 0, 30); 	// [10°, 20°[
		couleurs[2] = new Color(60, 0, 60); 	// [20°, 30°[
		couleurs[3] = new Color(90, 0, 90);	// [30°, 40°[
		couleurs[4] = new Color(120, 0, 120);	// [40°, 50°[
		couleurs[5] = new Color(150, 0, 150);	// [50°, 60°[
		couleurs[6] = new Color(180, 0, 180);	// [60°, 70°[
		couleurs[7] = new Color(210, 0, 210);	// [70°, 80°[
		couleurs[8] = new Color(240, 0, 240);	// [80°, 90°]

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
		return couleurs;
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 * 
	 * Actions à déclencher en cas de modification des items sélectionnés
	 */
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		int action = e.getStateChange();
		
		// Changement sur les cases cochées pour le choix des abjets affichés
		if(source == chkObjMNT){
			if(chkObjIsoligne.getState() == true){
				if(action == ItemEvent.SELECTED){
					choixObj = Constantes.OBJ_TOUT;
				}else{
					choixObj = Constantes.OBJ_ISOLIGNES;
				}
			}else if(chkObjIsoligne.getState() == false){
				if(action == ItemEvent.SELECTED){
					choixObj = Constantes.OBJ_MNT;
				}else{
					choixObj = Constantes.OBJ_RIEN;
				}
			}
		}else if(source == chkObjIsoligne){
			if(chkObjMNT.getState() == true){
				if(action == ItemEvent.SELECTED){
					choixObj = Constantes.OBJ_TOUT;
				}else{
					choixObj = Constantes.OBJ_MNT;
				}
			}else if(chkObjMNT.getState() == false){
				if(action == ItemEvent.SELECTED){
					choixObj = Constantes.OBJ_ISOLIGNES;
				}else{
					choixObj = Constantes.OBJ_RIEN;
				}
			}
		}
		
		// Changement sur les boutons radio de choix de la caméra
		if (source == radCamOrtho){
			choixCam = Constantes.CAM_DESSUS;
		}else if(source == radCamOrbite){
			choixCam = Constantes.CAM_ORBITE;
		}else if (source == radCamPerso){
			choixCam = Constantes.CAM_PERSO;
		}
		
		// Changement sur les boutons radio de choix de la coloration
		if (source == radCouAuto){
			choixCou = Constantes.COU_AUTO;
		}else if (source == radCouPerso){
			choixCou = Constantes.COU_PERSO;
		}
	}

}
