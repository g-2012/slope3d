package test;

import Utils.FilesUtils;
import Utils.GrilleATriangles;

import java.util.Arrays;
import java.util.List;

import structures.Grille;
import structures.Triangle;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Grille grille = FilesUtils.loadMNTAsc("/test/testMNT.asc");
		//String file = "/test/test.tri";
		String file = "/test/0473_6915_MNT.tri";
		String file2 = "/test/makabo.tri";
		//List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		
		//FilesUtils.triangles2file(triangles, file);
		List<Triangle> triangles = FilesUtils.loadTriangles(file);
		System.out.println(triangles.size());
		//FilesUtils.triangles2file(triangles, file2);
	}

}
