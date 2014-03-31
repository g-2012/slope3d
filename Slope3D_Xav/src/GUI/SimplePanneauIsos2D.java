package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import structures.Grille;
import structures.Isoligne;
import javax.swing.JComponent;

/**
 * Composant affichant des isolignes en 2D d'une Grille
 * 
 * @author Jigx
 * @version 0.6
 */
public class SimplePanneauIsos2D extends JComponent {
	/**
	 * la grille consideree
	 */
	private Grille grille;
	
	/**
	 * largeur de la frame contenante
	 */
	private int width;
	
	/**
	 * hauteur de la frame contenante
	 */
	private int height;
	
	/**
	 * liste des isolignes
	 * @deprecated structure non utilisee
	 */
	private List<Isoligne> listIsos;
	
	/**
	 * liste des isolignes sous forme d'une liste de liste de segments de Point2D 
	 */
	private ArrayList<List<Point2D.Double[]>> listIsos2D; //ugly as fuck, i know.. voir commentaire ci dessous
	
	/**
	 * Constructeur SimplePanneauIsos2D. 
	 * 
	 * @param listIsos
	 * 	            liste des isolignes
	 * @param grille
	 *            Grille considérée
	 * @param w
	 *            largeur
	 * @param h
	 *            hauteur
	 *            
	 * @deprecated structure Isoligne depreciee
	 */
	public SimplePanneauIsos2D(List<Isoligne> listIsos, Grille grille, int w, int h){
		this.listIsos = listIsos;
		this.listIsos2D = null;
		this.grille = grille;
		this.width = w;
		this.height = h;
		System.out.println(w + " *** "+ h);
	}

	/**
	 * Constructeur SimplePanneauIsos2D. 
	 * ugly mais les types generiques de java ne sont pas reifies (list<E> et list<E'> ne sont pas differencies)
	 * @param listIsos2D
	 * 	            liste des isolignes
	 * @param grille
	 *            Grille considérée
	 * @param w
	 *            largeur
	 * @param h
	 *            hauteur
	 *            
	 */
	public SimplePanneauIsos2D(ArrayList<List<Point2D.Double[]>> listIsos2D, Grille grille, int w, int h){
		this.listIsos2D = listIsos2D;
		this.listIsos = null;
		this.grille = grille;
		this.width = w;
		this.height = h;
		System.out.println(w + " *** "+ h);
	}
	

	/**
	 * Surcharge la méthode paintComponent de JComponent pour dessiner nos isolignes
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Line2D line = null;
		g2.scale(this.width / (grille.pas*grille.nCol), - this.height / (grille.pas*grille.nLig));
		g2.translate(-(grille.x0), -grille.y0/*+1000*/);
		
		if (listIsos != null){
			for (Isoligne iso: listIsos ){
				if (iso != null){
					for (double[][] p : iso.segments){
						line = new Line2D.Double(p[0][0], p[0][1], p[1][0], p[1][1]);
						g2.draw(line);
					}
				}
			}
		}
		if (listIsos2D != null){
			for (List<Point2D.Double[]> iso: listIsos2D ){
				if (iso != null){
					for (Point2D.Double[] p : iso){
						line = new Line2D.Double(p[0], p[1]);
						g2.draw(line);
					}
				}
			}
		}
	}

}
