package edu.cgu.ist303.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDataSource {

	
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "personnel";
	private static final String PERSONNEL_ID = "Personnel_Id";
	private static final String FIRST_NAME = "First_Name";
	private static final String LAST_NAME = "Last_Name";
	private static final String USERNAME = "User_Name";
	private static final String PASSWORD = "Password";
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public PersonnelDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	
	public Personnel createPersonnel(Personnel p)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "("+ FIRST_NAME + ", " + LAST_NAME + ", " + USERNAME + ", "+ PASSWORD +")" +
							 "VALUES ('" + p.getFirstName() + "', '" + p.getLastName() + "', '" + p.getUsername() + "', '" + p.getPassword() + "');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				rs = stmt.getGeneratedKeys();
				rs.next();
				p.setId(rs.getInt(1));
				
			   } catch (SQLException ex) {
				   System.out.println("SQLException: " + ex.getMessage());
				   System.out.println("SQLState: " + ex.getSQLState());
				   System.out.println("VendorError: " + ex.getErrorCode());
			   }finally {
				   if (stmt != null) {
				        try {
				            stmt.close();
				        } catch (SQLException sqlEx) { } // ignore

				        stmt = null;
				    }
			   }
           
                            
            return p;
    }
	
	 public void deletePersonnel(Personnel p) 
	 {
         
         try {
				
        	 	int id = p.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + PERSONNEL_ID + " = " + id + ";" ; 
				stmt.executeUpdate(sql);
				
			   } catch (SQLException ex) {
				   System.out.println("SQLException: " + ex.getMessage());
				   System.out.println("SQLState: " + ex.getSQLState());
				   System.out.println("VendorError: " + ex.getErrorCode());
			   }finally {
				   if (stmt != null) {
				        try {
				            stmt.close();
				        } catch (SQLException sqlEx) { } // ignore

				        stmt = null;
				    }
			   }
         
         
     }
	
	 public String getPasswordByUsername(String username){
		 String password = new String();
		 try {
				stmt = mySqlconn.conn.createStatement();
				
				String sql = "SELECT " + PASSWORD +
							" FROM "+ TABLE_NAME + " WHERE " + USERNAME + " = '" + username + "';" ; 
				rs = stmt.executeQuery(sql);
				rs.next();
				password = rs.getString(PASSWORD);
			   } catch (SQLException ex) {
				   System.out.println("SQLException: " + ex.getMessage());
				   System.out.println("SQLState: " + ex.getSQLState());
				   System.out.println("VendorError: " + ex.getErrorCode());
			   }finally {
				   if (rs != null) {
				        try {
				            rs.close();
				        } catch (SQLException sqlEx) { } // ignore

				        rs = null;
				    }
				   
				   if (stmt != null) {
				        try {
				            stmt.close();
				        } catch (SQLException sqlEx) { } // ignore

				        stmt = null;
				    }
			   }
     

   
 
      return password;
	 }
	 public List<Personnel> getAllPersonnel() {
         List<Personnel> personnelList = new ArrayList<Personnel>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					Personnel p = new Personnel();
					p.setId(rs.getInt(PERSONNEL_ID));
					p.setFirstName(rs.getString(FIRST_NAME));
					p.setLastName(rs.getString(LAST_NAME));
					p.setUsername(rs.getString(USERNAME));
					p.setPassword(rs.getString(PASSWORD));
					
					personnelList.add(p);
				}
			   } catch (SQLException ex) {
				   System.out.println("SQLException: " + ex.getMessage());
				   System.out.println("SQLState: " + ex.getSQLState());
				   System.out.println("VendorError: " + ex.getErrorCode());
			   }finally {
				   if (rs != null) {
				        try {
				            rs.close();
				        } catch (SQLException sqlEx) { } // ignore

				        rs = null;
				    }
				   
				   if (stmt != null) {
				        try {
				            stmt.close();
				        } catch (SQLException sqlEx) { } // ignore

				        stmt = null;
				    }
			   }
        

      
    
         return personnelList;
       }

	
}