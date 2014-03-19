package structures;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		chaine	+= " z min = " 					+ 	zMinMax()[0] + 	"\n";
		chaine	+= " z max = " 					+ 	zMinMax()[1] + 	"\n";
		
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
		
		Grille grille = FilesUtils.loadMNTAsc("D:\\Slope3D\\testMNT.asc");
		
		System.out.println(grille.zMinMax()[0]);
		System.out.println(grille.zMinMax()[1]);
		
	}	//fin du main
	
		
	
}		//fin de la classe
