package gauthierTest;

//import gauthierUtils.FilesUtils;
import gauthierTest.Grille;
import gauthierUtils.GrilleATriangles;
import gauthierUtils.Triangle;
import gauthierUtils.TrianglesOutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import Utils.FilesUtils;



public class Isoligne {

	/*******************************************************************************************************
	 ******    ATTRIBUTS
	 *******************************************************************************************************/	
	
	public		double				z;
	public		ArrayList<Point> 	listeP   = new ArrayList<>();
	public		ArrayList<double[][]>	segments = new ArrayList<>();
	
	/*******************************************************************************************************
	 ******   CONSTRUCTEURS
	 *******************************************************************************************************/				
	
	public Isoligne ( double zi , List<Triangle> listeT ){
		
		z = zi;
		double[] pi = new double[3];
		double[] pf = new double[3];
		pi[0] = pi[1] = pi[2] = 0;
		pf[0] = pf[1] = pf[2] = 0;
		int k = 0;
		boolean rien = false;
		for (int i = 0 ; i<listeT.size() ; i++){
			System.out.print("\n"+i+"eme triangle \n");
			System.out.print(listeT.get(i).geta()[0]+" ");
			System.out.print(listeT.get(i).geta()[1]+" ");
			System.out.println(listeT.get(i).geta()[2]+" ");
			
			System.out.print(listeT.get(i).getb()[0]+" ");
			System.out.print(listeT.get(i).getb()[1]+" ");
			System.out.println(listeT.get(i).getb()[2]+" ");
			
			System.out.print(listeT.get(i).getc()[0]+" ");
			System.out.print(listeT.get(i).getc()[1]+" ");
			System.out.println(listeT.get(i).getc()[2]+"\n ");
			
			//1
			if (	listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()<zi  ||  
					listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()>zi  ) {
					
					pi =  listeT.get(i).geta();
					pf =  TrianglesOutils.interpol(  zi , listeT.get(i).getb() , listeT.get(i).getc() );
			} 
			
			//2
			if (	listeT.get(i).getz1()>zi  &&   listeT.get(i).getz2()==zi   &&   listeT.get(i).getz3()<zi  ||  
					listeT.get(i).getz1()<zi  &&   listeT.get(i).getz2()==zi   &&   listeT.get(i).getz3()>zi  ) {
					
					pi =  listeT.get(i).getb();
					pf =  TrianglesOutils.interpol(  zi , listeT.get(i).geta() , listeT.get(i).getc() );
				} 
			//3
			if (	listeT.get(i).getz1()>zi  &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()==zi  ||  
					listeT.get(i).getz1()<zi  &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()==zi  ) {
					
					pi =  listeT.get(i).getc();
					pf =  TrianglesOutils.interpol(  zi , listeT.get(i).geta() , listeT.get(i).getb() );
			} 
			
			//4
			if (	listeT.get(i).getz1()>zi  &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()<zi  ||  
					listeT.get(i).getz1()<zi  &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()>zi  ) {
				
					pi =  TrianglesOutils.interpol(  zi , listeT.get(i).geta() , listeT.get(i).getb() );
					pf =  TrianglesOutils.interpol(  zi , listeT.get(i).geta() , listeT.get(i).getc() );
			} 
			
			//5
			if (	listeT.get(i).getz1()<zi  &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()<zi  ||  
					listeT.get(i).getz1()>zi  &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()>zi  ) {
				
					pi =  TrianglesOutils.interpol(  zi , listeT.get(i).geta() , listeT.get(i).getb() );
					pf =  TrianglesOutils.interpol(  zi , listeT.get(i).getb() , listeT.get(i).getc() );
			} 
			
			//6
			if (	listeT.get(i).getz1()<zi  &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()>zi  ||  
					listeT.get(i).getz1()>zi  &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()<zi  ) {
				
					pi =  TrianglesOutils.interpol(  zi , listeT.get(i).geta() , listeT.get(i).getc() );
					pf =  TrianglesOutils.interpol(  zi , listeT.get(i).getb() , listeT.get(i).getc() );
			} 
			
			//7
			if (	listeT.get(i).getz1()>zi  &&   listeT.get(i).getz2()==zi   &&   listeT.get(i).getz3()==zi  ||  
					listeT.get(i).getz1()<zi  &&   listeT.get(i).getz2()==zi   &&   listeT.get(i).getz3()==zi  ) {
				
					pi =  listeT.get(i).getb();
					pf =  listeT.get(i).getc();
			} 
			
			//8
			if (	listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()==zi  ||  
					listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()==zi  ) {
				
					pi =  listeT.get(i).geta();
					pf =  listeT.get(i).getc();
			} 
			
			//9
			if (	listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()==zi   &&   listeT.get(i).getz3()>zi  ||  
					listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()==zi   &&   listeT.get(i).getz3()<zi  ) {
				
					pi =  listeT.get(i).geta();
					pf =  listeT.get(i).getb();
			} 
			
			//10
			if (	listeT.get(i).getz1()<zi   &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()<zi   ||  
					listeT.get(i).getz1()>zi   &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()>zi   ||
					listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()>zi   ||  
					listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()<zi   ||
					listeT.get(i).getz1()>zi   &&   listeT.get(i).getz2()==zi  &&   listeT.get(i).getz3()>zi   ||  
					listeT.get(i).getz1()<zi   &&   listeT.get(i).getz2()==zi  &&   listeT.get(i).getz3()<zi   ||
					listeT.get(i).getz1()>zi   &&   listeT.get(i).getz2()>zi   &&   listeT.get(i).getz3()==zi  ||  
					listeT.get(i).getz1()<zi   &&   listeT.get(i).getz2()<zi   &&   listeT.get(i).getz3()==zi  ||
					listeT.get(i).getz1()==zi  &&   listeT.get(i).getz2()==zi  &&   listeT.get(i).getz3()==zi  ) {
				
					rien = true;
					//pi[0] = pi[1] = pi[2] = 0;
					//pf[0] = pf[1] = pf[2] = 0;
			} 
			
			
			
		double[][] seg = new double[2][3];
		seg[0] = pi;
		seg[1] = pf;
		if (!rien)	
			segments.add(seg);
		rien = false;
			
		}     // end for i
	}
	

	
	
	
	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	
	
	
	
	
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
		
		public static void main(String[] args) {
			
			double[] a = new double[3];
			a[0] = 0 ;
			a[1] = -1 ;
			a[2] = 300 ;
			
			double[] b = new double[3];
			b[0] = 1 ;
			b[1] = -1 ;
			b[2] = 650 ;
			
			double[] c = new double[3];
			c[0] = 0 ;
			c[1] = -2 ;
			c[2] = 350 ;
			
			Triangle abc = new Triangle( 0, a , b , c );
			
			List<Triangle> listeT0;
			listeT0 = new ArrayList();
			listeT0.add(abc);
			
			Isoligne iso500bis = new Isoligne( 500 , listeT0 );
			for ( int i =0 ; i<iso500bis.segments.size() ; i++){
				System.out.println(Arrays.deepToString(iso500bis.segments.get(i)));
			}
			
			Grille grille = new Grille();
			List<Triangle> listeT = GrilleATriangles.grilleVersTriangles(grille);
			
			/*Isoligne iso500 = new Isoligne( 500 , listeT );
			for ( int i =0 ; i<iso500.segments.size() ; i++){
				System.out.println(Arrays.deepToString(iso500.segments.get(i)));
			}
			
			System.out.println(iso500.segments.size());*/
			
			Isoligne iso5 = new Isoligne( 5 , listeT );
			for ( int i =0 ; i<iso5.segments.size() ; i++){
				System.out.println(Arrays.deepToString(iso5.segments.get(i)));
			}
			
			System.out.println(iso5.segments.size());
			
			
		}	//fin du main
		
		
	
} 			//fin de la classe