
public class ArbreBinaire {

	private class Noeud {
		
		Point point ;
		int indice ; 
		int dim ; 
	
		public Noeud(Point point) { // Création d'une sous-classe Noeud, nécessitant un point caractérisant le noeud et la dimension de ce point

			this.point = point ;
			this.indice = 1 ; 
			this.dim = point.dim ; 
		}	
		
		boolean equals(Noeud noeud) {
			return this.point ==noeud.point ; 
		}
	} 
	
	ArbreBinaire filsGauche ; 
	ArbreBinaire filsDroit ; 
	Noeud racine ; 
	int dim ; 
	
	ArbreBinaire() { // crée un arbre vide 
		this.racine = null ; 
		this.filsGauche = null ; 
		this.filsDroit = null ;		
		this.dim = 1 ;
	}
	
	ArbreBinaire(Point point) { // crée un arbre à partir d'un point
		this.racine = new Noeud(point) ; 
		this.filsGauche = null ; 
		this.filsDroit = null ;		
		this.dim = 1 ;
	}
	
	boolean estNul(){
		return (this==null || this.racine==null); 
	}
	
	public String toString() {
		
		if (this.racine ==null) {
			return "" ; 
		} 
		
		else{
			if((this.filsDroit.racine==null) & (this.filsGauche.racine==null)) {
				return this.racine.point.toString() ; 
			}
			
			else {
			
				if(this.filsGauche.racine==null) {
					return this.racine.point.toString()+" ( null , " + this.filsDroit.toString() + " ) " ;
				}
			
				if(this.filsDroit.racine==null) {
					return this.racine.point.toString()+" ( " + this.filsGauche.toString()+ " ,  null ) " ;
				}
		
				else {
					return this.racine.point.toString()+" ( " +this.filsGauche.toString()+ " , " + this.filsDroit.toString() + " ) " ; 
				}
			}
		}
	} 
		
	boolean estaGauche(Point point) { // cette fonction permet de déterminer si le point en entrée est  dans le fils gauche ou droit de l'arbre initial (this). A chaque noeud est associé ou bien l'hyperplan (x0...,xdim-1), ou bien l'hyperplan (x1...xdim)
				
		int indice = this.racine.indice ; 
		for (int i=0 ; i<this.dim ; i++) {
			if (i!=indice) {
				if(this.racine.point.distanceAxe(point, i)<0) { // alors on n'est pas dans le demi hyperplan généré par les (x0,.xindice-1, xindice+1,..., xdim)
					return false ;
				}
			}
		}
		return true ; //on est dans le demi hyperplan généré par les (x0,..., xdim-1)
		
	}
			
	void addPointAux(Point point, int i) { //ajoute un point à l'arbre, cette fonction auxiliaire permet de gardr l'indice i correspondant à l'hyperplan du père en mémoire
		
		if (this.racine==null) { // l'emplacement est libre, on peut donc créer une feuille noeud
			
			this.dim = point.dim ; 

			this.racine = new Noeud(point) ; // initialisation du nouveau noeud et de ses fils

			this.filsGauche = new ArbreBinaire() ; 
			this.filsGauche.racine = null ; 
			this.filsDroit = new ArbreBinaire() ; 
			this.filsDroit.racine = null ; 

			
			if(i==this.dim){ 
				this.racine.indice = 1 ;
			}
			else {
				this.racine.indice=i ; 
			}
		}
		
		else { // récurrence 
			if(this.estaGauche(point)) {

				this.filsGauche.addPointAux(point, i+1) ; 
			}
			else {

				this.filsDroit.addPointAux(point, i+1) ; 
			}			
		}
	}
	
	void addPoint(Point point){ // c'est la véritable fonction addPoint, qui est la fonction addPointAux initialisée à 1 (i.e la racine de l'arbre a un hyperplan d'indice 1)
		addPointAux(point, 1) ; 
	}
	
	ArbreBinaire getParent(Point p) {
		assert(p!=null);
		
		ArbreBinaire noeud = null, fils = this ; 

		while (fils.racine.point != p) {
			noeud = fils;
			if ( fils.estaGauche(p)){
				fils = noeud.filsGauche;
			} else {
				fils = noeud.filsDroit;
			}
		}
		
		return noeud;
	}
		
	
	void removePointAux(Point point, ArbreBinaire root) { // on commence la récurrence après la racine, appelée root
		
		if (this == root) { // dans ce cas, on applique removePoint à un de ses fils
			
			if (this.estaGauche(point)){ 
				this.filsGauche.removePointAux(point, root) ; 
			}
			else {
				this.filsDroit.removePointAux(point, root) ; 

			}
			
		}
		
		else {
			ArbreBinaire pere = root.getParent(this.racine.point) ; // On identifie le père de l'arbre dans lequel on est
			
			if (pere.filsGauche ==this) { // le point recherché est le fils gauche de son père
				
				if (this.filsGauche.estNul() & this.filsDroit.estNul()) { // on est au niveau d'une feuille, il suffit de la supprimer 
						this.racine = null ;
						this.filsGauche = null ; 
						this.filsDroit = null ; 
				}
				else {
					Point mem = this.filsGauche.racine.point ; 
					this.racine = this.filsDroit.racine ; // L'arbre considéré prend tous les attributs de son fils
					this.filsGauche = this.filsDroit.filsGauche ; 
					this.filsDroit = this.filsDroit.filsDroit ; 
					this.addPoint(mem) ; 				// on insère le fils gauche de l'ancien arbre au nouveau
				}			
			}
			
			else if (pere.filsDroit ==this) { // le point recherché est le fils droit de son père
	
					if (this.filsGauche.estNul() & this.filsDroit.estNul()) { // on supprime la feuille
						this.racine = null ; 
						this.filsGauche = null ; 
						this.filsDroit = null ; 
					}
					else {
						Point mem = this.filsDroit.racine.point ; 
						this.racine = this.filsGauche.racine ; // L'arbre considéré prend tous les attributs de son fils
						this.filsDroit = this.filsGauche.filsDroit ; 
						this.filsGauche = this.filsGauche.filsGauche ; 
						this.addPoint(mem) ; 				// on insère le fils droit de l'ancien arbre au nouveau
					}			
			}
			
			else { // le point recherché n'est dans aucun des fils, il faut alors chercher plus profondément dans l'arbre
				if (this.estaGauche(point)){ 
					this.filsGauche.removePointAux(point, root) ; 
				}
				else {
					this.filsDroit.removePointAux(point, root) ; 
	
				}
			} 
		}
		
	} 
	
	void removePoint(Point point) {
		removePointAux(point, this) ; 
	}

}

