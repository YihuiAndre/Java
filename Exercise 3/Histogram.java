import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Histogram extends Application{
	public static void main(String[] args){
		//launch the program
		Application.launch(args);
	}
	
	@Override
	public void start(Stage ps) {
		//width and height of the canvas
		int w = 1000, h = 1000;
		//title
		ps.setTitle("Pie Chart");
		//set up the canvas with the width and height
		Canvas canvas = new Canvas(w,h);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//create a MyPieChart object
		HistogramAlphaBet A = new MyPieChart("Alice in Wonderland.txt", 5);
		//draw the image to the canvas
		A.draw(gc);
		//create a Pane class to manage the canvas
		Pane root = new Pane();
		//add canvas into the pane class
		root.getChildren().add(canvas);
		//create a Scene class to store the physical context of canvas
		Scene scene = new Scene(root);
		//set the scene to the stage
		ps.setScene(scene);
		//display the images on the window.
		ps.show();
	}
}

