import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DatabaseConnector extends Application{
    //indicate the database name
    private static String url = "jdbc:mysql://localhost/Student";
    //indicate the user and corresponding of password
    private static String user = "";
    private static String password = "";
    private static Connection conn = null;
    
	public static void main (String args[]){
		try{
			// Loads the class object for the mysql driver into the DriverManager.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Attempt to establish a connection to the specified database via the DriverManager
			conn = DriverManager.getConnection(url, user, password);
			// Check the connection
			if (conn != null){
				System.out.println("We have connected to our database!");
				//check if there is a exist table of Students, Courses Classes in the database.
				//DDL Statement "DROP TABLE IF EXISTS [tableName]"
				PreparedStatement stmnt = conn.prepareStatement("DROP TABLE IF EXISTS Students, Courses, Classes");
				stmnt.execute();
				// Create all the tables
				TablesGenerator.generateTable(conn);
				
				//Insert the data into all the tables
				TablesGenerator.insertStudentsInfo(conn);
				System.out.println("Finish generate students info");
				
				TablesGenerator.insertCoursesInfo(conn);
				System.out.println("Finish generate courses info");
				
				TablesGenerator.insertClassesInfo(conn);
				System.out.println("Finish generate classes info");
				
				//launch the program
				Application.launch(args);
				
				// Close the connection
				conn.close();
			}
		} 
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();
		} 
		catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}
	
	//print the two dimensional table to the terminal
	public static void displayTable(ArrayList<ArrayList<String>> table) {
		//iterate the row first
		for(int y = 0; y < table.size(); y++) {
			System.out.print("(");
			//then, iterate the each column base on the current row number
			for(int x = 0; x < table.get(y).size()-1; x++) {
					System.out.printf("%s, ",table.get(y).get(x));
			}
			//print the last column value
			System.out.println(table.get(y).get(table.get(y).size()-1) + ")");
		}
		System.out.println();
	}
	
	
	//return the 2 dimensional table base on the user query
	public static ArrayList<ArrayList<String>> query(Connection conn, String sql)
			throws SQLException {
		//store SQL statement
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		//execute the SQL statement and execute it to ResulSet object
		ResultSet rs = preparedStatement.executeQuery();
		//create a new to dimensional array list to store the table value
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
		//get column data from ResultSet object 
		ResultSetMetaData rsmd = rs.getMetaData();
		
		//insert all the column label in the array list
		if(rsmd.getColumnCount() > 0) {
			table.add(new ArrayList<String>());
			//append all the column label to the array list
			for(int index = 1; index <= rsmd.getColumnCount(); index++) {
				table.get(0).add(rsmd.getColumnLabel(index));
			}
		}

		//iterate all the rows and insert the values as String to the array list
		while(rs.next()) {
			table.add(new ArrayList<String>());		
			for(int col = 1; col <= rsmd.getColumnCount(); col++) {
				table.get(rs.getRow()).add(rs.getString(col));
			}
		}
		return table;
	}

	@Override
	public void start(Stage ps) throws SQLException{
				//width and height of the canvas
				int w = 1000, h = 1000;
				//title
				ps.setTitle("Pie Chart");
				//set up the canvas with the width and height
				Canvas canvas = new Canvas(w,h);
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				System.out.println("=========================================");
				ArrayList<ArrayList<String>> table;
				//Obtain a new table by calling method query
				//SQL statement: "SELECT * FROM [tableName] WHERE [condition]"
				table = query(conn, "SELECT * "
						+ "FROM Classes "
						+ "WHERE courseID = 'Csc 22100' AND year = '2020'"
						+ "AND semester = 'Spring' ORDER BY GPA");
				System.out.println("There are " + (table.size()-1) + " students enrolled in CSc " + 
						"22100 in the Spring 2020 semester");
				//print the table
				displayTable(table);
				PieChart grade = new PieChart(table, 5);
				//draw the image to the canvas
				grade.draw(gc);
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