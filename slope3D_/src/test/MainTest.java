package test;

import java.util.Arrays;

import Utils.GrilleATriangles;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrilleTest grille = new GrilleTest();
		//System.out.println(Arrays.deepToString(grille.valeurs)); // affichage pour test du contenu
		GrilleATriangles.grilleVersTriangles(grille);
	}

}
