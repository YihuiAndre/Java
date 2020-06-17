import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Histogram extends Application{
	public static int table;
	public static void main(String[] args){
		//launch the program
		Histogram a = new Histogram();
		table = 10;
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
		//Create a text input dialog
		TextInputDialog input = new TextInputDialog();
		input.setTitle("Characters Frequency");
		input.setHeaderText("Enter number");
		Optional<String> result;
		//Create a error dialog to show the invalid input
		Alert invalidInput = new Alert(AlertType.ERROR);
		invalidInput.setHeaderText("Invalid input!!");
		while(true){
			result = input.showAndWait();
			//check if user enter result and the result only contain integer and smaller than 26
			if(result.isPresent() && (!result.get().matches("[0-9]+") || Integer.parseInt(result.get()) > 26)) {
				invalidInput.showAndWait();
			}
			else {
				//create a MyPieChart object, if there is no result provide by the user, then return zero in the result by default
				HistogramAlphaBet A = new MyPieChart("Alice in Wonderland.txt", Integer.parseInt(result.orElse("0")));
				//draw the image to the canvas
				A.draw(gc);
				break;
			}
		}
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

