package gauthierUtils;

import java.util.Arrays;

import gauthierUtils.FilesUtils;

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
		
		for (double[] l: valeurs)
			chaine += Arrays.toString(l)+"\n";
		
		return chaine;
	}
	
	
	
	public double[] zMinMax() {
		double[] minmax = new double[2];
			
		int 	indColMin = 0;
		int 	indLigMin = 0;
		double 	valueMin  = valeurs[0][0];
	
		int 	indColMax = 0;
		int 	indLigMax = 0;
		double 	valueMax = valeurs[0][0];
	
	
		for ( int i=0 ; i<nLig ; i++ ){
			for ( int j=0 ; j<nCol ; j++ ){
				if ( valeurs[i][j] > valueMax ){
					indLigMax = i ;
					indColMax = j ;
					valueMax = valeurs[i][j];
				} 
				if ( valeurs[i][j] < valueMin ){
					indLigMin = i ;
					indColMin = j ;
					valueMin = valeurs[i][j];
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
		
		Grille grille = FilesUtils.loadMNTAsc("D:\\Slope3D\\testMNT.asc");
		
		System.out.println(grille.zMinMax()[0]);
		System.out.println(grille.zMinMax()[1]);
		
	}	//fin du main
	
		
	
}		//fin de la classe
