import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;


public class MyPieChart extends HistogramAlphaBet{
	//store the number of events
	private int n;
	
	//non-default
	public MyPieChart(String fileName,int n){
		//call the super class constructor
		super(fileName);
		this.n = n;
	}
	
	//draw a pie chart that display the number of events
	@Override
	public void draw(GraphicsContext gc) {
		//create a random class object
		Random random = new Random();
		//index number: the number of events have been processed 
		int count = 0;
		//startAngle: the beginning angle of segment. angle: the central angle of segment
		double startAngle = 90.0, angle;
		//sumPercent: sum of all events' probability. percent: one event probability
		double sumPercent = 0.0, percent;
		//store the event's name and its probability
		String data;
		//iterate n keys in the hashmap
		for(Character alphabet : getMap().keySet()) {
			//set up the color of segment
			gc.setFill(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			//if the index number reach to the number of events, break the loop
			if(count == n) {
				break;
			}
			//calculate the percentage of the event
			percent = (double) getMap().get(alphabet)/getTotalAlphabet();
			//round the percentage into four decimal point
			percent = roundTo(percent, 4);
			//sum the probability of all the events
			sumPercent += percent;
			//calculate the central angle of the segment
			angle = percent * 360;
			data = alphabet + ", " + percent;
			//draw the segment
			gc.fillArc(200, 200, 500, 500, startAngle, -angle, ArcType.ROUND);
			//set the color of the text
			gc.setFill(Color.BLACK);
			//draw the text next to the segment
			gc.fillText(data, MyPieChart.computeLocX(450, 250, -startAngle+angle/2, data.length()),MyPieChart.computeLocY(450, 250, -startAngle+angle/2, data.length()), 2000);
			//change the beginning angle
			startAngle -= percent*360;
			count++;
		}
		//sum all the remaining events and draw the segment
		if(count != 26) {
			angle = (1-sumPercent) * 360;
			data = "All other letters, " + roundTo(1-sumPercent, 4);
			//draw the segment
			gc.fillArc(200, 200, 500, 500, startAngle, -angle, ArcType.ROUND);
			gc.setFill(Color.BLACK);
			//draw the text
			gc.fillText(data, MyPieChart.computeLocX(450, 250, -startAngle+angle/2, data.length()),MyPieChart.computeLocY(450, 250, -startAngle+angle/2, data.length()), 2000);
		}
	}
	//helper method to round the number with specific decimal point
	private static double roundTo(double number, int decimal) {
		return Math.round(number*Math.pow(10,decimal))/Math.pow(10,decimal);
	}
	//compute the x location of the text
	private static double computeLocX(double centerX, double radius, double angle, int lenOfString) {
		//calculate the x location of the middle point of the segment line
		double locX = centerX + radius*Math.cos(Math.toRadians(angle));
		//if the location that calculate above is on the left side of circle, move the text to left
		if(locX > centerX) {
			return locX + 5;
		}
		//otherwise, move the text to right
		return locX - 6*lenOfString - 5;
	}
	//compute the y location of the text
	private static double computeLocY(double centerY, double radius, double angle, int lenOfString) {
		//calculate the y location of the middle point of the segment line
		double locY = centerY + radius*Math.sin(Math.toRadians(angle));
		//if the location that calculate above is on the upper side of the circle, move the text up
		if(locY < centerY) {
			return locY - 7;
		}
		//otherwise, move the text down
		return locY + 2*lenOfString;
	}
}
