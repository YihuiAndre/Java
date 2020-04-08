
import javafx.scene.canvas.GraphicsContext;

public class MyRectangle extends MyShape {
	//height of the rectangle
	private double h;
	//width of the rectangle
	private double w;
	
	//non-default constructor 
	public MyRectangle(double x, double y, double w, double h, MyColor c) {
		super(x,y,c);
		this.h = h;
		this.w = w;
	}
	
	//getter method for width
	public double getWidth() {
		return w;
	}
	
	//getter method for height
	public double getHeight() {
		return h;
	}
	
	//setter method for width
	public void setWidth(double w) {
		this.w = w;
	}
	
	//setter method for height
	public void setHeight(double h) {
		this.h = h;
	}
	
	//return the perimeter of the rectangle
	public double getPerimeter() {
		//if the actually perimeter is approximate close to .9999, then round up to 1
		if(2*w + 2*h - Math.round(2*w + 2*h) <= .0001) {
			return Math.round(2*w + 2*h);
		}
		return 2*w + 2*h;
	}
	//return the area of the rectangle
	public double getArea() {
		//if the actually area is approximate close to .9999, then round up to 1
		if(w*h - Math.round(w*h) <= .0001) {
			return Math.round(w*h);
		}
		return w*h;
	}
	
	//set up the corresponding perimeter of the height and the width 
	public void setPerimeter(double perimeter) {
		//percentage of changing from old perimeter to new perimeter
		double percentage = perimeter/getPerimeter();
		//change the height and width according to the percentage
		h = percentage*h;
		w = percentage*w;
	}
	
	//set up the corresponding area of the height and the width 	
	public void setArea(double area) {
		//percentage of changing from old area to new area
		double percentage = area/getArea();
		//apply those change to height and width
		h = Math.sqrt(percentage)*h;
		w = Math.sqrt(percentage)*w;
	}
	
	//return the info of the rectangle
	@Override
	public String toString(){
		return "Width: " + w + "\nHeight: " + h + "\nPerimeter: " + getPerimeter() +  "\nArea: " + getArea() + "\n";
	}
	
	//return the bounding box around the rectangle
	@Override
	public MyRectangle getMyBoundingBox() {
		return new MyRectangle(super.getPoint()[0], super.getPoint()[1], w, h, MyColor.TRANSPARENT);
	}
	
	//check to see if two two shapes are overlap or not
	@Override
	public boolean doOverlap(MyShape p) {
		//get the bounding box of the shape p
		MyRectangle other = p.getMyBoundingBox();
		//check one of the rectangle is above the top edge of another rectangle 
		if(this.getPoint()[1]+h/2 < other.getPoint()[1]-other.getHeight()/2 ||
				this.getPoint()[1]-h/2 > other.getPoint()[1]+other.getHeight()/2) {
			return false;
		}
		//check one of the rectangle is on the left side of the left edge o another rectangle
		else if(this.getPoint()[0]+w/2 < other.getPoint()[0]-other.getWidth()/2 ||
				this.getPoint()[0]-w/2 > other.getPoint()[0]+other.getWidth()/2) {
			return false;
		}
		//otherwise, return true as two rectangles are overlapping
		return true;
	}

	
	//add the rectangle to the GraphicsContext gc
	@Override
	public void draw(GraphicsContext gc) {
		//set up the color of rectangle
		gc.setFill(super.getColor().getColor());
		//if the color is transparent, it only add a rectangle with stroke
		if(super.getColor() == MyColor.TRANSPARENT) {
			gc.strokeRect(super.getPoint()[0]-w/2, super.getPoint()[1]-h/2, w, h);
		}
		//Otherwise, fill the color into the rectangle
		else {
			gc.fillRect(super.getPoint()[0]-w/2, super.getPoint()[1]-h/2, w, h);
		}
	}

}
