import imranUtils.SimpleFrame;

import java.awt.EventQueue;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import structures.Grille;
import structures.Isoligne;
import structures.Triangle;
import Utils.FilesUtils;
import Utils.GrilleATriangles;


public class Exec{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "/test/MNT_ios200_test.asc";
		String file2 = "/test/fakemnt.asc";
		String file3 = "/test/0473_6915_MNT.asc";
		String file4 = "/test/Ecrins2.xyz";
		String file5 = "/test/MNT_tests/iso50_MNT_test_02.asc";
		
		//chargement d'une grille depuis un fichier
		long startTime = System.nanoTime();
		//final Grille grille = FilesUtils.loadMNTxyz2(file4);
		final Grille grille = FilesUtils.loadMNTAsc(file5);
		long endTime = System.nanoTime();
		System.out.print("Chargement MNT xyz en grille : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println(grille);
		
		
		
		/* 
		startTime = System.nanoTime();
		FilesUtils.triangles2file(triangles, file);
		endTime = System.nanoTime();
		System.out.print("Ecriture liste de (env 2 000 000) triangles  dans un fichier : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
				
		startTime = System.nanoTime();
		List<Triangle> triangles2 = FilesUtils.loadTriangles(file);
		endTime = System.nanoTime();
		System.out.print("Chargement d'env 2 000 000 triangles d'un fichier vers une liste : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		*/
		
		//Transfo grille en triangles
		
		startTime = System.nanoTime();
		final List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		endTime = System.nanoTime();
		System.out.print("Transfo grille en liste de triangles : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println(triangles.size() +" triangles");
		

		//calcul isolignes à partir triangles
		
		startTime = System.nanoTime();
		final List<Isoligne> isos = new ArrayList<Isoligne>();
		//for (double i = (int)grille.zMinMax()[0] ; i < (int)grille.zMinMax()[1]; i += 300)
			isos.add(new Isoligne(50, triangles));
		endTime = System.nanoTime();
		System.out.print("Creation isolignes : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		
		System.out.println("Nb isolignes :"+isos.size());
		
		
		//calcul isolignes à partir de la grille directoss
		/*
		startTime = System.nanoTime();
		final ArrayList<List<Point2D.Double[]>> segments = new ArrayList<List<Point2D.Double[]>>();
		for (double i = (int)grille.zMinMax()[0] ; i < (int)grille.zMinMax()[1]; i += 10){
			segments.add(grille.makeIsoZt(50));
			//System.out.println(i);
		}
		endTime = System.nanoTime();
		System.out.print("Creation isolignes : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println("Nb isolignes :"+segments.size());
		*/
		
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				SimpleFrame frame = new SimpleFrame(isos, grille);
				frame.setVisible(true);
			}
		});
	}

}
