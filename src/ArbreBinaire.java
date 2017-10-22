import java.util.ArrayList ; 

public class ArbreBinaire {

	private class Noeud {
		
		protected Noeud filsGauche, filsDroit; 
		Point point ;
		int dim ; 
	
		Noeud(Point point,int dim) { // Création d'une sous-classe Noeud, nécessitant un point caractérisant le noeud et la dimension de ce point
			this.filsGauche = null ; // on initialise les fils aux fils nuls
			this.filsDroit = null ; 
			this.point = point ;
			this.dim = dim ; 
		}
		
		
	} 
	
	boolean planHorizontal ; // renvoie vrai si l'hyperplan associé au noeud est horizontal (même en dimension supérieure à 3, on n'utilise que deux plans pour déterminer fils gauche et fils droit)
	ArbreBinaire filsGauche ; 
	ArbreBinaire filsDroit ; 
	Noeud etiquette ; 
	
	ArbreBinaire(Noeud _etiquette) {
		this.etiquette = _etiquette ; 
		this.planHorizontal = true ; // on crée le noeud avec un hyperplan associé, soit (x0,x1, ... xdim-1), soit (x1,..., xdim)

		if (_etiquette!=null){ // dans ce cas elle possède des fils, éventuellement égaux à null
			this.filsGauche = new ArbreBinaire(_etiquette.filsGauche) ;
			this.filsDroit = new ArbreBinaire(_etiquette.filsDroit) ;

		}
		else{ // dans ce cas, le noeud n'a pas de fils

			this.filsGauche = null ; 
			this.filsDroit = null ; 
		} 
		
	}

	public boolean equals(ArbreBinaire arbre) {
		return (this.etiquette==arbre.etiquette) ;
	}
	
	public boolean estNul() {
		return(this.etiquette==null) ; 
	}
	
	public String toString() {
		
		if((this.filsGauche.estNul()) & (this.filsDroit.estNul())) { // initialisation : on est au niveau d'une feuille
			return this.etiquette.point.toString() ;
		}
			
		else {
			if(this.filsGauche.estNul()) {
				return this.etiquette.point.toString()+" ( null , " + this.filsDroit./*etiquette.point.*/toString() + " ) " ; 
			}
		
			if(this.filsDroit.estNul()) {
				return this.etiquette.point.toString()+" ( " + this.filsGauche./*etiquette.point.*/toString()+ " , null ) " ; 
			}
			else {
				return this.etiquette.point.toString()+" ( " +this.filsGauche./*etiquette.point.*/toString()+ " , " + this.filsDroit./*etiquette.point.*/toString() + " ) " ; 
			}
		}
	} 
	

	void setPlan() { // cette fonction fait en sorte qu'on alterne les hyperplans associés aux points, on l'utilise avec true en entrée
		
		if (!this.filsGauche.estNul()) {
			this.filsGauche.planHorizontal = !this.planHorizontal ; 
			this.filsGauche.setPlan() ; 
		}
		if (!this.filsDroit.estNul()) {
			this.filsDroit.planHorizontal = !this.planHorizontal ; 
			this.filsDroit.setPlan() ; 
		}
	}
	
	boolean estaGauche(Point point) { // cette fonction permet de déterminer si le point en entrée est  dans le fils gauche ou droit de l'arbre initial (this). A chaque noeud est associé ou bien l'hyperplan (x0...,xdim-1), ou bien l'hyperplan (x1...xdim)
				
		if (this.planHorizontal) { //parite

			for (int i=0 ; i<this.etiquette.dim-1 ; i++) {
				if(this.etiquette.point.distanceAxe(point, i)<0) { // alors on n'est pas dans le demi hyperplan généré par les (x0,..., xdim-1)
					return false ; 
				}
			}
			return true ; //on est dans le demi hyperplan généré par les (x0,..., xdim-1)
		}
		
		else {
			for (int i=1 ; i<this.etiquette.dim ; i++) {
				if(this.etiquette.point.distanceAxe(point, i)<0) { // alors on n'est pas dans le demi hyperplan généré par les (x1,..., xdim)
					return false ; 
				}
			}
			return true ; 
			
		}
	}
			
	void addPoint(Point point) { //ajoute un point à l'arbre	
		
			if (this.estaGauche(point)) {
		
				if (this.filsGauche.estNul()) { // dans ce cas, on insère le point au niveau du fils gauche 
					this.etiquette.filsGauche = new Noeud(point, this.etiquette.dim);  
					this.filsGauche = new ArbreBinaire(this.etiquette.filsGauche);  
				}
				else { // appel récursif
					filsGauche.addPoint(point) ; 
				}
			}
			else {
				if (this.filsDroit.estNul()) { // dans ce cas, on insère le point au niveau du fils droit
					this.etiquette.filsDroit = new Noeud(point, this.etiquette.dim) ; 
					this.filsDroit = new ArbreBinaire(this.etiquette.filsDroit);  
				}
				else { // appel récursif 
					filsDroit.addPoint(point) ; 
				}
			}
	}
	
	/*
	Noeud getParent(Noeud noeud) {
		
		if (noeud.point =
		
	} */
	
	void removePoint(Point point) { // enlève un point à l'arbre
	}
	
	//Noeud getNearestNeighbor(Noeud noeud) { // trouve le noeud le plus proche du noeud en entrée
		
	//	return null ;
	//}


	
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

