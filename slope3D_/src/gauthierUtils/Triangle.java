package gauthierUtils;

public class Triangle {

	
	/*******************************************************************************************************
	 ******    ATTRIBUTS
	 *******************************************************************************************************/	
	
	private		double[]	a = new double[3];
	private		double[] 	b = new double[3];
	private		double[] 	c = new double[3];
	private 	double 		pente;
	private 	int 		indice;
	private		double[] 	vecteurNormal = new double[3];
	
	/*******************************************************************************************************
	 ******   CONSTRUCTEURS
	 *******************************************************************************************************/				
	
	public Triangle ( double xA , double yA , double zA , double xB , double yB , double zB , double xC , double yC , double zC ){
		
		a[0] = xA;
		a[1] = yA;
		a[2] = zA;
		
		b[0] = xB;
		b[1] = yB;
		b[2] = zB;
		
		c[0] = xC;
		c[1] = yC;
		c[2] = zC;
		
		double[] k, ab, ac;
		k  = new double[3];
		ab = new double[3];
		ac = new double[3];
		k[0]=0;
		k[1]=0;
		k[2]=1;
		ab = TrianglesOutils.coordVecteur(a,b);
		ac = TrianglesOutils.coordVecteur(a,c);
		double[] n = TrianglesOutils.produitVectoriel(ab,ac);
		double N = TrianglesOutils.norme(n);
		if (n[2]<0){
			for (int j=0;j<3;j++){
				double  T = - n[j]/N;
				n[j] = T;
				vecteurNormal[j] = n[j];
			}	
		} 
		else{
			for (int i=0;i<3;i++){
				double t = n[i]/N;
				n[i] = t;
				vecteurNormal[i] = n[i];
			}
		}
		
		double nScalairek = TrianglesOutils.produitScalaire(n,k);
		pente = Math.acos(nScalairek) ;
		pente = Math.toDegrees(pente);	
		
	}
	
	public Triangle ( double e[] , double f[] , double g[] ){
		
		a[0] = e[0];
		a[1] = e[1];
		a[2] = e[2];
		
		b[0] = f[0];
		b[1] = f[1];
		b[2] = f[2];
		
		c[0] = g[0];
		c[1] = g[1];
		c[2] = g[2];
		
		double[] k, ab, ac;
		k  = new double[3];
		ab = new double[3];
		ac = new double[3];
		k[0]=0;
		k[1]=0;
		k[2]=1;
		ab = TrianglesOutils.coordVecteur(a,b);
		ac = TrianglesOutils.coordVecteur(a,c);
		double[] n = TrianglesOutils.produitVectoriel(ab,ac);
			/*double xn = n[0];
			double yn = n[1];
			double zn = n[2];*/
		double N = TrianglesOutils.norme(n);
			/*System.out.println(" xn  = " +xn);
			System.out.println(" yn  = " +yn);
			System.out.println(" zn  = " +zn);
			System.out.println(" norme  n  =  " +N);
			System.out.println(" ");*/
		if (n[2]<0){
			for (int j=0;j<3;j++){
				double  T = - n[j]/N;
				n[j] = T;
				vecteurNormal[j] = n[j];
			}
		}
		else{
			for (int i=0;i<3;i++){
				double t = n[i]/N;
				n[i] = t;	
				vecteurNormal[i] = n[i];
			}
		}
			/*double xnn = n[0];
			double ynn = n[1];
			double znn = n[2];
			System.out.println(" xn' = " +xnn);
			System.out.println(" yn' = " +ynn);
			System.out.println(" zn' = " +znn);	
			System.out.println(" norme  n' =  " +TrianglesOutils.norme(n));
			System.out.println(" ");*/
		double nScalairek = TrianglesOutils.produitScalaire(n,k);
		pente = Math.acos(nScalairek);
		pente = Math.toDegrees(pente);	
		
	}
	
	public Triangle ( double xA , double yA , double zA , double xB , double yB , double zB , double xC , double yC , double zC , int ind ){

		indice = ind;
		
		a[0] = xA;
		a[1] = yA;
		a[2] = zA;
		
		b[0] = xB;
		b[1] = yB;
		b[2] = zB;
		
		c[0] = xC;
		c[1] = yC;
		c[2] = zC;
		
		double[] k, ab, ac;
		k  = new double[3];
		ab = new double[3];
		ac = new double[3];
		k[0]=0;
		k[1]=0;
		k[2]=1;
		ab = TrianglesOutils.coordVecteur(a,b);
		ac = TrianglesOutils.coordVecteur(a,c);
		double[] n = TrianglesOutils.produitVectoriel(ab,ac);
		double N = TrianglesOutils.norme(n);
		if (n[2]<0){
			for (int j=0;j<3;j++){
				double  T = - n[j]/N;
				n[j] = T;
				vecteurNormal[j] = n[j];
			}
		}
		else{
			for (int i=0;i<3;i++){
				double t = n[i]/N;
				n[i] = t;	
				vecteurNormal[i] = n[i];
			}
		}
		
		double nScalairek = TrianglesOutils.produitScalaire(n,k);
		pente = Math.acos(nScalairek) ;
		pente = Math.toDegrees(pente);	
		
	}

	public Triangle ( double e[] , double f[] , double g[] , int ind ){
		
		indice = ind;
		
		a[0] = e[0];
		a[1] = e[1];
		a[2] = e[2];
		
		b[0] = f[0];
		b[1] = f[1];
		b[2] = f[2];
		
		c[0] = g[0];
		c[1] = g[1];
		c[2] = g[2];
		
		double[] k, ab, ac;
		k  = new double[3];
		ab = new double[3];
		ac = new double[3];
		k[0]=0;
		k[1]=0;
		k[2]=1;
		ab = TrianglesOutils.coordVecteur(a,b);
		ac = TrianglesOutils.coordVecteur(a,c);
		double[] n = TrianglesOutils.produitVectoriel(ab,ac);
			/*double xn = n[0];
			double yn = n[1];
			double zn = n[2];*/
		double N = TrianglesOutils.norme(n);
			/*System.out.println(" xn  = " +xn);
			System.out.println(" yn  = " +yn);
			System.out.println(" zn  = " +zn);
			System.out.println(" norme  n  =  " +N);
			System.out.println(" ");*/
		if (n[2]<0){
			for (int j=0;j<3;j++){
				double  T = - n[j]/N;
				n[j] = T;
				vecteurNormal[j] = n[j];
			}
		}
		else{
			for (int i=0;i<3;i++){
				double t = n[i]/N;
				n[i] = t;	
				vecteurNormal[i] = n[i];
			}
		}
			/*double xnn = n[0];
			double ynn = n[1];
			double znn = n[2];
			System.out.println(" xn' = " +xnn);
			System.out.println(" yn' = " +ynn);
			System.out.println(" zn' = " +znn);	
			System.out.println(" norme  n' =  " +TrianglesOutils.norme(n));
			System.out.println(" ");*/
		double nScalairek = TrianglesOutils.produitScalaire(n,k);
		pente = Math.acos(nScalairek);
		pente = Math.toDegrees(pente);	
		
	}
	
	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	
	public double getx1(){return a[0];}
	public double gety1(){return a[1];}
	public double getz1(){return a[2];}
	
	public double getx2(){return b[0];}
	public double gety2(){return b[1];}
	public double getz2(){return b[2];}
	
	public double getx3(){return c[0];}
	public double gety3(){return c[1];}
	public double getz3(){return c[2];}
	
	public double getxn(){return vecteurNormal[0];}
	public double getyn(){return vecteurNormal[1];}
	public double getzn(){return vecteurNormal[2];}
	
	public int getIndice(){return indice;}
	public double getPente(){return pente;}
	
	// Affiche les trois coordonnées des trois sommets du triangle
	public static void afficherCoordonneesPointsTriangle(Triangle abc){	
		
		System.out.println(" ");
		System.out.println("Voici les coordonnées des trois sommets du Triangle "+abc.getIndice()+" : ");
		System.out.println(" ");
		System.out.println(" x1 : "+abc.getx1());
		System.out.println(" y1 : "+abc.gety1());
		System.out.println(" z1 : "+abc.getz1());
		System.out.println(" ");
		System.out.println(" x2 : "+abc.getx2());
		System.out.println(" y2 : "+abc.gety2());
		System.out.println(" z2 : "+abc.getz2());
		System.out.println(" ");
		System.out.println(" x3 : "+abc.getx3());
		System.out.println(" y3 : "+abc.gety3());
		System.out.println(" z3 : "+abc.getz3());
		System.out.println(" ");
		System.out.println("La pente du triangle est : "+abc.getPente());
	}		
		
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
		
		public static void main(String[] args) {
			
			/* 				Quelques exemples qui fonctionnent :
			 * 
			 * 		A(4,0,0)     B(2,-2,2)     C(0,0,4)      >>  45°
			 * 		A(6,0,2)     B(3,4,1)      C(0,0,0)      >>  18.44°	
			 * 		A(1,0,0)     B(0,1,0)      C(0,0,1)      >>  54.73°
			 * 		A(0,0,0)     B(1,0,0)      C(0,0,1)      >>  90.00°
			 * 		A(0,0,0)     B(9,2,0)      C(-1,6,0)     >>  00.00°
			 * 
			 */
						
			
			double[] a = new double[3];
			a[0] = 150 ;
			a[1] = -150 ;
			a[2] = 500 ;
			
			double[] b = new double[3];
			b[0] = 120 ;
			b[1] = -180 ;
			b[2] = 96 ;
			
			double[] c = new double[3];
			c[0] = 150 ;
			c[1] = -180 ;
			c[2] = 96 ;
			
			
			Triangle abc = new Triangle(a,b,c,2);
			afficherCoordonneesPointsTriangle(abc);
			//System.out.println( " la pente du " +abc.getIndice()+ "ème triangle est de " +abc.getPente() +"°");
			System.out.println(" ");
			System.out.println(" xn  = " +abc.getxn());
			System.out.println(" yn  = " +abc.getyn());
			System.out.println(" zn  = " +abc.getzn());
		
			
		}	//fin du main
		
		
	
} 			//fin de la classe
