package edu.cgu.ist303.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeCategoryDataSource {
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "Service_Type_Category";
	private static final String SERVICE_TYPE_CATEGORY_ID = "Service_Type_Category_Id";
	private static final String CATEGORY = "Category";
	private static final String UNIT_COST = "Unit_Cost";
	private static final String DURATION = "Duration";
	private static final String SERVICE_ID = "Service_Id";

	private Statement stmt = null;
	private ResultSet rs = null;
	
	public ServiceTypeCategoryDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	public ServiceTypeCategory createServiceTypeCategory(ServiceTypeCategory stc)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "(" +  CATEGORY + 
						 ", " + UNIT_COST + ", " + DURATION + ", " + SERVICE_ID + ")" +
							 "VALUES ('" + stc.getCategory() + "', '" + stc.getUnit_cost() + 
							 "', '" + stc.getDuration() + "', '" + stc.getFk_service_type_id() + "');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				stc.setId(rs.getInt(1)); // 
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
           
                            
            return stc;
    }
	
	 public void deleteServiceTypeCategory(ServiceTypeCategory stc) 
	 {
         
         try {
				
        	 	int id = stc.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + SERVICE_TYPE_CATEGORY_ID + " = " + id + "" ; 
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
	
	 
	 public List<ServiceTypeCategory> getAllServiceTypeCategory() {
         List<ServiceTypeCategory> stcList = new ArrayList<ServiceTypeCategory>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					ServiceTypeCategory stc = new ServiceTypeCategory();
					stc.setId(rs.getInt(SERVICE_TYPE_CATEGORY_ID));
					stc.setCategory(rs.getString(CATEGORY));
					stc.setUnit_cost(rs.getDouble(UNIT_COST));
					stc.setDuration(rs.getInt(DURATION));
					stc.setFk_service_type_id(rs.getInt(SERVICE_ID));
					stcList.add(stc);
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
        

         return stcList;
       }
	 
	 public List<ServiceTypeCategory> getServiceTypeCategoryByServiceId(int service_id) {
         List<ServiceTypeCategory> stcList = new ArrayList<ServiceTypeCategory>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME +
							 " WHERE " + SERVICE_ID + " = " + service_id + ";" ; 
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					ServiceTypeCategory stc = new ServiceTypeCategory();
					stc.setId(rs.getInt(SERVICE_TYPE_CATEGORY_ID));
					stc.setCategory(rs.getString(CATEGORY));
					stc.setUnit_cost(rs.getDouble(UNIT_COST));
					stc.setDuration(rs.getInt(DURATION));
					stc.setFk_service_type_id(rs.getInt(SERVICE_ID));
					stcList.add(stc);
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
        

         return stcList;
       }
	 
	 public double getPriceByServiceCategoryTypeId(int service_type_category_id) {
		 double price=0;
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT " + UNIT_COST + " * " + DURATION +  
							 " FROM "+ TABLE_NAME +
							 " WHERE " +  SERVICE_TYPE_CATEGORY_ID + " = " + service_type_category_id + ";" ; 
				rs = stmt.executeQuery(sql);
				
				rs.next();
				price = rs.getDouble(1);
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
        

         return price;
       }
}
