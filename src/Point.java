
public abstract class Point {
	
	protected int coordonnees[];
	
	public int getCoord(int x) {
		return coordonnees[x];
	}
	
	public int distance(Point p) {
		
		int dist = 0;
		
		for (int i = 0 ; i < coordonnees.length ; i++) {
			
			dist = dist + (this.coordonnees[i] - p.coordonnees[i]) ^ 2;
			
		}
		
		return dist;
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
