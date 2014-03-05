package imranUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
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
				System.out.println("bad shit happened : "+e.getMessage());
			}
			return lignes;
		}
		
		public static int getIndice(String wellFormedLine){
			String pattern = "i:(\\d*);";
			Matcher mat = Pattern.compile(pattern).matcher(wellFormedLine);

			mat.find();
			return Integer.parseInt(mat.group(1));
		}
		
		public static double getPente(String wellFormedLine){
			String pattern = "p:(\\d*\\.\\d*);";
			Matcher mat = Pattern.compile(pattern).matcher(wellFormedLine);

			mat.find();
			return Double.parseDouble(mat.group(1));
		}
		
		public static double[][] getVertices(String wellFormedLine){
			double [][] vertices = new double[3][3];
			String pattern = "a:\\((\\d*\\.\\d*),(\\d*\\.\\d*),(\\d*\\.\\d*)\\)"+
							 "b:\\((\\d*\\.\\d*),(\\d*\\.\\d*),(\\d*\\.\\d*)\\)"+
							 "c:\\((\\d*\\.\\d*),(\\d*\\.\\d*),(\\d*\\.\\d*)\\)";
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
			String pattern = "n:\\((\\d*\\.\\d*),(\\d*\\.\\d*),(\\d*\\.\\d*)\\)";
			Matcher mat = Pattern.compile(pattern).matcher(wellFormedLine);
			mat.find();
			normale[0] = Double.parseDouble(mat.group(1));
			normale[1] = Double.parseDouble(mat.group(2));
			normale[2] = Double.parseDouble(mat.group(3));
			return normale;
		}
		
		public static void main(String[] args) {
			String file = "/test/test.txt";
			String[] lines = {"i:3;a:(1.2,2.3,4.2)b:(10.2,2.1,40.1)c:(0.1,0.2,0.4);p:21.365;n:(1.2,2.001,0.24)"};
			System.out.println(file);
			//System.out.println(Arrays.toString(lines));
			FilesUtils.addLinesToFile(lines, file);
			List<String> f = FilesUtils.tabLines(file);
			for (String l: f)
				System.out.println(l);
			System.out.println(getIndice(lines[0]));
			System.out.println(getPente(lines[0]));
			System.out.println(Arrays.deepToString(getVertices(lines[0])));
			System.out.println(Arrays.toString(getNormale(lines[0])));
		}

}
