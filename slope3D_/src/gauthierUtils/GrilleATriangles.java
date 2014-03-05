package gauthierUtils;

import gauthierTest.GrilleTest;

import java.util.ArrayList;


public abstract class GrilleATriangles {
	public static void grilleVersTriangles(GrilleTest grille) { // lit la grille fournie pour en extraire les sommets des triangles
		int nTL = 2*(grille.nCol-1); // nombre de triangles par ligne
		int nTT = nTL*(grille.nLig-1); // nombre total de triangles dans la grille
		System.out.println(nTL+" triangles par ligne, "+nTT+" triangles au total.");
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
					
				listeT.add(new Triangle( n , b , c , d ));
			}
			
		}
		
		/*for (int n = 0; n < nTT; n++){   
			listeT.get(n).afficherCaracteristiquesTriangle();*/
		for (Triangle t: listeT)
			t.afficherCaracteristiquesTriangle();
		}
	}

