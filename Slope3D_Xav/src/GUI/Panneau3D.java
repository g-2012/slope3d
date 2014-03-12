package GUI;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;

import com.jogamp.opengl.util.FPSAnimator;

public class Panneau3D extends GLJPanel implements GLEventListener {
	
	private FPSAnimator animateur;
	
	public Panneau3D() {
		super();
		//animateur = new FPSAnimator(this, 60, true);
	}
	
	
	// Getter pour l'objet animateur
	public FPSAnimator getAnimateur(){
		return animateur;
	}
	
	
	
	
	
	// Méthodes implémentées pour l'écouteur GLEventListener

	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

	
	


}
