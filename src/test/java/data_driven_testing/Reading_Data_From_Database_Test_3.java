package data_driven_testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Reading_Data_From_Database_Test_3 {

	public static void main(String[] args) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/tp30", "root", "root");
		Statement state = con.createStatement();
		String query = "insert into emp values('Raju','Banglore',789456123);";
		int result = state.executeUpdate(query);
		System.out.println("result = "+result);
//		while(result.next()) {
//			System.out.println(result.getString(1));
//		}
		con.close();

	}

}
