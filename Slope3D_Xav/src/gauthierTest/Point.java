package gauthierTest;

public class Point {

	
	/*******************************************************************************************************
	 ******    ATTRIBUTS
	 *******************************************************************************************************/	
	
	private 	int 		indice;
	private		double[]	p = new double[3];
	
	
	/*******************************************************************************************************
	 ******   CONSTRUCTEURS
	 *******************************************************************************************************/				
	

	public Point ( double m[]){
		
		p[0] = m[0];
		p[1] = m[1];
		p[2] = m[2];
	
	}
	
	
	
	public Point ( int i , double m[]){
		
		indice = i;
		
		p[0] = m[0];
		p[1] = m[1];
		p[2] = m[2];
	
	}

	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	public 	double 		getxp()			{return p[0];}
	public 	double 		getyp()			{return p[1];}
	public 	double 		getzp()			{return p[2];}
	
	public 	int 		getIndice()		{return indice;}
	
	
	public void afficherPoint(){	
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" Voici les caractéristiques  du point "+this.getIndice()+" : ");
		System.out.println(" ");
		System.out.println("   xp : "+this.getxp());
		System.out.println("   yp : "+this.getyp());
		System.out.println("   zp : "+this.getzp());
	}	
	
	
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
		
		public static void main(String[] args) {
		
		int ind = 10 ;
		double[] P = new double[3];
		P[0] = 1 ;
		P[1] = 1 ;
		P[2] = 1 ;	
		
		Point p= new Point(ind ,P);
		p.afficherPoint();
		}	//fin du main

}	//fin de la classe



