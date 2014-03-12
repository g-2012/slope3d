import java.util.List;
import structures.Grille;
import structures.Triangle;
import Utils.FilesUtils;
import Utils.GrilleATriangles;


public class Exec{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "/test/0473_6915_MNT.tri";
		long startTime = System.nanoTime();
		Grille grille = FilesUtils.loadMNTAsc("/test/0473_6915_MNT.asc");
		long endTime = System.nanoTime();
		System.out.print("Chargement MNT asc en grille : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		
		startTime = System.nanoTime();
		List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		endTime = System.nanoTime();
		System.out.print("Transfo grille en liste de triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println(grille);
		System.out.println(triangles.size() +" triangles");
		/*
		startTime = System.nanoTime();
		FilesUtils.triangles2file(triangles, file);
		endTime = System.nanoTime();
		System.out.print("Ecriture liste de (env 2 000 000) triangles  dans un fichier : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		*/
		/*
		startTime = System.nanoTime();
		List<Triangle> triangles2 = FilesUtils.loadTriangles(file);
		endTime = System.nanoTime();
		System.out.print("Chargement d'env 2 000 000 triangles d'un fichier vers une liste : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		*/
		//triangles.get(5).afficherCaracteristiquesTriangle();
	}

}
