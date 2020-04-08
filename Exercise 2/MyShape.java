
import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape implements MyShapePosition{
	//store the coordinate of the point into array
	private double[] point = new double[2];
	//record the color using the enum class MyColor
	private MyColor c; 
	
	//constructor which initialize x, y and color
	public MyShape(double x, double y, MyColor c){
		//store the first element of the array as x coordinate
		point[0] = x;
		//store the second element of the array as y coordinate
		point[1] = y;
		this.c = c;
	}
	
	//return the string of the location of x and y and the color of the shape
	public String toString(){
		return "The location of X: " + point[0] + "\nThe location of Y: " + point[1] + "\nThe color is: " + c.toString();
	}
	
	//getter and setter method that retrieve and set the color
	public MyColor getColor() {
		return c;
	}
	
	public void setColor(MyColor c) {
		this.c = c;
	}
	
	//return the x and y coordinate of the point
	@Override
	public double[] getPoint() {
		return point;
	}

	//set the x and y coordinate of the point
	@Override
	public void setPoint(double x, double y) {
		point[0] = x;
		point[1] = y;
	}	
	
	//this method is intended for override by the subclasses
	public abstract void draw(GraphicsContext gc);

	//move the x and y coordinate of the point by delta x and delta y
	@Override
	public void moveTo(double deltaX, double deltaY) {
		point[0] = point[0] + deltaX;
		point[1] = point[1] + deltaY;
	}	
	
	//Overridden by subclass
	@Override
	public MyRectangle getMyBoundingBox() {
		return null;
	}
	
	//getting bounding box of two shapes and return true if overlap.
	@Override
	public boolean doOverlap(MyShape p) {
		MyRectangle origin = this.getMyBoundingBox();
		return origin.doOverlap(p);
	}

	
	
	
}
