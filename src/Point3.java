

	public class Point3 extends Point4 {
		Point3(int x, int y, int z) {
			v = new int[]{x, y, z}; 
		}
		
		@Override
		public Point4 zero() {
			return new Point3(0,0,0);
		}
}