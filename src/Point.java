
public class Point {
	
	protected int coordonnees[];
	int dim ; 
	
	Point(int coord[], int dim) {
		this.coordonnees = coord ; 
		this.dim = dim ; 
	}
	
	public int getCoord(int x) {
		return this.coordonnees[x];

	}
		
	public int distance(Point p) {
		
		int dist = 0;
		
		for (int i = 0 ; i < this.coordonnees.length ; i++) {
			
			dist = dist + (this.coordonnees[i] - p.coordonnees[i]) ^ 2;
			
		}
		
		return dist;
	}
	
	int distanceAxe(Point point, int axe) { // renvoie la distance entre deux points selon l'axe en entrée
		return point.getCoord(axe)-this.getCoord(axe) ; 
	}
	
	public String toString() {
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
	


public class Point {
	
	protected int coordonnees[];
	int dim ; 
	
	Point(int coord[], int dim) {
		this.coordonnees = coord ; 
		this.dim = dim ; 
	}
	
	public int getCoord(int x) {
		return this.coordonnees[x];

	}
		
	public int distance(Point p) {
		
		int dist = 0;
		
		for (int i = 0 ; i < this.coordonnees.length ; i++) {
			
			dist = dist + (this.coordonnees[i] - p.coordonnees[i]) ^ 2;
			
		}
		
		return dist;
	}
	
	int distanceAxe(Point point, int axe) { // renvoie la distance entre deux points selon l'axe en entrée
		return point.getCoord(axe)-this.getCoord(axe) ; 
	}
	
	public String toString() {
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