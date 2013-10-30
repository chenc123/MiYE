package edu.cgu.ist303.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class CustomerDataSource {
	
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "customer";
	private static final String CUSTOMER_ID = "Customer_Id";
	private static final String FIRST_NAME = "First_Name";
	private static final String LAST_NAME = "Last_Name";
	private static final String ADDRESS_ONE = "Address_One";
	private static final String ADDRESS_TWO = "Address_Two";
	private static final String CITY = "City";
	private static final String STATE = "State";
	private static final String ZIPCODE = "Zip_code";
	private static final String PHONE_NUMBER = "Phone_Number";
	private static final String[] allColumns = {CUSTOMER_ID, FIRST_NAME, LAST_NAME,
		ADDRESS_ONE, ADDRESS_TWO, CITY, STATE, ZIPCODE, PHONE_NUMBER};
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public CustomerDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	
	public Customer createCustomer(Customer c)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "("+ FIRST_NAME + ", " + LAST_NAME + ", " + ADDRESS_ONE + ", "+ ADDRESS_TWO +
							  ", "+ CITY + ", "+ STATE + ", "+ ZIPCODE + ", "+ PHONE_NUMBER +")" +
							 "VALUES ('" + c.getFirstName() + "', '" + c.getLastName() + "', '" + c.getAddress_one() + "', '" + 
							  c.getAddress_two() + "', '" + c.getCity() + "', '" + c.getState() + "', '" + c.getZipCode() + "', '" +
							  c.getPhoneNumber() +"');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				c.setId(rs.getInt(1)); // 
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
           
                            
            return c;
    }
	
	 public void deleteCustomer(Customer c) 
	 {
         
         try {
				
        	 	int id = c.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + CUSTOMER_ID + " = " + id + "" ; 
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
	
	 
	 public List<Customer> getAllCustomer() {
         List<Customer> customerList = new ArrayList<Customer>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					Customer c = new Customer();
					c.setId(rs.getInt(CUSTOMER_ID));
					c.setFirstName(rs.getString(FIRST_NAME));
					c.setLastName(rs.getString(LAST_NAME));
					c.setAddress_one(rs.getString(ADDRESS_ONE));
					c.setAddress_two(rs.getString(ADDRESS_TWO));
					c.setCity(rs.getString(CITY));
					c.setState(rs.getString(STATE));
					c.setZipCode(rs.getString(ZIPCODE));
					c.setPhoneNumber(rs.getString(PHONE_NUMBER));
					customerList.add(c);
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
        

         return customerList;
       }

	 public Customer getCustomerByPhone(String phone) {
		 Customer c = new Customer();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME +
							 " WHERE " + PHONE_NUMBER + " = \"" + phone + "\";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					
					c.setId(rs.getInt(CUSTOMER_ID));
					c.setFirstName(rs.getString(FIRST_NAME));
					c.setLastName(rs.getString(LAST_NAME));
					c.setAddress_one(rs.getString(ADDRESS_ONE));
					c.setAddress_two(rs.getString(ADDRESS_TWO));
					c.setCity(rs.getString(CITY));
					c.setState(rs.getString(STATE));
					c.setZipCode(rs.getString(ZIPCODE));
					c.setPhoneNumber(rs.getString(PHONE_NUMBER));
					
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
        

         return c;
       }
	 public List<Customer> getCustomerByName(String name) {
		
		 List<Customer> customerList = new ArrayList<Customer>();
         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME +
							 " WHERE " + FIRST_NAME + " = \"" + name + "\"" + "OR \"" + LAST_NAME + "\" = " + name + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					Customer c = new Customer();
					c.setId(rs.getInt(CUSTOMER_ID));
					c.setFirstName(rs.getString(FIRST_NAME));
					c.setLastName(rs.getString(LAST_NAME));
					c.setAddress_one(rs.getString(ADDRESS_ONE));
					c.setAddress_two(rs.getString(ADDRESS_TWO));
					c.setCity(rs.getString(CITY));
					c.setState(rs.getString(STATE));
					c.setZipCode(rs.getString(ZIPCODE));
					c.setPhoneNumber(rs.getString(PHONE_NUMBER));
					customerList.add(c);
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
        

         return customerList;
       }

	 public String[] getAllColumns(){
		 return allColumns;
	 }
}
