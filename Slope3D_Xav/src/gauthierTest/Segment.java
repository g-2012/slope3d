package gauthierTest;

public class Segment {

	/*******************************************************************************************************
	 ******    ATTRIBUTS
	 *******************************************************************************************************/	
	
	private		int		numero;
	private 	int 	indiceInitial;
	private		int		indiceFinal;
	
	
	/*******************************************************************************************************
	 ******   CONSTRUCTEURS
	 *******************************************************************************************************/				
	
	public Segment ( int indInitial , int indFinal ){
		
		indiceInitial	= 	indInitial ;
		indiceFinal		=	indFinal;
		
	}	
	
	
	public Segment ( int number , int indInitial , int indFinal ){
		
		numero			=	number;
		indiceInitial	= 	indInitial ;
		indiceFinal		=	indFinal;
		
	}	
	

	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	
	public 	double 		getIndI()		{return indiceInitial;}
	public 	double 		getIndF()		{return indiceFinal;}
	
	public void afficherSegment(){	
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" Voici les caractéristiques  du Segment : ");
		System.out.println(" ");
		System.out.println(" indice du point initial : "	+	this.indiceInitial);
		System.out.println(" indice du point final   : "	+	this.indiceFinal);
	}
	
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
		
		public static void main(String[] args) {
		
			int ind0 = 0 ;
			double[] P0 = new double[3];
			P0[0] = 1 ;
			P0[1] = 1 ;
			P0[2] = 1 ;
			
			int ind1 = 1 ;
			double[] P1 = new double[3];
			P1[0] = 10 ;
			P1[1] = 10 ;
			P1[2] = 10 ;
			
			Point p0= new Point(ind0 ,P0);
			Point p1= new Point(ind1 ,P1);
			
			Segment AB = new Segment(p0.getIndice(),p1.getIndice());
			
			AB.afficherSegment();
			
		}	//fin du main

}	//fin de la classe	
	
	
	
	
	
	
	
	
