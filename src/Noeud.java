
public class Noeud {
		
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