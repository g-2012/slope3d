package gauthierUtils;
//package premier_projet;

public abstract class TrianglesOutils {
	
	// Retourne les coordonnées du vecteur AB à partir des points A et B
	public static double[] coordVecteur(double A[], double B[]){
		double[] coord = new double[3];
		coord[0] = B[0] - A[0];
		coord[1] = B[1] - A[1];
		coord[2] = B[2] - A[2];
		return coord;	
	}
	
	// Retourne la norme du vecteur V
	public static double norme(double V[]){
		double norme;
		norme = Math.sqrt(  Math.pow(V[0], 2) + Math.pow(V[1], 2) + Math.pow(V[2], 2)  );
		return norme;	
	}
	
	// Retourne le Produit Scalaire des vecteurs U et V
	public static double produitScalaire(double U[],double V[]){
		double produitScalaire;
		produitScalaire = U[0]*V[0] + U[1]*V[1] + U[2]*V[2];
		return produitScalaire;	
	}
	
	// Retourne le Produit Vectoriel des vecteurs U et V
	public static double[] produitVectoriel(double U[],double V[]){
		double[] produitVect;
		produitVect = new double[3];
		produitVect[0]  =  U[1]*V[2] - U[2]*V[1];
		produitVect[1]  =  U[2]*V[0] - U[0]*V[2];
		produitVect[2]  =  U[0]*V[1] - U[1]*V[0];
		//double n = norme(produitVect);
		return	 produitVect;	
	}
	
	// Retourne la pente d'un triangle ABC
	public static double penteTriangle(double A[], double B[], double C[]){
		double pente;
		pente=0;
		double[] k, AB, AC;
		k  = new double[3];
		AB = new double[3];
		AC = new double[3];
		k[0]=0;
		k[1]=0;
		k[2]=1;
		AB = coordVecteur(A,B);
											double xAB = AB[0];
											double yAB = AB[1];
											double zAB = AB[2];
											System.out.println(" xAB = " +xAB);
											System.out.println(" yAB = " +yAB);
											System.out.println(" zAB = " +zAB);	
											System.out.println(" ");	

		AC = coordVecteur(A,C);
											double xAC = AC[0];
											double yAC = AC[1];
											double zAC = AC[2];
											System.out.println(" xAC = " +xAC);
											System.out.println(" yAC = " +yAC);
											System.out.println(" zAC = " +zAC);	
											System.out.println(" ");	
		double[] n = produitVectoriel(AB,AC);
											double xn = n[0];
											double yn = n[1];
											double zn = n[2];
											double N = norme(n);
											System.out.println(" xn  = " +xn);
											System.out.println(" yn  = " +yn);
											System.out.println(" zn  = " +zn);
											System.out.println(" norme  n  =  " +N);
											System.out.println(" ");
		if (n[2]<0){
			for (int j=0;j<3;j++){
				double  T = - n[j]/N;
				n[j] = T;
			}
		}
		else{
			for (int i=0;i<3;i++){
				double t = n[i]/N;
				n[i] = t;	
			}
		}
											double xnn = n[0];
											double ynn = n[1];
											double znn = n[2];
											System.out.println(" xn' = " +xnn);
											System.out.println(" yn' = " +ynn);
											System.out.println(" zn' = " +znn);	
											System.out.println(" norme  n' =  " +norme(n));
											System.out.println(" ");
											
		double nScalairek = produitScalaire(n,k);
		pente =  /*Math.PI/2 - */Math.acos(nScalairek) ;
		pente = Math.toDegrees(pente);
		return	pente;	
	}
	

	public static void main(String[] args) {
		
		
		/* 				Quelques exemples qui fonctionnent :
		 * 
		 * 		A(4,0,0)     B(2,-2,2)     C(0,0,4)      >>  45°
		 * 		A(6,0,2)     B(3,4,1)      C(0,0,0)      >>  18.44°	
		 * 		A(1,0,0)     B(0,1,0)      C(0,0,1)      >>  70.53°
		 * 		A(0,0,0)     B(1,0,0)      C(0,0,1)      >>  90.00°
		 * 		A(0,0,0)     B(9,2,0)      C(-1,6,0)     >>  00.00°
		 */
		
		double[] A = new double[3];
		A[0] = 150 ;
		A[1] = -150 ;
		A[2] = 500 ;
		
		double[] B = new double[3];
		B[0] = 180 ;
		B[1] = -150 ;
		B[2] = 96 ;
		
		double[] C = new double[3];
		C[0] = 150 ;
		C[1] = -180 ;
		C[2] = 96 ;
						
		double p = penteTriangle(A,B,C);
		System.out.println(" pente = " +p);
		
	}   //fin du main

}       //fin de la classe