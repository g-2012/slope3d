import java.util.List;

import structures.Grille;
import structures.Triangle;
import Utils.FilesUtils;
import Utils.GrilleATriangles;


public class Exec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "/test/0473_6915_MNT.tri";
		//Grille grille = FilesUtils.loadMNTAsc("/test/0473_6915_MNT.asc");
		//List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		//FilesUtils.triangles2file(triangles, file);
		List<Triangle> triangles = FilesUtils.loadTriangles(file);
		triangles.get(5).afficherCaracteristiquesTriangle();
	}

}
