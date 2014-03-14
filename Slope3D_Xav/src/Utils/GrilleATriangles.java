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
			i = n / nTL; // indice de la ligne du coin sup�rieur gauche de la case dans la grille
			j = (n - i*nTL) / 2; // indice de la colonne du coin sup�rieur gauche de la case dans la grille
			
			if (n%2 == 0){ // indice pair -> triangle droit
				double a[] = { //sommet sup�rieur gauche du triangle (angle droit)
					grille.x0 + j * grille.pas, // absisse du sommet en m (Easting)
					grille.y0 - i * grille.pas, // ordonn�e du sommet en m (Northing)
					grille.valeurs[i][j] // altitude du sommet
				};
				
				double b[] = { //sommet sup�rieur droit du triangle
						grille.x0 + (j+1) * grille.pas,
						grille.y0 - i * grille.pas,
						grille.valeurs[i][j+1]
				};
				
				double c[] = { //sommet inf�rieur gauche du triangle
						grille.x0 + j * grille.pas,
						grille.y0 - (i+1) * grille.pas,
						grille.valeurs[i+1][j]
				};
				
				listeT.add(new Triangle( n , a , b , c ));
			}
			
			else if(n%2 == 1) { // indice impair -> triangle invers�
				double d[] = { //sommet inf�rieur droit du triangle (angle droit)
						grille.x0 + (j+1) * grille.pas, // absisse du sommet en m (Easting)
						grille.y0 - (i+1) * grille.pas, // ordonn�e du sommet en m (Northing)
						grille.valeurs[i+1][j+1] // altitude du sommet
				};
				
				double b[] = { //sommet sup�rieur droit du triangle
							grille.x0 + (j+1) * grille.pas,
							grille.y0 - i * grille.pas,
							grille.valeurs[i][j+1]
				};
				
				double c[] = { //sommet inf�rieur gauche du triangle
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
	}

