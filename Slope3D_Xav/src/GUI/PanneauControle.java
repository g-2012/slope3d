package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class PanneauControle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4760065352215783253L;
	private PanneauControleDeplacement panTra;
	private PanneauControleRotation panRot;
	
	public PanneauControle(MenuReglage mReg, Dimension d){ 
		super();
		this.setBackground(Color.GRAY);
		this.setPreferredSize(d);
		this.setLayout(new FlowLayout());
		
		panTra = new PanneauControleDeplacement(mReg, new Dimension(d.width, 200));
		//panTra.setLocation(0,0);
		this.add(panTra);
		
		/*JSeparator sep = new JSeparator();
		sep.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		sep.setLocation(0,200);
		this.add(sep);*/
		
		panRot = new PanneauControleRotation(mReg, new Dimension(d.width, d.height-200));
		//panRot.setVisible(true);
		//panRot.setLocation(0,203);
		this.add(panRot);
		
		
	}
	
}
