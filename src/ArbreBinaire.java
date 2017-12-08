
import java.util.ArrayList ;


public class ArbreBinaire {
	
	Noeud racine;
<<<<<<< HEAD
	    
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
=======
	   
	
//////////////////////////////CLASSE NOEUD PRIVEE//////////////////////////////
				/* Permet de contruire le KDnode ArbreBinaire */
	
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

		/** Indique si un point est au niveau du sous-arbre gauche ou non
		 * @return true si le point est "à gauche" de l'hyperplan
		 * @ param Point point
		 */
		boolean estaGauche(Point point) { 
>>>>>>> master
			if(this.point.distanceAxe(point, this.indice)<0) { 
				return true;
			}
			else {
				return false; 
			}
		}
		
		/** Ajoute un noeud au fils considéré
		* @param Point p
		* @return none 
		*/	
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
			if (this.estaGauche(p)){ indiceFils // en tant que fils gauche
								
				if (this.filsGauche==null) {
					this.filsGauche = new Noeud(p) ;
					this.filsGauche.indice = indiceFils ; 
				}
				
				else {
					filsGauche.addFils(p) ; // récurrence sur le fils gauche
				}
			}
			else { // en tant que fils gauche
				
				if (this.filsDroit==null) {
					this.filsDroit = new Noeud(p) ; 
					this.filsDroit.indice = indiceFils ; 
				}
				
				else {
					filsDroit.addFils(p) ; // récurrence sur le fils droit
				}
			}
		}
		
		/**FONCTIONS AUXILIAIRES DE REMOVE POINT */
		
		/** suprime la racine de l'arbre 
		 * fonction auxiliaire de removePeoint
		 * @param le noeud dont on veut supprimer la racine
		 * @return none
		 */
		private void supprimeRacine(){
			if(this.filsGauche==null&&this.filsDroit==null){  // les deux fils de la racine sont nuls
				System.out.println("Arbre vide désormais") ; 
				this.point = null ; ; 
			}
			else if(this.filsGauche==null){// seul son fils gauche est nul
				this.point = this.filsDroit.point ; 
				this.filsGauche = this.filsDroit.filsGauche ; 
				this.filsDroit= this.filsDroit.filsDroit ; 

			}
			else if(this.filsDroit==null){// seul son fils droit est nul
				this.point = this.filsGauche.point ; 
				this.filsDroit = this.filsGauche.filsDroit ; 
				this.filsGauche= this.filsGauche.filsGauche ; 

			}
			else { // aucun des fils sont non nuls
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
		
		/** supprime le fils gauche du noeud considéré
		 * fonction auxiliaire de removePeoint
		 *@return none
		 */
		private void supprimeFilsGauche(){
			
			Noeud memoire = this.filsGauche ; // on garde en mémoire le noeud à supprimer 
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
		
		/** supprime le fils droit du noeud considéré
		 * fonction auxiliaire de removePeoint
		 *@return none
		 */
		private void supprimeFilsDroit(){
			
			Noeud memoire = this.filsDroit ; // on garde en mémoire le noeud à supprimer 
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

		/** enlève le point p de l'arbre d'origine origine
		 * @param Point p le point à supprimer, Noeud origine est la racine de l'arbre dans ArbreBinaire
		 * */
		void removePoint(Point p, Noeud origine) { // origine est la racine de l'arbre
			
			if(origine.point == p){
				supprimeRacine() ; 
			}
			
			/* INITIALISATION */ 
			else if(this.filsGauche!=null && this.filsGauche.point==p){	/* suppression dufils Gauche */
				this.supprimeFilsGauche() ; 
			}
			else if(this.filsDroit!=null && this.filsDroit.point==p) {	/* suppression fils Droit */
				this.supprimeFilsDroit() ; 			
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
		
		/** Trouve le père du point p 
		 * @param le point dont on cherche le père
		 * @return un Noeud le père du point p
		 */
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
		
		/** Renvoie le point le plus proche de point p
		* @param Point p le point dont on cherche le plus proche voisin,
		* Point candidat le point possiblement le plus proche voisin
		* @return Point candidat le plus proche voisin de Point p
		*/		
		Point getNNNoeud( Point p, Point candidat){
			Noeud n = this ; 
			if(p.distance2(n.point)<p.distance2(candidat)){
				candidat = n.point ; 
			}
			double dist1D = n.point.distanceAxe(p, n.indice) ; 
			Noeud n1 = (dist1D>0)? n.filsDroit:n.filsGauche ; 
			Noeud n2 = (dist1D>0)? n.filsGauche:n.filsDroit ; 
			
			if(n1!=null){
				candidat = n1.getNNNoeud(p,candidat) ; 
			}
			if(n2!=null && dist1D*dist1D<p.distance2(candidat)){
				candidat = n2.getNNNoeud(p, candidat) ; 
			}
			return candidat ; 
		} 
	 
	 
		/** AFFICHE LE NOEUD
		* @param le noeud à afficher
		* @return une string affichant le noeud sous la forme d'arbre
		* "racine" ("filsGauche", "filsDroit")
		*/		
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
 
//////////////////////////////IMPLEMENTATION DE LA CLASSE PUBLIQUE//////////////////////////////
    									/* ArbreBinaire */

        
	/**CONSTRUCTEUR DE L'ARBRE BINAIRE*/
       
		public ArbreBinaire(Point p) { // est défini par sa racine
			 this.racine=new Noeud(p);
		//	 this.dim = 0 ; 
		}
		
		/** AFFICHE L'ARBRE CONSIDERE */
		
		public String toString(){
			return racine.toString(); 
		}
		
		/** Ajoute un noeud à l'arbre considéré
		* @param this l'ArbreBinaire, Point p le point à ajouter 
		* @return none 
		*/
		void addPoint(Point p) { 		
				this.racine.addFils(p) ; 
		}
		
		public Point getNN(Point p){
			if (this.racine ==null){
				return null ;
			}
			else {
				return this.racine.getNNNoeud(p, this.racine.point);
			}
		}
		
		/** supprime de l'arbre le point considéré
		* @param ArbreBinaire l'arbre dont on veut supprimer le point, 
		* Point p le point à supprimer de l'arbre
		*@return none
		*/
		void removePoint(Point p) { 
			this.racine.removePoint(p, this.racine) ; 
		} 
		
		/** renvoie le plus proche voisin du point en entrée
		 * @param Point p le point dont on cherche le plus proche voisin
		 *@return Point voisin le plus proche voisin
		 */
		public Point getNN(Point p){
			if (this.racine ==null){
				return null ;
			}
			else {
				return this.racine.getNNNoeud(p, this.racine.point);
			}
		}
		
		/** renvoie les k plus proches voisins du point en entrée
		* @param Point p le point dont on cherche les plus proches voisins,
		* k le nombre de voisins recherchés
		*@return une ArrayList<Point> de longueur k contenant les k plus proches voisins
		*s'ils existent
		*/
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
