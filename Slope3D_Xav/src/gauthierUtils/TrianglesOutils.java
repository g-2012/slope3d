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
	
	
	// Retourne le vecteur normal normalisé d'un triangle défini par trois points
	public static double[] vecteurNormalTriangle(double a[], double b[], double c[]){
		double [] n;
		n= new double[3];
		n[0]=0;
		n[1]=0;
		n[2]=0;
		
		double[] ab, ac;
		
		ab = coordVecteur(a,b);
		/*double xAB = AB[0];
		double yAB = AB[1];
		double zAB = AB[2];
		System.out.println(" xAB = " +xAB);
		System.out.println(" yAB = " +yAB);
		System.out.println(" zAB = " +zAB);	
		System.out.println(" ");*/	

		ac = coordVecteur(a,c);
		/*double xAC = AC[0];
		double yAC = AC[1];
		double zAC = AC[2];
		System.out.println(" xAC = " +xAC);
		System.out.println(" yAC = " +yAC);
		System.out.println(" zAC = " +zAC);	
		System.out.println(" ");*/	
		
		n = produitVectoriel(ab,ac);
		
		double N = norme(n);
		
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
		/*double xnn = n[0];
		double ynn = n[1];
		double znn = n[2];
		System.out.println(" xn' = " +xnn);
		System.out.println(" yn' = " +ynn);
		System.out.println(" zn' = " +znn);	
		System.out.println(" norme  n' =  " +norme(n));
		System.out.println(" ");*/
		
		return n;
	}
	
	
	// Retourne la pente d'un triangle ABC
	public static double penteTriangle(double n[]){
		double pente;
		pente=0;
		double[] k;
		k  = new double[3];
		k[0]=0;
		k[1]=0;
		k[2]=1;
		
		double nScalairek = produitScalaire(n,k);
		pente = Math.acos(nScalairek) ;
		pente = Math.toDegrees(pente);
		return	pente;	
	}
	
	// Retourne le point I de cote zi  interpolé entre M de cote zm et n de cote zn
	public static double[] interpol( double zi , double[] m , double[] n ){
		
		double[] i = new double[3];
		
		if ( m[0] == n[0] ) { i[0] = m[0];}
		else {
				double ax , bx;
				ax = (n[2]-m[2])/(n[0]-m[0]);
				bx =  m[2] - ax * m[0];
				//System.out.println(" ax = " +ax);	
				//System.out.println(" bx = " +bx);
				i[0] =	( zi - bx ) / ax ;
		}
		
		if ( m[1] == n[1] ) { i[1] = m[1];}
		else {
				double ay , by;
				ay = (n[2]-m[2])/(n[1]-m[1]);
				by =  m[2] - ay * m[1];
				//System.out.println(" ay = " +ay);	
				//System.out.println(" by = " +by);
				i[1] =	( zi - by ) / ay ;
		}
		
		i[2] = zi;

		return i;
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
		A[0] = 30  ;
		A[1] = 0   ;
		A[2] = 0   ;
		
		double[] B = new double[3];
		B[0] = -20  ;
		B[1] = 20  ;
		B[2] = 10  ;
		
		double[] C = new double[3];
		C[0] = 150  ;
		C[1] = -180 ;
		C[2] = 100  ;
		
		double[] I = new double[3];
		
		I = interpol ( 5 , A , B );
		System.out.println(" xi = "+ I[0]);
		System.out.println(" yi = "+ I[1]);
		System.out.println(" zi = "+ I[2]);
		
		//double[] n = vecteurNormalTriangle(A,B,C);
		 
		//double p = penteTriangle(n);
		
		//System.out.println(" pente = " +p);
		
	}   //fin du main

	
	
}       //fin de la classe