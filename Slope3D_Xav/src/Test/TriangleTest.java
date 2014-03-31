package Test;

import structures.Triangle;

public class TriangleTest {

	public static void main(String[] args) {
		/* 				Quelques exemples qui fonctionnent :
		 * 
		 * 		A(4,0,0)     B(2,-2,2)     C(0,0,4)      >>  45�
		 * 		A(6,0,2)     B(3,4,1)      C(0,0,0)      >>  18.44�	
		 * 		A(1,0,0)     B(0,1,0)      C(0,0,1)      >>  54.73�
		 * 		A(0,0,0)     B(1,0,0)      C(0,0,1)      >>  90.00�
		 * 		A(0,0,0)     B(9,2,0)      C(-1,6,0)     >>  00.00�
		 * 
		 */


		double[] a = new double[3];
		a[0] = 1;//150 ;
		a[1] = 0;//-150 ;
		a[2] = 0;//500 ;

		double[] b = new double[3];
		b[0] = 0;//120 ;
		b[1] = 1;//-180 ;
		b[2] = 0;//200 ;

		double[] c = new double[3];
		c[0] = 0;//150 ;
		c[1] = 0;//-180 ;
		c[2] = 1;//100;

		double[] N = new double[3];
		N[0] = 1 ;
		N[1] = 2 ;
		N[2] = 3 ;

		//Triangle abc = new Triangle( 10 , a , b , c , N , 0 );

		Triangle abc = new Triangle( 2 , a , b , c );
		//abc.afficherCaracteristiquesTriangle();

		//System.out.println(" ");
		//System.out.println(abc.caracteristiquesTriangle());

		String chaineTriangle1;
		chaineTriangle1 = abc.caracteristiquesTriangle();
		System.out.println(chaineTriangle1);
		Triangle def = new Triangle(chaineTriangle1);
		def.afficherCaracteristiquesTriangle();



		System.out.println("\n Le triangle num�ro "+ abc.getIndice());

	}

}
