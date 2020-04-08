import javafx.scene.canvas.GraphicsContext;


public class MyCircle extends MyShape {
	//radius of circle
	private double r; 
	
	//constructor that initializes the center point, radius and color of the circle
	public MyCircle(double x, double y, double r, MyColor c){
		//called the super class to initilize the center point and color
		super(x,y,c);
		this.r = r;
	}
	
	//calculate the area of the circle
	public double getArea(){
		return Math.PI * Math.pow(r, 2);
	}
	
	//calculate the perimeter of the circle
	public double getPerimeter(){
		return 2 * Math.PI * r;
	}
	
	//return the radius of the circle
	public double getRadius(){
		return r;
	}
	
	//return the string that contain the info of the circle
	public String toString(){
		return "Radius: " + getRadius() + "\nPerimeter: " + getPerimeter() + "\nArea: " + getArea();
	}
	
	//override method which used to draw the circle
	@Override
	public void draw(GraphicsContext gc){
		//set up the specific color to fill the polygon later
		gc.setFill(super.getColor().getColor());
		//if there is not color, then it only draw the stroke of circle
		if(super.getColor() == MyColor.TRANSPARENT){
			gc.strokeOval(super.getX()-r,super.getY()-r, 2*r, 2*r);
		}
		//else fill the circle with the color
		else{
			gc.fillOval(super.getX()-r,super.getY()-r, 2*r, 2*r);
		}
		
	}
}
