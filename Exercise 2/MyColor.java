
import java.util.Random;
import javafx.scene.paint.Color;

//enum class MyColor define different color with own definition 
public enum MyColor{
	//list of color with its rgb value
	RED (255,0,0), 
	GREEN (0,255,0),
	BLUE (154,217,234),
	BLACK (0,0,0),
	WHITE (255,255,255),
	YELLOW (255,255,0),
	GRAY (128,128,128),
	PINK (255,192,203),
	//Transparent color will not display color but the stroke of the shape
	TRANSPARENT (255,255,255),
	//random color with default rgb value of 0,0,0
	RANDOM (0,0,0)
	;
	
	//red value of rgb value
	private final int r; 
	//green value of rgb value
	private final int g; 
	//blue value of rgb value
	private final int b;
	
	//constructor
	private MyColor(int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	//get the color from class Color using rgb value
	public Color getColor() { 
		//if the function called by member of RANDOM, then it will return a random color
		if(this == RANDOM) {
			//create a reference called random which point to the object of Random class
			Random random = new Random();
			return Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		}
        return Color.rgb(r,g,b);
    }
	

}