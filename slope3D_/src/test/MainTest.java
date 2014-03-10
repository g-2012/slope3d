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
		
		Grille grille = FilesUtils.loadMNTAsc("D:\\Slope3D\\MNT\\ASC\\0473_6915_MNT.asc");
		String file = "D:\\Slope3D\\MNT\\ASC\\0473_6915_MNT.tri";
		List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		
		FilesUtils.triangles2file(triangles, file);
	}

}
