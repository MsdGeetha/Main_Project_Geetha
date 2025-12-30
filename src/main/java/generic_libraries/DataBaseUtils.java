package generic_libraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtils {
	
	//global Variables
	Connection conn = null;
	ResultSet result = null;
	
	/**
	 * This Method which is used to connect to database
	 * @return
	 * @throws SQLException
	 */
	public Connection connectToDB() throws SQLException {
		//register the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//get connection for database
		conn = DriverManager.getConnection(IpathConstants.dbPath, IpathConstants.dbUsename, IpathConstants.dbPassword);
		return conn;
	}
	/**
	 * This method which is used to execute the query return the result in ResultSet
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		//create statement
		Statement statemant = conn.createStatement();
	
		//Execute query
		result = statemant.executeQuery(query);
		return result;
	}
	/**
	 * This method which is used to execute or update the table which the result will be in INT
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int updateQuery(String query) throws SQLException {
		//create statement
		Statement statemant = conn.createStatement();
	
		//Execute query
		int res = statemant.executeUpdate(query);
		return res;
	}
	/**
	 * this method which is used to close the Database connection
	 * @throws SQLException
	 */
	public void disconnectDB() throws SQLException {
		//closing db
		conn.close();
	}
	
	

}
