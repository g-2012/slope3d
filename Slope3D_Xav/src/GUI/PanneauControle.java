package GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanneauControle extends JPanel {
	private PanneauControleDeplacement panTra;
	private PanneauControleRotation panRot;
	
	public PanneauControle(MenuReglage mReg, Dimension d){
		super();
		this.setBackground(Color.GRAY);
		this.setSize(d);
		this.setLayout(null);
		
		panTra = new PanneauControleDeplacement(mReg, new Dimension(d.width, 200));
		panTra.setLocation(0,0);
		this.add(panTra);
		
		panRot = new PanneauControleRotation(mReg, new Dimension(d.width, d.height-200));
		panRot.setLocation(0,203);
		this.add(panRot);
		
		this.setVisible(true);
		
	}
}
