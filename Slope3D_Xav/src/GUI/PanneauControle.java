package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
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
		
		// Definition du gestionnaire de mise en page
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		// Création des composants
		Dimension dPanTra = new Dimension(d.width, 200);
		panTra = new PanneauControleDeplacement(mReg, dPanTra);

		JSeparator sep1 = new JSeparator();
		JSeparator sep2 = new JSeparator();
				
		Dimension dPanRot = new Dimension(d.width, d.height-202  /*prend en compte la taille des 2 séparateurs*/);
		panRot = new PanneauControleRotation(mReg, dPanRot);

		// Ajout des composants au conteneur
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(panTra, dPanTra.width, dPanTra.width, dPanTra.width)
				.addComponent(sep1)
				.addComponent(sep2)
				.addComponent(panRot, dPanRot.width, dPanRot.width, dPanRot.width)				
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(panTra, dPanTra.height, dPanTra.height, dPanTra.height)
				.addComponent(sep1)
				.addComponent(sep2)
				.addComponent(panRot, dPanRot.height, dPanRot.height, dPanRot.height)
				);
		
	}
	
}
