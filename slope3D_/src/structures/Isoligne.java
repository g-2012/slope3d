package structures;

import structures.Triangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Utils.FilesUtils;
import Utils.GrilleATriangles;
import Utils.TrianglesOutils;

/**
 * @deprecated plus utilisée pour raison de perfs execution/memoire
 * Isoligne était la classe représentant une isoligne.
 * Elle n'est plus utilisée pour des raisons de performance 
 * Elle produisait les isolignes à partir d'un ensemble de triangles
 * <p>
 * Une isoligne est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un z </li>
 * <li>Un ensemble de segments</li>
 * </ul>
 * </p>
 * 
 * @author Jigx
 * @version 0.6
 * 
 */
public class Isoligne {

	/* ******************************************************************************************************
	 * ***** ATTRIBUTS
	 * ***********************************************************
	 * ******************************************
	 */
	/**
	 * z de l'isoligne considérée
	 */
	public double z;

	/**
	 * liste de segments composant l'isoligne
	 */
	public ArrayList<double[][]> segments = new ArrayList<>();

	/* ******************************************************************************************************
	 * ***** CONSTRUCTEURS
	 * *******************************************************
	 * **********************************************
	 */

	/**
	 * Constructeur Isoligne. met dans l'attribut segments l'ensemble des
	 * segments constituant l'isoligne
	 * 
	 * @param zi
	 *            Le z voulu pour l'isoligne
	 * @param listeT
	 *            La liste de triangles sur laquelle on calcule l'isoligne
	 * 
	 */
	public Isoligne(double zi, List<Triangle> listeT) {

		z = zi;
		double[] pi = new double[3];
		double[] pf = new double[3];
		pi[0] = pi[1] = pi[2] = 0;
		pf[0] = pf[1] = pf[2] = 0;
		int k = 0;
		boolean rien = false;
		for (int i = 0; i < listeT.size(); i++) {
			/*
			 * System.out.print("\n"+i+"eme triangle \n");
			 * System.out.print(listeT.get(i).geta()[0]+" ");
			 * System.out.print(listeT.get(i).geta()[1]+" ");
			 * System.out.println(listeT.get(i).geta()[2]+" ");
			 * 
			 * System.out.print(listeT.get(i).getb()[0]+" ");
			 * System.out.print(listeT.get(i).getb()[1]+" ");
			 * System.out.println(listeT.get(i).getb()[2]+" ");
			 * 
			 * System.out.print(listeT.get(i).getc()[0]+" ");
			 * System.out.print(listeT.get(i).getc()[1]+" ");
			 * System.out.println(listeT.get(i).getc()[2]+"\n ");
			 */

			// 1
			if (listeT.get(i).getz1() == zi && listeT.get(i).getz2() > zi
					&& listeT.get(i).getz3() < zi
					|| listeT.get(i).getz1() == zi
					&& listeT.get(i).getz2() < zi && listeT.get(i).getz3() > zi) {

				pi = listeT.get(i).geta();
				pf = TrianglesOutils.interpol(zi, listeT.get(i).getb(), listeT
						.get(i).getc());
			}

			// 2
			if (listeT.get(i).getz1() > zi && listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() < zi || listeT.get(i).getz1() < zi
					&& listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() > zi) {

				pi = listeT.get(i).getb();
				pf = TrianglesOutils.interpol(zi, listeT.get(i).geta(), listeT
						.get(i).getc());
			}
			// 3
			if (listeT.get(i).getz1() > zi && listeT.get(i).getz2() < zi
					&& listeT.get(i).getz3() == zi
					|| listeT.get(i).getz1() < zi && listeT.get(i).getz2() > zi
					&& listeT.get(i).getz3() == zi) {

				pi = listeT.get(i).getc();
				pf = TrianglesOutils.interpol(zi, listeT.get(i).geta(), listeT
						.get(i).getb());
			}

			// 4
			if (listeT.get(i).getz1() > zi && listeT.get(i).getz2() < zi
					&& listeT.get(i).getz3() < zi || listeT.get(i).getz1() < zi
					&& listeT.get(i).getz2() > zi && listeT.get(i).getz3() > zi) {

				pi = TrianglesOutils.interpol(zi, listeT.get(i).geta(), listeT
						.get(i).getb());
				pf = TrianglesOutils.interpol(zi, listeT.get(i).geta(), listeT
						.get(i).getc());
			}

			// 5
			if (listeT.get(i).getz1() < zi && listeT.get(i).getz2() > zi
					&& listeT.get(i).getz3() < zi || listeT.get(i).getz1() > zi
					&& listeT.get(i).getz2() < zi && listeT.get(i).getz3() > zi) {

				pi = TrianglesOutils.interpol(zi, listeT.get(i).geta(), listeT
						.get(i).getb());
				pf = TrianglesOutils.interpol(zi, listeT.get(i).getb(), listeT
						.get(i).getc());
			}

			// 6
			if (listeT.get(i).getz1() < zi && listeT.get(i).getz2() < zi
					&& listeT.get(i).getz3() > zi || listeT.get(i).getz1() > zi
					&& listeT.get(i).getz2() > zi && listeT.get(i).getz3() < zi) {

				pi = TrianglesOutils.interpol(zi, listeT.get(i).geta(), listeT
						.get(i).getc());
				pf = TrianglesOutils.interpol(zi, listeT.get(i).getb(), listeT
						.get(i).getc());
			}

			// 7
			if (listeT.get(i).getz1() > zi && listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() == zi
					|| listeT.get(i).getz1() < zi
					&& listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() == zi) {

				pi = listeT.get(i).getb();
				pf = listeT.get(i).getc();
			}

			// 8
			if (listeT.get(i).getz1() == zi && listeT.get(i).getz2() > zi
					&& listeT.get(i).getz3() == zi
					|| listeT.get(i).getz1() == zi
					&& listeT.get(i).getz2() < zi
					&& listeT.get(i).getz3() == zi) {

				pi = listeT.get(i).geta();
				pf = listeT.get(i).getc();
			}

			// 9
			if (listeT.get(i).getz1() == zi && listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() > zi
					|| listeT.get(i).getz1() == zi
					&& listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() < zi) {

				pi = listeT.get(i).geta();
				pf = listeT.get(i).getb();
			}

			// 10
			if (listeT.get(i).getz1() < zi && listeT.get(i).getz2() < zi
					&& listeT.get(i).getz3() < zi || listeT.get(i).getz1() > zi
					&& listeT.get(i).getz2() > zi && listeT.get(i).getz3() > zi
					|| listeT.get(i).getz1() == zi
					&& listeT.get(i).getz2() > zi && listeT.get(i).getz3() > zi
					|| listeT.get(i).getz1() == zi
					&& listeT.get(i).getz2() < zi && listeT.get(i).getz3() < zi
					|| listeT.get(i).getz1() > zi
					&& listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() > zi || listeT.get(i).getz1() < zi
					&& listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() < zi || listeT.get(i).getz1() > zi
					&& listeT.get(i).getz2() > zi
					&& listeT.get(i).getz3() == zi
					|| listeT.get(i).getz1() < zi && listeT.get(i).getz2() < zi
					&& listeT.get(i).getz3() == zi
					|| listeT.get(i).getz1() == zi
					&& listeT.get(i).getz2() == zi
					&& listeT.get(i).getz3() == zi) {

				rien = true;
				// pi[0] = pi[1] = pi[2] = 0;
				// pf[0] = pf[1] = pf[2] = 0;
			}

			double[][] seg = new double[2][3];
			seg[0] = pi;
			seg[1] = pf;
			if (!rien)
				segments.add(seg);
			rien = false;

		} // end for i
	}

	/**
	 * Constructeur Isoligne. met dans l'attribut segments l'ensemble des
	 * segments constituant l'isoligne
	 * 
	 * @param zi
	 *            Le z voulu pour l'isoligne
	 * @param listeT
	 *            Le tableau de triangles sur lequel on calcule l'isoligne
	 * 
	 */
	public Isoligne(double zi, Triangle[] triangles) {

		z = zi;
		double[] pi = new double[3];
		double[] pf = new double[3];
		pi[0] = pi[1] = pi[2] = 0;
		pf[0] = pf[1] = pf[2] = 0;
		int k = 0;
		boolean rien = false;
		for (int i = 0; i < triangles.length; i++) {
			/*
			 * System.out.print("\n"+i+"eme triangle \n");
			 * System.out.print(triangles[i].geta()[0]+" ");
			 * System.out.print(triangles[i].geta()[1]+" ");
			 * System.out.println(triangles[i].geta()[2]+" ");
			 * 
			 * System.out.print(triangles[i].getb()[0]+" ");
			 * System.out.print(triangles[i].getb()[1]+" ");
			 * System.out.println(triangles[i].getb()[2]+" ");
			 * 
			 * System.out.print(triangles[i].getc()[0]+" ");
			 * System.out.print(triangles[i].getc()[1]+" ");
			 * System.out.println(triangles[i].getc()[2]+"\n ");
			 */

			// 1
			if (triangles[i].getz1() == zi && triangles[i].getz2() > zi
					&& triangles[i].getz3() < zi || triangles[i].getz1() == zi
					&& triangles[i].getz2() < zi && triangles[i].getz3() > zi) {

				pi = triangles[i].geta();
				pf = TrianglesOutils.interpol(zi, triangles[i].getb(),
						triangles[i].getc());
			}

			// 2
			if (triangles[i].getz1() > zi && triangles[i].getz2() == zi
					&& triangles[i].getz3() < zi || triangles[i].getz1() < zi
					&& triangles[i].getz2() == zi && triangles[i].getz3() > zi) {

				pi = triangles[i].getb();
				pf = TrianglesOutils.interpol(zi, triangles[i].geta(),
						triangles[i].getc());
			}
			// 3
			if (triangles[i].getz1() > zi && triangles[i].getz2() < zi
					&& triangles[i].getz3() == zi || triangles[i].getz1() < zi
					&& triangles[i].getz2() > zi && triangles[i].getz3() == zi) {

				pi = triangles[i].getc();
				pf = TrianglesOutils.interpol(zi, triangles[i].geta(),
						triangles[i].getb());
			}

			// 4
			if (triangles[i].getz1() > zi && triangles[i].getz2() < zi
					&& triangles[i].getz3() < zi || triangles[i].getz1() < zi
					&& triangles[i].getz2() > zi && triangles[i].getz3() > zi) {

				pi = TrianglesOutils.interpol(zi, triangles[i].geta(),
						triangles[i].getb());
				pf = TrianglesOutils.interpol(zi, triangles[i].geta(),
						triangles[i].getc());
			}

			// 5
			if (triangles[i].getz1() < zi && triangles[i].getz2() > zi
					&& triangles[i].getz3() < zi || triangles[i].getz1() > zi
					&& triangles[i].getz2() < zi && triangles[i].getz3() > zi) {

				pi = TrianglesOutils.interpol(zi, triangles[i].geta(),
						triangles[i].getb());
				pf = TrianglesOutils.interpol(zi, triangles[i].getb(),
						triangles[i].getc());
			}

			// 6
			if (triangles[i].getz1() < zi && triangles[i].getz2() < zi
					&& triangles[i].getz3() > zi || triangles[i].getz1() > zi
					&& triangles[i].getz2() > zi && triangles[i].getz3() < zi) {

				pi = TrianglesOutils.interpol(zi, triangles[i].geta(),
						triangles[i].getc());
				pf = TrianglesOutils.interpol(zi, triangles[i].getb(),
						triangles[i].getc());
			}

			// 7
			if (triangles[i].getz1() > zi && triangles[i].getz2() == zi
					&& triangles[i].getz3() == zi || triangles[i].getz1() < zi
					&& triangles[i].getz2() == zi && triangles[i].getz3() == zi) {

				pi = triangles[i].getb();
				pf = triangles[i].getc();
			}

			// 8
			if (triangles[i].getz1() == zi && triangles[i].getz2() > zi
					&& triangles[i].getz3() == zi || triangles[i].getz1() == zi
					&& triangles[i].getz2() < zi && triangles[i].getz3() == zi) {

				pi = triangles[i].geta();
				pf = triangles[i].getc();
			}

			// 9
			if (triangles[i].getz1() == zi && triangles[i].getz2() == zi
					&& triangles[i].getz3() > zi || triangles[i].getz1() == zi
					&& triangles[i].getz2() == zi && triangles[i].getz3() < zi) {

				pi = triangles[i].geta();
				pf = triangles[i].getb();
			}

			// 10
			if (triangles[i].getz1() < zi && triangles[i].getz2() < zi
					&& triangles[i].getz3() < zi || triangles[i].getz1() > zi
					&& triangles[i].getz2() > zi && triangles[i].getz3() > zi
					|| triangles[i].getz1() == zi && triangles[i].getz2() > zi
					&& triangles[i].getz3() > zi || triangles[i].getz1() == zi
					&& triangles[i].getz2() < zi && triangles[i].getz3() < zi
					|| triangles[i].getz1() > zi && triangles[i].getz2() == zi
					&& triangles[i].getz3() > zi || triangles[i].getz1() < zi
					&& triangles[i].getz2() == zi && triangles[i].getz3() < zi
					|| triangles[i].getz1() > zi && triangles[i].getz2() > zi
					&& triangles[i].getz3() == zi || triangles[i].getz1() < zi
					&& triangles[i].getz2() < zi && triangles[i].getz3() == zi
					|| triangles[i].getz1() == zi && triangles[i].getz2() == zi
					&& triangles[i].getz3() == zi) {

				rien = true;
				// pi[0] = pi[1] = pi[2] = 0;
				// pf[0] = pf[1] = pf[2] = 0;
			}

			double[][] seg = new double[2][3];
			seg[0] = pi;
			seg[1] = pf;
			if (!rien)
				segments.add(seg);
			rien = false;

		} // end for i
	}

} // fin de la classe