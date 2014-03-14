package imranUtils;
//import structures.Grille;
import structures.Triangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FilesUtils {

	//ajoute un tableau de lignes a un fichier file
	//ecrase le fichier si existant
	public static void addLinesToFile(String[] lines, String file){
		List<String> towrite = new ArrayList<String>(Arrays.asList(lines));
		Path target = Paths.get(file);

		try{
			Files.write(target, towrite, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		}catch(IOException e) {
			System.out.println("bad shit happened : "+e.getMessage());
		}
	}

	//renvoie une List<String> de chaque ligne d'un fichier
	public static List<String> tabLines(String file){
		List<String> lignes = new ArrayList<String>();
		Path target = Paths.get(file);
		try{
			lignes = Files.readAllLines(target,StandardCharsets.UTF_8);

		}catch(IOException e) {
			System.out.println("bad shit happened here : "+e.getMessage());
		}
		return lignes;
	}

	// ecrit dans file une description des triangles contenus dans triangles
	public static void triangles2file(List<Triangle> triangles, String file){
		int taille = triangles.size();
		String [] lignes = new String[taille];
		int i = 0;
		for (Triangle t: triangles)
			lignes[i++] = t.caracteristiquesTriangle();
		//System.out.println(t.caracteristiquesTriangle());
		addLinesToFile(lignes, file);
		System.out.println("file written");
	}

	//charge les triangles decrits dans le fichier file dans un liste de triangles
	public static List<Triangle> loadTriangles(String file){
		List<Triangle> triangles = new ArrayList<>();
		List<String> stri = new ArrayList<String>();
		stri = tabLines(file);
		System.out.println("fin chargement des lignes dans un String[]");
		for (String s:stri)
			triangles.add(new Triangle(s));
		System.out.println("fin appels de tous les constructeurs");
		return triangles;
	}

	// methodes qui parsent une chaine bien formee pour renvoyer
	// les elements adequats, i.e indice, vecteur normal, sommets etc..
	public static int getIndice(String wellFormedLine){
		String pattern = "i:(\\d*);";
		Matcher mat = Pattern.compile(pattern).matcher(wellFormedLine);

		mat.find();
		return Integer.parseInt(mat.group(1));
	}

	public static double getPente(String wellFormedLine){
		String pattern = "p:(-?\\d*\\.\\d*);";
		Matcher mat = Pattern.compile(pattern).matcher(wellFormedLine);

		mat.find();
		return Double.parseDouble(mat.group(1));
	}

	public static double[][] getVertices(String wellFormedLine){
		double [][] vertices = new double[3][3];
		String pattern = "a:\\((-?\\d*\\.\\d*),(-?\\d*\\.\\d*),(-?\\d*\\.\\d*)\\)"+
				"b:\\((-?\\d*\\.\\d*),(-?\\d*\\.\\d*),(-?\\d*\\.\\d*)\\)"+
				"c:\\((-?\\d*\\.\\d*),(-?\\d*\\.\\d*),(-?\\d*\\.\\d*)\\)";
		Matcher mat = Pattern.compile(pattern).matcher(wellFormedLine);
		mat.find();
		vertices[0][0] = Double.parseDouble(mat.group(1));
		vertices[0][1] = Double.parseDouble(mat.group(2));
		vertices[0][2] = Double.parseDouble(mat.group(3));
		vertices[1][0] = Double.parseDouble(mat.group(4));
		vertices[1][1] = Double.parseDouble(mat.group(5));
		vertices[1][2] = Double.parseDouble(mat.group(6));
		vertices[2][0] = Double.parseDouble(mat.group(7));
		vertices[2][1] = Double.parseDouble(mat.group(8));
		vertices[2][2] = Double.parseDouble(mat.group(9));
		return vertices;
	}

	public static double[] getNormale(String wellFormedLine){
		double [] normale = new double[3];
		String pattern = "n:\\((-?\\d*\\.\\d*),(-?\\d*\\.\\d*),(-?\\d*\\.\\d*)\\)";
		Matcher mat = Pattern.compile(pattern).matcher(wellFormedLine);
		mat.find();
		normale[0] = Double.parseDouble(mat.group(1));
		normale[1] = Double.parseDouble(mat.group(2));
		normale[2] = Double.parseDouble(mat.group(3));
		return normale;
	}



	public static Grille loadMNTAsc (String file){
		// cr�e liste de string � partir de l'adresse du fichier sous forme de string
		List<String> liste;
		liste = FilesUtils.tabLines(file);		
		// parse la liste de string



		// recuperation des metadonn�es

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
		x0 = xllcenter ;//+ ( 1 - rows)*step/2;
		y0 = yllcenter ;//+ ( 1 - cols)*step/2;



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




		// creation de la grille � retourner

		Grille grille;
		grille = new Grille ( step  , rows , cols , x0 , y0 ,  values );

		return grille;

	}

	// charge un MNT de la forme xyz dans une grille
	// on suppose que ce fichier est bien forme ,qu'il comporte au moins une cellule 
	public static Grille loadMNTxyz(String file){
		List<String> liste;
		long startTime = System.nanoTime();
		liste = FilesUtils.tabLines(file);
		System.out.println(liste.size());
		long endTime = System.nanoTime();
		System.out.print("charge les lignes du fichier dans un tableau : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		String pattern = "(-?\\d*\\.\\d*)\\s*(-?\\d*\\.\\d*)\\s*(-?\\d*\\.\\d*)";
		Pattern pat = Pattern.compile(pattern);
		Matcher mat;
		mat = pat.matcher(liste.get(0));
		mat.find();

		double x0 = Double.parseDouble(mat.group(1));
		double y0 = Double.parseDouble(mat.group(2));
		double x,y,z, pasX = 0, pasY = 0, oldX = x0, oldY = y0;
		int nbLignes = 1, nbCols = 1;
		boolean ligne1 = true;
		double[] zs = new double[liste.size()];
		// 1ere passe, on recupere le nblignes, le nbcols, le pas et les z;
		startTime = System.nanoTime();
		int i = 0;
		int tailleLigne = 0;
		for (String ligne: liste){
			mat = pat.matcher(ligne);
			mat.find();
			x = Double.parseDouble(mat.group(1));
			y = Double.parseDouble(mat.group(2));
			z = Double.parseDouble(mat.group(3));
			zs[i++]= z;
			++tailleLigne;
			if (y != oldY){ // teste si on change de ligne, si c'est le cas on courtcircuite 
				nbLignes++; // le prochain test c'en est fini du nb de cols et du pas en y
				System.out.print("nL");
				if (tailleLigne % 3201 != 0){
					System.out.println(nbLignes +" : "+ tailleLigne);
				}
				if (pasY == 0 && ligne1)
					pasY = oldY - y;
				ligne1 = false;
				tailleLigne = 1;
			}
			if (ligne1 && x != oldX){
				nbCols++;
				if (pasX == 0)
					pasX = x - oldX;
			}

			//System.out.println(x + " " +y + " " +z);
			oldY = y;
			oldX = x;
		}
		System.out.println("nbLignes : "+ nbLignes);
		System.out.println("i : "+ i);
		endTime = System.nanoTime();
		liste = null;
		System.out.print("passe 1 : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");

		startTime = System.nanoTime();
		// 2e passe, on cree la grille vu qu'on a ce qu'il faut pour creer un tableau de bonnes dimensions
		double[][] values = new double[nbLignes][nbCols];
		for (i = 0; i< nbLignes ; ++i)
			for(int j = 0 ; j< nbCols ; ++j)
				values[i][j]= zs[i*nbCols+j];
		endTime = System.nanoTime();
		System.out.print("passe 2 : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		zs = null;
		return  new Grille(pasX, nbLignes, nbCols, x0, y0, values);
	}

	public static Grille loadMNTxyz2(String file){
		String pattern = "(-?\\d*\\.\\d*)\\s*(-?\\d*\\.\\d*)\\s*(-?\\d*\\.\\d*)";
		Pattern pat = Pattern.compile(pattern);
		Matcher mat;
		int i = 0;
		Path target = Paths.get(file);
		double x0 = 0;
		double y0 = 0;
		long startTime = System.nanoTime();
		try(BufferedReader reader = Files.newBufferedReader(target, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (i ==0 ){
					mat = pat.matcher(line);
					mat.find();
					x0 = Double.parseDouble(mat.group(1));
					y0 = Double.parseDouble(mat.group(2));
				}
				++i;	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		System.out.print("passe 1 : "+ i +" lignes lues en ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		int nbPoints = i;
		int nbLignes = 1, nbCols = 1;		
		double x,y,z, pasX = 0, pasY = 0, oldX = x0, oldY = y0;
		int oldLl = 1;
		i=0;
		//int nbColsMax = 0;
		double[] zs = new double[nbPoints];
		try(BufferedReader reader = Files.newBufferedReader(target, StandardCharsets.UTF_8)) {
			String line;
			startTime = System.nanoTime();
			while ((line = reader.readLine()) != null) {
				mat = pat.matcher(line);
				mat.find();
				x = Double.parseDouble(mat.group(1));
				y = Double.parseDouble(mat.group(2));
				z = Double.parseDouble(mat.group(3));
				zs[i] = z;
				if (oldY != y){
					nbLignes++;
					if (oldLl != nbCols)
						System.out.println(i + " : anc long ligne = "+ oldLl + " -- nv long lig = "+nbCols);
					/*if (nbCols > oldLl)
						nbColsMax = nbCols;*/
					oldLl = nbCols;
					nbCols = 0;
				}
				if (oldX != x){
					nbCols++;
					if (pasX == 0)
						pasX = x-oldX; 
				}
				oldX = x;
				oldY = y;
				++i;
			}
			endTime = System.nanoTime();
			System.out.print("passe 2 -- recupere les z + nblignes et nbcolonnes : ");
			System.out.println(((float)endTime-startTime)/1e9 +" secondes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		startTime = System.nanoTime();
		// 2e passe, on cree la grille vu qu'on a ce qu'il faut pour creer un tableau de bonnes dimensions
		double[][] values = new double[nbLignes][nbCols];
		for (i = 0; i< nbLignes ; ++i)
			for(int j = 0 ; j< nbCols ; ++j)
				values[i][j]= zs[i*nbCols+j];
		endTime = System.nanoTime();
		System.out.print("passe 3 -- creation grille sous forme double[][] : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");

		//return  new Grille(pasX, nbLignes , nbCols, x0, y0, values);
		return new Grille(pasX, nbLignes, nbCols, x0, y0, values);
	}

	public static void main(String[] args) {
		String file = "/home/mac/testMNT.xyz";
		String file2 = "/home/mac/Ecrins2.xyz";
		/*String[] lines = {"i:3;a:(1.2,2.3,-4.2)b:(10.2,-2.1,40.1)c:(-0.1,0.2,0.4);p:21.365;n:(1.2,-2.001,0.24)"};
		System.out.println(file);
		System.out.println(Arrays.toString(lines));
		FilesUtils.addLinesToFile(lines, file);
		List<String> f = FilesUtils.tabLines(file);
		for (String l: f)
			System.out.println(l);
		System.out.println(getIndice(lines[0]));
		System.out.println(getPente(lines[0]));
		System.out.println(Arrays.deepToString(getVertices(lines[0])));
		System.out.println(Arrays.toString(getNormale(lines[0])));
		 */

		long startTime = System.nanoTime();
		Grille grille = FilesUtils.loadMNTxyz2(file2);
		//System.out.println(grille);
		long endTime = System.nanoTime();
		System.out.print("grille : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");

		//System.out.println("nb triangles : " + triangles.length);
		startTime = System.nanoTime();
		//double[][][] triangles = GrilleATriangles.grilleVersTriangles2(grille);
		//List<Triangle> triangles = GrilleATriangles.grilleVersTriangles(grille);
		endTime = System.nanoTime();
		System.out.print("grille -> Triangles[] : ");
		System.out.println(((float)endTime-startTime)/1e9 +" secondes");

		//System.out.println(triangles[51183].caracteristiquesTriangle());
		//long endTime = System.nanoTime();
		//System.out.print("Chargement MNT xyz en grille : ");
		//System.out.println(((float)endTime-startTime)/1e9 +" secondes");
	}

}