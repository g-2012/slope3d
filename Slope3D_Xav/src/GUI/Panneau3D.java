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
import structures.Isoligne;
import structures.Triangle;
import Test.GrilleTest;
import Utils.Constantes;
import Utils.FilesUtils;
import Utils.GrilleATriangles;

import com.jogamp.opengl.util.FPSAnimator;

public class Panneau3D extends GLJPanel implements GLEventListener {
	
	private GLU glu;  // Utilitaire GLU
	private double angleRot; // Angle de rotation
	private double vitRot; // Vitesse de rotation
	// R�cup�ration du profil OpenGL par d�faut de la machine
	private static GLProfile glp = GLProfile.getDefault();
	// Sp�cification des param�tres OpenGL bas�s sur le profil charg�
	private static GLCapabilities caps = new GLCapabilities(glp);
	// Cr�ation des polygones qui seront dessin�s, � partir de la grille
	private Grille grille = FilesUtils.loadMNTAsc("/test/testMNT.asc");
	//private Grille grille = FilesUtils.loadMNTxyz2("/test/Ecrins2.xyz");
	//private Grille grille = new Grille();
	private List<Triangle> listeT = GrilleATriangles.grilleVersTrianglesStandard(grille);
	private ArrayList<double[][]> iso5 = grille.transfoIso(grille.makeIsoZt(5), 5);
	private ArrayList<double[][]> iso9 = grille.transfoIso(grille.makeIsoZt(9), 9);
	
	// Cr�ation de l'animateur :
	private final FPSAnimator animateur;
	private static int IPS; // Taux d'images par secondes voulu pour l'animateur
	
	/* Param�tres li�s � la zone d'affichage (projection sur l'�cran, ou plus exactement sur le composant.
	Au composant / A l'�cran est associ� un rep�re orthonormal (x,y,z).
	L'axe x est horizontal (largeur), l'axe y vertical (hauteur). Ces deux axes sont dans le plan de l'�cran.
	L'axe z est orthogonal � l'�cran (profondeur), son origine (z=0) est la surface de l'�cran, et les z sont compt�s
	positivement quand on s'�loigne de l'oeil de l'utilisateur. */
	private double champVertical; // Champ de vision (angle en degr�s) dans la direction verticale (de l'�cran).
	private double ratio; // Ratio entre le champ de vison horizontal et le champ vertical = ratio largeur / hauteur de la zone d'affichage.
    private double profProche; // Profondeur (en unit�s de distance de l'environnement 3D) du plus proche plan visible. (Doit �tre strictement positif)
    private double profLoin; // Profondeur du plan visible le plus �loign�. (Doit �tre sup�rieur � profProche)
    
    /* Param�tres de la cam�ra (hors projection */
    private double[] cam; // Coordonn�es de la cam�ra
    private double[] cible; // Coordonn�es du point vis�
    double rayonOrbite; // Rayon de l'orbite plane de la cam�ra
    double zPlanOrbital; // Hauteur (en rep�re environnement) de la cam�ra en orbite
    
    
    /* Param�tres d�finissant la nature de l'affichage */
    private MenuReglage mReg; // Menu de reglage, contenant les diff�rents r�glages g�n�raux d'affichage.
	
    /* Param�tres de la grille */
    double pas = grille.pas,
    		x0 = grille.x0,
    		y0 = grille.y0,
    		zMin = grille.zMinMax()[0],
    		zMax = grille.zMinMax()[1];
    int nLig = grille.nLig,
    		nCol = grille.nCol;
    double 	empriseX = pas*(nCol-1),
    		empriseY = pas*(nLig-1),
    		xCentre = x0 + (empriseX/2),
    		yCentre = y0 - (empriseY/2),
    	 	empriseZ = zMax-zMin,
    		grossissementZ;
    
    
	
    /*****************************************************************************************************************************************/
    
    /*
     * M�thodes de base
     */
    
	// Constructeur
	public Panneau3D(MenuReglage mReg, Dimension d) {
		super(caps);
		caps.setDepthBits(64);
		this.setPreferredSize(d);
		this.addGLEventListener(this);
		IPS = 60;
		animateur = new FPSAnimator(this, IPS, true);
		champVertical = 50;
		ratio = (double) (d.getWidth() / d.getHeight());
		profProche = 0.1;
		profLoin = 1000;
		this.mReg=mReg;
		grossissementZ = 2;
		cam = new double[]{0, 0, 250};
        cible = new double[]{0, 0, 5};
        
        angleRot = 0;
        vitRot = 0.01;
        rayonOrbite = 200;
        zPlanOrbital = 200*Math.sqrt(2);
        
        if (mReg.getChoixCam() == Constantes.CAM_DESSUS) {
        	System.out.println("Choix cam�ra : vue de dessus.");
        } else if (mReg.getChoixCam() == Constantes.CAM_ORBITE) {
        	System.out.println("Choix cam�ra : cam�ra en orbite.");
        }
        
        if (mReg.getChoixCou() == Constantes.COU_AUTO) {
        	System.out.println("Choix couleurs : automatique.");
        } else if (mReg.getChoixCou() == Constantes.COU_PERSO) {
        	System.out.println("Choix couleurs : personnalis�.");
        	System.out.println(mReg.getCouleurs()[0]
        			+"\n"+mReg.getCouleurs()[1]
        			+"\n"+mReg.getCouleurs()[2]
        			+"\n"+mReg.getCouleurs()[3]
					+"\n"+mReg.getCouleurs()[4]
        			+"\n"+mReg.getCouleurs()[5]
        			+"\n"+mReg.getCouleurs()[6]
        			+"\n"+mReg.getCouleurs()[7]
        			);
        }
	}
	
	
	// Getter pour l'objet animateur
	public FPSAnimator getAnimateur(){
		return animateur;
	}
	
	/*****************************************************************************************************************************************/
	
	/*
	 * Fonctions relatives au r�glages de la cam�ra.
	 */
	
	// R�glage de la matrice de projection, qui permet de calculer la mise en perspective de l'image 3D.
    public void setPerspective(GL2 gl, GLU glu) {
        // D�finit la matrice de projection GL_PROJECTION comme matrice active.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        glu.gluPerspective(champVertical, ratio, profProche, profLoin);
        
        // D�finit la matrice active comme �tant � nouveau la matrice de mod�lisation et d'affichage GL_MODELVIEW.
        gl.glMatrixMode(GL_MODELVIEW);
        
    }
    
    /* R�glage de la position et de l'orientation de la cam�ra.
     * Ici la cam�ra est mise en orbite autour de la verticale terrain passant par le centre du MNT et vise le MNT en son centre en contre-plong�e.
     */
    public void setCameraOrbite(GL2 gl, GLU glu, double angleRot /* Angle de rotation en radians de la cam�ra en orbite*/, double zPlanOrbital, double rOrbite, double[] coordCentreVue ) {
    	setPerspective(gl, glu);
        gl.glLoadIdentity();
        // Param�trage de la vue : cam�ra en orbite autour de l'axe vertical coupant le MNT en son centre, et visant ce centre
        glu.gluLookAt(rOrbite*Math.cos(angleRot), rOrbite*Math.sin(angleRot), zPlanOrbital, coordCentreVue[0], coordCentreVue[1], coordCentreVue[2], 0, 0, 1);
    }
    
    
    /* R�glage de la position et de l'orientation de la cam�ra.
     * Ici la cam�ra est plac�e � la verticale du MNT et l'observe dans une direction parall�le � l'axe central du MNT.
     */
    public void setCameraOrtho(GL2 gl, GLU glu, double[] cam, double[] cible) {
    	setPerspective(gl, glu);
    	gl.glLoadIdentity();
    	glu.gluLookAt(cam[0], cam[1], cam[2], cible[0], cible[1], cible[2], 0, 1, 0);
    }
	
	
	
    /*****************************************************************************************************************************************/
    
	/*
	 *  M�thodes impl�ment�es pour l'�couteur GLEventListener
	 */
    
    /* M�thode appel�e � chaque foix que l'environnement est dessin�, c'est � dire id�alement toutes les 1/IPS secondes. */
	public void display(GLAutoDrawable dessin) {
		GL2 gl = dessin.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        /*
         * Param�trage de la cam�ra
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
         * Dessin du MNT si l'utilisateur a choisi de l'affich�, ou n'a encore rien choisi (d�faut)
         */
        if(mReg.getChoixObj()==Constantes.OBJ_MNT || mReg.getChoixObj()==Constantes.OBJ_TOUT){
	        gl.glBegin(GL_TRIANGLES); // D�but du dessin des triangles
	        double nivGris;
	        double pente;
			for (int compteur = 0; compteur < listeT.size(); compteur++){		
				Triangle triangle = listeT.get(compteur);
				pente = triangle.getPente();
				/* D�finition de la couleur du triangle en cours de dessin */
				if (mReg.getChoixCou() == Constantes.COU_AUTO) { // Niveau de gris automatique
					nivGris = pente/90;
					gl.glColor3d(nivGris, nivGris, nivGris); 
				}
				else { // Couleur choisie selon la plage de valeur
					float rouge, vert, bleu;
					float[] couleur = {1f,0f,0f};
					Color[] couleurs = this.mReg.getCouleurs();
					if (pente<10) {
						couleur = couleurs[0].getRGBColorComponents(null);
					}
					else if ((pente >=10) && (pente<20)) {
						couleur = couleurs[1].getRGBColorComponents(null);
					}
					else if ((pente >=20) && (pente<30)) {
						couleur = couleurs[2].getRGBColorComponents(null);
					}
					else if ((pente >=30) && (pente<40)) {
						couleur = couleurs[3].getRGBColorComponents(null);
					}
					else if ((pente >=40) && (pente<50)) {
						couleur = couleurs[4].getRGBColorComponents(null);
					}
					else if ((pente >=50) && (pente<60)) {
						couleur = couleurs[5].getRGBColorComponents(null);
					}
					else if ((pente >=60) && (pente<70)) {
						couleur = couleurs[6].getRGBColorComponents(null);
					}
					else if ((pente >=70) && (pente<80)) {
						couleur = couleurs[7].getRGBColorComponents(null);
					}
					else if (pente >=80) {
						couleur = couleurs[8].getRGBColorComponents(null);
					}
					gl.glColor3f(couleur[0], couleur[1], couleur[2]);
				}
				/* Dessin du triangle */
				gl.glVertex3d(
						triangle.getx1(),
						triangle.gety1(),
						grossissementZ*triangle.getz1()
						);
				gl.glVertex3d(
						triangle.getx2(),
						triangle.gety2(),
						grossissementZ*triangle.getz2()
						);
				gl.glVertex3d(
						triangle.getx3(),
						triangle.gety3(),
						grossissementZ*triangle.getz3()
						);
				
			}	
			gl.glEnd(); // fin du MNT
        }
        
        /*
         * Dessin des isolignes si l'utilisateur veut les afficher
         */
        if(mReg.getChoixObj()==Constantes.OBJ_ISOLIGNES || mReg.getChoixObj()==Constantes.OBJ_TOUT){
			gl.glBegin(GL2.GL_LINES);
			gl.glColor3d(1, 0, 0);
			
			for (int compteur = 0; compteur < iso5.size() ; compteur++) {
				gl.glVertex3d(
						iso5.get(compteur)[0][0],
						iso5.get(compteur)[0][1],
						grossissementZ*iso5.get(compteur)[0][2]
						);
				gl.glVertex3d(
						iso5.get(compteur)[1][0],
						iso5.get(compteur)[1][1],
						grossissementZ*iso5.get(compteur)[1][2]
						);
			}
			gl.glColor3d(0, 1, 0);
			for (int compteur = 0; compteur < iso9.size() ; compteur++) {
				gl.glVertex3d(
						iso9.get(compteur)[0][0],
						iso9.get(compteur)[0][1],
						grossissementZ*iso9.get(compteur)[0][2]
						);
				gl.glVertex3d(
						iso9.get(compteur)[1][0],
						iso9.get(compteur)[1][1],
						grossissementZ*iso9.get(compteur)[1][2]
						);
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
		GL2 gl = dessin.getGL().getGL2();      // Obtention du contexte graphique OpenGL 2
		dessin.setGL(new DebugGL2(gl)); // Fournit des informations en cas d'exception
		glu = new GLU();                         // Obtention des utilitaires OpenGL (GLU)
		gl.glClearColor(0.0f, 0.0f, 0.5f, 1.0f); // D�finit la couleur de fond (Clear)
		gl.glClearDepth(1.0f);      // d�finit la profondeur du fond � "le plus �loign�"
		gl.glEnable(GL_DEPTH_TEST); // active les tests de profondeur
		gl.glDepthFunc(GL_LEQUAL);  // type de test de profondeur souhait�
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // meilleure correction de perspective
		gl.glShadeModel(GL_SMOOTH); // Gestion douce du m�lange de couleurs et de l'�clairage
		animateur.start(); // boucle d'animation
		
	}

	public void reshape(GLAutoDrawable dessin, int x, int y, int largeur, int hauteur) {
		GL2 gl = dessin.getGL().getGL2();  // Obtention du contexte graphique OpenGL 2

		if (hauteur == 0) { hauteur = 1; }   // �vite la division par z�ro
		ratio = (double)largeur / hauteur;

		// D�finit la zone de visualisation comme l'int�gralit� du panneau
		gl.glViewport(0, 0, largeur, hauteur);

	}
	
	


}
