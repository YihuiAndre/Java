import javafx.scene.canvas.GraphicsContext;


public class MyPolygon extends MyShape {
	//number of side of the polygon
	private int N;
	//radius of the polygon
	private double r;
	
	//constructor that initialize the center point, number of side, radius and color of the plygon
	public MyPolygon(double x, double y, int N, double r, MyColor c){
		//called the super class constructor to initialize the center of x and y and the color
		super(x,y,c);
		this.N = N;
		this.r = r;
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
	
	//override method draw that draw the shape of polygon
	@Override
	public void draw(GraphicsContext gc){
		//set up the color of polygon
		gc.setFill(super.getColor().getColor());
		//list of x-axis vextex of polygon
		double[] x = new double[N]; 
		//list of y-axis vextex of polygon
		double[] y = new double[N]; 
		//calculate all the x and y location of the vetex of the polygon
		for (int i = 0; i < N; i++)
		{
		    x[i] = super.getX() + r * Math.sin(2 * Math.PI * i / N);
		    y[i] = super.getY() + r * Math.cos(2 * Math.PI * i / N);
		}
		//if the color is transparent, then it will only draw the stroke of polygon without color
		if(super.getColor() == MyColor.TRANSPARENT){
			gc.strokePolygon(x,y,N);
		}
		//else fill the polygon with the corresponding color
		else{
			gc.fillPolygon(x,y,N);
		}
	}
	
}



//