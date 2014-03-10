package structures;

import java.util.Arrays;

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
	
	public Grille ( double pa  , int nL , int nC , double xO , double yO , double[][] values ){
		
		pas		=	pa	;
		nLig	=	nL	;
		nCol	=	nC	;
		x0		=	xO	;
		y0		=	yO	;
		
		valeurs = 	new double[nL][nC]					;
		System.arraycopy(values, 0, valeurs, 0, nLig)	;
		
	}
		
	
	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	
	public String toString (){
		String chaine="\n";
		
		chaine  += " MNT : \n\n";
		chaine  += " pas : " 				+ 	pas 	+ 	"\n";
		chaine	+= " nombre de lignes: " 	+ 	nLig 	+ 	"\n";
		chaine	+= " nombre de colonnes: " 	+ 	nCol 	+ 	"\n";
		chaine	+= " x0= " 					+ 	x0 		+ 	"\n";
		chaine	+= " y0= " 					+ 	y0 		+ 	"\n";
		
		for (double[] l: valeurs)
			chaine += Arrays.toString(l)+"\n";
		
		return chaine;
	}
	
	/*public double[] maxGrille() {
	double[] vectMaximum = new double[3];
			
	int indColMax = 0;
	int indLigMax = 0;
	double valueMax = 0;
	
	for ( int i=0 ; i<nCol ; i++){
		for ( int j=0 ; i<nLig ; j++){
			if ( valeurs[i] [j]>valueMax ) {
				indColMax = i ;
				indLigMax = j ;
				valueMax  = valeurs[i] [j];
			} //end if
		} 
	}
	
	vectMaximum[1] = x0 + indColMax*pas;
	vectMaximum[2] = y0 + indLigMax*pas;
	vectMaximum[3] = valueMax;
	return vectMaximum;
}*/
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
	
	
	public static void main(String[] args) {
		
			
		
	}	//fin du main
	
		
	
}		//fin de la classe
