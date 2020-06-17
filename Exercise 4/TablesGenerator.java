import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesGenerator {
	//create all the table
	public static void generateTable(Connection conn) throws SQLException {
		//To create a table in database, it will need to execute the following
		//DDL statement "CREATE TABLE [tableName] (keyName type, ....) and set
		//one of the key as primary key which is not Null and unique
		
		//My DDL statement of create a table Students
		String studentSql ="CREATE TABLE Students " +
				"(studentID INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
				"PRIMARY KEY (studentID), firstName varChar(255)," +
				"lastName varchar(255), email varChar(255)," +
				"sex ENUM('F', 'M'))";
		
		//My DDL statement of create a table Courses
		String coursesSql = "CREATE TABLE Courses " +
				"(courseID varChar(255) NOT NULL, " + 
				"PRIMARY KEY (courseID), courseTitle varChar(255), " +
				"department varChar(255))";
		
		//My DDL statement of create a table Classes. There are three primary keys
		//inside the table. This is called composite primary key 
		String classesSql = "CREATE TABLE Classes " +
							"(courseID varChar(255) NOT NULL, " +
							"studentID INT UNSIGNED NOT NULL, " +
							"section INT UNSIGNED NOT NULL, " +
							"PRIMARY KEY (courseID, studentID, section), " +
							"FOREIGN KEY(courseID) REFERENCES Courses(courseID), " +
							"FOREIGN KEY(studentID) REFERENCES Students(studentID), " +
							"year INT UNSIGNED, semester ENUM('Spring', 'Summer', 'Fall', 'Winter'), " +
							"GPA ENUM('A', 'B', 'C', 'D', 'F', 'W'))";
		
		PreparedStatement stmnt = null;
		//record all the name of table
		String[] tableName = {"Students", "Courses", "Classes"};
		//record all the DDL statement
		String[] sql = {studentSql, coursesSql, classesSql};
		
		//iterative three loops which create three tables: Students, Courses, Classes
		for(int i = 0; i < 3; i++) {
			//put the DDL statement into PrepareStatement object
			stmnt = conn.prepareStatement(sql[i]);
			//execute the DDL statement
			stmnt.execute();
			System.out.println("Create the table of " + tableName[i]);
		}
	}
	
	//put the students data into the Students table
	public static void insertStudentsInfo(Connection conn) throws SQLException {
		//To put the data into the table, it will need to execute the following SQL statement
		//"INSERT INTO [tableName](var1, var2, ....) VALUES(data1, data2, ...)"
		
		PreparedStatement stmnt = null;
		//store the SQL statement into PrepareStatement to put the student data into the Students table
		stmnt = conn.prepareStatement("INSERT INTO Students(studentID, firstName, lastName, email, sex)"
									+ "VALUES(123456789, 'Yihui', 'Wuchen', 'ywuchen@gmail.com', 'M'),"
									+ "(123456781, 'John', 'Wang', 'johnWan@gmail.com', 'M'),"
									+ "(234562356, 'Kenndy', 'Smith', 'kennsmith@gmail.com', 'F'),"
									+ "(234596324, 'Ahmad', 'Davis', 'ahmaddavis@gmail.com', 'M'),"
									+ "(435754775, 'Michelle', 'Wong', 'michwong@gmail.com', 'F'),"
									+ "(456789422, 'Miclle', 'Shu', 'micleshu@gmail.com', 'F'),"
									+ "(535786433, 'Icer', 'Hui', 'icerhui@gmail.com', 'M'),"
									+ "(453568436, 'Mike', 'Shu', 'mikeshu@gmail.com', 'M'), "
									+ "(584638459, 'Gorge', 'Michelle', 'gorgemichelle@gmail.com', 'M'), "
									+ "(516531546, 'Donald', 'Trump', 'trump@gmail.com', 'M'), "
									+ "(456238425, 'Rick', 'Novak', 'ricknovak@gmail.com', 'M'), "
									+ "(374339182, 'Susan', 'Connor', 'susanconnor@gmail.com', 'F'), "
									+ "(475929313, 'Margaret', 'Adelman', 'margaretadekman@gmail.com', 'F'), "
									+ "(234738546, 'Ronald', 'Barr', 'ronaldbarr@gmail.com', 'M'), "
									+ "(457238913, 'Marie', 'Broadbet', 'mariebroadbet@gmail.com', 'F'), "
									+ "(345979243, 'Roger', 'Lum', 'rogerlum@gmail.com', 'M'), "
									+ "(349579243, 'Marlene', 'Donahue', 'marlenedonahue@gmail.com', 'M'), "
									+ "(234987924, 'Jeff', 'Johnson', 'jeffjohnson@gmail.com', 'M'), "
									+ "(648429374, 'Melvin', 'Forbis', 'melvinforbis@gmail.com', 'M')");
		stmnt.execute();
	}
	
	//insert the courses data into Courses table
	public static void insertCoursesInfo(Connection conn) throws SQLException {
		PreparedStatement stmnt = null;
		//store the SQL statement into PrepareStatement to put the courses data into the Courses table
		stmnt = conn.prepareStatement("INSERT INTO Courses(courseID, courseTitle, department)"
									+ "VALUES('Art 10000', 'Intro Principles of Art', 'Art'),"
									+ "('Csc 22000', 'Algorithm', 'Computer Science'),"
									+ "('Csc 22100', 'Software Design Laboratory', 'Computer Science'),"
									+ "('Eco 10400', 'Intro Quant Econ', 'Economic'),"
									+ "('Math 30800', 'Bridge to Advanced Mathematics', 'Math'),"
									+ "('Csc 10300', 'Intro to Computer Science', 'Computer Science')");
		stmnt.execute();
	}
	
	//insert the classes data into Classes table
	public static void insertClassesInfo(Connection conn) throws SQLException {
		PreparedStatement stmnt = null;
		//store the SQL statement into PrepareStatement to put the Classes data into the Classes table
		stmnt = conn.prepareStatement("INSERT INTO Classes(courseID, studentID, section, year, semester, GPA)"
									+ "VALUES('Art 10000', 123456789, 49618, 2020, 'Spring', 'B'),"
									+ "('Csc 22000', 123456789, 42262, 2020, 'Spring', 'B'),"
									+ "('Csc 22100', 123456789, 42264, 2020, 'Spring', 'A'),"
									+ "('Math 30800', 234562356, 45466, 2019, 'Fall', 'C'),"
									+ "('Csc 22000', 234562356, 32400, 2018, 'Summer', 'A'),"
									+ "('Art 10000', 123456781, 33400, 2018, 'Winter', 'W'),"
									+ "('Math 30800', 234596324, 30000, 2018, 'Fall', 'F'),"
									+ "('Csc 22100', 123456781, 42264, 2020, 'Spring', 'A'),"
									+ "('Csc 22100', 234562356, 42264, 2020, 'Spring', 'B'),"
									+ "('Csc 22100', 234596324, 42264, 2020, 'Spring', 'C'),"
									+ "('Csc 22100', 123456789, 39999, 2019, 'Fall', 'F'),"
									+ "('Csc 22100', 435754775, 42264, 2020, 'Spring', 'D'),"
									+ "('Csc 22100', 456789422, 42264, 2020, 'Spring', 'D'),"
									+ "('Csc 22100', 535786433, 42264, 2020, 'Spring', 'F'),"
									+ "('Csc 22100', 453568436, 42264, 2020, 'Spring', 'F'),"
									+ "('Csc 22100', 584638459, 42264, 2020, 'Spring', 'D'),"
									+ "('Csc 22100', 516531546, 42264, 2020, 'Spring', 'C'),"
									+ "('Csc 22100', 456238425, 42264, 2020, 'Spring', 'A'),"
									+ "('Csc 22100', 374339182, 42264, 2020, 'Spring', 'C'),"
									+ "('Csc 22100', 475929313, 42264, 2020, 'Spring', 'B'),"
									+ "('Csc 22100', 234738546, 42264, 2020, 'Spring', 'D'),"
									+ "('Csc 22100', 457238913, 42264, 2020, 'Spring', 'F'),"
									+ "('Csc 22100', 345979243, 42264, 2020, 'Spring', 'A'),"
									+ "('Csc 22100', 349579243, 42264, 2020, 'Spring', 'A'),"
									+ "('Csc 22100', 234987924, 42264, 2020, 'Spring', 'B'),"
									+ "('Csc 22100', 648429374, 42264, 2020, 'Spring', 'D')");
		stmnt.execute();
	}
}
