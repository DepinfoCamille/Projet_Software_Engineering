
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
		
		
		/**FONCTIONS AUXILIAIRES DE REMOVE POINT */
		
		/** suprime la racine de l'arbre */
		void supprimeRacine(){
			if(this.filsGauche==null&&this.filsDroit==null){ 
				System.out.println("Arbre vide désormais") ; 
				this.point = null ; ; 
			}
			else if(this.filsGauche==null){
				this.point = this.filsDroit.point ; 
				this.filsGauche = this.filsDroit.filsGauche ; 
				this.filsDroit= this.filsDroit.filsDroit ; 

			}
			else if(this.filsDroit==null){
				this.point = this.filsGauche.point ; 
				this.filsDroit = this.filsGauche.filsDroit ; 
				this.filsGauche= this.filsGauche.filsGauche ; 

			}
			else {
				Noeud memoire = this.filsGauche ; 
				this.point = this.filsGauche.point ; 
				this.filsGauche = memoire.filsGauche ; // on remplace la racine par son filsGauche
				
				if(this.filsGauche!=null){ // on déplace son fils droit
					this.filsGauche.addFils(memoire.filsDroit.point) ; 
				}
				else{
					this.filsGauche = memoire.filsDroit ; 
				}
			}
		}

		/** ENLEVE UN NOEUD */
		void removePoint(Point p, Noeud origine) { // origine est la racine de l'arbre
			
			if(origine.point == p){
				supprimeRacine() ; 
			}
			
			/* INITIALISATION */ 
			else if(this.filsGauche!=null && this.filsGauche.point==p){	/* fils Gauche */
				Noeud memoire = this.filsGauche ; 
				this.filsGauche = memoire.filsGauche ; // on remplace le noeud à supprimer par son filsGauche
					
				if(memoire.filsDroit!=null){ // on déplace son filsDroit s'il existe
					if(this.filsGauche!=null){
						this.filsGauche.addFils(memoire.filsDroit.point) ; 
					}
					else{
						this.filsGauche = memoire.filsDroit ; 
					}
				}
					
			}
			else if(this.filsDroit!=null && this.filsDroit.point==p) {	/* fils Droit */
				Noeud memoire = this.filsDroit ; 
				this.filsDroit = memoire.filsDroit ; // on remplace le noeud à supprimer par son filsDroit
				
				if(memoire.filsGauche!=null){ // on déplace son filsGauche s'il existe
					
					if(this.filsDroit!=null){
						this.filsDroit.addFils(memoire.filsGauche.point) ; 
					}
					else{
						this.filsDroit = memoire.filsGauche ; 
					}
				}
				
			}
			
			/* RECURRENCE */ 
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
		
		/** getNN corrigé */
		
		Point getNNNoeud( Point p, Point candidat){
			Noeud n = this ; 
			if(p.distance2(n.point)<p.distance2(candidat)){
				candidat = n.point ; 
			}
			double dist1D = n.point.distanceAxe(p, n.indice) ; 
			Noeud n1 = (dist1D>0)? n.filsDroit:n.filsGauche ; // ? : on renvoie la première valeur si c'est vrai, sinon on renvoie la deuxième
			Noeud n2 = (dist1D>0)? n.filsGauche:n.filsDroit ; 
			
			if(n1!=null){
				candidat = n1.getNNNoeud(p,candidat) ; 
			}
			if(n2!=null && dist1D*dist1D<p.distance2(candidat)){
				candidat = n2.getNNNoeud(p, candidat) ; 
			}
			return candidat ; 
		} 
	 
	 
		/** AFFICHE LE NOEUD */
		
		public String toString() {
			
			if (this ==null) {
				return "" ; 
			} 
			
			else{
				if((this.filsDroit==null) && (this.filsGauche==null)) {
					return this.point.toString() ; 
				}
				
				else {
				
					if(this.filsGauche==null) {
						return this.point.toString()+" ( null , " + this.filsDroit.toString() + " ) " ;
					}
				
					else {
						if(this.filsDroit==null) {
							return this.point.toString()+" ( " + this.filsGauche.toString()+ " ,  null ) " ;
						}
			
						else {
							return this.point.toString()+" ( " +this.filsGauche.toString()+ " , " + this.filsDroit.toString() + " ) " ; 
						}
					}
				}
			} 
			
			
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

		public Point getNN(Point p){
			if (this.racine ==null){
				return null ;
			}
			else {
				return this.racine.getNNNoeud(p, this.racine.point);
			}
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
			//	 pointProche = A.getNearestNeighbor(p).point ; 
				 pointProche = A.getNN(p) ; 
				 voisins.add(pointProche) ; 
				 if(A!=null){
					 A.removePoint(pointProche) ; 
				 }
			 }
			 
			 return voisins ; 
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
		Noeud trouverVoisinProche(Point p, Noeud voisin, Noeud noeud, double distanceMin) {
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
		    double distanceMin = p.distance2(this.point);  // distance minimale entre p et les points de l'arbre
		    	   			
		    /** ON PARCOURT UNE PREMIER FOIS L'ARBRE POUR TROUVER UN VOISIN PROCHE */		    
		   	voisin = trouverVoisinProche(p, voisin, noeud, distanceMin) ; 
		    	
		   	/** ON PARCOURT L'ARBRE EN CONSIDERANT LES DISTANCES AUX HYPERPLANS */
		  	Noeud noeud2 = voisin ; // noeud qui parcourt la branche voisine du noeud voisin 
		   	// i.e si voisin est une branche gauche, noeud2 parcourt la branche droite et inversement
		   	double distancePlan = p.distanceAxe(noeud2.point, noeud2.indice) ; // distance de p à l'hyperplan de noeud2 
		 
		   	/* On détermine la branche voisine d'où part noeud2 */
		   	noeud2 = brancheVoisine(voisin) ; 

			if(noeud2==null){
				return voisin;
			}
		   	/* On parcourt la branche voisine */
		   	while(distancePlan*distancePlan<distanceMin*distanceMin) { 

		   		/** actualisation du plus proche voisin */
		   		double distanceTest = noeud2.point.distance2(voisin.point) ; 
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
				 if(A!=null){
					 pointProche = A.getNN(p) ; 
					 voisins.add(pointProche) ;
					 A.removePoint(pointProche) ; 
				 }
				 else {
					 i = k-1 ; // si A est nul, on a supprimé tous les noeuds de l'arbre
				 }
			 }
			 return voisins ; 
		}

	
	}
