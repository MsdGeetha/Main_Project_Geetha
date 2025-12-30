package data_driven_testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class Reading_Data_From_Database_Test_4 {

	public static void main(String[] args) throws SQLException {
		Driver driver = new Driver();
		 DriverManager.registerDriver(driver);
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/tp30", "root", "root");
		 Statement state = con.createStatement();
		 String query = "update emp set location = 'Mysore' where name='Anil';";
		 int result = state.executeUpdate(query);
		 System.out.println(result);
		 con.close();
		 
		

	}

}
