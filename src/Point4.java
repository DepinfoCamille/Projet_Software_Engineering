public abstract class Point4 
{
	protected int v[]; 

	public int get(int i) {
		return v[i];
	}

	public void add(Point4 p) {
		for(int i=0;i<v.length; ++i) {
			v[i] += p.v[i];
		}
	}
	public void div(int d) {
		for(int i=0;i<v.length; ++i) {
			v[i] /= d;
		}
	}
	
	public int sqrDist(Point4 p) {
		int res = 0;
		for(int i=0;i<v.length; ++i) {
			res += (v[i]-p.v[i])*(v[i]-p.v[i]);
		}
		return res;
	}

	public boolean equals(Point4 p) {
		for(int i=0;i<v.length; ++i) {
			if(v[i]!=p.v[i]) return false;
		}
		return true;
	}
	
	int[] cloneValues() {
		return v.clone();		
	}
	
	// this method should be static but that's not possible in Java...
	public abstract Point4 zero();
}
