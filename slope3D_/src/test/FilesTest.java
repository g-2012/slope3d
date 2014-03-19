package test;

import java.awt.geom.Point2D;
import java.util.List;

import structures.Grille;
import structures.Triangle;
import Utils.FilesUtils;
import Utils.GrilleATriangles;

public class FilesTest {
	public static void main(String[] args) {
		String file1 = "/test/fakemnt.asc";
		String file2 = "/test/Ecrins2.xyz";
		String file3 = "/test/0473_6915_MNT.asc";
		String trianglesOut = "/test/triangles.tri";
		
		long startTime = System.nanoTime();
		Grille grille = FilesUtils.loadMNTAsc(file3);
		//Grille grille = FilesUtils.loadMNTxyz2(file2);
		long endTime = System.nanoTime();
		System.out.print("chargement grille from file : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		
		/*
		startTime = System.nanoTime();
		Triangle[] triangles = GrilleATriangles.grilleVersTriangles2(grille);
		endTime = System.nanoTime();
		System.out.print("Transfo grille en tableau de triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println("nb triangles : " + triangles.length);
		*/
		
		//creation d'une liste de triangles à partir de la grille
		startTime = System.nanoTime();
		List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		endTime = System.nanoTime();
		System.out.print("Transfo grille en liste de triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println("nb triangles : " + triangles.size());
		
		
		// Creation d'un fichier de triangles
		startTime = System.nanoTime();
		FilesUtils.triangles2file(triangles, trianglesOut);
		endTime = System.nanoTime();
		System.out.print("Ecriture liste de "+ triangles.size()+ " triangles  dans un fichier : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
				
		//chargement d'un fichier de triangles
		startTime = System.nanoTime();
		List<Triangle> triangles2 = FilesUtils.loadTriangles(trianglesOut);
		endTime = System.nanoTime();
		System.out.print("Chargement de "+ triangles.size()+ " triangles d'un fichier vers une liste : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		
		
	}
}
