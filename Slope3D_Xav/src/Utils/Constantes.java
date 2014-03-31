package Utils;

import javax.swing.UIManager;

/**
 * Classe contenant des valeurs de constantes utilisées dans le programme.
 * 
 * @author JIGX
 * @version 0.6
 */
public class Constantes {
	/*
	 * Constantes liées au choix des objets affichés dans l'environnement 3D
	 */
	public static final byte OBJ_RIEN = 0; // Aucun objet affiché
	public static final byte OBJ_MNT = 1; // Seul le MNT est affiché
	public static final byte OBJ_ISOLIGNES = 2; // Seules les isolignes sont
												// affichées
	public static final byte OBJ_TOUT = 3; // Le MNT et les isolignes sont tous
											// deux affichés

	/*
	 * Constantes liées au choix du type de caméra
	 */
	public static final byte CAM_ORBITE = 0; // Caméra en orbite
	public static final byte CAM_DESSUS = 1; // Vue orthogonale
	public static final byte CAM_PERSO = 2; // Navigation dans l'espace

	/*
	 * Constantes liées au choix du code couleurs pour la pente
	 */
	public static final boolean COU_AUTO = false; // Nuances de gris de valeur
													// proportionnelle à la
													// pente
	public static final boolean COU_PERSO = true; // Plages de couleurs définies
													// par l'utilisateur

	/*
	 * Textes des composants de type FileChosser pour la localisation française
	 */
	public static void langueFR() {
		UIManager.put("FileChooser.saveButtonText", "Enregistrer");
		UIManager.put("FileChooser.openButtonText", "Ouvrir");
		UIManager.put("FileChooser.cancelButtonText", "Annuler");
		UIManager.put("FileChooser.updateButtonText", "Actualiser");
		UIManager.put("FileChooser.helpButtonText", "Aide");
		UIManager.put("FileChooser.saveButtonToolTipText",
				"Enregistre le fichier");
		UIManager.put("FileChooser.fileNameLabelText", "Nom du fichier");
		UIManager.put("FileChooser.homeFolderToolTipText", "Dossier personnel");
		UIManager.put("FileChooser.newFolderToolTipText", "Nouveau dossier");
		UIManager.put("FileChooser.listViewButtonToolTipText", "Liste");
		UIManager.put("FileChooser.detailsViewButtonToolTipText", "Détails");
		UIManager.put("FileChooser.openButtonToolTipText",
				"Ouvrir le fichier sélectionné");
		UIManager.put("FileChooser.cancelButtonToolTipText",
				"Annuler et fermer la boîte de dialogue");
		UIManager.put("FileChooser.updateButtonToolTipText", "Rafraichir");
		UIManager.put("FileChooser.helpButtonToolTipText", "Ouvrir l'aide");
		UIManager.put("FileChooser.lookInLabelText", "Courant");
		UIManager.put("FileChooser.filesOfTypeLabelText", "Type de fichiers");
		UIManager.put("FileChooser.upFolderToolTipText", "Dossier parent");
		UIManager.put("FileChooser.openDialogTitleText", "Ouvrir un fichier");
	}
}
