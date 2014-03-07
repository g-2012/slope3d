package structures;

import Utils.FilesUtils;
import Utils.TrianglesOutils;

public class Triangle {

	
	/*******************************************************************************************************
	 ******    ATTRIBUTS
	 *******************************************************************************************************/	
	
	private 	int 		indice;
	private		double[]	a = new double[3];
	private		double[] 	b = new double[3];
	private		double[] 	c = new double[3];
	private		double[] 	vecteurNormal = new double[3];
	private 	double 		pente;
	
	/*******************************************************************************************************
	 ******   CONSTRUCTEURS
	 *******************************************************************************************************/				
	
	
	// 	Construit le triangle en lui donnant :	indice ; trois points sous forme de tableau ; vecteur normal sous forme de tableau ;  pente 
	public Triangle ( int i , double e[] , double f[] , double g[] , double n[] , double p ){
		
		indice = i;
		
		a[0] = e[0];
		a[1] = e[1];
		a[2] = e[2];
		
		b[0] = f[0];
		b[1] = f[1];
		b[2] = f[2];
		
		c[0] = g[0];
		c[1] = g[1];
		c[2] = g[2];
		
		vecteurNormal = n;
		
		pente = p;
		
	}
		
	
	//	Construit le triangle en lui donnant :  indice ; trois points sous forme 9 coordonnées
	public Triangle ( int ind , double xA , double yA , double zA , double xB , double yB , double zB , double xC , double yC , double zC  ){

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
		
		vecteurNormal = TrianglesOutils.vecteurNormalTriangle(a,b,c);
		
		pente = TrianglesOutils.penteTriangle(vecteurNormal);
		
	}
	
	
	//	Construit le triangle en lui donnant :  indice ; trois points sous forme de tableau 
	public Triangle ( int ind , double e[] , double f[] , double g[]  ){
		
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
		
		vecteurNormal = TrianglesOutils.vecteurNormalTriangle(a,b,c);
		
		pente = TrianglesOutils.penteTriangle(vecteurNormal);
		
	}
	
	
//	Construit le triangle en lui donnant :  une chaine bien pensée
	public Triangle ( String  chaine ){
		
		indice = FilesUtils.getIndice(chaine);
		
		a = FilesUtils.getVertices(chaine)[0];
		//a[1] = FilesUtils.getVertices(chaine)[0][1];
		//a[2] = FilesUtils.getVertices(chaine)[0][2];
		
		b = FilesUtils.getVertices(chaine)[1];
		//b[1] = FilesUtils.getVertices(chaine)[1][1];
		//b[2] = FilesUtils.getVertices(chaine)[1][2];
		
		c = FilesUtils.getVertices(chaine)[2];
		//c[1] = FilesUtils.getVertices(chaine)[2][1];
		//c[2] = FilesUtils.getVertices(chaine)[2][2];
		
		vecteurNormal = FilesUtils.getNormale(chaine);
		//vecteurNormal[1] = FilesUtils.getNormale(chaine)[1];
		//vecteurNormal[2] = FilesUtils.getNormale(chaine)[2];
		
		pente = FilesUtils.getPente(chaine);
		
	}
	
	
	
	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	
	public 	double 		getx1()			{return a[0];}
	public 	double 		gety1()			{return a[1];}
	public 	double 		getz1()			{return a[2];}
	
	public 	double 		getx2()			{return b[0];}
	public 	double 		gety2()			{return b[1];}
	public 	double 		getz2()			{return b[2];}
	
	public 	double 		getx3()			{return c[0];}
	public 	double 		gety3()			{return c[1];}
	public 	double 		getz3()			{return c[2];}
	
	public 	double 		getxn()			{return vecteurNormal[0];}
	public 	double 		getyn()			{return vecteurNormal[1];}
	public	double 		getzn()			{return vecteurNormal[2];}
	
	public 	int 		getIndice()		{return indice;}
	public 	double 		getPente()		{return pente;}
	
	
	// Affiche les trois coordonnées des trois sommets ; le vecteur normal ; la pente  du ième triangle
	public void afficherCaracteristiquesTriangle(){	
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" Voici les caractéristiques  du Triangle "+this.getIndice()+" : ");
		System.out.println(" ");
		System.out.println(" Ses trois sommets : ");
		System.out.println(" ");
		System.out.println(" x1 : "+this.getx1());
		System.out.println(" y1 : "+this.gety1());
		System.out.println(" z1 : "+this.getz1());
		System.out.println(" ");
		System.out.println(" x2 : "+this.getx2());
		System.out.println(" y2 : "+this.gety2());
		System.out.println(" z2 : "+this.getz2());
		System.out.println(" ");
		System.out.println(" x3 : "+this.getx3());
		System.out.println(" y3 : "+this.gety3());
		System.out.println(" z3 : "+this.getz3());
		System.out.println(" ");
		System.out.println(" Son vecteur normal : ");
		System.out.println(" ");
		System.out.println(" xn  = " +this.getxn());
		System.out.println(" yn  = " +this.getyn());
		System.out.println(" zn  = " +this.getzn());
		System.out.println(" ");
		System.out.println( " La pente du " +this.getIndice()+ "ème triangle est de " +this.getPente() +"°");
	}		
	
	// Chaine de caractères qui contient l'ensembles des caractéristiques d'un triangle
	public String caracteristiquesTriangle(){
		String chaine;
		chaine = 	"i:"  +  this.getIndice() +  ";"  +
					"a:(" +  this.getx1()	  +  ","  +  this.gety1()  + "," +  this.getz1()  + ")" 	+
					"b:(" +  this.getx2()     +  ","  +  this.gety2()  + "," +  this.getz2()  + ")"		+
					"c:(" +  this.getx3()     +  ","  +  this.gety3()  + "," +  this.getz3()  + ");"	+
					"p:"  +	 this.getPente()  +  ";"  +
					"n:(" +  this.getxn()	  +  ","  +  this.getyn()  + "," +  this.getzn()  + ")";
		return chaine;
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
			
			double[] N = new double[3];
			N[0] = 1 ;
			N[1] = 2 ;
			N[2] = 3 ;
			
			//Triangle abc = new Triangle( 10 , a , b , c , N , 0 );
			
			Triangle abc = new Triangle( 2 , a , b , c );
			//abc.afficherCaracteristiquesTriangle();
			
			//System.out.println(" ");
			//System.out.println(abc.caracteristiquesTriangle());
			
			String chaineTriangle1;
			chaineTriangle1 = abc.caracteristiquesTriangle();
			System.out.println(chaineTriangle1);
			Triangle def = new Triangle(chaineTriangle1);
			def.afficherCaracteristiquesTriangle();
		}	//fin du main
		
		
	
} 			//fin de la classe
