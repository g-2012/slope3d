package Utils;

/**
 * Classe contenant des valeurs de constantes utilisées dans le programme
 */
public class Constantes {
	
	/*
	 * Constantes liées au choix des objets affichés dans l'environnement 3D
	 */
	public static byte OBJ_RIEN = 0; // Aucun objet affiché
	public static byte OBJ_MNT = 1; // Seul le MNT est affiché
	public static byte OBJ_ISOLIGNES = 2; // Seules les isolignes sont affichées
	public static byte OBJ_TOUT = 3; // Le MNT et les isolignes sont tous deux affichés

	/*
	 * Constantes liées au choix du type de caméra
	 */
	public static byte CAM_ORBITE = 0; // Caméra en orbite
	public static byte CAM_DESSUS = 1; // Vue orthogonale
	public static byte CAM_PERSO = 2; // Navigation dans l'espace
	
	/*
	 * Constantes liées au choix du code couleurs pour la pente
	 */
	public static boolean COU_AUTO = false; // Nuances de gris de valeur proportionnelle à la pente
	public static boolean COU_PERSO = true; // Plages de couleurs définies par l'utilisateur
}
