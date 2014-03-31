package structures;

import java.io.Serializable;

import Utils.FilesUtils;
import Utils.TrianglesOutils;

/**
 * Triangle est la classe représentant un triangle en 3 dimensions.
 * <p>
 * Une grille est caractérisée par les informations suivantes :
 * <ul>
 * <li>les coordonnées de ses sommets</li>
 * <li>son vecteur normal</li>
 * <li>sa pente</li>
 * </ul>
 * </p>
 * 
 * @author Jigx
 * @version 0.6
 */
public class Triangle implements Serializable {

	/* ******************************************************************************************************
	 * ***** ATTRIBUTS
	 * ***********************************************************
	 * ******************************************
	 */
	/**
	 * indice du triangle
	 */
	private int indice;

	/**
	 * coordonnees du sommet a
	 */
	private double[] a = new double[3];
	/**
	 * coordonnees du sommet b
	 */
	private double[] b = new double[3];

	/**
	 * coordonnees du sommet c
	 */
	private double[] c = new double[3];

	/**
	 * coordonnees du vecteur normal
	 */
	private double[] vecteurNormal = new double[3];
	/**
	 * pente par rapport à l'horizontale
	 */
	private double pente;

	/* ******************************************************************************************************
	 * ***** CONSTRUCTEURS
	 * *******************************************************
	 * **********************************************
	 */

	/**
	 * Constructeur Triangle. construit un triangle en lui passant tous les
	 * paramètres (par ex en les relisant dans un fichier)
	 * 
	 * @param i
	 *            indice
	 * @param e
	 *            coordonnees d'un sommet
	 * @param f
	 *            coordonnees d'un sommet
	 * @param g
	 *            coordonnees d'un sommet
	 * @param n
	 *            coordonnees du vecteur normal
	 * @param p
	 *            pente
	 */
	public Triangle(int i, double e[], double f[], double g[], double n[],
			double p) {

		indice = i;

		a[0] = e[0];
		a[1] = e[1];
		a[2] = e[2];

		b[0] = f[0];
		b[1] = f[1];
		b[2] = f[2];

		c[0] = g[0];
		c[1] = g[1];
		c[2] = g[2];

		vecteurNormal = n;

		pente = p;

	}

	/**
	 * Constructeur Triangle. construit un triangle en lui passant son indice et
	 * les coordonnees des sommets calcule le vecteur normal et la pente
	 * 
	 * @param ind
	 *            indice
	 * @param xA
	 *            coordonnee x d'un sommet A
	 * @param yA
	 *            coordonnee y d'un sommet A
	 * @param zA
	 *            coordonnee z d'un sommet A
	 * @param xB
	 *            coordonnee x d'un sommet B
	 * @param yB
	 *            coordonnee y d'un sommet B
	 * @param zB
	 *            coordonnee y d'un sommet B
	 * @param xC
	 *            coordonnee x d'un sommet C
	 * @param yC
	 *            coordonnee y d'un sommet C
	 * @param zC
	 *            coordonnee y d'un sommet C
	 * 
	 */
	public Triangle(int ind, double xA, double yA, double zA, double xB,
			double yB, double zB, double xC, double yC, double zC) {

		indice = ind;

		a[0] = xA;
		a[1] = yA;
		a[2] = zA;

		b[0] = xB;
		b[1] = yB;
		b[2] = zB;

		c[0] = xC;
		c[1] = yC;
		c[2] = zC;

		vecteurNormal = TrianglesOutils.vecteurNormalTriangle(a, b, c);

		pente = TrianglesOutils.penteTriangle(vecteurNormal);

	}

	/**
	 * Constructeur Triangle. construit un triangle en lui passant les
	 * coordonnees des sommets sous forme de 3 tableaux de coordonnées calcule
	 * le vecteur normal et la pente
	 * 
	 * @param e
	 *            coordonnees d'un sommet e
	 * @param f
	 *            coordonnees d'un sommet f
	 * @param g
	 *            coordonnees d'un sommet g
	 * 
	 */
	public Triangle(double e[], double f[], double g[]) {

		a[0] = e[0];
		a[1] = e[1];
		a[2] = e[2];

		b[0] = f[0];
		b[1] = f[1];
		b[2] = f[2];

		c[0] = g[0];
		c[1] = g[1];
		c[2] = g[2];

		vecteurNormal = TrianglesOutils.vecteurNormalTriangle(a, b, c);

		pente = TrianglesOutils.penteTriangle(vecteurNormal);

	}

	/**
	 * Constructeur Triangle. construit un triangle en lui passant les
	 * coordonnees des sommets sous forme de 3 tableaux de coordonnées, ainsi
	 * qu'un indice calcule le vecteur normal et la pente
	 * 
	 * @param ind
	 *            indice
	 * @param e
	 *            coordonnees d'un sommet e
	 * @param f
	 *            coordonnees d'un sommet f
	 * @param g
	 *            coordonnees d'un sommet g
	 * 
	 */
	public Triangle(int ind, double e[], double f[], double g[]) {

		indice = ind;

		a[0] = e[0];
		a[1] = e[1];
		a[2] = e[2];

		b[0] = f[0];
		b[1] = f[1];
		b[2] = f[2];

		c[0] = g[0];
		c[1] = g[1];
		c[2] = g[2];

		vecteurNormal = TrianglesOutils.vecteurNormalTriangle(a, b, c);

		pente = TrianglesOutils.penteTriangle(vecteurNormal);

	}

	/**
	 * Constructeur Triangle. construit un triangle en lui passant une chaine
	 * formatée voir doc du projet par ailleurs pour le format
	 * 
	 * @param chaine
	 *            description du triangle
	 * 
	 * @see caracteristiquesTriangle()
	 */
	public Triangle(String chaine) {

		indice = FilesUtils.getIndice(chaine);

		a = FilesUtils.getVertices(chaine)[0];
		// a[1] = FilesUtils.getVertices(chaine)[0][1];
		// a[2] = FilesUtils.getVertices(chaine)[0][2];

		b = FilesUtils.getVertices(chaine)[1];
		// b[1] = FilesUtils.getVertices(chaine)[1][1];
		// b[2] = FilesUtils.getVertices(chaine)[1][2];

		c = FilesUtils.getVertices(chaine)[2];
		// c[1] = FilesUtils.getVertices(chaine)[2][1];
		// c[2] = FilesUtils.getVertices(chaine)[2][2];

		vecteurNormal = FilesUtils.getNormale(chaine);
		// vecteurNormal[1] = FilesUtils.getNormale(chaine)[1];
		// vecteurNormal[2] = FilesUtils.getNormale(chaine)[2];

		pente = FilesUtils.getPente(chaine);

	}

	/* ******************************************************************************************************
	 * ***** METHODES
	 * ************************************************************
	 * *****************************************
	 */
	/**
	 * Retourne coordonnées du 1er sommet du triangle.
	 * 
	 * @return coordonnées sommet A
	 */
	public double[] geta() {
		return a;
	}

	/**
	 * Retourne coordonnées du 2e sommet du triangle.
	 * 
	 * @return coordonnées sommet B
	 */
	public double[] getb() {
		return b;
	}

	/**
	 * Retourne coordonnées du 3e sommet du triangle.
	 * 
	 * @return coordonnées sommet C
	 */
	public double[] getc() {
		return c;
	}

	/**
	 * Retourne coordonnée x du 1er sommet du triangle.
	 * 
	 * @return x1
	 */
	public double getx1() {
		return a[0];
	}

	/**
	 * Retourne coordonnée y du 1er sommet du triangle.
	 * 
	 * @return y1
	 */
	public double gety1() {
		return a[1];
	}

	/**
	 * Retourne coordonnée z du 1er sommet du triangle.
	 * 
	 * @return z1
	 */
	public double getz1() {
		return a[2];
	}

	/**
	 * Retourne coordonnée x du 2e sommet du triangle.
	 * 
	 * @return x2
	 */
	public double getx2() {
		return b[0];
	}

	/**
	 * Retourne coordonnée y du 2e sommet du triangle.
	 * 
	 * @return y2
	 */
	public double gety2() {
		return b[1];
	}

	/**
	 * Retourne coordonnée z du 2e sommet du triangle.
	 * 
	 * @return z2
	 */
	public double getz2() {
		return b[2];
	}

	/**
	 * Retourne coordonnée x du 3e sommet du triangle.
	 * 
	 * @return x3
	 */
	public double getx3() {
		return c[0];
	}

	/**
	 * Retourne coordonnée y du 3e sommet du triangle.
	 * 
	 * @return y3
	 */
	public double gety3() {
		return c[1];
	}

	/**
	 * Retourne coordonnée z du 3e sommet du triangle.
	 * 
	 * @return z3
	 */
	public double getz3() {
		return c[2];
	}

	/**
	 * Retourne coordonnée x du vecteur normal.
	 * 
	 * @return x du vecteur normal
	 */
	public double getxn() {
		return vecteurNormal[0];
	}

	/**
	 * Retourne coordonnée y du vecteur normal.
	 * 
	 * @return y du vecteur normal
	 */
	public double getyn() {
		return vecteurNormal[1];
	}

	/**
	 * Retourne coordonnée z du vecteur normal.
	 * 
	 * @return z du vecteur normal
	 */
	public double getzn() {
		return vecteurNormal[2];
	}

	/**
	 * Retourne indice du triangle.
	 * 
	 * @return indice
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Retourne pente du triangle.
	 * 
	 * @return pente
	 */
	public double getPente() {
		return pente;
	}

	/**
	 * Affiche une description textuelle du triangle
	 * 
	 */

	public void afficherCaracteristiquesTriangle() {

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" Voici les caractéristiques  du Triangle "
				+ this.getIndice() + " : ");
		System.out.println(" ");
		System.out.println(" Ses trois sommets : ");
		System.out.println(" ");
		System.out.println(" x1 : " + this.getx1());
		System.out.println(" y1 : " + this.gety1());
		System.out.println(" z1 : " + this.getz1());
		System.out.println(" ");
		System.out.println(" x2 : " + this.getx2());
		System.out.println(" y2 : " + this.gety2());
		System.out.println(" z2 : " + this.getz2());
		System.out.println(" ");
		System.out.println(" x3 : " + this.getx3());
		System.out.println(" y3 : " + this.gety3());
		System.out.println(" z3 : " + this.getz3());
		System.out.println(" ");
		System.out.println(" Son vecteur normal : ");
		System.out.println(" ");
		System.out.println(" xn  = " + this.getxn());
		System.out.println(" yn  = " + this.getyn());
		System.out.println(" zn  = " + this.getzn());
		System.out.println(" ");
		System.out.println(" La pente du " + this.getIndice()
				+ "eme triangle est de " + this.getPente() + "°");
	}

	/**
	 * Retourne une description textuelle du triangle
	 * 
	 * @return un String formaté decrivant les caracteristiques du triangle
	 */
	public String caracteristiquesTriangle() {
		String chaine;
		chaine = "i:" + this.getIndice() + ";" + "a:(" + this.getx1() + ","
				+ this.gety1() + "," + this.getz1() + ")" + "b:("
				+ this.getx2() + "," + this.gety2() + "," + this.getz2() + ")"
				+ "c:(" + this.getx3() + "," + this.gety3() + ","
				+ this.getz3() + ");" + "p:" + this.getPente() + ";" + "n:("
				+ this.getxn() + "," + this.getyn() + "," + this.getzn() + ")";
		return chaine;
	}

} // fin de la classe