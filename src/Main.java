
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int tabA[] = {1,0,0} ; 
		int tabB[] = {1,1,1} ; 
		int tabC[] = {0,0,1} ; 
		int tabD[] = {0,1,0} ; 

		Point pointA = new Point(tabA) ; 
		Point pointB = new Point(tabB) ;
		Point pointC = new Point(tabC) ;
		
		System.out.println(pointA) ; 

		
		ArbreBinaire B = new ArbreBinaire(pointB, null, null, 3) ; 
		ArbreBinaire C = new ArbreBinaire(pointC, null, null, 3) ; 

		ArbreBinaire A = new ArbreBinaire(pointA, B, C,3) ; 
		
		System.out.println(A) ; 


	}

}
