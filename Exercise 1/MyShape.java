import javafx.scene.canvas.GraphicsContext;

public class MyShape {
	//record the x-axis of the shape 
	private double x;
	//record the y-axis of the shape
	private double y;
	//record the color using the enum class MyColor
	MyColor c; 
	
	//constructor which initilize x, y and color
	public MyShape(double x, double y, MyColor c){
		this.x = x;	
		this.y = y;
		this.c = c;
	}
	
	//return the string of the location of x and y and the color of the shape
	public String toString(){
		return "The location of X: " + x + "\nThe location of Y: " + y + "\nThe color is: " + c.toString();
	}
	
	//this method is intended for override in the subclasses
	public void draw(GraphicsContext gc){
	}
	
	//getter and setter method that reterive and set the color
	public MyColor getColor() {
		return c;
	}
	
	public void setColor(MyColor c) {
		this.c = c;
	}
	
	//getter and setter method that reterive and set the x-axis
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	//getter and setter method that reterive and set the y-axis
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
