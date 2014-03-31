package GUI;

import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.DebugGL2;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

import structures.Grille;
import structures.Triangle;
import Utils.Constantes;
import Utils.GrilleATriangles;

import com.jogamp.opengl.util.FPSAnimator;

public class Panneau3D extends GLJPanel implements GLEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2701339323983157455L;
	
	private GLU glu; // Utilitaire GLU
	private double angleRot; // Angle de rotation
	private double vitRot; // Vitesse de rotation
	// Récupération du profil OpenGL par défaut de la machine
	private static GLProfile glp = GLProfile.getDefault();
	// Spécification des paramètres OpenGL basés sur le profil chargé
	private static GLCapabilities caps = new GLCapabilities(glp);
	// Création des polygones qui seront dessinés, à partir de la grille
	private Grille grille;
	private List<Triangle> listeT;
	private ArrayList<double[][]> iso5;
	private ArrayList<double[][]> iso9;
	private ArrayList<List<Point2D.Double[]>> segments;

	// Création de l'animateur :
	private final FPSAnimator animateur;
	private static int IPS; // Taux d'images par secondes voulu pour l'animateur

	/*
	 * Paramètres liés à la zone d'affichage (projection sur l'écran, ou plus
	 * exactement sur le composant. Au composant / A l'écran est associé un
	 * repère orthonormal (x,y,z). L'axe x est horizontal (largeur), l'axe y
	 * vertical (hauteur). Ces deux axes sont dans le plan de l'écran. L'axe z
	 * est orthogonal à l'écran (profondeur), son origine (z=0) est la surface
	 * de l'écran, et les z sont comptés positivement quand on s'éloigne de
	 * l'oeil de l'utilisateur.
	 */
	private double champVertical; // Champ de vision (angle en degrés) dans la
									// direction verticale (de l'écran).
	private double ratio; // Ratio entre le champ de vison horizontal et le
							// champ vertical = ratio largeur / hauteur de la
							// zone d'affichage.
	private double profProche; // Profondeur (en unités de distance de
								// l'environnement 3D) du plus proche plan
								// visible. (Doit être strictement positif)
	private double profLoin; // Profondeur du plan visible le plus éloigné.
								// (Doit être supérieur à profProche)
	private double zV, zH, zM; // Servent à régler la hauteur par défaut de la
								// caméra

	/*
	 * Paramètres de la caméra (hors projection
	 */
	private double[] cam; // Coordonnées de la caméra
	private double[] cible; // Coordonnées du point visé
	double rayonOrbite; // Rayon de l'orbite plane de la caméra
	double zPlanOrbital; // Hauteur (en repère environnement) de la caméra en
							// orbite

	/*
	 * Paramètres définissant la nature de l'affichage
	 */
	private MenuReglage mReg; // Menu de reglage, contenant les diff�rents
								// réglages généraux d'affichage.

	/* Paramètres de la grille */
	double pas, x0, y0, zMin, zMax, empriseX, empriseY, xCentre, yCentre,
			empriseZ, grossissementZ;
	int nLig, nCol;

	/*****************************************************************************************************************************************/

	/*
	 * Méthodes de base
	 */

	// Constructeur
	public Panneau3D(MenuReglage mReg, Dimension d) {
		super(caps);
		caps.setDepthBits(64);
		this.setPreferredSize(d);
		this.addGLEventListener(this);
		IPS = 60;
		animateur = new FPSAnimator(this, IPS, true);

		/*
		 * Initialisation de la grille, des triangles et des isolignes
		 */
		grille = new Grille(); // Valeur par défaut donnée à la grille
		listeT = GrilleATriangles.grilleVersTrianglesStandard(grille);
		iso5 = grille.transfoIso(grille.makeIsoZt(5), 5);
		iso9 = grille.transfoIso(grille.makeIsoZt(9), 9);
		/*
		 * Initialisation des paramètres de la grille
		 */
		pas = grille.pas;
		x0 = grille.x0;
		y0 = grille.y0;
		zMin = grille.zMinMax()[0];
		zMax = grille.zMinMax()[1];
		nLig = grille.nLig;
		nCol = grille.nCol;
		empriseX = pas * (nCol - 1);
		empriseY = pas * (nLig - 1);
		xCentre = x0 + (empriseX / 2);
		yCentre = y0 - (empriseY / 2);
		empriseZ = zMax - zMin;

		segments = grille.makeListIsos(20);
		if (segments == null) {
			System.out.println("Segments non chargés");
		} else {
			System.out.println("Segments chargés avec succès");
		}

		/*
		 * Débogage int nbSeg = 0; for(List<Point2D.Double[]> iso : segments) {
		 * for (Point2D.Double[] seg : iso) { ++nbSeg; } }
		 * System.out.println("nbre total de segments pour toutes les isolignes : "
		 * +nbSeg);
		 */

		champVertical = 50;
		ratio = (double) (d.getWidth() / d.getHeight());
		profProche = 0.1;
		profLoin = 1000;
		this.mReg = mReg;

		angleRot = 0;
		vitRot = 0.01;
		rayonOrbite = 125 * Math.sqrt(2);
		grossissementZ = 2;

		/*
		 * Définition de la hauteur par défaut de la caméra par rapport au champ
		 * de vision dans les deux directions et à l'amplitude des altitudes, de
		 * façon à faire rentrer toute la scène dans le cadre en vue de dessus.
		 */
		if (Math.tan(champVertical * Math.PI / 360) != 0) {
			zV = (10 + (200 * empriseY / empriseX))
					/ (2 * Math.tan(champVertical * Math.PI / 360));
		} else {
			zV = 1;
		}
		if (Math.tan(ratio * champVertical * Math.PI / 360) != 0) {
			zH = 210 / (2 * Math.tan(ratio * champVertical * Math.PI / 360));
		} else {
			zH = 1;
		}
		zM = 50 + 200 * zMax / empriseX;

		if ((zM > zV) && (zM > zH)) {
			zPlanOrbital = zM;
		} else if ((zV >= zM) && (zV >= zH)) {
			zPlanOrbital = zV;
		} else {
			zPlanOrbital = zH;
		}

		System.out.println("zV = " + zV + "\nzH = " + zH + "\nzM = " + (zM)
				+ "\n=> zPlanOrbital = " + zPlanOrbital);

		cam = new double[] { 0, 0, zPlanOrbital };
		cible = new double[] { 0, 0, 5 };

		System.out.println("Table des couleurs personnalisées.");
		System.out.println(mReg.getCouleurs()[0] + "\n" + mReg.getCouleurs()[1]
				+ "\n" + mReg.getCouleurs()[2] + "\n" + mReg.getCouleurs()[3]
				+ "\n" + mReg.getCouleurs()[4] + "\n" + mReg.getCouleurs()[5]
				+ "\n" + mReg.getCouleurs()[6] + "\n" + mReg.getCouleurs()[7]);
	}

	// Getter pour l'objet animateur
	public FPSAnimator getAnimateur() {
		return animateur;
	}

	/*****************************************************************************************************************************************/
	/*
	 * Methodes getter / setter
	 */
	public Grille getGrille() {
		return grille;
	}

	public ArrayList<List<Double[]>> getSegments() {
		return segments;
	}

	public void setGrille(Grille gr) {
		grille = gr;
		listeT = GrilleATriangles.grilleVersTrianglesStandard(grille);
		iso5 = grille.transfoIso(grille.makeIsoZt(5), 5);
		iso9 = grille.transfoIso(grille.makeIsoZt(9), 9);
		segments = grille.makeListIsos(20);
		/*
		 * Débogage int nbSeg = 0; for(List<Point2D.Double[]> iso : segments) {
		 * for (Point2D.Double[] seg : iso) { ++nbSeg; } }
		 * System.out.println("nbre total de segments pour toutes les isolignes : "
		 * +nbSeg);
		 */

		pas = grille.pas;
		x0 = grille.x0;
		y0 = grille.y0;
		zMin = grille.zMinMax()[0];
		zMax = grille.zMinMax()[1];
		nLig = grille.nLig;
		nCol = grille.nCol;
		empriseX = pas * (nCol - 1);
		empriseY = pas * (nLig - 1);
		xCentre = x0 + (empriseX / 2);
		yCentre = y0 - (empriseY / 2);
		empriseZ = zMax - zMin;

		/*
		 * Définition de la hauteur par défaut de la caméra par rapport au champ
		 * de vision dans les deux directions et à l'amplitude des altitudes, de
		 * façon à faire rentrer toute la scène dans le cadre en vue de dessus.
		 */
		if (Math.tan(champVertical * Math.PI / 360) != 0) {
			zV = (10 + (200 * empriseY / empriseX))
					/ (2 * Math.tan(champVertical * Math.PI / 360));
		} else {
			zV = 1;
		}
		if (Math.tan(ratio * champVertical * Math.PI / 360) != 0) {
			zH = 210 / (2 * Math.tan(ratio * champVertical * Math.PI / 360));
		} else {
			zH = 1;
		}
		zM = 50 + 200 * zMax / empriseX;

		if ((zM > zV) && (zM > zH)) {
			zPlanOrbital = zM;
		} else if ((zV >= zM) && (zV >= zH)) {
			zPlanOrbital = zV;
		} else {
			zPlanOrbital = zH;
		}

		System.out.println("zV = " + zV + "\nzH = " + zH + "\nzM = " + (zM)
				+ "\n=> zPlanOrbital = " + zPlanOrbital);

		cam = new double[] { 0, 0, zPlanOrbital };

		System.out.println(grille);
	}

	/*****************************************************************************************************************************************/

	/*
	 * Fonctions relatives au réglages de la caméra.
	 */

	// Réglage de la matrice de projection, qui permet de calculer la mise en
	// perspective de l'image 3D.
	public void setPerspective(GL2 gl, GLU glu) {
		// Définit la matrice de projection GL_PROJECTION comme matrice active.
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();

		// Perspective.
		glu.gluPerspective(champVertical, ratio, profProche, profLoin);

		// Définit la matrice active comme étant à nouveau la matrice de
		// modélisation et d'affichage GL_MODELVIEW.
		gl.glMatrixMode(GL_MODELVIEW);

	}

	/*
	 * Réglage de la position et de l'orientation de la caméra. Ici la caméra
	 * est mise en orbite autour de la verticale terrain passant par le centre
	 * du MNT et vise le MNT en son centre en contre-plongée.
	 */
	public void setCameraOrbite(GL2 gl, GLU glu, double angleRot /*
																 * Angle de
																 * rotation en
																 * radians de la
																 * caméra en
																 * orbite
																 */,
			double zPlanOrbital, double rOrbite, double[] coordCentreVue) {
		setPerspective(gl, glu);
		gl.glLoadIdentity();
		// Paramétrage de la vue : caméra en orbite autour de l'axe vertical
		// coupant le MNT en son centre, et visant ce centre
		glu.gluLookAt(rOrbite * Math.cos(angleRot),
				rOrbite * Math.sin(angleRot), zPlanOrbital, coordCentreVue[0],
				coordCentreVue[1], coordCentreVue[2], 0, 0, 1);
	}

	/*
	 * Réglage de la position et de l'orientation de la caméra. Ici la caméra
	 * est placée à la verticale du MNT et l'observe dans une direction
	 * parallèle à l'axe central du MNT.
	 */
	public void setCameraOrtho(GL2 gl, GLU glu, double[] cam, double[] cible) {
		setPerspective(gl, glu);
		gl.glLoadIdentity();
		glu.gluLookAt(cam[0], cam[1], cam[2], cible[0], cible[1], cible[2], 0,
				1, 0);
	}

	/*****************************************************************************************************************************************/

	/*
	 * Méthodes implémentées pour l'écouteur GLEventListener
	 */

	/*
	 * Méthode appelée à chaque foix que l'environnement est dessiné, c'est à
	 * dire idéalement toutes les 1/IPS secondes.
	 */
	public void display(GLAutoDrawable dessin) {
		GL2 gl = dessin.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		/*
		 * Paramétrage de la caméra
		 */
		switch (mReg.getChoixCam()) {
		case Constantes.CAM_DESSUS:
			setCameraOrtho(gl, glu, cam, cible);
			break;
		case Constantes.CAM_ORBITE:
			setCameraOrbite(gl, glu, angleRot, zPlanOrbital, rayonOrbite, cible);
			break;
		}

		/*
		 * Dessin du MNT si l'utilisateur a choisi de l'afficher, ou n'a encore
		 * rien choisi (défaut)
		 */
		if (mReg.getChoixObj() == Constantes.OBJ_MNT
				|| mReg.getChoixObj() == Constantes.OBJ_TOUT) {
			gl.glBegin(GL_TRIANGLES); // Début du dessin des triangles
			double nivGris;
			double pente;
			for (int compteur = 0; compteur < listeT.size(); compteur++) {
				Triangle triangle = listeT.get(compteur);
				pente = triangle.getPente();
				/* Définition de la couleur du triangle en cours de dessin */
				if (mReg.getChoixCou() == Constantes.COU_AUTO) { // Niveau de
																	// gris
																	// automatique
					nivGris = pente / 90;
					gl.glColor3d(nivGris, nivGris, nivGris);
				} else { // Couleur choisie selon la plage de valeur
					float[] couleur = { 1f, 0f, 0f };
					Color[] couleurs = this.mReg.getCouleurs();
					if (pente < 10) {
						couleur = couleurs[0].getRGBColorComponents(null);
					} else if ((pente >= 10) && (pente < 20)) {
						couleur = couleurs[1].getRGBColorComponents(null);
					} else if ((pente >= 20) && (pente < 30)) {
						couleur = couleurs[2].getRGBColorComponents(null);
					} else if ((pente >= 30) && (pente < 40)) {
						couleur = couleurs[3].getRGBColorComponents(null);
					} else if ((pente >= 40) && (pente < 50)) {
						couleur = couleurs[4].getRGBColorComponents(null);
					} else if ((pente >= 50) && (pente < 60)) {
						couleur = couleurs[5].getRGBColorComponents(null);
					} else if ((pente >= 60) && (pente < 70)) {
						couleur = couleurs[6].getRGBColorComponents(null);
					} else if ((pente >= 70) && (pente < 80)) {
						couleur = couleurs[7].getRGBColorComponents(null);
					} else if (pente >= 80) {
						couleur = couleurs[8].getRGBColorComponents(null);
					}
					gl.glColor3f(couleur[0], couleur[1], couleur[2]);
				}
				/* Dessin du triangle */
				gl.glVertex3d(triangle.getx1(), triangle.gety1(),
						grossissementZ * triangle.getz1());
				gl.glVertex3d(triangle.getx2(), triangle.gety2(),
						grossissementZ * triangle.getz2());
				gl.glVertex3d(triangle.getx3(), triangle.gety3(),
						grossissementZ * triangle.getz3());

			}
			gl.glEnd(); // fin du MNT
		}

		/*
		 * Dessin des isolignes si l'utilisateur veut les afficher
		 */
		if (mReg.getChoixObj() == Constantes.OBJ_ISOLIGNES
				|| mReg.getChoixObj() == Constantes.OBJ_TOUT) {
			gl.glBegin(GL2.GL_LINES);
			gl.glColor3d(1, 0, 0);

			for (int compteur = 0; compteur < iso5.size(); compteur++) {
				gl.glVertex3d(iso5.get(compteur)[0][0],
						iso5.get(compteur)[0][1],
						grossissementZ * iso5.get(compteur)[0][2]);
				gl.glVertex3d(iso5.get(compteur)[1][0],
						iso5.get(compteur)[1][1],
						grossissementZ * iso5.get(compteur)[1][2]);
			}
			gl.glColor3d(0, 1, 0);
			for (int compteur = 0; compteur < iso9.size(); compteur++) {
				gl.glVertex3d(iso9.get(compteur)[0][0],
						iso9.get(compteur)[0][1],
						grossissementZ * iso9.get(compteur)[0][2]);
				gl.glVertex3d(iso9.get(compteur)[1][0],
						iso9.get(compteur)[1][1],
						grossissementZ * iso9.get(compteur)[1][2]);
			}

			gl.glEnd();
		}
		angleRot += vitRot;

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	public void init(GLAutoDrawable dessin) {
		GL2 gl = dessin.getGL().getGL2(); // Obtention du contexte graphique
											// OpenGL 2
		dessin.setGL(new DebugGL2(gl)); // Fournit des informations en cas
										// d'exception
		glu = new GLU(); // Obtention des utilitaires OpenGL (GLU)
		gl.glClearColor(0.0f, 0.0f, 0.5f, 1.0f); // Définit la couleur de fond
													// (Clear)
		gl.glClearDepth(1.0f); // Définit la profondeur du fond à
								// "le plus éloigné"
		gl.glEnable(GL_DEPTH_TEST); // Active les tests de profondeur
		gl.glDepthFunc(GL_LEQUAL); // Type de test de profondeur souhaité
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // Meilleure
																// correction de
																// perspective
		gl.glShadeModel(GL_SMOOTH); // Gestion douce du m�lange de couleurs et
									// de l'éclairage
		animateur.start(); // Boucle d'animation
	}

	public void reshape(GLAutoDrawable dessin, int x, int y, int largeur,
			int hauteur) {
		GL2 gl = dessin.getGL().getGL2(); // Obtention du contexte graphique
											// OpenGL 2

		if (hauteur == 0) {
			hauteur = 1;
		} // Evite la division par zéro
		ratio = (double) largeur / hauteur;

		// Définit la zone de visualisation comme l'intégralité du panneau
		gl.glViewport(0, 0, largeur, hauteur);

	}
}
