
import javafx.scene.canvas.GraphicsContext;

public class MyLine extends MyShape {
	private double[] endPoint = new double[2];
	
	//constructor that initialize the starting point, ending point and color of the line
	public MyLine(double x1, double y1, double x2, double y2, MyColor color){
		//called the superclass constructor to initialize the starting point and color
		super(x1,y1,color); 
		endPoint[0] = x2;
		endPoint[1] = y2;
	}
	
	public MyLine(double[] p1, double[] p2, MyColor color){
		super(p1[0],p1[1],color); 
		endPoint = p2;
	}
	
	
	//new
	public double[] getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(double x, double y) {
		endPoint[0] = x;
		endPoint[1] = y;
	}
	
	//use the equation to get the length of line
	public double getLength(){
		return MyPoint.distanceTo(super.getPoint(), endPoint);
	}
	
	//calculate the angle between the line and x-axis
	public double get_xAngle(){
		return Math.toDegrees(Math.atan((endPoint[1] - super.getPoint()[1])/(endPoint[0] - super.getPoint()[0])));
	}
	
	//return the String format that print out the info of line
	@Override
	public String toString(){
		return "The length is " + getLength() + " and the angle with the x-axis is " + get_xAngle();
	}

	//return a rectangle that can cover the entire segment
	@Override
	public MyRectangle getMyBoundingBox() {
		//calculate the changing of x coordinate and the changing of y coordinate
		double deltaX = endPoint[0] - super.getPoint()[0];
		double deltaY = endPoint[1] - super.getPoint()[1];
		//set the center point of rectangle as the middle point of the segment with width and height
		return new MyRectangle(super.getPoint()[0]+deltaX/2, super.getPoint()[1]+deltaY/2, 
				Math.abs(deltaX), Math.abs(deltaY), MyColor.TRANSPARENT);
	}
	
	//add a line to the gievn GraphicsContext and set up the color of the line
	@Override
	public void draw(GraphicsContext gc){
		//set up the color of the line
		gc.setStroke(super.getColor().getColor());
			//draw the line with the starting point and ending point
			gc.strokeLine(super.getPoint()[0], super.getPoint()[1], endPoint[0], endPoint[1]);
		}
	
}
