
import java.util.ArrayList ;




public class ArbreBinaire {
	
	ArbreBinaire filsGauche ; 
	ArbreBinaire filsDroit ; 
	Noeud racine;
	/**LA DIMENSION DE L'ESPACE*/
	int dim;
	
	
    
       public class Noeud { 
    	   ArbreBinaire filsGauche;
    	   ArbreBinaire filsDroit;
    	   Point point ;
		   int dim;
		   int indice;

		
		public Noeud(Point point) { 
				this.filsGauche = null ; 
				this.filsDroit = null ; 
				this.point = point ;
				this.dim=point.dim;
			}
			
		}		
		
 
	/**CONSTRUCTEUR DE L'ARBRE BINAIRE*/
       
		private ArbreBinaire() {
			 this.filsGauche=null;
			 this.filsDroit=null; 
			 this.racine=null;
			 
		}
		
		

	/**SI LE POINT EST A GAUCHE DE L'HYPERPLAN, C'EST DONC UN FILS GAUCHE*/
	/**RETOURNE TRUE S'IL SAGIT D'UN FILS GAUCHE*/
		
		boolean estaGauche(Point point, int indice) { 
					if(this.racine.point.distanceAxe(point, indice)<0) { 
						return true;
					}
					else {
						return false; 
		}
		}


		
		boolean estNul(){
			return (this==null || this.racine==null); 
		}



		void addPointAux(Point P, int i) { 		
			/**SI L'ARBRE EST VIDE, ON INSERT LE POINT DANS LA RACINE*/
			if (this.racine==null) {
				this.racine = new Noeud(P) ; 
                this.filsGauche = new ArbreBinaire() ; 
				this.filsDroit = new ArbreBinaire() ; 
			/**A CHAQUE PASSAGE A UN AUTRE NIVEAU DE L'ARBRE, ON COMPARE PAR RAPPORT A UN NOUVEL HYPERPLAN*/
				if(i==this.dim){ 
					this.racine.indice = 1 ;
				}
				else {
					this.racine.indice=i+1 ; 
				}
			}
			/**SI LA RACINE N'EST PAS NULL*/
			else {
				/**estaGauche RENVOIE TRUE SI LE POINT EST A GAUCHE DE L'HYPERPLAN*/
				if(estaGauche(P,i)) {
					this.filsGauche.addPointAux(P, i+1) ; 
				}
				else {
					this.filsDroit.addPointAux(P, i+1) ; 
				}			
			}
		}
		

		
		
/**LA FONCTION QUI PERMET D'AJOUTER UN POINT DANS L'ARBRE 	
/**LE PREMIER HYPERPLAN PAR RAPPORT AUXQUEL ON INSERT LE POINT EST CELUI D'INDICE 1*/
		void addPoint(Point point){ 
			addPointAux(point, 1) ; 
		}
		
		
		
		
/**SUPPRESSION D'UN POINT DANS L'ARBRE*/
		void removePoint1(Point P) { 
			if(this.racine.point == P) {
				this.filsGauche=null;
				this.filsDroit=null;
				this.racine=null;
			}
			else {
				if (this.estAGauche(P)) {
					this.filsGauche.removePoint1(P);
				}
				else {
					this.filsDroit.removePoint1(P);
				}
			}
		}
		
		
		/** estAGauche(p) UTILISEE DANS remove1(p)*/
		boolean estAGauche(Point point) { // cette fonction permet de déterminer si le point en entrée est  dans le fils gauche ou droit de l'arbre initial (this). A chaque noeud est associé ou bien l'hyperplan (x0...,xdim-1), ou bien l'hyperplan (x1...xdim)
			int indice = this.racine.indice ; 
			for (int i=0 ; i<this.dim ; i++) {
				if (i!=indice) {
					if(this.racine.point.distanceAxe(point, i)<0) { // alors on n'est pas dans le demi hyperplan généré par les (x0,.xindice-1, xindice+1,..., xdim)
						return false ;
					}
				}
			}
			return true ; ///on est dans le demi hyperplan généré par les (x0,..., xdim-1)
		}
		
	
		
		
		ArbreBinaire getParent(Point p) {
			ArbreBinaire noeud = null, fils = this ; 
			while (fils.racine.point != p) {
				noeud = fils;
				if ( fils.estAGauche(p)){
					fils = noeud.filsGauche;
				} else {
					fils = noeud.filsDroit;
				}
			}			
			return noeud;
		}
			
		

		
		
		 Point getNearestNeighbor(Point p){
		    	
			    Point test = this.racine.point ; // ce point sera le plus proche voisin Ã  la fin du code
		    	ArbreBinaire noeud = this ; 
	
		    	while((noeud.filsGauche !=null | noeud.filsDroit !=null) /*| (noeud.filsGauche.racine !=null | noeud.filsDroit.racine !=null)*/) { // tant qu'on n'est pas au niveau d'une feuille

			    	/* Actualisation Ã©ventuelle du point le plus proche */

		    		if ( (0< p.distance2(noeud.racine.point)) & (p.distance2(noeud.racine.point) <  p.distance2(test)))  { // on actualise le point qui peut Ãªtre potentiellement le plus proche voisin
				    	test = noeud.racine.point ;
		    		}
					    
		    		/* ItÃ©ration dans l'arbre */
		    		
				    if(noeud.estAGauche(p)) { 
				    	noeud = noeud.filsGauche ; 
				    }
				    
				    else {
					    noeud = noeud.filsDroit ; 
					    }
		    	}
		    	return test ; 
		 }
		 
		 
		 ArrayList<Point> getKNearestNeighbors(Point p, int k) { // renvoie les k plus proches voisins de p
			 
			 ArrayList<Point> voisins = new ArrayList<Point>() ;
			 float max = 0 ; // c'est la distance maximale parmi les k points proches de p
			 int indiceMax = 0 ; //c'est l'indice indiquant l'amplacement du point de distance maximale avec p
			 int compteur = 0 ; 
			 	 
				Point test = this.racine.point ; // ce point sera le plus proche voisin Ã  la fin du code
			    ArbreBinaire noeud = this ; 
			    	
			    while((noeud.filsGauche !=null | noeud.filsDroit !=null) /*| (noeud.filsGauche.racine !=null | noeud.filsDroit.racine !=null)*/) { // tant qu'on n'est pas au niveau d'une feuille

			    	float distTest = p.distance2(test) ; 
			    	
			    	/* Actualisation Ã©ventuelle de la liste voisin */
			    	
		    		if ( (0 < p.distance2(noeud.racine.point)) & (p.distance2(noeud.racine.point) <  distTest))  { 
				    	test = noeud.racine.point ;
				    	
				    	if(compteur<k) {
				    		voisins.add(test) ; 
				    		if (max<=distTest) {
				    			max = distTest ; 
				    			indiceMax = compteur ; 
				    		}			    		
				    		compteur++ ; 
				    	}
				    	
				    	else {
				    		if (distTest<max){
				    			voisins.remove(indiceMax) ; 
				    			voisins.add(test) ; 
				    			indiceMax = k ; 
				    		}
				    	}
				    	
				    	
			    	}
						
		    		/* ItÃ©ration dans l'arbre */ 
		    		
				    if(noeud.estAGauche(p)) { 
				    	noeud = noeud.filsGauche ; 
				    }
					    
				    else {
					    noeud = noeud.filsDroit ; 
					    }
			    }  
			    
			 return voisins ;		 
		 }

		
		 
		 
	

		/*void setPlan() { // cette fonction fait en sorte qu'on alterne les hyperplans associés aux points, on l'utilise avec true en entrée
			
			if (!this.filsGauche.estNul()) {
				this.filsGauche.planHorizontal = !this.planHorizontal ; 
				this.filsGauche.setPlan() ; 
			}
			if (!this.filsDroit.estNul()) {
				this.filsDroit.planHorizontal = !this.planHorizontal ; 
				this.filsDroit.setPlan() ; 
			}
		}*/
		
		
		
		


		
	}
		



	/* Indications pour la suite : 
	private Noeud getParent(Noeud n, Point p) peut être récursive ou itérative
		if n.getParent(n.d)-p.get(n.d)>0){
			if(n.filsGauche ==null return n ; 
			else return getParent(filsGauche, p) ;
		}
	}
	void addPoint(Point p){
		if(root == null) {
			root = nouveaunoeud ;
		}
		else {
			noeud  = getParent(root, p)
		}
		
		if (A.getParent(a.d)-p.get(A.d)>0) A.fils = new Noeud(p,d) ; 
		
	*/

