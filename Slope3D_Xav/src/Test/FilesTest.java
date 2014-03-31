package Test;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		String trianglesOutB = "/test/triangles.dat";
		
		long startTime = System.nanoTime();
		Grille grille = FilesUtils.loadMNTAsc(file3);
		//Grille grille = FilesUtils.loadMNTxyz2(file2);
		long endTime = System.nanoTime();
		System.out.print("chargement grille from file : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		
		/*
		 //creation d'un tableau de triangles � partir de la grille
		startTime = System.nanoTime();
		Triangle[] triangles = GrilleATriangles.grilleVersTriangles2(grille);
		endTime = System.nanoTime();
		System.out.print("Transfo grille en tableau de triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println("nb triangles : " + triangles.length);
		*/
		
		//creation d'une liste de triangles � partir de la grille
		startTime = System.nanoTime();
		List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		endTime = System.nanoTime();
		System.out.print("Transfo grille en liste de triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println("nb triangles : " + triangles.size());
		
		/*
		// Creation d'un fichier de triangles
		startTime = System.nanoTime();
		FilesUtils.triangles2file(triangles, trianglesOut);
		endTime = System.nanoTime();
		System.out.print("Ecriture liste de "+ triangles.size()+ " triangles dans un fichier : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		*/	
		/*
		//chargement d'un fichier de triangles
		startTime = System.nanoTime();
		List<Triangle> triangles2 = FilesUtils.loadTriangles(trianglesOut);
		endTime = System.nanoTime();
		System.out.print("Chargement de "+ triangles.size()+ " triangles d'un fichier vers une liste : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		*/
		
		
		//on serialise la liste de triangles
		startTime = System.nanoTime();
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(trianglesOutB));
			//int i =0;
			//for (Triangle t:triangles){
				out.writeObject(triangles);
				//System.out.println(++i);
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		System.out.print("Serialisation liste de "+ triangles.size()+ " triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		
		
		// on deserialise la liste
		startTime = System.nanoTime();
		List<Triangle> triangles2 = null;
		try {
			FileInputStream fileIn = new FileInputStream(trianglesOutB);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			triangles2 = (List) in.readObject();
				//for(Triangle t:in)
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		System.out.print("Deserialisation liste de "+ triangles2.size()+ " triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		startTime = System.nanoTime();
		
	}
}