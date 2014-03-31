package GUI;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import structures.Isoligne;
import structures.Grille;
import javax.swing.JFrame;

//frame simple pour contenir le panneau qui contiendra le dessin des isolignes 
public class SimpleFrame extends JFrame{
	private static int DEFAULT_WIDTH = 1000;//= 200;
	private static int DEFAULT_HEIGHT; //= 1000;

	public SimpleFrame()
	{
		//setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Isolignes 2D");
	}
	
	public SimpleFrame(List<Isoligne> isos, Grille grille)
	{
		this();
		add(new SimplePanneauIsos2D(isos, grille, DEFAULT_WIDTH, (int)(DEFAULT_WIDTH/((double) grille.nCol/grille.nLig))));
		setSize(DEFAULT_WIDTH, (int)(DEFAULT_WIDTH/((double) grille.nCol/grille.nLig)));
	}
	
	public SimpleFrame(ArrayList<List<Point2D.Double[]>> isos,Grille grille)
	{
		this();
		add(new SimplePanneauIsos2D(isos, grille, DEFAULT_WIDTH, (int)(DEFAULT_WIDTH/((double) grille.nCol/grille.nLig))));
		setSize(DEFAULT_WIDTH, (int)(DEFAULT_WIDTH/((double) grille.nCol/grille.nLig)));
	}
}
