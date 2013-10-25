package edu.cgu.ist303.db;

import java.sql.*;


public class MySQLConnector {

	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/MIYE";

	   //  Database credentials, if your setting is different, please modify them 
	   static final String USER = "root";
	   static final String PASS = "ist303@cgu";
	
	   public Connection conn = null;
	  
		
	   //constructor: connect to database we create
	   public MySQLConnector() 
	   {
		   try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				
			} catch (SQLException ex) { 
	            
	            // handle any errors
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	        } catch (ClassNotFoundException e1) { 
	            // Executes if the driver can't be found 
	            e1.printStackTrace(); 

	        } 
		   
	   }
	   // This method close the database connection 
	   public void close()
	   {
		   try {
			   conn.close();
		   } catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
	           System.out.println("SQLState: " + ex.getSQLState());
	           System.out.println("VendorError: " + ex.getErrorCode());
		   }
	   }
	   
	 
	   
	
}
