import java.lang.Math;

public class Point {
	
	
	protected int coordonnees[];
	int dim ; 
	
	Point() {}
	
	Point(int coord[]) {
		this.coordonnees = coord ; 
		this.dim = this.coordonnees.length; 
	}
	
	
	public int[] getCoord() {	
		int x,i;
		x=this.dim;
		int T[]= new int[x];
		for(i=0;i<dim;i++) {
			T[i]=this.coordonnees[i];
		}
		return T;
	}
	
	
	public int getCoord(int x) {
		return this.coordonnees[x];

	}  
	
	
<<<<<<< HEAD
	int distanceAxe(Point point, int axe) {
=======
	
	double distanceAxe(Point point, int axe) {
>>>>>>> master
		return point.getCoord(axe)-this.getCoord(axe) ; 
	}
	
	
	public double distance2(Point p) {
		int dist = 0,distance;
		int x=this.dim;
		for (int i = 0 ; i < x; i++) {
			dist = dist + (this.coordonnees[i] - p.coordonnees[i])*(this.coordonnees[i] - p.coordonnees[i]);
			}
		distance=(int) Math.sqrt(dist);
		return distance;
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





/*public String toString() { // affiche un point 
	String chaine = "["+this.coordonnees[0] ; 
	for (int i =1 ; i<(this.coordonnees).length ; i++) {
		chaine += ","+Integer.toString(this.coordonnees[i]) ;
	}
	chaine+= "]" ;
	
	return chaine ;
}*/