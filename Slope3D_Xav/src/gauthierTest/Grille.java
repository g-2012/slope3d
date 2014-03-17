package gauthierTest;

import java.util.Arrays;

import Utils.FilesUtils;

public class Grille {

	

	/*******************************************************************************************************
	 ******    ATTRIBUTS
	 *******************************************************************************************************/	
	
	public 		double 		pas			; 		// pas du MNT (m)
	public 		int 		nLig		; 		// nombre de lignes   de la grille
	public		int			nCol		; 		// nombre de colonnes de la grille
	public 		double 		x0  		; 		// abcsisse  du coin supérieur gauche
	public 		double 		y0 			; 		// ordonnée  du coin supérieur gauche
	
	public 		double[][] 	valeurs 	;		// valeurs
	
	
	/*******************************************************************************************************
	 ******   CONSTRUCTEURS
	 *******************************************************************************************************/	
	
	public Grille(){
		
		pas		=	25;//1	;
		nLig	=	5;//4	;
		nCol	=	5;//4	;
		x0		=	0;//0	;
		y0		=	100;//0	;
		
		valeurs = new double[][]{ 
				
				/*{  70   ,  70   ,  70   ,  70   ,  70   ,  70   ,  70   ,  70   ,  70   ,   70  }, // altitudes des points du MNT (m)
				{  70   ,  90   ,  90   ,  90   ,  90   ,  90   ,  90   ,  90   ,  90   ,   90  },
				{  70   ,  90   ,  94   ,  94   ,  94   ,  94   ,  94   ,  94   ,  94   ,   91  },
				{  70   ,  90   ,  94   ,  95   ,  95   ,  95   ,  95   ,  95   ,  95   ,   94  },
				{  70   ,  90   ,  94   ,  95   ,  96   ,  96   ,  96   ,  95   ,  95   ,   95  },
				{  70   ,  92   ,  94   ,  95   ,  96   ,  500  ,  96   ,  95   ,  95   ,   95  },
				{  70   ,  90   ,  94   ,  95   ,  96   ,  96   ,  96   ,  95   ,  95   ,   95  },
				{  70   ,  90   ,  94   ,  95   ,  95   ,  95   ,  95   ,  95   ,  95   ,   94  },
				{  70   ,  90   ,  94   ,  94   ,  94   ,  94   ,  94   ,  94   ,  94   ,   91  },
				{  70   ,  90   ,  90   ,  90   ,  90   ,  90   ,  90   ,  90   ,  90   ,   90  }*/
		
				
				/*{  350   ,  400   ,  425   ,  400     }, // grille test pour isolignes 500
				{  300   ,  650   ,  700   ,  420     },
				{  350   ,  600   ,  650   ,  440     },
				{  400   ,  450   ,  500   ,  460     },*/
				
				 {0.5, 1.0, 1.0, 3.0, 2.0},
				 {1.0, 3.0, 6.0, 6.0, 3.0},
				 {3.0, 7.0, 9.0, 7.0, 3.0},
				 {5.0, 7.0, 8.0, 6.0, 2.0},
				 {1.0, 2.0, 3.0, 4.0, 3.0},
				
				
				
				
		};
	}
		
	
	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	
	public 	double 		getpa()			{return pas;}
	public 	double 		getnL()			{return nLig;}
	public 	double 		getnC()			{return nCol;}
	public 	double 		getx0()			{return x0;}
	public 	double 		gety0()			{return y0;}
	
	
	
	public String toString (){
		String chaine="\n";
		
		chaine  += " MNT : \n\n";
		chaine  += " pas : " 				+ 	pas 	+ 	"\n";
		chaine	+= " nombre de lignes: " 	+ 	nLig 	+ 	"\n";
		chaine	+= " nombre de colonnes: " 	+ 	nCol 	+ 	"\n";
		chaine	+= " x0= " 					+ 	x0 		+ 	"\n";
		chaine	+= " y0= " 					+ 	y0 		+ 	"\n";
		
		/*for (double[] l: valeurs)
			chaine += Arrays.toString(l)+"\n";*/
		
		return chaine;
	}
	
	
	
	public double[] zMinMax() {
		double[] minmax = new double[2];
			
		int 	indColMin = 0	;
		int 	indLigMin = 0;
		double 	valueMin  = valeurs[0][0];
	
		int 	indColMax = 0;
		int 	indLigMax = 0;
		double 	valueMax = 0;
	
	
		for ( int i=0 ; i<nLig ; i++ ){
			for ( int j=0 ; j<nCol ; j++ ){
				if ( valeurs[i][j] > valueMax ){
					indLigMax = i ;
					indColMax = j ;
				} 
				if ( valeurs[i][j] < valueMin ){
					indLigMin = i ;
					indColMin = j ;
				} 
			} 
		}
	
		minmax[0]  = valeurs[indLigMin][indColMin];
		minmax[1]  = valeurs[indLigMax][indColMax];
	
		return minmax;
	}
	
	 	
	
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
	
	
	public static void main(String[] args) {
		
		
		
	}	//fin du main
	
		
	
}		//fin de la classe
