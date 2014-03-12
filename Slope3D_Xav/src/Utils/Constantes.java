package Utils;

/**
 * Classe contenant des valeurs de constantes utilis�es dans le programme
 */
public class Constantes {
	
	/*
	 * Constantes li�es au choix des objets affich�s dans l'environnement 3D
	 */
	public static byte OBJ_RIEN = 0; // Aucun objet affich�
	public static byte OBJ_MNT = 1; // Seul le MNT est affich�
	public static byte OBJ_ISOLIGNES = 2; // Seules les isolignes sont affich�es
	public static byte OBJ_TOUT = 3; // Le MNT et les isolignes sont tous deux affich�s

	/*
	 * Constantes li�es au choix du type de cam�ra
	 */
	public static byte CAM_ORBITE = 0; // Cam�ra en orbite
	public static byte CAM_DESSUS = 1; // Vue orthogonale
	public static byte CAM_PERSO = 2; // Navigation dans l'espace
	
	/*
	 * Constantes li�es au choix du code couleurs pour la pente
	 */
	public static boolean COU_AUTO = false; // Nuances de gris de valeur proportionnelle � la pente
	public static boolean COU_PERSO = true; // Plages de couleurs d�finies par l'utilisateur
}
