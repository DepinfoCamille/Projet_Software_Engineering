
import java.util.ArrayList ;


public class ArbreBinaire {
	
	Noeud racine;
	    
       public class Noeud { 
    	   Noeud filsGauche;
    	   Noeud filsDroit;
		   int indice;
		   Point point ;

		
		private Noeud(Point point) { 
				this.filsGauche = null ; 
				this.filsDroit = null ; 
				this.point = point ;
				this.indice = 0 ; 
			}
		
		@SuppressWarnings("unused")
		public Point getPoint() {
			return this.point;
		}

		/**SI LE POINT EST A GAUCHE DE L'HYPERPLAN, C'EST DONC UN FILS GAUCHE*/
		/**RETOURNE TRUE S'IL SAGIT D'UN FILS GAUCHE*/
		/** l'hyperplan est caractérisé par l'indice i */
		boolean estaGauche(Point point) {
			if(this.point.distanceAxe(point, this.indice)<0) { 
				return true;
			}
			else {
				return false; 
			}
		}
		
		/** AJOUTE UN FILS AU NOEUD CONSIDERE */
		void addFils(Point p) {
			
			/* on met à jour l'indice du fils */			
			int indiceFils ; 
			if(this.indice==this.point.dim-1){ 
				indiceFils = 0 ;
			}
			else {
				indiceFils=this.indice+1 ; 
			}
			
			/* on insère le point en tant que fils gauche ou droit */
			if (this.estaGauche(p)){
								
				if (this.filsGauche==null) {
					this.filsGauche = new Noeud(p) ;
					this.filsGauche.indice = indiceFils ; 
				}
				
				else {
					filsGauche.addFils(p) ; // récurrence sur le fils gauche
				}
			}
			
			else {
				
				if (this.filsDroit==null) {
					this.filsDroit = new Noeud(p) ; 
					this.filsDroit.indice = indiceFils ; 
				}
				
				else {
					filsDroit.addFils(p) ; // récurrence sur le fils droit
				}
			}
		}
		
		/** DETERMINE SI LE NOEUD CONSIDERE EST UN FILS GAUCHE OU DROIT */
		boolean estunfilsGauche(Noeud origine) {
			return origine.getParent(this.point).filsGauche.point ==this.point ; 
		}
		/** ENLEVE UN NOEUD */
		void removePoint(Point p, Noeud origine) {
			
			if(this.point == p) {
				
				if(estunfilsGauche(origine)) {
					origine.getParent(this.point).filsGauche = this.filsGauche ; 
					this.filsGauche.filsDroit = this.filsDroit ;	
				}
				else {
					origine.getParent(this.point).filsDroit  = this.filsDroit ; 
					this.filsDroit.filsGauche = this.filsGauche ;						
				}
			}
			else { // récurrence jusqu'à atteindre le point à supprimer
				if (this.estaGauche(p)) {
					this.filsGauche.removePoint(p, origine);
				}
				else {
					this.filsDroit.removePoint(p, origine);
				}
			}
		}
		/** TROUVE LE PERE DU POINT P */
		Noeud getParent(Point p) {
			
			Noeud noeud = null, fils = this ; 
			while (fils.point != p) {
				noeud = fils;
				if ( fils.estaGauche(p)){
					fils = noeud.filsGauche;
				} else {
					fils = noeud.filsDroit;
				}
			}			
			return noeud;
		}
		
		/** FONCTIONS AUXILIAIRES DE getNearestNeighbor */
		
		/** Trouve un voisin proche de noeud */
		Noeud trouverVoisinProche(Point p, Noeud voisin, Noeud noeud, float distanceMin) {
			do { // tant qu'on n'est pas au niveau d'une feuille
		   		
					/** Actualisation éventuelle du point le plus proche */
				if ( (0<= p.distance2(noeud.point)) && (p.distance2(noeud.point) <  distanceMin))  { // on actualise le point qui peut être potentiellement le plus proche voisin
					voisin = noeud ;
					distanceMin = p.distance2(noeud.point) ; 
				}
				    
				/* Itération dans l'arbre */
				if(noeud.filsGauche!=null && noeud.estaGauche(p)) { 
					noeud = noeud.filsGauche ; 
				}
				else if (noeud.filsDroit!=null) {
					noeud = noeud.filsDroit ; 
				}
			}while(noeud.filsGauche !=null || noeud.filsDroit !=null) ; 
			
			return voisin ; 
			
		}
		
		/** Renvoie le noeud racine de la branche voisine du noeud en entrée */
		/* i.e si le noeud en entrée est un fils gauche, la fonction renvoie le fils droit du père */
		Noeud brancheVoisine(Noeud voisin) { 
			 Noeud noeud = this.getParent(voisin.point) ;
			 if(noeud.filsGauche!=null && noeud.filsGauche.point == voisin.point){ // alors on explore l'arbre droit
				 if(noeud.filsDroit !=null){
					 return noeud.filsDroit ; 
				 }
			 }
			 else if (noeud.filsDroit!=null && noeud.filsDroit.point ==voisin.point){
				 if(noeud.filsGauche !=null){
					 return noeud.filsGauche ; 
				 }
			 }
			 return null ; 
		}
		
		/** permet de parcourir la branche en profondeur, grâce à noud2 */ 
		Noeud parcourtBranche(Point p, Noeud noeud2, Noeud voisin){
			 /* Mise à jour de noeud2*/
			 if(noeud2.filsDroit ==null && noeud2.filsGauche==null){
				 return voisin ; 
			 }
			 else if(noeud2.filsGauche==null) {
				 return noeud2.filsDroit ;
			 }
			 else if (noeud2.filsDroit==null){
				 return noeud2.filsGauche ; 
			 }
			 else {
				 if(p.distance2(noeud2.filsGauche.point)<p.distance2(noeud2.filsDroit.point)){
				 	 return noeud2.filsGauche ; 
				 }
				 else {
					 return noeud2.filsDroit ; 
				 }
			 }
		}
		
		/** FONCTION getNearestNeighbor POUR LES NOEUDS */
		 Noeud gNN2(Point p) {

		    Noeud voisin = this ; // noeud qui sera le plus proche voisin
		    Noeud noeud = this  ; // noeud qui parcourt l'arbre
		    float distanceMin = p.distance2(this.point);  // distance minimale entre p et les points de l'arbre
		    	   			
		    /** ON PARCOURT UNE PREMIER FOIS L'ARBRE POUR TROUVER UN VOISIN PROCHE */		    
		   	voisin = trouverVoisinProche(p, voisin, noeud, distanceMin) ; 
		    	
		   	/** ON PARCOURT L'ARBRE EN CONSIDERANT LES DISTANCES AUX HYPERPLANS */
		   	Noeud noeud2 = voisin ; // noeud qui parcourt la branche voisine du noeud voisin 
		   	// i.e si voisin est une branche gauche, noeud2 parcourt la branche droite et inversement
		   	float distancePlan = p.distanceAxe(noeud2.point, noeud2.indice) ; // distance de p à l'hyperplan de noeud2 
		 
		   	/* On détermine la branche voisine d'où part noeud2 */
		   	noeud2 = brancheVoisine(voisin) ; 
		 
		   	/* On parcourt la branche voisine */
		   	while(distancePlan*distancePlan<distanceMin*distanceMin) { 

		   		/** actualisation du plus proche voisin */
		   		float distanceTest = noeud2.point.distance2(voisin.point) ; 
		   		// si noeud2 est plus proche que voisin, on actualise voisin
		   		if (distanceTest*distanceTest <distanceMin*distanceMin) {
		   			distanceMin = distanceTest ; 
		   			voisin = noeud2 ; 
		   		}
		   		
		   		/** parcourt de la branche */ 
		   		/* Mise à jour de noeud2*/
		   		noeud2 = parcourtBranche(p, noeud2, voisin) ; 
		   		/* Mise à jour de distancePlan */
		   		distancePlan =  p.distanceAxe(noeud2.point, noeud2.indice) ; 

			 
		 }
		 
		 return voisin ; 
	 }
		 
   }
 
	/**CONSTRUCTEUR DE L'ARBRE BINAIRE*/
       
		public ArbreBinaire(Point p) {
			 this.racine=new Noeud(p);
		//	 this.dim = 0 ; 
		}
		
		void addPoint(Point p) { 		
				this.racine.addFils(p) ; 
		}
		
		/** AFFICHE L'ARBRE CONSIDERE */
		
		public String toString(){
			return racine.toString(); 
		}
		
		public Noeud getParent(Point p) {
			return this.racine.getParent(p) ; 
		}

		public Noeud getNearestNeighbor(Point p) {
			return this.racine.gNN2(p) ; 
		}
		
		/**SUPPRESSION D'UN POINT DANS L'ARBRE*/
		void removePoint(Point p) { 
			this.racine.removePoint(p, this.racine) ; 
		} 
		
		/** RENVOIE LES k PLUS PROCHES VOISINS DE p */
		ArrayList<Point> getKNearestNeighbors(Point p, int k) { 
			
			 ArrayList<Point> voisins = new ArrayList<Point>() ; // liste des k plus proches voisins
			 ArbreBinaire A = this ; // arbre sur lequel on va itérer
			 Point pointProche ; // un des k plus proches voisins
			 
			 for(int i = 0 ; i<k ; i++) {
				 pointProche = A.getNearestNeighbor(p).point ; 
				 voisins.add(pointProche) ; 
				 A.removePoint(pointProche) ; 
			 }
			 
			 return voisins ; 
		}

	
	}
