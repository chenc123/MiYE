package edu.cgu.ist303.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDataSource {
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "Service";
	private static final String SERVICE_ID = "Service_Id";
	private static final String SERVICE_NAME = "Service_Name";
	private static final String SERVICE_CAPACITY = "Service_Capacity";
	private static final String SERVICE_TYPE_ID = "Service_Type_Id";

	private Statement stmt = null;
	private ResultSet rs = null;
	
	public ServiceDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	
	public Service createService(Service s)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "(" +  SERVICE_NAME + 
						 ", " + SERVICE_CAPACITY + ", " + SERVICE_TYPE_ID  + ")" +
							 "VALUES ('" + s.getService_name() + 
							 "', '" + s.getService_capacity() + "', '" + s.getFk_service_type_id() + "');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				s.setId(rs.getInt(1)); // 
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
           
                            
            return s;
    }
	
	 public void deleteService(Service s) 
	 {
         
         try {
				
        	 	int id = s.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + SERVICE_TYPE_ID + " = " + id + "" ; 
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
	
	 
	 public List<Service> getAllService() {
         List<Service> sList = new ArrayList<Service>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					Service s = new Service();
					s.setId(rs.getInt(SERVICE_TYPE_ID));
					s.setService_name(rs.getString(SERVICE_NAME));
					s.setService_capacity(rs.getInt(SERVICE_CAPACITY));
					s.setFk_service_type_id(rs.getInt(SERVICE_TYPE_ID));
					sList.add(s);
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
        

         return sList;
       }
	
	 public List<Service> getServiceByServiceTypeId(int service_type_id) {
         List<Service> sList = new ArrayList<Service>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + 
							 " WHERE "+ SERVICE_TYPE_ID + " = " + service_type_id + ";" ; 
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					Service s = new Service();
					s.setId(rs.getInt(SERVICE_ID));
					s.setService_name(rs.getString(SERVICE_NAME));
					s.setService_capacity(rs.getInt(SERVICE_CAPACITY));
					s.setFk_service_type_id(rs.getInt(SERVICE_TYPE_ID));
					sList.add(s);
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
        

         return sList;
       }
}
