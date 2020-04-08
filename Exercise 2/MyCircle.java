

public class MyCircle extends MyOval {
	
	//constructor that initializes the center point, radius and color of the circle
	public MyCircle(double x, double y, double r, MyColor c){
		//called the super class MyOval to initialize the center point and color
		super(x,y,r,r,c);
	}
	
	//calculate the area of the circle
	public double getArea(){
		return Math.PI * Math.pow(super.getA(), 2);
	}
	
	//calculate the perimeter of the circle
	public double getPerimeter(){
		return 2 * Math.PI * super.getA();
	}
	
	//return the radius of the circle
	public double getRadius(){
		return super.getA();
	}
	
	//return the string that contain the info of the circle
	public String toString(){
		return "Radius: " + getRadius() + "\nPerimeter: " + getPerimeter() + "\nArea: " + getArea();
	}
	
	//check two shapes are overlapping or not
	@Override
	public boolean doOverlap(MyShape p) {
		//if the p is not MyCircle object, then check if the bounding box of two shapes are overlapping
		if(!(p instanceof MyCircle)) {
			return this.getMyBoundingBox().doOverlap(p);
		}
		//downcasting
		MyCircle tmp2 = (MyCircle) p;
		//if the distance between the center point of two circle is smaller or equal 
		//to the sum of their radius, then the two circles will be overlapping
		return MyPoint.distanceTo(this.getPoint(), tmp2.getPoint()) <= 
				this.getRadius() + tmp2.getRadius();
	}
}
