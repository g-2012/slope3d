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
	/** Menu permettant d'acc�der aux r�glages de la visualisation.
	 * Ces r�glages incluent :
	 *	- Le r�glage des objets affich�s (MNT et/ou courbes de niveau)
	 * 	- Le r�glage du type de Cam�ra :
	 * 		� Vue de dessus, d�pla�able uniquement par translation
	 * 		� Cam�ra en orbite pr�r�gl�e, vitesse de rotation param�trable
	 *		� Cam�ra personnalis�e d�pla�able par rotation et translation
	 *	- Le r�glage des plages de couleurs affect�es aux diff�rentes pentes :
	 *		� Mode automatique : nuance de gris de valeur proportionnelle � la pente.
	 *		� Mode utilisateur : l'utilisateur choisit les couleurs associ�es � des plages de valeurs (10� d'amplitude)
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
	 * Param�tres : couleurs choisies pour la coloration personnalis�e, en fonction de la valeur de la pente
	 */
	private Color[] couleurs; // { [0�, 10�[ , [10�, 20�[ , [20�, 30�[ , [30�, 40�[ , [40�, 50�[ , [50�, 60�[ ,  [60�, 70�[ , [70�, 80�[ , [80�, 90�] }


	/*
	 * Constructeur
	 */
	public MenuReglage() {
		super("Reglages");

		
		// Menu de s�lection des objets affich�s
		JLabel titreObj = new JLabel("  Objets affich�s  ", CENTER);
		titreObj.setHorizontalTextPosition(CENTER);
		this.add(titreObj);
		// Cr�ation des cases � cocher
		chkObjMNT = new JCheckBoxMenuItem("MNT", true);
		chkObjIsoligne = new JCheckBoxMenuItem("Isolignes");
		// Ajout de l'�couteur
		chkObjMNT.addItemListener(this);
		chkObjIsoligne.addItemListener(this);
		// Ajout des cases au menu
		this.add(chkObjMNT);
		this.add(chkObjIsoligne);


		this.addSeparator();


		// Menu de s�lection du type de vue
		JLabel titreCam = new JLabel("  Type de cam�ra  ", CENTER);
		titreCam.setHorizontalTextPosition(CENTER);
		this.add(titreCam);
		grpCamera = new ButtonGroup();
		radCamOrtho = new JRadioButtonMenuItem("Vue de dessus");
		radCamOrbite = new JRadioButtonMenuItem("En orbite", true);
		radCamPerso = new JRadioButtonMenuItem("Navigation 3D");
		// Ajout de l'�couteur
		radCamOrtho.addItemListener(this);
		radCamOrbite.addItemListener(this);
		radCamPerso.addItemListener(this);
		// Groupement des boutons
		grpCamera.add(radCamOrtho);
		grpCamera.add(radCamOrbite);
		grpCamera.add(radCamPerso);
		// D�sactivation du bouton de cam�ra libre en attendant l'impl�mentation de cette cam�ra
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
		radCouPerso = new JRadioButtonMenuItem("Personnalis�");
		// Ajout de l'�couteur
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
		
		// Initialisation des couleurs par d�faut
		couleurs = new Color[9];
		couleurs[0] = new Color(0, 0, 0); 		// [0�, 10�[
		couleurs[1] = new Color(30, 0, 30); 	// [10�, 20�[
		couleurs[2] = new Color(60, 0, 60); 	// [20�, 30�[
		couleurs[3] = new Color(90, 0, 90);	// [30�, 40�[
		couleurs[4] = new Color(120, 0, 120);	// [40�, 50�[
		couleurs[5] = new Color(150, 0, 150);	// [50�, 60�[
		couleurs[6] = new Color(180, 0, 180);	// [60�, 70�[
		couleurs[7] = new Color(210, 0, 210);	// [70�, 80�[
		couleurs[8] = new Color(240, 0, 240);	// [80�, 90�]

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
	
	public Color[] getCouleurs(){
		return couleurs;
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 * 
	 * Actions � d�clencher en cas de modification des items s�lectionn�s
	 */
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		int action = e.getStateChange();
		
		// Changement sur les cases coch�es pour le choix des abjets affich�s
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
		
		// Changement sur les boutons radio de choix de la cam�ra
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
