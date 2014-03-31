package GUI;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import javax.media.opengl.GL2;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

/**
 * @deprecated Dépréciée pour une gestion plus aisée des variables d'affichage
 *             directement depuis la classe Panneau3D.
 */

public abstract class Camera {

	/*
	 * Paramètres liés à la zone d'affichage (projection sur l'écran, ou plus
	 * exactement sur le composant. Au composant / A l'écran est associé un
	 * repère orthonormal (x,y,z). L'axe x est horizontal (largeur), l'axe y
	 * vertical (hauteur). Ces deux axes sont dans le plan de l'écran. L'axe z
	 * est orthogonal à l'écran (profondeur), son origine (z=0) est la surface
	 * de l'écran, et les z sont comptés positivement quand on s'éloigne de
	 * l'oeil de l'utilisateur.
	 */
	private static double champVertical; // Champ de vision (angle en degrés)
											// dans la direction verticale (de
											// l'écran).
	private static double ratio; // Ratio entre le champ de vison horizontal et
									// le champ vertical = ratio largeur /
									// hauteur de la zone d'affichage.
	private static double profProche; // Profondeur (en unités de distance de
										// l'environnement 3D) du plus proche
										// plan visible. (Doit être strictement
										// positif)
	private static double profLoin; // Profondeur du plan visible le plus
									// éloigné. (Doit être supérieur à
									// profProche)

	// Réglage de la matrice de projection, qui permet de calculer la mise en
	// perspective de l'image 3D.
	public static void setPerspective(GL2 gl, GLU glu, GLJPanel pan) {
		// Définit la matrice de projection GL_PROJECTION comme matrice active.
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();

		// Perspective.
		ratio = (double) (pan.getWidth() / pan.getHeight());
		champVertical = 45;
		profProche = 0.1;
		profLoin = 100;
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
	public static void setCameraOrbite(GL2 gl, GLU glu, GLJPanel pan,
			double angleRot /*
							 * Angle de rotation en radians de la caméra en
							 * orbite
							 */, double zPlanOrbital, double rOrbite,
			double[] coordCentreVue) {
		setPerspective(gl, glu, pan);
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
	public static void setCameraOrtho(GL2 gl, GLU glu, GLJPanel pan,
			double[] cam, double[] cible) {
		setPerspective(gl, glu, pan);
		gl.glLoadIdentity();
		glu.gluLookAt(cam[0], cam[1], cam[2], cible[0], cible[1], cible[2], 0,
				1, 0);
	}

}
