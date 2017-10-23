
public class ArbreBinaire {

	private class Noeud {
		
		protected Noeud filsGauche, filsDroit; 
		Point point ;
		int indice ; 
		int dim ; 
	
		public Noeud(Point point) { // Création d'une sous-classe Noeud, nécessitant un point caractérisant le noeud et la dimension de ce point
			this.filsGauche = null ; // on initialise les fils aux fils nuls
			this.filsDroit = null ; 
			this.point = point ;
			this.indice = 1 ; 
			this.dim = point.dim ; 
		}	
	} 
	
	ArbreBinaire filsGauche ; 
	ArbreBinaire filsDroit ; 
	Noeud racine ; 
	int dim ; 
	
	ArbreBinaire() {
		this.racine = null ; 
		this.filsGauche = null ; 
		this.filsDroit = null ;		
		this.dim = 1 ;
	}
	
/*	public String toString() {
		
		if((this.filsGauche.estNul()) & (this.filsDroit.estNul())) { // initialisation : on est au niveau d'une feuille
			return this.etiquette.point.toString() ;
		}
			
		else {
			if(this.filsGauche.estNul()) {
				return this.etiquette.point.toString()+" ( null , " + this.filsDroit.toString() + " ) " ; 
			}
		
			if(this.filsDroit.estNul()) {
				return this.etiquette.point.toString()+" ( " + this.filsGauche.toString()+ " , null ) " ; 
			}
			else {
				return this.etiquette.point.toString()+" ( " +this.filsGauche.toString()+ " , " + this.filsDroit.toString() + " ) " ; 
			}
		}
	} */
		
	boolean estaGauche(Point point, int indice) { // cette fonction permet de déterminer si le point en entrée est  dans le fils gauche ou droit de l'arbre initial (this). A chaque noeud est associé ou bien l'hyperplan (x0...,xdim-1), ou bien l'hyperplan (x1...xdim)
				
		for (int i=0 ; i<this.dim ; i++) {
			if (i!=indice) {
				if(this.racine.point.distanceAxe(point, i)<0) { // alors on n'est pas dans le demi hyperplan généré par les (x0,.xindice-1, xindice+1,..., xdim)
					return false ;
				}
			}
		}
		return true ; //on est dans le demi hyperplan généré par les (x0,..., xdim-1)
		
	}
			
	void addPointAux(Point point, int i) { //ajoute un point à l'arbre	
		
		if (this.racine==null) { // l'arbre est vide
			this.racine = new Noeud(point) ; 
			this.dim = point.dim ; 
			
			if(i==this.dim){
				this.racine.indice = 1 ;
			}
			else {
				this.racine.indice=i ; 
			}
		}
		
		else {
			if(this.estaGauche(point, this.racine.indice)) {
				this.filsGauche.addPointAux(point, i+1) ; 
			}
			else {
				this.filsDroit.addPointAux(point, i+1) ; 
			}			
		}
	}
	
	void addPoint(Point point){
		addPointAux(point, 1) ; 
	}
		
}
