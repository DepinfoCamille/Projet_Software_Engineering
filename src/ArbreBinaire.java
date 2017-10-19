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
		}
	}
		
	void addPoint(Point point) { //ajoute un point à l'arbre
	}
	
	
	void removePoint(Point point) { // enlève un point à l'arbre
	}
	
	Noeud getNearestNeighbor(Noeud noeud) { // trouve le noeud le plus proche du noeud en entrée
		
		return null ;
	}

	
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