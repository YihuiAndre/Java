import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class PieChart {

	//store all the event occurrence into hash map
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	//total students
	private int total = 0;
	
	//non-default constructor
	public PieChart(ArrayList<ArrayList<String>> table, int col) {
		this.total = table.size()-1;
		//iterate the column list
		for(int i = 1; i < table.size(); i++) {
			//increment the value base on the column that obtain from the input
			increment(map, table.get(i).get(col));
		}
	}
	
	//increase the value by one with the corresponding key
	public static<K> void increment(Map<K, Integer> map, K key) {
		//if the value of the key is null, initialize it to zero.
		map.putIfAbsent(key,0);
		//replace the old value with new value
		map.put(key, map.get(key) + 1);
	}
	
	//helper method to round the number with specific decimal point
	private static double roundTo(double number, int decimal) {
		return Math.round(number*Math.pow(10,decimal))/Math.pow(10,decimal);
	}
	
	//draw a pie chart that display the number of events
	public void draw(GraphicsContext gc) {
		//create a random class object
		Random random = new Random();
		//startAngle: the beginning angle of segment. angle: the central angle of segment
		double startAngle = 90.0, angle;
		//percent: one event probability
		double percent;
		//store the event's name and its probability
		String data;
		//x and y coordinate of legend
		int legendY = 200, legendX = 600;
		//iterate n keys in the hashmap
		for(String event : map.keySet()) {
			//set up the color of segment
			gc.setFill(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			//calculate the percentage of the event
			percent = (double) map.get(event)/this.total;
			//round the percentage into four decimal point
			percent = roundTo(percent, 4);
			//calculate the central angle of the segment
			angle = percent * 360;
			data = event + ", " + map.get(event) + " students";
			//draw the segment
			gc.fillArc(200, 200, 300, 300, startAngle, -angle, ArcType.ROUND);
			//draw a square that indicates the color of segments
			gc.fillRect(legendX, legendY, 30, 30);
			//set the color of the text
			gc.setFill(Color.BLACK);
			//draw the text
			gc.fillText(data, legendX+40, legendY+20);
			//change the beginning angle
			startAngle -= percent*360;
			//move the legend downward
			legendY += 30;
		}
	}
	
	
}
