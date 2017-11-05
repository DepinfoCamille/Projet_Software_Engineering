
import java.util.ArrayList ;



public class ArbreBinaire {
	
	Noeud racine;
	
	private class Noeud { 
		Noeud filsGauche;
   	   	Noeud filsDroit;
   	   	Point point ;
   	   	int indice;

   	   	private Noeud(Point point) { 
   	   		this.filsGauche = null ; 
   	   		this.filsDroit = null ; 
   	   		this.point = point ;
			this.indice = 0 ; 
		}
   	   	
   	   	/**SI LE POINT EST A GAUCHE DE L'HYPERPLAN, C'EST DONC UN FILS GAUCHE*/
   	   	/**RETOURNE TRUE S'IL SAGIT D'UN FILS GAUCHE*/
   	   	/** l'hyperplan est caractérisé par l'indice i */
   	   	boolean estaGauche(Point point) { 
   	   		if(this.point.distanceAxe(point, this.indice)<0) { 
   	   			if(point==pointE){
   	   				System.out.println("E est à gauche") ; 
   	   			}
   	   			return true;
   	   		}
   	   		else {
   	   			if(point==pointE){
   	   				System.out.println("E est à droite") ; 
   	   			}
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
				/** ENLEVE LE POINT p */
		void removePoint(Point p, Noeud origine) {
			
			if(this.point == p) { // on est arrivé au point à supprimer
				
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
			
		/** RENVOIE LE PERE DE p */ 
		Noeud getParent(Point p) {
			
			Noeud noeud = null, fils = this ; 
			while (fils.point != p) {
				noeud = fils;
				if ( fils.estaGauche(p)){
					fils = noeud.filsGauche;
				} 
				else {
					fils = noeud.filsDroit;
				}
			}			
			return noeud;
		}
						 
		/** RENVOIE LE PLUS PROCHE VOISIN DE p */
		 Noeud gNN2(Point p) {
		    Noeud voisin = this ; // noeud qui sera le plus proche voisin
		    Noeud noeud = this  ; // noeud qui parcourt l'arbre
		    float distanceMin = p.distance2(this.point);  // distance minimale entre p et les points de l'arbre
			    	   			
		    /** ON PARCOURT UNE PREMIER FOIS L'ARBRE POUR TROUVER UN VOISIN PROCHE */		    
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

		    /** ON PARCOURT L'ARBRE EN CONSIDERANT LES DISTANCES AUX HYPERPLANS */
		    Noeud noeud2 = voisin ; // noeud qui parcourt la branche voisine du noeud voisin 
		    // i.e si voisin est une branche gauche, noeud2 parcourt la branche droite et inversement
			 float distancePlan = p.distanceAxe(noeud2.point, noeud2.indice) ; // distance de p à l'hyperplan de noeud2 
			 
			 /* On détermine la branche voisine d'où part noeud2 */
			 noeud = this.getParent(voisin.point) ;
			 if(noeud.filsGauche!=null && noeud.filsGauche.point == voisin.point){ // alors on explore l'arbre droit
				 if(noeud.filsDroit !=null){
					 noeud2 = noeud.filsDroit ; 
				 }
			 }
			 else if (noeud.filsDroit!=null && noeud.filsDroit.point ==voisin.point){
				 if(noeud.filsGauche !=null){
					 noeud2 = noeud.filsGauche ; 
				 }
			 }
			 /* On parcourt la branche voisine */
			 while(distancePlan*distancePlan<distanceMin*distanceMin) { 
	
				 float distanceTest = noeud2.point.distance2(voisin.point) ; 
				 // si noeud2 est plus proche que voisin, on actualise voisin
				 if (distanceTest*distanceTest <distanceMin*distanceMin) {
					 distanceMin = distanceTest ; 
					 voisin = noeud2 ; 
				 }
				 
				 /* on met à jour noeud2 et distancePlan */	 
				 /* Mise à jour de noeud2*/
				 if(noeud2.filsDroit ==null && noeud2.filsGauche==null){
					 return voisin ; 
				 }
				 else if(noeud2.filsGauche==null) {
					 noeud2 = noeud2.filsDroit ;
				 }
				 else if (noeud2.filsDroit==null){
					 noeud2 = noeud2.filsGauche ; 
				 }
				 else {
					 if(p.distance2(noeud2.filsGauche.point)<p.distance2(noeud2.filsDroit.point)){
					 	 noeud2 = noeud2.filsGauche ; 
					 }
					 else {
						 noeud2=noeud2.filsDroit ; 
					 }
				 }
				 /* Mise à jour de distancePlan */
				 distancePlan =  p.distanceAxe(noeud2.point, noeud2.indice) ; 
	
				 
			 }
			 
			 return voisin ; 
		 }
			 
		 
		 
 
	/**CONSTRUCTEUR DE L'ARBRE BINAIRE*/
       
		public ArbreBinaire(Point p) {
			 this.racine=new Noeud(p);
		}
		
		/** AJOUT D'UN POINT DANS L'ARBRE */
		void addPoint(Point p) { 		
				this.racine.addFils(p) ; 
		}

		/** RENVOIE LE PLUS PROCHE VOISIN DE p */
		public Noeud getNearestNeighbor(Point p) {
			return this.racine.gNN2(p) ; 
		}
		
		/**SUPPRESSION D'UN POINT DANS L'ARBRE*/
		void removePoint(Point p) { 
			this.racine.removePoint(p, this.racine) ; 
		} 
		
		/** RENVOIE LES k PLUS PROCHES VOISINS DE p */
		ArrayList<Point> getKNearestNeighbors2(Point p, int k) { 
			
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
