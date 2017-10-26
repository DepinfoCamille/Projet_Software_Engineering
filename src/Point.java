public class Point {
	
	protected int coordonnees[], dim ; 
	
	Point(int coord[]) {
		this.coordonnees = coord ; 
		this.dim = this.coordonnees.length; 
	}
	
	public int getCoord(int x) {
		return this.coordonnees[x];

	}
		
	public float distance(Point p) {
		
		float dist = 0;
		
		for (int i = 0 ; i < this.dim ; i++) {
			
			dist = dist + (this.coordonnees[i] - p.coordonnees[i])*(this.coordonnees[i] - p.coordonnees[i]);
			
		}
		
		return dist;
	}
	
	float distanceAxe(Point point, int axe) { // renvoie la distance entre deux points selon l'axe en entrée
		return point.getCoord(axe)-this.getCoord(axe) ; 
	}
	
	public String toString() { // affiche un point 
		String chaine = "["+this.coordonnees[0] ; 
		for (int i =1 ; i<(this.coordonnees).length ; i++) {
			chaine += ","+Integer.toString(this.coordonnees[i]) ;
		}
		chaine+= "]" ;
		
		return chaine ;
	}

	public boolean egalite(Point p) {
		
		for(int i = 0 ; i < coordonnees.length ; i++) {
			
			if (this.coordonnees[i] != p.coordonnees[i]) {
				return false;
			}
			
		}
		
		return true;
	}
}

