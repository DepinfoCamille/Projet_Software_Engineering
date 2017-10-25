

public class RefColor extends Point4 {
			private int id;
			private Point3 p;

			RefColor(int x, int y, int z, int id) {
				v = new int[]{x, y, z}; 
				this.id = id;
			}

			RefColor(Point3 p, int id) {
				v = p.cloneValues(); 
				this.p=p;
				this.id = id;
			}
			
			Point3 getPoint(){return(p);}

			int getId() { return id; }
			
			@Override
			public Point4 zero() {
				return new RefColor(0,0,0,-1);
			}
		}
