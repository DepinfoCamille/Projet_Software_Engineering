
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
			
				if(this/*.racine*/.filsGauche.racine==null) {
					return this.racine.point.toString()+" ( null , " + this.filsDroit.toString() + " ) " ;
				}
			
				if(this/*.racine*/.filsDroit.racine==null) {
					return this.racine.point.toString()+" ( " + this.filsGauche.toString()+ " ,  null ) " ;
				}
		
				else {
					return this.racine.point.toString()+" ( " +this.filsGauche.toString()+ " , " + this.filsDroit.toString() + " ) " ; 
				}
			}
		}
	} 
		
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
			
	void addPointAux(Point point, int i) { //ajoute un point à l'arbre, cette fonction auxiliaire permet de gardr l'indice i correspondant à l'hyperplan du père en mémoire
		
		if (this.racine==null) { // l'emplacement est libre, on peut donc créer une feuille noeud
			System.out.println("Création de feuille") ; 
			
			this.dim = point.dim ; 

	
			this.racine = new Noeud(point) ; // initialisation du nouveau noeud et de ses fils
			System.out.println(this.racine.point) ; 

			System.out.println(point) ; 
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
		
		else {
			if(this.estaGauche(point, this.racine.indice)) {
				System.out.println("on est à gauche") ; 

				this.filsGauche.addPointAux(point, i+1) ; 
			}
			else {
				System.out.println("on est à droite") ; 

				this.filsDroit.addPointAux(point, i+1) ; 
			}			
		}
	}
	
	void addPoint(Point point){ // c'est la véritable fonction addPoint, qui est la fonction addPointAux initialisée à 1 (i.e la racine de l'arbre a un hyperplan d'indice 1)
		addPointAux(point, 1) ; 
	}
		
	Noeud getParentAux(Noeud noeud, Noeud pere) { // fonction auxiliaire qui regarde si le noeud correspondant est bien égal au père en entrée
		
		if(this.racine ==pere){
			return this.racine ; 
		}
		
		else {
			if(estaGauche(noeud.point, this.racine.indice)){ 
				return getParentAux(noeud,this.filsGauche.racine);  
			}
			else { 
				return getParentAux(noeud,this.filsDroit.racine);  
			}
		}		
	}
	
	Noeud getParent(Noeud noeud) { // véritable getParent
		if(estaGauche(noeud.point, this.racine.indice)){ 
			return getParentAux(noeud,this.filsGauche.racine);  
		}
		else { 
			return getParentAux(noeud,this.filsDroit.racine);  
		}
	}
	
	void removePoint(Point point) {
		
		
		
	}
}
