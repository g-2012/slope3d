package GUI;

import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Dimension;
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
import Utils.FilesUtils;
import Utils.GrilleATriangles;

import com.jogamp.opengl.util.FPSAnimator;

public class Panneau3D extends GLJPanel implements GLEventListener {
	
	private GLU glu;  // Utilitaire GLU
	private double angleRot = 0; // Angle de rotation
	private double vitRot = 0.05; // Vitesse de rotation
	// Récupération du profil OpenGL par défaut de la machine
	private static GLProfile glp = GLProfile.getDefault();
	// Spécification des paramètres OpenGL basés sur le profil chargé
	private static GLCapabilities caps = new GLCapabilities(glp);
	// Création des polygones qui seront dessinés, à partir de la grille
	private Grille grille = FilesUtils.loadMNTAsc("/test/testMNT.asc");
	private List<Triangle> listeT = GrilleATriangles.grilleVersTriangles(grille);

	// Création de l'animateur :
	private final FPSAnimator animateur;
	private static final int IPS = 60; // Taux d'images par secondes voulu pour l'animateur
	
	/* Paramètres liés à la zone d'affichage (projection sur l'écran, ou plus exactement sur le composant.
	Au composant / A l'écran est associé un repère orthonormal (x,y,z).
	L'axe x est horizontal (largeur), l'axe y vertical (hauteur). Ces deux axes sont dans le plan de l'écran.
	L'axe z est orthogonal à l'écran (profondeur), son origine (z=0) est la surface de l'écran, et les z sont comptés
	positivement quand on s'éloigne de l'oeil de l'utilisateur. */
	private double champVertical; // Champ de vision (angle en degrés) dans la direction verticale (de l'écran).
	private double ratio; // Ratio entre le champ de vison horizontal et le champ vertical = ratio largeur / hauteur de la zone d'affichage.
    private double profProche; // Profondeur (en unités de distance de l'environnement 3D) du plus proche plan visible. (Doit être strictement positif)
    private double profLoin; // Profondeur du plan visible le plus éloigné. (Doit être supérieur à profProche)
	
	
    /*****************************************************************************************************************************************/
    
    /*
     * Méthodes de base
     */
    
	// Constructeur
	public Panneau3D(Dimension d) {
		super(caps);
		caps.setDepthBits(64);
		this.setPreferredSize(d);
		this.addGLEventListener(this);
		animateur = new FPSAnimator(this, 60, true);
		champVertical = 50;
		ratio = (double) (d.getWidth() / d.getHeight());
		profProche = 1;
		profLoin = 100;
	}
	
	
	// Getter pour l'objet animateur
	public FPSAnimator getAnimateur(){
		return animateur;
	}
	
	/*****************************************************************************************************************************************/
	
	/*
	 * Fonctions relatives au réglages de la caméra.
	 */
	
	// Réglage de la matrice de projection, qui permet de calculer la mise en perspective de l'image 3D.
    public void setPerspective(GL2 gl, GLU glu) {
        // Définit la matrice de projection GL_PROJECTION comme matrice active.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        ratio = (double) (this.getWidth() / this.getHeight());
        glu.gluPerspective(champVertical, ratio, profProche, profLoin);
        
        // Définit la matrice active comme étant à nouveau la matrice de modélisation et d'affichage GL_MODELVIEW.
        gl.glMatrixMode(GL_MODELVIEW);
        
    }
    
    /* Réglage de la position et de l'orientation de la caméra.
     * Ici la caméra est mise en orbite autour de la verticale terrain passant par le centre du MNT et vise le MNT en son centre en contre-plongée.
     */
    public void setCameraOrbite(GL2 gl, GLU glu, double angleRot /* Angle de rotation en radians de la caméra en orbite*/, double zPlanOrbital, double rOrbite, double[] coordCentreVue ) {
    	setPerspective(gl, glu);
        gl.glLoadIdentity();
        // Paramétrage de la vue : caméra en orbite autour de l'axe vertical coupant le MNT en son centre, et visant ce centre
        glu.gluLookAt(rOrbite*Math.cos(angleRot), rOrbite*Math.sin(angleRot), zPlanOrbital, coordCentreVue[0], coordCentreVue[1], coordCentreVue[2], 0, 0, 1);
    }
    
    
    /* Réglage de la position et de l'orientation de la caméra.
     * Ici la caméra est placée à la verticale du MNT et l'observe dans une direction parallèle à l'axe central du MNT.
     */
    public void setCameraOrtho(GL2 gl, GLU glu, double[] cam, double[] cible) {
    	setPerspective(gl, glu);
    	gl.glLoadIdentity();
    	glu.gluLookAt(cam[0], cam[1], cam[2], cible[0], cible[1], cible[2], 0, 1, 0);
    }
	
	
	
    /*****************************************************************************************************************************************/
    
	/*
	 *  Méthodes implémentées pour l'écouteur GLEventListener
	 */
    
	public void display(GLAutoDrawable dessin) {
		GL2 gl = dessin.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        
        double[] cible = {0,0,0.1};
        setCameraOrbite(gl, glu, angleRot, 5, 7*Math.sqrt(2), cible );
        
        gl.glBegin(GL_TRIANGLES); // Début du dessin des triangles

		for (int compteur = 0; compteur < listeT.size(); compteur++){
			
			Triangle triangle = listeT.get(compteur);
			double nivGris = 0.25;
			double pente = triangle.getPente();
			double penteNorm = pente/90;
			double penteReduite = penteNorm;
			nivGris = penteReduite;
			
			gl.glColor3d(nivGris, nivGris, nivGris); 
			gl.glVertex3d(triangle.getx1()/100-(473000+500)/100, triangle.gety1()/100-(6914001.000-500)/100, triangle.getz1()/100);
			gl.glVertex3d(triangle.getx2()/100-(473000+500)/100, triangle.gety2()/100-(6914001.000-500)/100, triangle.getz2()/100);
			gl.glVertex3d(triangle.getx3()/100-(473000+500)/100, triangle.gety3()/100-(6914001.000-500)/100, triangle.getz3()/100);

			
			
		}
	
		gl.glEnd(); // fin du MNT

		angleRot += vitRot;
        
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void init(GLAutoDrawable dessin) {
		GL2 gl = dessin.getGL().getGL2();      // Obtention du contexte graphique OpenGL 2
		dessin.setGL(new DebugGL2(gl)); // Fournit des informations en cas d'exception
		glu = new GLU();                         // Obtention des utilitaires OpenGL (GLU)
		gl.glClearColor(0.0f, 0.0f, 0.5f, 1.0f); // Définit la couleur de fond (Clear)
		gl.glClearDepth(1.0f);      // définit la profondeur du fond à "le plus éloigné"
		gl.glEnable(GL_DEPTH_TEST); // active les tests de profondeur
		gl.glDepthFunc(GL_LEQUAL);  // type de test de profondeur souhaité
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // meilleure correction de perspective
		gl.glShadeModel(GL_SMOOTH); // Gestion douce du mélange de couleurs et de l'éclairage
		animateur.start(); // boucle d'animation
	}

	public void reshape(GLAutoDrawable dessin, int x, int y, int largeur, int hauteur) {
		GL2 gl = dessin.getGL().getGL2();  // Obtention du contexte graphique OpenGL 2

		if (hauteur == 0) { hauteur = 1; }   // évite la division par zéro
		float aspect = (float)largeur / hauteur;

		// Définit la zone de visualisation comme l'intégralité du panneau
		gl.glViewport(0, 0, largeur, hauteur);

	}
	
	


}
