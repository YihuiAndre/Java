
import javafx.scene.canvas.GraphicsContext;


public class MyPolygon extends MyShape {
	//number of side of the polygon
	private int N;
	//radius of the polygon
	private double r;
	//record all the x coordinate of the vertex
	private double[] vertexX;
	//record all the y coordinate of the vertex
	private double[] vertexY;
	
	//constructor that initialize the center point, number of side, radius, color and coordinate 
	//of vertex of the polygon
	public MyPolygon(double x, double y, int N, double r, MyColor c){
		//called the super class constructor to initialize the center of x and y and the color
		super(x,y,c);
		this.N = N;
		this.r = r;
		//list of x-axis vertex of polygon
		vertexX = new double[N]; 
		//list of y-axis vertex of polygon
		vertexY = new double[N]; 
		//calculate all the x and y location of the vertex of the polygon
		for (int i = 0; i < N; i++)
		{
			vertexX[i] = super.getPoint()[0] + r * Math.sin(2 * Math.PI * i / N);
			vertexY[i] = super.getPoint()[1] + r * Math.cos(2 * Math.PI * i / N);
		}
	}
	
	//calculate the area of the polygon
	public double getArea(){
		return N * Math.pow(r, 2) * Math.tan(Math.toRadians(180/N));
	}
	
	//calculate the perimeter
	public double getPerimeter(){
		return getSide()*N;
	}
	
	//calculate the interior angle of the polygon
	public double getAngle(){
		return (180*(N-2))/N;
	}
	
	//calculate the side length
	public double getSide(){
		return 2*r*Math.tan(Math.toRadians(180/N));
	}
	
	//override method toString from super class. return the string contain the info of polygon
	@Override
	public String toString(){
		return "Side Length: " + getSide() + "\nInterior Angle: " + getAngle() + "\nPerimeter: " + getPerimeter() + "\nArea: " + getArea();
	}

	//helper function that return the smaller value in the array
	private double smallerVal(double[] list) {
		//set the first element as smaller value
		double small = list[0];
		//iterate the list element
		for(int i = 1; i < list.length; i++) {
			if(small > list[i]) {
				small = list[i];
			}
		}
		return small;
	}
	
	//helper function that return the bigger value in the array
	private double biggerVal(double[] list) {
		//set the first element as bigger value
		double big = list[0];
		//iterrate rest of the list to look for the bigger value
		for(int i = 1; i < list.length; i++) {
			if(big < list[i]) {
				big = list[i];
			}
		}
		return big;
	}
	
	//return a new MyRectangle object that can cover the polygon object
	@Override
	public MyRectangle getMyBoundingBox() {
		//rightEdge is x coordinate of the right edge and leftEdge is the x coordinate of the left edge of the rectangle
		double rightEdge = biggerVal(vertexX), leftEdge = smallerVal(vertexX);
		//bottomEdge is y coordinate of the bottom and topEdge is the y coordinate of the top of the rectangle	
		double bottomEdge = biggerVal(vertexY), topEdge = smallerVal(vertexY);		
		//calculate the width and height of the rectangle
		double width = rightEdge - leftEdge;
		double height = bottomEdge - topEdge;
		//if the distance between the center point of rectangle and top edge of the rectangle is not equal to
		//the distance between the center point of rectangle and bottom edge of the rectangle, then it will 
		//need to shift center point of the rectangle
		if(this.getPoint()[1]-topEdge != bottomEdge - this.getPoint()[1]) {
			//calculate the shift point which the center point to shift
			double correction = height/2 - (this.getPoint()[1]-topEdge);
			return new MyRectangle(super.getPoint()[0], super.getPoint()[1]+correction, width, height, MyColor.TRANSPARENT);
		}
		//otherwise, return the normal rectangle
		return new MyRectangle(super.getPoint()[0], super.getPoint()[1], width, height, MyColor.TRANSPARENT);
	}
	
	
	//override method draw that draw the shape of polygon
	@Override
	public void draw(GraphicsContext gc){
		//set up the color of polygon
		gc.setFill(super.getColor().getColor());
		//if the color is transparent, then it will only draw the stroke of polygon without color
		if(super.getColor() == MyColor.TRANSPARENT){
			gc.strokePolygon(vertexX,vertexY,N);
		}
		//else fill the polygon with the corresponding color
		else{
			gc.fillPolygon(vertexX,vertexY,N);
		}
	}
	
	
}



//