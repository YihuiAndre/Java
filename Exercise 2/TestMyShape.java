import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class TestMyShape extends Application {
	public static void main(String[] args) {
		// launch the program
		Application.launch(args);
	}

	@Override
	public void start(Stage ps) {
		// width and height of the canvas
		int w = 900, h = 600;
		// center point of the canvas
		double centerX = w / 2, centerY = h / 2;
		// height and width of the oval and rectangle
		double width = 750, height = 500;
		// title
		ps.setTitle("My shape");
		// set up the canvas with the width and height
		Canvas canvas = new Canvas(w, h);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// set up the width of the line
		gc.setLineWidth(6);

		// create 3 rectangle and 3 oval
		MyShape[] shape = new MyShape[6];
		shape[0] = new MyRectangle(centerX, centerY, width, height, MyColor.RANDOM);
		shape[1] = new MyOval(centerX, centerY, width / 2, height / 2, MyColor.RANDOM);
		// decrease the height and width proportionally
		height = height * Math.sin(Math.PI / 4);
		width = width * Math.cos(Math.PI / 4);
		shape[2] = new MyRectangle(centerX, centerY, width, height, MyColor.RANDOM);
		shape[3] = new MyOval(centerX, centerY, width / 2, height / 2, MyColor.RANDOM);
		height = height * Math.sin(Math.PI / 4);
		width = width * Math.cos(Math.PI / 4);
		shape[4] = new MyRectangle(centerX, centerY, width, height, MyColor.RANDOM);
		shape[5] = new MyOval(centerX, centerY, width / 2, height / 2, MyColor.RANDOM);
		for (int i = 0; i < 6; i++) {
			shape[i].draw(gc);
		}
		// create one diagonal line
		MyShape diagonal = new MyLine(0, 0, w, h, MyColor.BLACK);
		// create a square box
		MyShape square = diagonal.getMyBoundingBox();
		// draw the box on the canvas
		square.draw(gc);
		// draw the diagonal line
		diagonal.draw(gc);
		// create a Pane class to manage the canvas
		Pane root = new Pane();
		// add canvas into the pane class
		root.getChildren().add(canvas);
		// create a Scene class to store the physical context of canvas
		Scene scene = new Scene(root);
		// set the scene to the stage
		ps.setScene(scene);
		// display the images on the window.
		ps.show();

	}

}
