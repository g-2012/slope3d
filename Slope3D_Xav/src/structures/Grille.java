package structures;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.FilesUtils;

/**
 * Grille est la classe représentant un MNT sous forme de grille de z.
 * <p>
 * Une grille est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un pas</li>
 * <li>Un nombre de lignes</li>
 * <li>Les coordonnees x0 et y0 du coin superieur gauche de la grille</li>
 * <li>les valeurs de z stockees dans un tableau à 2 dimensions
 * (lignes/colonnes)</li>
 * </ul>
 * </p>
 * <p>
 * Une Grille peut donner son z max et son z min Une Grille pourra generer une
 * isoligne pour un z donné ou un ensemble de n isolignes interpolees entre son
 * zmin et son zmax
 * </p>
 * 
 * 
 * 
 * @author Jigx
 * @version 0.6
 */
public class Grille {
	/* ******************************************************************************************************
	 * ***** ATTRIBUTS
	 * ***********************************************************
	 * ******************************************
	 * 
	 * les attributs devraient etre prives (les constructeurs ont ete ecrits)
	 * cependant ils ont ete laisses public pour l'aisance d'ecriture
	 */

	/**
	 * pas du MNT
	 */
	public double pas;

	/**
	 * nombre de lignes de la grille
	 */
	public int nLig;

	/**
	 * nombre de colonne de la grille
	 */
	public int nCol;

	/**
	 * abcsisse du coin supérieur gauche
	 */
	public double x0;

	/**
	 * ordonnée du coin supérieur gauche
	 */
	public double y0;

	/**
	 * matrice des z de la grille
	 */
	public double[][] valeurs;

	/* ******************************************************************************************************
	 * ***** CONSTRUCTEURS
	 * *******************************************************
	 * **********************************************
	 */

	/**
	 * Constructeur Grille.
	 * <p>
	 * Constructeur par defaut essentiellement un constructeur de test qui
	 * construit une grille arbitraire 5x5
	 * </p>
	 */
	public Grille() {

		pas = 25;// 1 ;
		nLig = 5;// 4 ;
		nCol = 5;// 4 ;
		x0 = 0;// 0 ;
		y0 = 100;// 0 ;

		valeurs = new double[][] {

		/*
		 * { 70 , 70 , 70 , 70 , 70 , 70 , 70 , 70 , 70 , 70 }, // altitudes des
		 * points du MNT (m) { 70 , 90 , 90 , 90 , 90 , 90 , 90 , 90 , 90 , 90
		 * }, { 70 , 90 , 94 , 94 , 94 , 94 , 94 , 94 , 94 , 91 }, { 70 , 90 ,
		 * 94 , 95 , 95 , 95 , 95 , 95 , 95 , 94 }, { 70 , 90 , 94 , 95 , 96 ,
		 * 96 , 96 , 95 , 95 , 95 }, { 70 , 92 , 94 , 95 , 96 , 500 , 96 , 95 ,
		 * 95 , 95 }, { 70 , 90 , 94 , 95 , 96 , 96 , 96 , 95 , 95 , 95 }, { 70
		 * , 90 , 94 , 95 , 95 , 95 , 95 , 95 , 95 , 94 }, { 70 , 90 , 94 , 94 ,
		 * 94 , 94 , 94 , 94 , 94 , 91 }, { 70 , 90 , 90 , 90 , 90 , 90 , 90 ,
		 * 90 , 90 , 90 }
		 */

		/*
		 * { 350 , 400 , 425 , 400 }, // grille test pour isolignes 500 { 300 ,
		 * 650 , 700 , 420 }, { 350 , 600 , 650 , 440 }, { 400 , 450 , 500 , 460
		 * },
		 */

		{ 0.5, 1.0, 1.0, 3.0, 2.0 }, { 1.0, 3.0, 6.0, 6.0, 3.0 },
				{ 3.0, 7.0, 9.0, 7.0, 3.0 }, { 5.0, 7.0, 8.0, 6.0, 2.0 },
				{ 1.0, 2.0, 3.0, 4.0, 3.0 },

		};
	}

	/**
	 * Constructeur Grille.
	 * 
	 * @param pas
	 *            Le pas de la grille.
	 * @param nL
	 *            Le nombre de lignes de la grille.
	 * @param nC
	 *            Le nombre de colonnes de la grille.
	 * @param x0
	 *            coordonnee x du point superieur gauche.
	 * @param y0
	 *            coordonnee y du point superieur gauche.
	 * @param values
	 *            matrice des z de la grille.
	 */
	public Grille(double pa, int nL, int nC, double xO, double yO,
			double[][] values) {

		pas = pa;
		nLig = nL;
		nCol = nC;
		x0 = xO;
		y0 = yO;

		valeurs = new double[nL][nC];
		System.arraycopy(values, 0, valeurs, 0, nLig);

	}

	/* ******************************************************************************************************
	 * ***** METHODES
	 * ************************************************************
	 * *****************************************
	 */

	/**
	 * Retourne le pas de la grille.
	 * 
	 * @return Le pas de la grille
	 */
	public double getpa() {
		return pas;
	}

	/**
	 * Retourne le nombre de lignes de la grille.
	 * 
	 * @return Le nLig de la grille
	 */
	public double getnL() {
		return nLig;
	}

	/**
	 * Retourne le nombre de colonnes de la grille.
	 * 
	 * @return Le nCol de la grille
	 */
	public double getnC() {
		return nCol;
	}

	/**
	 * Retourne la coordonnee x du coin superieur gauche de la grille
	 * 
	 * @return Le x0 de la grille
	 */
	public double getx0() {
		return x0;
	}

	/**
	 * Retourne la coordonnee y du coin superieur gauche de la grille
	 * 
	 * @return Le y0 de la grille
	 */
	public double gety0() {
		return y0;
	}

	/**
	 * Retourne une description textuelle de la grille
	 * 
	 * @return un String decrivant les caracteristiques de la grille
	 */
	public String toString() {
		String chaine = "\n";

		chaine += " MNT : \n\n";
		chaine += " pas : " + pas + "\n";
		chaine += " nombre de lignes: " + nLig + "\n";
		chaine += " nombre de colonnes: " + nCol + "\n";
		chaine += " x0= " + x0 + "\n";
		chaine += " y0= " + y0 + "\n";
		chaine += " z min = " + zMinMax()[0] + "\n";
		chaine += " z max = " + zMinMax()[1] + "\n";

		/*
		 * for (double[] l: valeurs) chaine += Arrays.toString(l)+"\n";
		 */

		return chaine;
	}

	/**
	 * Retourne les valeurs min et max de la grille sous forme d'un tableau de 2
	 * elements le 1er est le min et le 2e est le max
	 * 
	 * @return double[2] minmax ; avec minmax[0] le min et minmax[1] le max
	 */
	public double[] zMinMax() {
		double[] minmax = new double[2];

		int indColMin = 0;
		int indLigMin = 0;
		double valueMin = valeurs[0][0];

		int indColMax = 0;
		int indLigMax = 0;
		double valueMax = valeurs[0][0];

		for (int i = 0; i < nLig; i++) {
			for (int j = 0; j < nCol; j++) {
				if (valeurs[i][j] > valueMax) {
					valueMax = valeurs[i][j];
					indLigMax = i;
					indColMax = j;
				}
				if (valeurs[i][j] < valueMin) {
					valueMin = valeurs[i][j];
					indLigMin = i;
					indColMin = j;
				}
			}
		}

		minmax[0] = valeurs[indLigMin][indColMin];
		minmax[1] = valeurs[indLigMax][indColMax];

		return minmax;
	}

	/**
	 * Retourne le type d'un triangle donné, par rapport à un z donné voir
	 * codification dans doc du projet par ailleurs les sommets du triangle sont
	 * dans ces deux configuration possibles
	 * 
	 * <pre>
	 * a--b  ou     c
	 * |            |
	 * c 		 b--a
	 * </pre>
	 * 
	 * @param z
	 *            Le z donné.
	 * @param a
	 *            z du sommet a du triangle considéré
	 * @param b
	 *            z du sommet b du triangle considéré
	 * @param c
	 *            z du sommet c du triangle considéré
	 * 
	 *            Retourne un octet caractérisant de manière unique le type de
	 *            triangle pour un calcul d'interpolation
	 * 
	 * @return un nombre caractéristique de la configuration du triangle
	 *         considéré
	 * 
	 * @see makeIsoZt(double)
	 */
	private byte cellType(double z, double a, double b, double c) {
		byte type = 0;
		if (a > z)
			type += 0b0100;
		if (b > z)
			type += 0b0010;
		if (c > z)
			type += 0b0001;
		if (a == z)
			type += 0b01000000;
		if (b == z)
			type += 0b00100000;
		if (c == z)
			type += 0b00010000;
		return type;
	}

	/**
	 * Retourne une liste de couple de Point2D composant l'isoligne z les
	 * sommets du triangle sont dans ces deux configuration possibles code
	 * volontairement explosé pour chercher la performance gain polynomial sur
	 * la methode ecrite dans Isoligne testé sur core i3
	 * 
	 * <pre>
	 * a--b  ou     c
	 * |            |
	 * c         b--a
	 * </pre>
	 * 
	 * @param z
	 *            Le z de l'isoligne qu'on cherche à construire.
	 * 
	 * @return liste de couple de Point2D formant les segments de l'isoligne
	 * 
	 * @see cellType(double,double,double,double)
	 */
	public List<Point2D.Double[]> makeIsoZt(double z) {
		List<Point2D.Double[]> segments = new ArrayList<>();
		byte type;
		double x, y, x1, x2, y1, y2, xa, ya, xb, yb, xc, yc, za, zb, zc, k;
		for (int i = 0; i < nLig - 1; ++i) {
			for (int j = 0; j < nCol - 1; ++j) {
				// coordonnees point haut gauche du carre considere
				x = x0 + pas * j;
				y = y0 - pas * i;
				// 1er triangle
				xa = x;
				ya = y;
				xb = xa + pas;
				yb = ya;
				xc = xa;
				yc = ya - pas;
				za = valeurs[i][j];
				zb = valeurs[i][j + 1];
				zc = valeurs[i + 1][j];
				type = cellType(z, za, zb, zc);
				switch (type) {
				// cas où on ne fait rien : cas "0"
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
				// cas "1"
				case 0b00000001:
				case 0b00000110:
					k = (z - za) / (zc - za);
					x1 = xa;
					y1 = ya - k * pas;
					k = (zc - z) / (zc - zb);
					x2 = xa + k * pas;
					y2 = yc + k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "2"
				case 0b00000010:
				case 0b00000101:
					k = (z - za) / (zb - za);
					x1 = xa + k * pas;
					y1 = ya;
					k = (z - zc) / (zb - zc);
					x2 = xa + k * pas;
					y2 = yc + k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "3"
				case 0b00000011:
				case 0b00000100:
					k = (z - za) / (zc - za);
					x1 = xa;
					y1 = ya - k * pas;
					k = (z - za) / (zb - za);
					x2 = xa + k * pas;
					y2 = ya;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "4"
				case 0b00110100:
				case 0b00110000:
					x1 = xc;
					y1 = yc;
					x2 = xb;
					y2 = yb;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "5"
				case 0b01010010:
				case 0b01010000:
					x1 = xa;
					y1 = ya;
					x2 = xc;
					y2 = yc;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "6"
				case 0b01100001:
				case 0b01100000:
					x1 = xa;
					y1 = ya;
					x2 = xb;
					y2 = yb;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "7"
				case 0b01000010:
				case 0b01000001:
					x1 = xa;
					y1 = ya;
					k = (zc - z) / (zc - zb);
					x2 = xa + k * pas;
					y2 = yc + k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "8"
				case 0b00100100:
				case 0b00100001:
					x1 = xb;
					y1 = yb;
					k = (z - zc) / (za - zc);
					x2 = xa;
					y2 = yc + k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "9"
				case 0b00010010:
				case 0b00010100:
					x1 = xc;
					y1 = yc;
					k = (z - za) / (zb - za);
					x2 = xa + k * pas;
					y2 = ya;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				}

				// 2e triangle, idem precedemment mais les signes sont inverses
				// pour k*pas
				xa = x + pas;
				ya = y - pas;
				xb = xc;
				yb = yc;
				xc = x + pas;
				yc = y;
				za = valeurs[i + 1][j + 1];
				zb = valeurs[i + 1][j];
				zc = valeurs[i][j + 1];
				type = cellType(z, za, zb, zc);
				switch (type) {
				// cas où on ne fait rien : cas "0"
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
				// cas "1"
				case 0b00000001:
				case 0b00000110:
					k = (z - za) / (zc - za);
					x1 = xa;
					y1 = ya + k * pas;
					k = (zc - z) / (zc - zb);
					x2 = xa - k * pas;
					y2 = yc - k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "2"
				case 0b00000010:
				case 0b00000101:
					k = (z - za) / (zb - za);
					x1 = xa - k * pas;
					y1 = ya;
					k = (z - zc) / (zb - zc);
					x2 = xa - k * pas;
					y2 = yc - k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "3"
				case 0b00000011:
				case 0b00000100:
					k = (z - za) / (zc - za);
					x1 = xa;
					y1 = ya + k * pas;
					k = (z - za) / (zb - za);
					x2 = xa - k * pas;
					y2 = ya;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "4"
				case 0b00110100:
				case 0b00110000:
					x1 = xc;
					y1 = yc;
					x2 = xb;
					y2 = yb;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "5"
				case 0b01010010:
				case 0b01010000:
					x1 = xa;
					y1 = ya;
					x2 = xc;
					y2 = yc;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "6"
				case 0b01100001:
				case 0b01100000:
					x1 = xa;
					y1 = ya;
					x2 = xb;
					y2 = yb;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "7"
				case 0b01000010:
				case 0b01000001:
					x1 = xa;
					y1 = ya;
					k = (zc - z) / (zc - zb);
					x2 = xa - k * pas;
					y2 = yc - k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "8"
				case 0b00100100:
				case 0b00100001:
					x1 = xb;
					y1 = yb;
					k = (z - zc) / (za - zc);
					x2 = xa;
					y2 = yc - k * pas;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				// cas "9"
				case 0b00010010:
				case 0b00010100:
					x1 = xc;
					y1 = yc;
					k = (z - za) / (zb - za);
					x2 = xa - k * pas;
					y2 = ya;
					segments.add(new Point2D.Double[] {
							new Point2D.Double(x1, y1),
							new Point2D.Double(x2, y2) });
					break;
				}

			}
			// System.out.println();
		}
		return segments;
	}

	/**
	 * Retourne un ensemble d'isolignes de taille nombre, interpolées entre zmin
	 * et zmax
	 * 
	 * @param nombre
	 *            Le nombre d'isolignes souhaitées
	 * 
	 * @return ArrayList de n isolignes, chaque isoligne étant une liste de
	 *         couples de Point2D
	 * 
	 * @see makeIsoZt(double)
	 */
	public ArrayList<List<Point2D.Double[]>> makeListIsos(int nombre) {
		System.out.println("Entr�e dans la fonction makeListIsos");
		ArrayList<List<Point2D.Double[]>> isos = new ArrayList<List<Point2D.Double[]>>();
		System.out.println("tableau initialis�");
		double zmin = this.zMinMax()[0];
		double zmax = this.zMinMax()[1];
		System.out.println("zmin et zmax calcul�s");
		System.out.println("zmin=" + zmin + "m et zmax=" + zmax + "m");
		System.out.println("(zmax-zmin)/(nombre -1)=" + (zmax - zmin)
				/ (nombre - 1));
		long startTime = System.nanoTime();
		if (nombre <= 1) // si on en veut 1 ou moins on genere que dalle
			return isos;
		double increment = 1;
		double depart = 0;
		double arrivee = 100;
		double zMinInt = (int) zmin;
		double zMaxInt = (int) zmax;
		if (zmin - zMinInt == 0) { // zmin est un entier
			depart = zmin;
		} else {
			depart = zMinInt + 1;
		}
		if (zmax - zMaxInt == 0) { // zmax est un entier
			arrivee = zmax;
		} else {
			arrivee = zMaxInt;
		}
		if ((arrivee - depart) / (nombre - 1) < 1) {
			increment = (arrivee - depart) / (nombre - 1);
		} else {
			increment = (int) (arrivee - depart) / (nombre - 1);
		}
		for (double i = depart; i < arrivee; i += increment) {
			isos.add(this.makeIsoZt(i));
			// System.out.println("isoligne z="+i+"m cr��e");
		}
		long endTime = System.nanoTime();
		System.out.print("Creation isolignes : ");
		System.out.println(((float) endTime - startTime) / 1e9 + " secondes");
		System.out.println("Nb isolignes :" + isos.size());
		return isos;
	}

	/**
	 * Méthode permettant de transformer les coordonnées d'une isoligne. Les
	 * isolignes obtenues avec la méthode makeIsoZt() sont ainsi converties en
	 * listes de segments, eux-même formés d'un tableau 2*3 exprimant les
	 * coordonnées du point initial et du point final dans le repère du modèle
	 * 3D et sont ainsi directement exploitables par le moteur de la classe
	 * Panneau3D
	 * 
	 * @param isoI
	 *            isoligne considérée, i.e liste de couples de Point2D
	 * @param z
	 *            z de cette isoligne
	 * 
	 * @return ArrayList des couples de coordonnées tridimensionnelles de chaque
	 *         segment de l'isoligne
	 * 
	 * @see makeIsoZt(double)
	 */

	public ArrayList<double[][]> transfoIso(List<Point2D.Double[]> isoI,
			double z) {
		// isoI = isoligne initiale
		// z = altitude de cette isoligne
		ArrayList<double[][]> isoF = new ArrayList<>(); // isoligne transform�e

		double empriseX = pas * (nCol - 1), // Emprise Nord-Sud de la grille
		empriseY = pas * (nLig - 1), // Emprise Est-Ouest de la grille
		xCentre = x0 + (empriseX / 2), // Coordonn�e Easting du centre de la
										// grille
		yCentre = y0 - (empriseY / 2), // Coordonn�e Northing du centre de la
										// grille
		zMin = this.zMinMax()[0]; // Altitude la plus basse du MNT

		for (int i = 0; i < isoI.size(); i++) {
			double[][] segment = {
					{ 200 * (isoI.get(i)[0].x - xCentre) / empriseX,
							200 * (isoI.get(i)[0].y - yCentre) / empriseX,
							200 * (z - zMin) / empriseX },
					{ 200 * (isoI.get(i)[1].x - xCentre) / empriseX,
							200 * (isoI.get(i)[1].y - yCentre) / empriseX,
							200 * (z - zMin) / empriseX } };
			isoF.add(segment);
		}

		return isoF;
	}

} // fin de la classe
