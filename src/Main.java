
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
		int tabE[] = {0,0,-1} ; 

		Point pointA = new Point(tabA) ; 
		Point pointB = new Point(tabB) ;
		Point pointC = new Point(tabC) ;
		Point pointD = new Point(tabD) ; 
		Point pointE = new Point(tabE) ; 
		
		System.out.println("pointA : " +pointA) ; 
		 
		Noeud noeudB = new Noeud(pointB,3) ; 
		Noeud noeudC = new Noeud(pointC,3) ; 
		Noeud noeudA = new Noeud(pointA,3) ;
	//	noeudA.filsDroit = noeudC ; 
		
		ArbreBinaire A = new ArbreBinaire() ; 
		
	/*	ArbreBinaire B = new ArbreBinaire(noeudB) ; 
		ArbreBinaire C = new ArbreBinaire(noeudC/*, null, null*/) ; 

	/*	System.out.println("B : " + B) ;
		
		B.setPlan(); 
		B.addPoint(pointD) ; 
		
		System.out.println("B après ajout de D : " + B) ; 

		B.setPlan() ; 
		B.addPoint(pointE) ; 
		
		System.out.println("B après ajout de D et E : " + B) ; 
		
		noeudA.filsGauche = noeudB ;  

		
		ArbreBinaire A = new ArbreBinaire(noeudA) ;
		A.setPlan(); 
		System.out.println(" A : " +A) ; 
		
		System.out.println("\n test des plans \n") ; 


		if(A.planHorizontal){ //1
			System.out.println("victoire"); 
		}
		else {
			System.out.println("échec ") ; 
		} 
		
		if(A.filsGauche.planHorizontal){ //2 
			System.out.println("échec"); 
		}
		else {
			System.out.println("victoire ! ") ; 
		} 
		
		if(A.filsGauche.filsDroit.planHorizontal){ //3 
			System.out.println("victoire ! "); 
		}
		else {
			System.out.println("échec") ; 
		}

		if(A.filsGauche.filsDroit.filsDroit.planHorizontal){ //4
			System.out.println("échec"); 
		}
		else {
			System.out.println("victoire ! ") ; 
		}
		
		System.out.println(" \n test de la nullité d'un arbre") ; 
		if (C.filsGauche.estNul()){
			System.out.println(" estNul fonctionne") ; 

		} */
		
		
		

}
}