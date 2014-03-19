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
import structures.Isoligne;
import structures.Triangle;
import Utils.Constantes;
import Utils.FilesUtils;
import Utils.GrilleATriangles;

import com.jogamp.opengl.util.FPSAnimator;

public class Panneau3D extends GLJPanel implements GLEventListener {
	
	private GLU glu;  // Utilitaire GLU
	private double angleRot = 0; // Angle de rotation
	private double vitRot = 0.05; // Vitesse de rotation
	// R�cup�ration du profil OpenGL par d�faut de la machine
	private static GLProfile glp = GLProfile.getDefault();
	// Sp�cification des param�tres OpenGL bas�s sur le profil charg�
	private static GLCapabilities caps = new GLCapabilities(glp);
	// Cr�ation des polygones qui seront dessin�s, � partir de la grille
	//private Grille grille = FilesUtils.loadMNTAsc("/test/testMNT.asc");
	private Grille grille = FilesUtils.loadMNTxyz2("/test/Ecrins2.xyz");
	private List<Triangle> listeT = GrilleATriangles.grilleVersTriangles(grille);
	private Isoligne iso5 = new Isoligne( 5 , listeT );
	private Isoligne iso9 = new Isoligne( 9 , listeT );

	// Cr�ation de l'animateur :
	private final FPSAnimator animateur;
	private static final int IPS = 60; // Taux d'images par secondes voulu pour l'animateur
	
	/* Param�tres li�s � la zone d'affichage (projection sur l'�cran, ou plus exactement sur le composant.
	Au composant / A l'�cran est associ� un rep�re orthonormal (x,y,z).
	L'axe x est horizontal (largeur), l'axe y vertical (hauteur). Ces deux axes sont dans le plan de l'�cran.
	L'axe z est orthogonal � l'�cran (profondeur), son origine (z=0) est la surface de l'�cran, et les z sont compt�s
	positivement quand on s'�loigne de l'oeil de l'utilisateur. */
	private double champVertical; // Champ de vision (angle en degr�s) dans la direction verticale (de l'�cran).
	private double ratio; // Ratio entre le champ de vison horizontal et le champ vertical = ratio largeur / hauteur de la zone d'affichage.
    private double profProche; // Profondeur (en unit�s de distance de l'environnement 3D) du plus proche plan visible. (Doit �tre strictement positif)
    private double profLoin; // Profondeur du plan visible le plus �loign�. (Doit �tre sup�rieur � profProche)
    
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
		animateur = new FPSAnimator(this, IPS, true);
		champVertical = 50;
		ratio = (double) (d.getWidth() / d.getHeight());
		profProche = 0.1;
		profLoin = 1000;
		this.mReg=mReg;
		grossissementZ = 1;

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
        ratio = (double) (this.getWidth() / this.getHeight());
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
    
	public void display(GLAutoDrawable dessin) {
		GL2 gl = dessin.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        double[] cam = {0, 0, 20};
        double[] cible = {0, 0, 5};
        if (mReg.getChoixCam() == Constantes.CAM_DESSUS) {
        	setCameraOrtho(gl, glu, cam, cible );
        }
        if (mReg.getChoixCam() == Constantes.CAM_ORBITE) {
        	setCameraOrbite(gl, glu, angleRot, 80, 190, cible);
        }
        
        if(mReg.getChoixObj()==Constantes.OBJ_MNT || mReg.getChoixObj()==Constantes.OBJ_TOUT){
	        gl.glBegin(GL_TRIANGLES); // D�but du dessin des triangles
			for (int compteur = 0; compteur < listeT.size(); compteur++){
				
				Triangle triangle = listeT.get(compteur);
				double nivGris = 0.25;
				double pente = triangle.getPente();
				double penteNorm = pente/90;
				double penteReduite = penteNorm;
				nivGris = penteReduite;
				
				gl.glColor3d(nivGris, nivGris, nivGris); 
				gl.glVertex3d(
						200*(triangle.getx1()-xCentre)/empriseX,
						200*(triangle.gety1()-yCentre)/empriseX,
						200*grossissementZ*(triangle.getz1()-zMin)/empriseX
						);
						//System.out.println("Coordonn�es du sommet A : ("+200*(triangle.getx1()-xCentre)/empriseX+", "+200*(triangle.gety1()-yCentre)/empriseX+", "+20*triangle.getz1()/empriseZ+")");
				gl.glVertex3d(
						200*(triangle.getx2()-xCentre)/empriseX,
						200*(triangle.gety2()-yCentre)/empriseX,
						200*grossissementZ*(triangle.getz2()-zMin)/empriseX
						);
				gl.glVertex3d(
						200*(triangle.getx3()-xCentre)/empriseX,
						200*(triangle.gety3()-yCentre)/empriseX,
						200*grossissementZ*(triangle.getz3()-zMin)/empriseX
						);
			}	
			gl.glEnd(); // fin du MNT
        }
        if(mReg.getChoixObj()==Constantes.OBJ_ISOLIGNES || mReg.getChoixObj()==Constantes.OBJ_TOUT){
			gl.glBegin(GL2.GL_LINES);
			gl.glColor3d(1, 0, 0);
			for (int compteur = 0; compteur < iso5.segments.size() ; compteur++) {
				gl.glVertex3d(
						(iso5.segments.get(compteur)[0][0]-xCentre)/(empriseX/100),
						(iso5.segments.get(compteur)[0][1]-yCentre)/(empriseY/100),
						iso5.segments.get(compteur)[0][2]/(empriseZ/10)
						);
				gl.glVertex3d(
						(iso5.segments.get(compteur)[1][0]-xCentre)/(empriseX/100),
						(iso5.segments.get(compteur)[1][1]-yCentre)/(empriseY/100),
						iso5.segments.get(compteur)[1][2]/(empriseZ/10)
						);
			}
			gl.glColor3d(0, 1, 0);
			for (int compteur = 0; compteur < iso9.segments.size() ; compteur++) {
				gl.glVertex3d(
						(iso9.segments.get(compteur)[0][0]-xCentre)/(empriseX/100),
						(iso9.segments.get(compteur)[0][1]-yCentre)/(empriseY/100),
						iso9.segments.get(compteur)[0][2]/(empriseZ/10)
						);
				gl.glVertex3d(
						(iso9.segments.get(compteur)[1][0]-xCentre)/(empriseX/100),
						(iso9.segments.get(compteur)[1][1]-yCentre)/(empriseY/100),
						iso9.segments.get(compteur)[1][2]/(empriseZ/10)
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
		float aspect = (float)largeur / hauteur;

		// D�finit la zone de visualisation comme l'int�gralit� du panneau
		gl.glViewport(0, 0, largeur, hauteur);

	}
	
	


}
