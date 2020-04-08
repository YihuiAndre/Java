
import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape {
	//semi major axis which represent as the half length of width of the oval
	private double a; 
	//semi minor axis which represent as the half length of height of the oval
	private double b;
	
	//constructor
	public MyOval(double x, double y, double a, double b, MyColor c) {
		super(x,y,c);
		this.a = a;
		this.b = b;
	}
	
	//getter method for the semi major axis
	public double getA() {
		return a;
	}
	
	//setter method for the semi major axis
	public void setA(double a) {
		this.a = a;
	}
	
	//getter method for the semi minor axis
	public double getB() {
		return b;
	}

	//setter method for the semi minor axis
	public void setB(double b) {
		this.b = b;
	}

	//return the perimeter of the oval
	public double getPerimeter() {
		return 2*Math.PI*Math.sqrt((a * a + b * b)/2);
	}

	//return the area of the oval
	public double getArea() {
		return Math.PI * a * b;
	}
	
	//return the string of info of the oval 
	public String toString() {
		return "Major axis: " + 2*a + "\nMinor axis" + 2*b + "\nPerimeter: " + getPerimeter() + "\nArea: " + getArea() + "\n";
	}

	//return the bounding box around the oval
	@Override
	public MyRectangle getMyBoundingBox() {
		return new MyRectangle(super.getPoint()[0], super.getPoint()[1], 2*a, 2*b, MyColor.TRANSPARENT);
	}
	
	//add the oval into GraphicsContext gc
	@Override
	public void draw(GraphicsContext gc) {
		//set up the specific color to fill the polygon later
		gc.setFill(super.getColor().getColor());
		//if there is not color, then it only draw the stroke of circle
		if(super.getColor() == MyColor.TRANSPARENT){
			gc.strokeOval(super.getPoint()[0]-a,super.getPoint()[1]-b, 2*a, 2*b);
		}
		//else fill the circle with the color
		else{
			gc.fillOval(super.getPoint()[0]-a,super.getPoint()[1]-b, 2*a, 2*b);
		}
	}
}
