package imranUtils;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grille {

	

	/*******************************************************************************************************
	 ******    ATTRIBUTS
	 *******************************************************************************************************/	
	
	public 		double 		pas			; 		// pas du MNT (m)
	public 		int 		nLig		; 		// nombre de lignes   de la grille
	public		int			nCol		; 		// nombre de colonnes de la grille
	public 		double 		x0  		; 		// abcsisse  du coin sup�rieur gauche
	public 		double 		y0 			; 		// ordonnee  du coin sup�rieur gauche
	
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
			
		int 	indColMin = 0	;
		int 	indLigMin = 0;
		double 	valueMin  = valeurs[0][0];
	
		int 	indColMax = 0;
		int 	indLigMax = 0;
		double 	valueMax = valeurs[0][0];
	
	
		for ( int i=0 ; i<nLig ; i++ ){
			for ( int j=0 ; j<nCol ; j++ ){
				if ( valeurs[i][j] > valueMax ){
					valueMax = valeurs[i][j];
					indLigMax = i ;
					indColMax = j ;
				} 
				if ( valeurs[i][j] < valueMin ){
					valueMin = valeurs[i][j];
					indLigMin = i ;
					indColMin = j ;
				} 
			} 
		}
	
		minmax[0]  = valeurs[indLigMin][indColMin];
		minmax[1]  = valeurs[indLigMax][indColMax];
	
		return minmax;
	}
	
		
	public byte cellType(double z, double a, double b, double c){
		byte type = 0;
		if (a > z )
			type += 0b0100;
		if (b > z)
			type += 0b0010;
		if (c > z)
			type += 0b0001;
		if (a == z )
			type += 0b01000000;
		if (b == z)
			type += 0b00100000;
		if (c == z)
			type += 0b00010000;
		return type;
	}
	
	//retourne une liste de doublets de Point2D composant l'isoligne z 
	// a--b      c
	// |         |
	// c      b--a
	//code volontairement explosé pour chercher la performance
	//facteur 10 sur la methode ecrite dans Isoligne teste sur core i3
	public List<Point2D.Double[]> makeIsoZt(double z){
		List<Point2D.Double[]> segments = new ArrayList<>();
		byte type;
		double x,y,x1,x2,y1,y2,xa,ya,xb,yb,xc,yc, za,zb,zc,k;
		for(int i = 0; i < nLig-1 ; ++i){
			for(int j = 0; j < nCol-1 ; ++j){
				//coordonnees point haut gauche du carre considere
				x = x0 + pas * j;
				y = y0 - pas * i;
				// 1er triangle
				xa = x ; ya = y;				
				xb = xa + pas ; yb = ya;
				xc = xa ; yc = ya - pas;
				za = valeurs[i][j] ; zb = valeurs[i][j+1] ; zc = valeurs[i+1][j];
				type = cellType(z, za, zb, zc);
				switch(type){
					//cas où on ne fait rien : cas "0"
					case 0b00000000:
					case 0b00000111:
					case 0b01000000:
					case 0b01000011:
					case 0b00100000:
					case 0b00100101:
					case 0b00010000:
					case 0b00010110:
					case 0b01110000:
						break;
					//cas "1"
					case 0b00000001:
					case 0b00000110:
						k = (z-za)/(zc-za);
						x1 = xa;
						y1 = ya - k * pas;
						k = (zc-z)/(zc-zb);
						x2 = xa + k * pas;
						y2 = yc + k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "2"
					case 0b00000010:
					case 0b00000101:
						k = (z-za)/(zb-za);
						x1 = xa + k * pas;
						y1 = ya;
						k = (z-zc)/(zb-zc);
						x2 = xa + k * pas;
						y2 = yc + k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "3"
					case 0b00000011:
					case 0b00000100:
						k = (z-za)/(zc-za);
						x1 = xa;
						y1 = ya - k * pas;
						k = (z-za)/(zb-za);
						x2 = xa + k * pas;
						y2 = ya;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "4"
					case 0b00110100:
					case 0b00110000:
						x1 = xc;
						y1 = yc;
						x2 = xb;
						y2 = yb;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "5"
					case 0b01010010:
					case 0b01010000:
						x1 = xa;
						y1 = ya;
						x2 = xc;
						y2 = yc;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "6"
					case 0b01100001:
					case 0b01100000:
						x1 = xa;
						y1 = ya;
						x2 = xb;
						y2 = yb;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "7"
					case 0b01000010:
					case 0b01000001:
						x1 = xa;
						y1 = ya;
						k = (zc-z)/(zc-zb);
						x2 = xa + k * pas;
						y2 = yc + k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "8"
					case 0b00100100:
					case 0b00100001:
						x1 = xb;
						y1 = yb;
						k = (z-zc)/(za-zc);
						x2 = xa;
						y2 = yc + k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "9"
					case 0b00010010:
					case 0b00010100:
						x1 = xc;
						y1 = yc;
						k = (z-za)/(zb-za);
						x2 = xa + k * pas;
						y2 = ya;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
				}
				
				// 2e triangle, idem precedemment mais les signes sont inverses pour k*pas
				xa = x + pas ; ya = y - pas;
				xb = xc ; yb = yc;
				xc = x + pas ; yc = y;
				za = valeurs[i+1][j+1] ; zb = valeurs[i+1][j] ; zc = valeurs[i][j+1];
				type = cellType(z, za, zb, zc);
				switch(type){
					//cas où on ne fait rien : cas "0"
					case 0b00000000:
					case 0b00000111:
					case 0b01000000:
					case 0b01000011:
					case 0b00100000:
					case 0b00100101:
					case 0b00010000:
					case 0b00010110:
					case 0b01110000:
						break;
					//cas "1"
					case 0b00000001:
					case 0b00000110:
						k = (z-za)/(zc-za);
						x1 = xa;
						y1 = ya + k * pas;
						k = (zc-z)/(zc-zb);
						x2 = xa - k * pas;
						y2 = yc - k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "2"
					case 0b00000010:
					case 0b00000101:
						k = (z-za)/(zb-za);
						x1 = xa - k * pas;
						y1 = ya;
						k = (z-zc)/(zb-zc);
						x2 = xa - k * pas;
						y2 = yc - k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "3"
					case 0b00000011:
					case 0b00000100:
						k = (z-za)/(zc-za);
						x1 = xa;
						y1 = ya + k * pas;
						k = (z-za)/(zb-za);
						x2 = xa - k * pas;
						y2 = ya;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "4"
					case 0b00110100:
					case 0b00110000:
						x1 = xc;
						y1 = yc;
						x2 = xb;
						y2 = yb;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "5"
					case 0b01010010:
					case 0b01010000:
						x1 = xa;
						y1 = ya;
						x2 = xc;
						y2 = yc;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "6"
					case 0b01100001:
					case 0b01100000:
						x1 = xa;
						y1 = ya;
						x2 = xb;
						y2 = yb;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "7"
					case 0b01000010:
					case 0b01000001:
						x1 = xa;
						y1 = ya;
						k = (zc-z)/(zc-zb);
						x2 = xa - k * pas;
						y2 = yc - k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "8"
					case 0b00100100:
					case 0b00100001:
						x1 = xb;
						y1 = yb;
						k = (z-zc)/(za-zc);
						x2 = xa;
						y2 = yc - k * pas;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
					//cas "9"
					case 0b00010010:
					case 0b00010100:
						x1 = xc;
						y1 = yc;
						k = (z-za)/(zb-za);
						x2 = xa - k * pas;
						y2 = ya;
						segments.add(new Point2D.Double[]{new Point2D.Double(x1,y1), new Point2D.Double(x2,y2) });
						break;
				}
				
			}
			//System.out.println();
		}
		return segments;
	}
	
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
	
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		Grille grille = FilesUtils.loadMNTAsc("/test/fakemnt.asc");
		long endTime = System.nanoTime();
		System.out.print("creation grille : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		//System.out.println(grille);
		List<Point2D.Double[]> segment;
		System.out.println(grille.zMinMax()[0]);
		System.out.println(grille.zMinMax()[1]);
		startTime = System.nanoTime();
		segment = grille.makeIsoZt(5);
		endTime = System.nanoTime();
		System.out.print("calcul isoligne : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		System.out.println(segment.size());
		for (Point2D.Double[] p: segment)
			System.out.println(Arrays.deepToString(p));
		//System.out.println(grille.makeIsoZ(5).get(0)[0]);
		//System.out.println(grille.makeIsoZ(5).get(0)[1]);
		//System.out.println(Integer.toBinaryString(grille.cellType(5,1,3,7,3)));
	}	//fin du main
	
		
	
}		//fin de la classe
