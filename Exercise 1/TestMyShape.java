import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


public class TestMyShape extends Application {  
  public static void main(String[] args) {
	  //launch the program
	  Application.launch(args);
  }

  @Override
  public void start(Stage ps) {
	  //width and height of the canvas
	  int w = 850, h = 550;
	  //center point of the canvas
	  double centerX = w/2, centerY = h/2;
	  //this is the radius of the lasgest polygon
	  double radius = 200;
	  //title
	  ps.setTitle("My shape");
	  //set up the canvas with the width and height
	  Canvas canvas = new Canvas(w,h);
	  GraphicsContext gc = canvas.getGraphicsContext2D();
	  
	  //create a square box
	  MyShape[] square = new MyShape[4];
	  square[0] = new MyLine(0,0,w,0,MyColor.BLACK);
	  square[1] = new MyLine(0,h,w,h,MyColor.BLACK);
	  square[2] = new MyLine(0,0,0,h,MyColor.BLACK);
	  square[3] = new MyLine(w,0,w,h,MyColor.BLACK);
	  for(int i = 0; i < 4; i++) {
		  square[i].draw(gc);
	  }

	  //create 4 polygon and 2 circle
	  MyShape[] shape = new MyShape[6];
	  shape[0] = new MyPolygon(centerX,centerY,6,radius,MyColor.GRAY);
	  //create a polygon with no color but only stroke
	  shape[1] = new MyPolygon(centerX,centerY,6,radius,MyColor.TRANSPARENT);
	  //reduce the side of radius so that the shape can inscribe inside the another shape.
	  radius = radius*Math.cos(Math.toRadians(180/6));
	  shape[2] = new MyCircle(centerX,centerY,radius,MyColor.YELLOW);
	  shape[3] = new MyPolygon(centerX,centerY,6,radius,MyColor.GREEN);
	  radius = radius*Math.cos(Math.toRadians(180/6));
	  shape[4] = new MyCircle(centerX,centerY,radius,MyColor.PINK);
	  shape[5] = new MyPolygon(centerX,centerY,6,radius,MyColor.BLUE);
	  for(int i = 0; i < 6; i++) {
		  shape[i].draw(gc);
	  }
	  
	  //create two diagonal line
	  MyShape[] diagonal = new MyShape[2];
	  diagonal[0] = new MyLine(0,0,w,h,MyColor.BLACK);
	  diagonal[1] = new MyLine(w,0,0,h,MyColor.BLACK);
	  for(int i = 0; i < 2; i++) {
		  diagonal[i].draw(gc);
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
