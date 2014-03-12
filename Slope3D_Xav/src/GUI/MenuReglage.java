package GUI;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

public class MenuReglage extends JMenu {
	/* Menu permettant d'accéder aux réglages de la visualisation.
	 * Ces réglages incluent :
	 *	- Le réglage des objets affichés (MNT et/ou courbes de niveau)
	 * 	- Le réglage du type de Caméra :
	 * 		° Vue de dessus, déplaçable uniquement par translation
	 * 		° Caméra en orbite préréglée, vitesse de rotation paramétrable
	 *		° Caméra personnalisée déplaçable par rotation et translation
	 *	- Le réglage des plages de couleurs affectées aux différentes pentes :
	 *		° Mode automatique : nuance de gris de valeur proportionnelle à la pente.
	 *		° Mode utilisateur : l'utilisateur choisit des plages de valeurs et y associe des couleurs
	 */
	
	// Cases à cocher pour le choix des objets affichés
	JCheckBoxMenuItem chkObjMNT, chkObjIsoligne;
	// Groupes de boutons radio pour s'assurer que seule une option soit sélectionnable par groupe.
	ButtonGroup grpCamera, grpCouleurs; 
	// Boutons radio
	JRadioButtonMenuItem radCamOrtho, radCamOrbite, radCamPerso, // pour les réglages caméra
						radCouAuto, radCouPerso; // pour les réglages couleurs
	

	
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
		radCouPerso = new JRadioButtonMenuItem("Personnalisé");
		// Groupement des boutons
		grpCouleurs.add(radCouAuto);
		grpCouleurs.add(radCouPerso);
		// Ajout des boutons
		this.add(radCouAuto);
		this.add(radCouPerso);
		
		
		

		
	}
	

}
