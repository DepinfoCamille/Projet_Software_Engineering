
import java.lang.Math;



public class Point {
	
	protected int coordonnees[];
	int dim;
	
	
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
	
	
	
	int distanceAxe(Point point, int axe) {
		return point.getCoord(axe)-this.getCoord(axe) ; 
	}
	
	
	
	public int distance(Point p) {
		int dist = 0,distance;
		int x=this.dim;
		for (int i = 0 ; i < x; i++) {
			dist = dist + (this.coordonnees[i] - p.coordonnees[i]) ^ 2;
			}
		distance=(int) Math.sqrt(dist);
		return distance;
	}

	
	
	/*public boolean egalite(Point p) {
		
		for(int i = 0 ; i < coordonnees.length ; i++) {
			
			if (this.coordonnees[i] != p.coordonnees[i]) {
				return false;
			}
			
		}
		
		return true;
	}*/

	

}