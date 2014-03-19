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
				double a[] = { //sommet sup�rieur gauche du triangle
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
				double d[] = { //sommet inf�rieur droit du triangle
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
	
	public static Triangle[] grilleVersTriangles2(Grille grille) { // lit la grille fournie pour en extraire les sommets des triangles
		int nTL = 2*(grille.nCol-1); // nombre de triangles par ligne
		int nTT = nTL*(grille.nLig-1); // nombre total de triangles dans la grille
		//System.out.println(nTL+" triangles par ligne, "+nTT+" triangles au total.");
		Triangle[] listeT = new Triangle[nTT];
		int i,j;
		
		for (int n = 0; n < nTT; n++){
			i = n / nTL; // indice de la ligne du coin sup�rieur gauche de la case dans la grille
			j = (n - i*nTL) / 2; // indice de la colonne du coin sup�rieur gauche de la case dans la grille
			
			if (n%2 == 0){ // indice pair -> triangle droit
				double a[] = { //sommet sup�rieur gauche du triangle
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
				
				listeT[n] = new Triangle( n , a , b , c );
			}
			
			else if(n%2 == 1) { // indice impair -> triangle invers�
				double d[] = { //sommet inf�rieur droit du triangle
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
					
				listeT[n] = new Triangle( n , b , c , d );
			}
			
		}
		
		/*for (Triangle t: listeT)
			t.afficherCaracteristiquesTriangle();*/
		return listeT;
		}
	
	
	public static List<Triangle> grilleVersTrianglesStandard(Grille grille) {
		/*
		 * Extrait une liste d'objets Triangle depuis la la grille de MNT fournie en entr�e.
		 * Les coordonn�es des points sont ici ramen�es � l'�chelle du mod�le affich� sous OpenGL,
		 * le centre du MNT est ramen� � l'origine planim�trique du rep�re de l'environnement 3D,
		 * et l'altitude minimale est ramen�e � 0.
		 * Ceci permet d'exprimer les coordonn�es des points du MNT dans un rep�re et une �chelle plus pratique pour la mod�lisation 3D,
		 * et ainsi d'�viter de recalculer le changement de rep�re � chaque tic de l'animateur OpenGL.
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
			i = n / nTL; // indice de la ligne du coin sup�rieur gauche de la case dans la grille
			j = (n - i*nTL) / 2; // indice de la colonne du coin sup�rieur gauche de la case dans la grille
			
			if (n%2 == 0){ // indice pair -> triangle droit
				double a[] = { //sommet sup�rieur gauche du triangle
					(j * grille.pas - (empriseX/2)) * 200/empriseX, // absisse du sommet (Easting)
					(-i * grille.pas + (empriseY/2)) * 200/empriseX, // ordonn�e du sommet (Northing)
					(grille.valeurs[i][j] - zMin) * 200/empriseX // altitude du sommet
				};
				
				double b[] = { //sommet sup�rieur droit du triangle
						((j+1) * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - i * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i][j+1] - zMin) * 200/empriseX
				};
				
				double c[] = { //sommet inf�rieur gauche du triangle
						(j * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - (i+1) * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i+1][j] - zMin) * 200/empriseX
				};
				
				listeT.add(new Triangle( n , a , b , c ));
			}
			
			else if(n%2 == 1) { // indice impair -> triangle invers�
				double d[] = { //sommet inf�rieur droit du triangle
						((j+1) * grille.pas - (empriseX/2)) * 200/empriseX, // absisse du sommet en m (Easting)
						(- (i+1) * grille.pas + (empriseY/2)) * 200/empriseX, // ordonn�e du sommet en m (Northing)
						(grille.valeurs[i+1][j+1] - zMin) * 200/empriseX // altitude du sommet
				};
				
				double b[] = { //sommet sup�rieur droit du triangle
						((j+1) * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - i * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i][j+1] - zMin) * 200/empriseX
				};
				
				double c[] = { //sommet inf�rieur gauche du triangle
						(j * grille.pas - (empriseX/2)) * 200/empriseX,
						(0 - (i+1) * grille.pas + (empriseY/2)) * 200/empriseX,
						(grille.valeurs[i+1][j] - zMin) * 200/empriseX
				};
					
				listeT.add(new Triangle( n , d , b , c ));
			}
			
		}
		
		/*for (Triangle t: listeT)
			t.afficherCaracteristiquesTriangle();*/
		return listeT;
	}
	
	
	
	}

