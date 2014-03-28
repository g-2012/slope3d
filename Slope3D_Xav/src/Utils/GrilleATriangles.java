package Utils;

import structures.Grille;
import structures.Triangle;

import java.util.ArrayList;
import java.util.List;


public abstract class GrilleATriangles {
	public static List<Triangle> grilleVersTriangles(Grille grille) { // lit la grille fournie pour en extraire les sommets des triangles
		int nTL = 2*(grille.nCol-1); // nombre de triangles par ligne
		int nTT = nTL*(grille.nLig-1); // nombre total de triangles dans la grille
		//System.out.println(nTL+" triangles par ligne, "+nTT+" triangles au total.");
		ArrayList<Triangle> listeT = new ArrayList<>();
		int i,j;
		
		for (int n = 0; n < nTT; n++){
			i = n / nTL; // indice de la ligne du coin supérieur gauche de la case dans la grille
			j = (n - i*nTL) / 2; // indice de la colonne du coin supérieur gauche de la case dans la grille
			
			if (n%2 == 0){ // indice pair -> triangle droit
				double a[] = { //sommet supérieur gauche du triangle
					grille.x0 + j * grille.pas, // absisse du sommet en m (Easting)
					grille.y0 - i * grille.pas, // ordonnée du sommet en m (Northing)
					grille.valeurs[i][j] // altitude du sommet
				};
				
				double b[] = { //sommet supérieur droit du triangle
						grille.x0 + (j+1) * grille.pas,
						grille.y0 - i * grille.pas,
						grille.valeurs[i][j+1]
				};
				
				double c[] = { //sommet inférieur gauche du triangle
						grille.x0 + j * grille.pas,
						grille.y0 - (i+1) * grille.pas,
						grille.valeurs[i+1][j]
				};
				
				listeT.add(new Triangle( n , a , b , c ));
			}
			
			else if(n%2 == 1) { // indice impair -> triangle inversé
				double d[] = { //sommet inférieur droit du triangle
						grille.x0 + (j+1) * grille.pas, // absisse du sommet en m (Easting)
						grille.y0 - (i+1) * grille.pas, // ordonnée du sommet en m (Northing)
						grille.valeurs[i+1][j+1] // altitude du sommet
				};
				
				double b[] = { //sommet supérieur droit du triangle
							grille.x0 + (j+1) * grille.pas,
							grille.y0 - i * grille.pas,
							grille.valeurs[i][j+1]
				};
				
				double c[] = { //sommet inférieur gauche du triangle
							grille.x0 + j * grille.pas,
							grille.y0 - (i+1) * grille.pas,
							grille.valeurs[i+1][j]
				};
					
				listeT.add(new Triangle( n , d , b , c ));
			}
			
		}
		
		/*for (Triangle t: listeT)
			t.afficherCaracteristiquesTriangle();*/
		return listeT;
		}
	
	public static Triangle[] grilleVersTriangles2(Grille grille) { // lit la grille fournie pour en extraire les sommets des triangles
		int nTL = 2*(grille.nCol-1); // nombre de triangles par ligne
		int nTT = nTL*(grille.nLig-1); // nombre total de triangles dans la grille
		//System.out.println(nTL+" triangles par ligne, "+nTT+" triangles au total.");
		Triangle[] listeT = new Triangle[nTT];
		int i,j;
		
		for (int n = 0; n < nTT; n++){
			i = n / nTL; // indice de la ligne du coin supérieur gauche de la case dans la grille
			j = (n - i*nTL) / 2; // indice de la colonne du coin supérieur gauche de la case dans la grille
			
			if (n%2 == 0){ // indice pair -> triangle droit
				double a[] = { //sommet supérieur gauche du triangle
					grille.x0 + j * grille.pas, // absisse du sommet en m (Easting)
					grille.y0 - i * grille.pas, // ordonnée du sommet en m (Northing)
					grille.valeurs[i][j] // altitude du sommet
				};
				
				double b[] = { //sommet supérieur droit du triangle
						grille.x0 + (j+1) * grille.pas,
						grille.y0 - i * grille.pas,
						grille.valeurs[i][j+1]
				};
				
				double c[] = { //sommet inférieur gauche du triangle
						grille.x0 + j * grille.pas,
						grille.y0 - (i+1) * grille.pas,
						grille.valeurs[i+1][j]
				};
				
				listeT[n] = new Triangle( n , a , b , c );
			}
			
			else if(n%2 == 1) { // indice impair -> triangle inversé
				double d[] = { //sommet inférieur droit du triangle
						grille.x0 + (j+1) * grille.pas, // absisse du sommet en m (Easting)
						grille.y0 - (i+1) * grille.pas, // ordonnée du sommet en m (Northing)
						grille.valeurs[i+1][j+1] // altitude du sommet
				};
				
				double b[] = { //sommet supérieur droit du triangle
							grille.x0 + (j+1) * grille.pas,
							grille.y0 - i * grille.pas,
							grille.valeurs[i][j+1]
				};
				
				double c[] = { //sommet inférieur gauche du triangle
							grille.x0 + j * grille.pas,
							grille.y0 - (i+1) * grille.pas,
							grille.valeurs[i+1][j]
				};
					
				listeT[n] = new Triangle( n , b , c , d );
			}
			
		}
		
		/*for (Triangle t: listeT)
			t.afficherCaracteristiquesTriangle();*/
		return listeT;
		}
	
	public static List<Triangle> grilleVersTrianglesStandard(Grille grille) {
		/*
		 * Extrait une liste d'objets Triangle depuis la la grille de MNT fournie en entrée.
		 * Les coordonnées des points sont ici ramenées à l'échelle du modéle affiché sous OpenGL,
		 * le centre du MNT est ramené à l'origine planimétrique du repère de l'environnement 3D,
		 * et l'altitude minimale est ramenée à 0.
		 * Ceci permet d'exprimer les coordonnées des points du MNT dans un repère et une échelle plus pratique pour la modélisation 3D,
		 * et ainsi d'éviter de recalculer le changement de repère à chaque tic de l'animateur OpenGL.
		 */

		int nTL = 2*(grille.nCol-1); // nombre de triangles par ligne
		int nTT = nTL*(grille.nLig-1); // nombre total de triangles dans la grille
		//System.out.println(nTL+" triangles par ligne, "+nTT+" triangles au total.");
		ArrayList<Triangle> listeT = new ArrayList<>();
		int i,j;
		double empriseX = grille.pas*(grille.nCol-1),
				empriseY = grille.pas*(grille.nLig-1),
				zMin = grille.zMinMax()[0];
		System.out.println("Emprise X : "+empriseX+"m, Emprise Y : "+empriseY+"m");

		for (int n = 0; n < nTT; n++){
			i = n / nTL; // indice de la ligne du coin supérieur gauche de la case dans la grille
			j = (n - i*nTL) / 2; // indice de la colonne du coin supérieur gauche de la case dans la grille

			if (n%2 == 0){ // indice pair -> triangle droit
				double a[] = { //sommet supérieur gauche du triangle
						(j * grille.pas - (empriseX/2)) * 200/empriseX, // absisse du sommet (Easting)
						(-i * grille.pas + (empriseY/2)) * 200/empriseX, // ordonnée du sommet (Northing)
						(grille.valeurs[i][j] - zMin) * 200/empriseX // altitude du sommet
				};

				double b[] = { //sommet supérieur droit du triangle
						((j+1) * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - i * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i][j+1] - zMin) * 200/empriseX
				};

				double c[] = { //sommet inférieur gauche du triangle
						(j * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - (i+1) * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i+1][j] - zMin) * 200/empriseX
				};

				listeT.add(new Triangle( n , a , b , c ));
			}

			else if(n%2 == 1) { // indice impair -> triangle inversé
				double d[] = { //sommet inférieur droit du triangle
						((j+1) * grille.pas - (empriseX/2)) * 200/empriseX, // absisse du sommet en m (Easting)
						(- (i+1) * grille.pas + (empriseY/2)) * 200/empriseX, // ordonnée du sommet en m (Northing)
						(grille.valeurs[i+1][j+1] - zMin) * 200/empriseX // altitude du sommet
				};

				double b[] = { //sommet supérieur droit du triangle
						((j+1) * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - i * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i][j+1] - zMin) * 200/empriseX
				};

				double c[] = { //sommet inférieur gauche du triangle
						(j * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - (i+1) * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i+1][j] - zMin) * 200/empriseX
				};

				listeT.add(new Triangle( n , d , b , c ));
			}

		}
		return listeT;
	}
	}

