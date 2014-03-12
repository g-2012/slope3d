package GUI;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import javax.media.opengl.GL2;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

public abstract class Camera {
	
	/* Param�tres li�s � la zone d'affichage (projection sur l'�cran, ou plus exactement sur le composant.
	Au composant / A l'�cran est associ� un rep�re orthonormal (x,y,z).
	L'axe x est horizontal (largeur), l'axe y vertical (hauteur). Ces deux axes sont dans le plan de l'�cran.
	L'axe z est orthogonal � l'�cran (profondeur), son origine (z=0) est la surface de l'�cran, et les z sont compt�s
	positivement quand on s'�loigne de l'oeil de l'utilisateur. */
	private static double champVertical; // Champ de vision (angle en degr�s) dans la direction verticale (de l'�cran).
	private static double ratio; // Ratio entre le champ de vison horizontal et le champ vertical = ratio largeur / hauteur de la zone d'affichage.
    private static double profProche; // Profondeur (en unit�s de distance de l'environnement 3D) du plus proche plan visible. (Doit �tre strictement positif)
    private static double profLoin; // Profondeur du plan visible le plus �loign�. (Doit �tre sup�rieur � profProche) 
    
    // R�glage de la matrice de projection, qui permet de calculer la mise en perspective de l'image 3D.
    public static void setPerspective(GL2 gl, GLU glu, GLJPanel pan) {
        // D�finit la matrice de projection GL_PROJECTION comme matrice active.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        ratio = (double) (pan.getWidth() / pan.getHeight());
        champVertical = 45;
        profProche = 0.1;
        profLoin = 100;
        glu.gluPerspective(champVertical, ratio, profProche, profLoin);
        
        // D�finit la matrice active comme �tant � nouveau la matrice de mod�lisation et d'affichage GL_MODELVIEW.
        gl.glMatrixMode(GL_MODELVIEW);
        
    }
    
    /* R�glage de la position et de l'orientation de la cam�ra.
     * Ici la cam�ra est mise en orbite autour de la verticale terrain passant par le centre du MNT et vise le MNT en son centre en contre-plong�e.
     */
    public static void setCameraOrbite(GL2 gl, GLU glu, GLJPanel pan, double angleRot /* Angle de rotation en radians de la cam�ra en orbite*/, double zPlanOrbital, double rOrbite, double[] coordCentreVue ) {
    	setPerspective(gl, glu, pan);
        gl.glLoadIdentity();
        // Param�trage de la vue : cam�ra en orbite autour de l'axe vertical coupant le MNT en son centre, et visant ce centre
        glu.gluLookAt(rOrbite*Math.cos(angleRot), rOrbite*Math.sin(angleRot), zPlanOrbital, coordCentreVue[0], coordCentreVue[1], coordCentreVue[2], 0, 0, 1);
    }
    
    
    /* R�glage de la position et de l'orientation de la cam�ra.
     * Ici la cam�ra est plac�e � la verticale du MNT et l'observe dans une direction parall�le � l'axe central du MNT.
     */
    public static void setCameraOrtho(GL2 gl, GLU glu, GLJPanel pan, double[] cam, double[] cible) {
    	setPerspective(gl, glu, pan);
    	gl.glLoadIdentity();
    	glu.gluLookAt(cam[0], cam[1], cam[2], cible[0], cible[1], cible[2], 0, 1, 0);
    }
    
    


}
