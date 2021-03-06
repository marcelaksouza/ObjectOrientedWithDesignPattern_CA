package ObjectOrientedWithDesignPattern_CA.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//db connect Mysql extends abstract Db Connect Factory class

public class DbConnectMySql implements DbConnectInterface {
	
	//db is initiated only once though the life time of the program
	private static DbConnectInterface instance = new DbConnectMySql();
	private Connection conn;
	String host = "52.50.23.197";
	String port = "3306";
	String dbName = "world";
	String user = "cctstudent";
	String password = "Pass1234!";
	
	//to make it more flexible I am passing this parameters to the constructor
	private DbConnectMySql() {
	}

	@Override
	//get connection from the constructor
	public Connection getConnection() {
		return conn;
	}
	
	//get the db instance created
	public static DbConnectInterface getInstance() {
		return instance;
	}

	//setter of the instance
	public static void setInstance(DbConnectInterface instance) {
		DbConnectMySql.instance = instance;
	}
	
	@Override
	// establish connection
	public void connect()  {
		if (conn != null) {
			return;
		} 
		try {
		        // Load the database driver
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        String dbServer = "jdbc:mysql://"+host+":"+port+"/"+dbName;
		        
		        // Get a connection to the database
		        conn = DriverManager.getConnection(dbServer, user, password);
		        System.out.println("Database connected");
	    } catch (SQLException se) {
	        System.out.println("SQL Exception:");

	        // Loop through the SQL Exceptions
	        while (se != null) {
	            System.out.println("State  : " + se.getSQLState());
	            System.out.println("Message: " + se.getMessage());
	            System.out.println("Error  : " + se.getErrorCode());
	            se = se.getNextException();
	        }
	    } catch (Exception e) {
	        System.out.println("connection error"+e);
	    	}
		}

	@Override
	//this method close the instance connection
	public void disconnect() {
		if(conn != null) {
			try {
				conn.close();
				System.out.println("Database disconnected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
		}
		conn = null;	
	}
}
