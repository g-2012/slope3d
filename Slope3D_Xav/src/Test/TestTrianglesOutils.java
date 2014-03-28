package Test;

import Utils.TrianglesOutils;
import structures.Grille;
import Utils.FilesUtils;

/**
 * <b>TestTrianglesOutils est la classe de test de tous les outils permettant d'obtenir les caractéristiques d'un triangle.</b>
 * @author slope3d_merge
 *			Nom du projet.
 */

public class TestTrianglesOutils {
/**
 * <b>Liste des outils permettant de calculer les coordonnées d'un vecteur, sa norme, son produit scalaire, son produit vectoriel, puis le vecteur normal et la pente d'un triangle ainsi que les coordonnées d'un point interpolé entre deux points du triangle.</b>
 * @param args
 * 			Outils testés avec un exemple précis de 3 points d'un triangle quelconque, chacun des points ayant des coordonnées x, y et z.
 */
		public static void main(String[] args) {
			
			
			/* 				Quelques exemples qui fonctionnent :
			 * 
			 * 		A(4,0,0)     B(2,-2,2)     C(0,0,4)      >>  45°
			 * 		A(6,0,2)     B(3,4,1)      C(0,0,0)      >>  18.44°	
			 * 		A(1,0,0)     B(0,1,0)      C(0,0,1)      >>  70.53°
			 * 		A(0,0,0)     B(1,0,0)      C(0,0,1)      >>  90.00°
			 * 		A(0,0,0)     B(9,2,0)      C(-1,6,0)     >>  00.00°
			 */
			
			double[] A = new double[3];
			A[0] = 30  ;
			A[1] = 0   ;
			A[2] = 0   ;
			
			double[] B = new double[3];
			B[0] = -20  ;
			B[1] = 20  ;
			B[2] = 10  ;
			
			double[] C = new double[3];
			C[0] = 150  ;
			C[1] = -180 ;
			C[2] = 100  ;
			
			double[] I = new double[3];
			
			//Permet d'obtenir les coordonnées du vecteur AB
			double[] ab = TrianglesOutils.coordVecteur(A, B);
			System.out.println(" --> Coordonnées du vecteur AB : ");
			System.out.println(" Résultat attendu pour x : -50 ");
			System.out.println(" Résultat calculé pour x : "+ ab[0]);
			System.out.println(" Résultat attendu pour y : 20 ");
			System.out.println(" Résultat calculé pour y : "+ ab[1]);
			System.out.println(" Résultat attendu pour z : 10 ");
			System.out.println(" Résultat calculé pour z : "+ ab[2]);
			
			//Permet d'obtenir la norme du vecteur AB
			double norme = TrianglesOutils.norme(ab);
			System.out.println(" --> Norme du vecteur AB : ");
			System.out.println(" Résultat attendu : 54.77225575 ");
			System.out.println(" Résultat calculé : "+ norme);
			
			//Permet d'obtenir les coordonnées du vecteur AC
			double[] ac = TrianglesOutils.coordVecteur(A, C);
			System.out.println(" --> Coordonnées du vecteur AC : ");
			System.out.println(" Résultat attendu pour x : 120 ");
			System.out.println(" Résultat calculé pour x : "+ ac[0]);
			System.out.println(" Résultat attendu pour y : -180 ");
			System.out.println(" Résultat calculé pour y : "+ ac[1]);
			System.out.println(" Résultat attendu pour z : 100 ");
			System.out.println(" Résultat calculé pour z : "+ ac[2]);
			
			//Permet d'obtenir la norme du vecteur AC
			double norme2 = TrianglesOutils.norme(ac);
			System.out.println(" --> Norme du vecteur AC : ");
			System.out.println(" Résultat attendu : 238.3275058 ");
			System.out.println(" Résultat calculé : "+ norme2);
			
			//Permet d'obtenir le produit scalaire du vecteur BC
			double produitScalaire = TrianglesOutils.produitScalaire(B, C);
			System.out.println(" --> Produit scalaire du vecteur BC : ");
			System.out.println(" Résultat attendu : -5600 ");
			System.out.println(" Résultat calculé : "+ produitScalaire);
			
			//Permet d'obtenir le produit vectoriel du vecteur BC
			double[] produitVectoriel = TrianglesOutils.produitVectoriel(B, C);
			System.out.println(" --> Produit vectoriel du vecteur BC : ");
			System.out.println(" Résultat attendu pour x : 3800 ");
			System.out.println(" Résultat calculé pour x : "+ produitVectoriel[0]);
			System.out.println(" Résultat attendu pour y : 3500 ");
			System.out.println(" Résultat calculé pour y : "+ produitVectoriel[1]);
			System.out.println(" Résultat attendu pour z : 600 ");
			System.out.println(" Résultat calculé pour z : "+ produitVectoriel[2]);
			
			//Permet d'obtenir le vecteur normal du triangle ABC
			double[] n = TrianglesOutils.vecteurNormalTriangle(A, B, C);
			System.out.println(" --> Vecteur normal du triangle ABC : ");
			System.out.println(" Résultat attendu pour x : 0.386950129 ");
			// coordonnée x du produit vectoriel (AB, AC) divisé par la norme
			System.out.println(" Résultat calculé pour x : "+ n[0]);
			System.out.println(" Résultat attendu pour y : 0.6313396841 ");
			// coordonnée y du produit vectoriel (AB, AC) divisé par la norme
			System.out.println(" Résultat calculé pour y : "+ n[1]);
			System.out.println(" Résultat attendu pour z : 0.6720712766 ");
			// coordonnée z du produit vectoriel (AB, AC) divisé par la norme
			System.out.println(" Résultat calculé pour z : "+ n[2]);
			
			//Permet de calculer la pente d'un triangle
			double pente = TrianglesOutils.penteTriangle(n);
			System.out.println(" --> Pente du triangle ABC : ");
			System.out.println(" Résultat attendu : 47.7728711 ");
			System.out.println(" Résultat calculé : "+ pente);
			
			//Permet de calculer les coordonnées du point I interpolé entre A et B
			double[] i = TrianglesOutils.interpol(5, A, B);
			System.out.println(" --> Coordonnées d'un point de côte 5 interpolé entre A et B : ");
			System.out.println(" Résultat attendu pour x : 5 ");
			// Différence de la côte zi du point I et du coefficient directeur de la droite zi=ax+b, divisé par l'ordonnée à l'origine de cette droite.
			System.out.println(" Résultat calculé pour x : "+ i[0]);
			System.out.println(" Résultat attendu pour y : 10 ");
			// Différence de la côte zi du point I et du coefficient directeur de la droite zi=ay+b, divisé par l'ordonnée à l'origine de cette droite.
			System.out.println(" Résultat calculé pour y : "+ i[1]);
			System.out.println(" Résultat attendu pour z : 5 ");
			System.out.println(" Résultat calculé pour z : "+ i[2]);
			
			
		}   //fin du main

	}

