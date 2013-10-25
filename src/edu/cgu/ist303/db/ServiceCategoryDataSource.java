package edu.cgu.ist303.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategoryDataSource {
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "service_category";
	private static final String SERVICE_CATEGORY_ID = "Service_Category_Id";
	private static final String CATEGORY = "Category";
	private static final String UNIT_COST = "Unit_Cost";
	private static final String DURATION = "Duration";
	private static final String SERVICE_ID = "Service_Id";

	private Statement stmt = null;
	private ResultSet rs = null;
	
	public ServiceCategoryDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	public ServiceCategory createServiceCategory(ServiceCategory sc)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "(" + SERVICE_CATEGORY_ID + ", " + CATEGORY + 
						 ", " + UNIT_COST + ", " + DURATION + ", " + SERVICE_ID + ")" +
							 "VALUES ('" + sc.getId() + "', '" + sc.getCategory() + "', '" + sc.getUnit_cost() + 
							 "', '" + sc.getDuration() + "', '" + sc.getFk_service_id() + "');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				sc.setId(rs.getInt(1)); // 
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
           
                            
            return sc;
    }
	
	 public void deleteServiceCategory(ServiceCategory sc) 
	 {
         
         try {
				
        	 	int id = sc.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + SERVICE_CATEGORY_ID + " = " + id + "" ; 
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
	
	 
	 public List<ServiceCategory> getAllServiceCategory() {
         List<ServiceCategory> scList = new ArrayList<ServiceCategory>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					ServiceCategory sc = new ServiceCategory();
					sc.setId(rs.getInt(SERVICE_CATEGORY_ID));
					sc.setCategory(rs.getString(CATEGORY));
					sc.setUnit_cost(rs.getDouble(UNIT_COST));
					sc.setDuration(rs.getInt(DURATION));
					sc.setFk_service_id(rs.getInt(SERVICE_ID));
					scList.add(sc);
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
        

         return scList;
       }

}
