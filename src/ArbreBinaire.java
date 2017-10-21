import java.util.ArrayList ; 

public class ArbreBinaire {

/*	private class Noeud {
		
		protected Noeud filsGauche, filsDroit; 
		Point point ;
		int dim ; 
	
		Noeud(Point point,int dim) { // Création d'une sous-classe Noeud, nécessitant un point caractérisant le noeud et la dimension de ce point
			this.filsGauche = null ; // on initialise les fils aux fils nuls
			this.filsDroit = null ; 
			this.point = point ;
		}
		
		
	} */
	
	boolean planHorizontal ; // renvoie vrai si l'hyperplan associé au noeud est horizontal (même en dimension supérieure à 3, on n'utilise que deux plans pour déterminer fils gauche et fils droit)
	ArbreBinaire filsGauche ; 
	ArbreBinaire filsDroit ; 
	Noeud etiquette ; 
	
	ArbreBinaire(Noeud _etiquette, ArbreBinaire _filsG, ArbreBinaire _filsD) {
		this.etiquette = _etiquette ; 
		this.filsGauche = _filsG ; 
		this.filsDroit = _filsD ; 
		this.planHorizontal = true ; // on crée le noeud avec un hyperplan associé, soit (x0,x1, ... xdim-1), soit (x1,..., xdim)
	}
	
	
	public String toString() {
		
		if(this.filsGauche==null & this.filsDroit ==null) {
			return this.etiquette.point.toString() ;
		}
			
		if(this.filsGauche==null) {
			return this.etiquette.point.toString()+" ( null , " + this.filsDroit.etiquette.point.toString() + " ) " ; 
		}
		
		if(this.filsDroit==null) {
			return this.etiquette.point.toString()+" ( " + this.filsGauche.etiquette.point.toString()+ " , null ) " ; 
		}
		else {
			return this.etiquette.point.toString()+" ( " +this.filsGauche.etiquette.point.toString()+ " , " + this.filsDroit.etiquette.point.toString() + " ) " ; 
			
		}
		
	} 
	
	boolean estaGauche(Point point) { // cette fonction permet de déterminer si le point en entrée est à dans le fils gauche ou droit du noeud/arbre initial (this)
		if (this.planHorizontal) {
			for (int i=0 ; i<this.etiquette.dim ; i++) {
				if(this.etiquette.point.distanceAxe(point, i)<0) { // alors on n'est pas dans le demi hyperplan généré par les (x0,..., xdim-1)
					return false ; 
				}
			}
			return true ; 
		}
		
		else {
			for (int i=1 ; i<this.etiquette.dim ; i++) {
				if(this.etiquette.point.distanceAxe(point, i)<0) { // alors on n'est pas dans le demi hyperplan généré par les (x0,..., xdim-1)
					return false ; 
				}
			}
			return true ; 
			
		}
	}
			
	void addPoint(Point point) { //ajoute un point à l'arbre
		if (this.estaGauche(point)) {
			if (this.filsGauche==null) { // dans ce cas, on insère le point au niveau du fils gauche
				this.filsGauche = new ArbreBinaire(new Noeud(point, this.etiquette.dim), null, null);  
			}
			else { // appel récursif
				filsGauche.addPoint(point) ; 
			}
		}
		else {
			if (this.filsDroit ==null) { // dans ce cas, on insère le point au niveau du fils droit
				this.filsDroit = new ArbreBinaire(new Noeud(point, this.etiquette.dim), null, null);  
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

