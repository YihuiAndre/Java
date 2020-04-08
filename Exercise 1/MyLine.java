import javafx.scene.canvas.GraphicsContext;

public class MyLine extends MyShape {
	//x-axis of end point
	private double x2;
	//y-axis of end point
	private double y2;
	
	//constructor that initilize the starting point, ending point and color of the line
	public MyLine(double x1, double y1, double x2, double y2, MyColor color){
		//called the superclass constructor to initialize the starting point and color
		super(x1,y1,color); 
		this.x2 = x2;
		this.y2 = y2;
	}
	
	//use the equation to get the length of line
	public double getLength(){
		return Math.sqrt(Math.pow(x2-super.getX(),2)+Math.pow(y2-super.getY(),2));
	}
	
	//calculate the angle between the line and x-axis
	public double get_xAngle(){
		return Math.toDegrees(Math.atan((y2 - super.getY())/(x2 - super.getX())));
	}
	
	//return the String format that print out the info of line
	@Override
	public String toString(){
		return "The length is " + getLength() + " and the angle with the x-axis is " + get_xAngle();
	}
	
	//add a line to the gievn GraphicsContext and set up the color of the line
	@Override
	public void draw(GraphicsContext gc){
		//set up the color of the line
		gc.setStroke(super.getColor().getColor());
		//set up the width of the line
		gc.setLineWidth(6);
		//draw the line with the starting point and ending point
		gc.strokeLine(super.getX(), super.getY(), x2, y2);
	}
	
	
}
