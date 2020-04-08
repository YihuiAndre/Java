

public interface MyPoint {
	//return the coordinate of the point
	double[] getPoint();
	//set up the coordinate of the point
	void setPoint(double x, double y);
	//move the coordinate of the point by deltaX and deltaY
	void moveTo(double deltaX, double deltaY);
	//return the distance between two coordinates
	static double distanceTo(double[] p1, double[] p2) {
		return Math.sqrt(Math.pow(p2[0]-p1[0],2)+Math.pow(p2[1]-p1[1],2));
	}
}

