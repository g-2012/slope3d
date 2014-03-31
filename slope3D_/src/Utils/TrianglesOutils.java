package Utils;

/**
 * Classe abstraite utilitaire permettant un certain nombre d'opérations
 * mathématiques utiles pour la géométrie d'un triangle
 * <p>
 * <ul>
 * <li>Calculer coordonnées du vecteur
 * <math
	 * xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>AB</mi>
	 * <mo>&rarr;</mo> </mover> </math> à partir des points A et B</li>
 * <li>Calculer la norme d'un vecteur</li>
 * <li>Calculer le produit scalaire de 2 vecteurs</li>
 * <li>Calculer le produit vectoriel de 2 vecteurs</li>
 * <li>Calculer le vecteur normal unitaire à un triangle</li>
 * <li>Interpoler coordonnées d'un point de cote zi entre M de cote zm et N de
 * cote zn</li>
 * </ul>
 * </p>
 * <p>
 * 
 * @author Jigx
 * @version 0.6
 */
public abstract class TrianglesOutils {

	/**
	 * Calcule les coordonnees du vecteur <math
	 * xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>AB</mi>
	 * <mo>&rarr;</mo> </mover> </math> à partir de A et B
	 * 
	 * @param A
	 *            tableau des coordonnees x,y,z du point A
	 * @param B
	 *            tableau des coordonnees x,y,z du point B
	 * 
	 * @return un tableau de 3 double, contenant x,y,z du vecteur <math
	 *         xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>AB</mi>
	 *         <mo>&rarr;</mo> </mover> </math>
	 * 
	 */
	public static double[] coordVecteur(double A[], double B[]) {
		double[] coord = new double[3];
		coord[0] = B[0] - A[0];
		coord[1] = B[1] - A[1];
		coord[2] = B[2] - A[2];
		return coord;
	}

	/**
	 * Calcule la norme du vecteur <math
	 * xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>V</mi>
	 * <mo>&rarr;</mo> </mover> </math>
	 * 
	 * @param V
	 *            tableau des coordonnees x,y,z du vecteur <math
	 *            xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>V</mi>
	 *            <mo>&rarr;</mo> </mover> </math>
	 * 
	 * @return la norme de <math xmlns="http://www.w3.org/1998/Math/MathML">
	 *         <mover> <mi>V</mi> <mo>&rarr;</mo> </mover> </math>
	 * 
	 */
	public static double norme(double V[]) {
		double norme;
		norme = Math.sqrt(Math.pow(V[0], 2) + Math.pow(V[1], 2)
				+ Math.pow(V[2], 2));
		return norme;
	}

	/**
	 * Calcule le produit scalaire des vecteurs <math
	 * xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>U</mi>
	 * <mo>&rarr;</mo> </mover> </math> et <math
	 * xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>V</mi>
	 * <mo>&rarr;</mo> </mover> </math>
	 * 
	 * @param U
	 *            tableau des coordonnees x,y,z du vecteur <math
	 *            xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>U</mi>
	 *            <mo>&rarr;</mo> </mover> </math>
	 * @param V
	 *            tableau des coordonnees x,y,z du vecteur <math
	 *            xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>V</mi>
	 *            <mo>&rarr;</mo> </mover> </math>
	 * 
	 * 
	 * @return le produit scalaire des vecteurs en entrée
	 */
	public static double produitScalaire(double U[], double V[]) {
		double produitScalaire;
		produitScalaire = U[0] * V[0] + U[1] * V[1] + U[2] * V[2];
		return produitScalaire;
	}

	/**
	 * Calcule le produit vectoriel des vecteurs <math
	 * xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>U</mi>
	 * <mo>&rarr;</mo> </mover> </math> et <math
	 * xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>V</mi>
	 * <mo>&rarr;</mo> </mover> </math>
	 * 
	 * @param U
	 *            tableau des coordonnees x,y,z du vecteur <math
	 *            xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>U</mi>
	 *            <mo>&rarr;</mo> </mover> </math>
	 * @param V
	 *            tableau des coordonnees x,y,z du vecteur <math
	 *            xmlns="http://www.w3.org/1998/Math/MathML"> <mover> <mi>V</mi>
	 *            <mo>&rarr;</mo> </mover> </math>
	 * 
	 * 
	 * @return un tableau contenant les coordonnees du vecteur resultant du
	 *         produit vectoriel des vecteurs en entrée
	 */
	public static double[] produitVectoriel(double U[], double V[]) {
		double[] produitVect;
		produitVect = new double[3];
		produitVect[0] = U[1] * V[2] - U[2] * V[1];
		produitVect[1] = U[2] * V[0] - U[0] * V[2];
		produitVect[2] = U[0] * V[1] - U[1] * V[0];
		// double n = norme(produitVect);
		return produitVect;
	}

	/**
	 * Calcule le produit normal à un plan defini par 3 points
	 * 
	 * @param a
	 *            tableau des coordonnees x,y,z d'un point
	 * @param b
	 *            tableau des coordonnees x,y,z d'un point
	 * @param c
	 *            tableau des coordonnees x,y,z d'un point
	 * 
	 * @return un tableau contenant les coordonnees du vecteur normal unitaire
	 *         au plan abc
	 */
	public static double[] vecteurNormalTriangle(double a[], double b[],
			double c[]) {
		double[] n;
		n = new double[3];
		n[0] = 0;
		n[1] = 0;
		n[2] = 0;

		double[] ab, ac;

		ab = coordVecteur(a, b);
		ac = coordVecteur(a, c);
		n = produitVectoriel(ab, ac);

		double N = norme(n);

		if (n[2] < 0) {
			for (int j = 0; j < 3; j++) {
				double T = -n[j] / N;
				n[j] = T;
			}
		} else {
			for (int i = 0; i < 3; i++) {
				double t = n[i] / N;
				n[i] = t;
			}
		}
		return n;
	}

	/**
	 * Calcule la pente d'un vecteur
	 * 
	 * @param n
	 *            tableau des coordonnees x,y,z du vecteur
	 * 
	 * @return la pente du vecteur par rapport à l'horizontale
	 */
	public static double penteTriangle(double n[]) {
		double pente;
		pente = 0;
		double[] k;
		k = new double[3];
		k[0] = 0;
		k[1] = 0;
		k[2] = 1;

		double nScalairek = produitScalaire(n, k);
		pente = Math.acos(nScalairek);
		pente = Math.toDegrees(pente);
		return pente;
	}

	/**
	 * Calcule les coordonnées d'un point I de cote zi, interpolé entre m de
	 * cote zm et n de cote zn
	 * 
	 * @param zi
	 *            côte zi voulue
	 * @param m
	 *            tableau des coordonnees x,y,z du point m
	 * @param n
	 *            tableau des coordonnees x,y,z du point n
	 * 
	 * @return coordonnées x,y,zi du point I entre m et n
	 */
	public static double[] interpol(double zi, double[] m, double[] n) {

		double[] i = new double[3];

		if (m[0] == n[0]) {
			i[0] = m[0];
		} else {
			double ax, bx;
			ax = (n[2] - m[2]) / (n[0] - m[0]);
			bx = m[2] - ax * m[0];
			// System.out.println(" ax = " +ax);
			// System.out.println(" bx = " +bx);
			i[0] = (zi - bx) / ax;
		}

		if (m[1] == n[1]) {
			i[1] = m[1];
		} else {
			double ay, by;
			ay = (n[2] - m[2]) / (n[1] - m[1]);
			by = m[2] - ay * m[1];
			// System.out.println(" ay = " +ay);
			// System.out.println(" by = " +by);
			i[1] = (zi - by) / ay;
		}

		i[2] = zi;

		return i;
	}

} // fin de la classe