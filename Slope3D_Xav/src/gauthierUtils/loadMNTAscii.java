package gauthierUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import imranUtils.FilesUtils;

public abstract class loadMNTAscii {

	
	
	/*******************************************************************************************************
	 ******    METHODES
	 *******************************************************************************************************/
	
	public static Grille loadMNTAsc (String file){
		
	// crée liste de string à partir de l'adresse du fichier sous forme de string
			
			List<String> liste;
			liste = FilesUtils.tabLines(file);		
			
			
	// parse la liste de string
			
			
			
			// recuperation des metadonnées
				
				String line;
				line = liste.get(0);
				//ligne 1 : la nombre de colonnes
				int cols = Integer.parseInt(line.substring(6,line.length()));
				//ligne 2 : le nombre de lignes
				line = liste.get(1);
				int rows = Integer.parseInt(line.substring(6,line.length()));
				//ligne 3 : x du centre
				line = liste.get(2);
				double xllcenter  = Double.parseDouble(line.substring(10,line.length()));
				//ligne 4 : y du centre
				line = liste.get(3);
				double yllcenter = Double.parseDouble(line.substring(10,line.length()));
				//ligne 5
				line = liste.get(4);
				double step = Double.parseDouble(line.substring(9,line.length()));
				
				
				double x0 , y0 ;
				x0 = xllcenter + ( 1 - rows)*step/2;
				y0 = yllcenter + ( 1 - cols)*step/2;
			
				
		
			// recuperation des valeurs dans values
				
				double[][] values = 	new double[rows][cols];
				String ligne;
				String[] str;
				
				for (int i=6 ; i<liste.size() ; i++){
					
					ligne = liste.get(i).trim();
					str = ligne.split(" ");
					
					for (int j=0 ; j < str.length ; j++){
						values[i-6][j] = Double.parseDouble(str[j]);
					}	// fin du for j
					
				} // fin du for i
		
			
				
				
			// creation de la grille à retourner
				
			Grille grille;
			grille = new Grille ( step  , rows , cols , x0 , y0 ,  values );
		 
			return grille;
		  
	}
	
	
	/*******************************************************************************************************
	 ******    MAIN   
	 *******************************************************************************************************/
	
	
	public static void main(String[] args) {
			
		Grille maGrilleTest;
		//maGrilleTest = loadMNTAsc("D:\\Slope3D\\MNT\\ASC\\0473_6915_MNT.asc");
		maGrilleTest = loadMNTAsc("D:\\Slope3D\\testMNT.asc");
		System.out.println(maGrilleTest); 
		System.out.println("loading complete");
	}	//fin du main	
	
	
	
}		//fin de la classe
