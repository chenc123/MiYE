package edu.cgu.ist303.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceAppointmentDataSource {
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "Service_Appointmene";
	private static final String SERVICE_APPOINTMENT_ID = "Service_Appointment_Id";
	private static final String RESERVATION_DATE_TIME = "Reservation_Date_Time";
	private static final String APPOINTMENT_DATE_TIME = "Appointment_Date_Time";
	private static final String SERVICE_TYPE_CATEGORY_ID = "Service_Type_Category_Id";
	private static final String SERVICE_DATE = "Service_Date";
	private static final String SERVICE_START_TIME = "Service_Start_Time";
	private static final String RESERVATION_ID = "Reservation_Id";
	private static final String PAYMENT_TYPE_ID = "Payment_type_Id";
	private static final String PAYMENT_FIELD1 = "Payment_Field1";
	private static final String PAYMENT_FIELD2 = "Payment_Field2";
	private static final String PAYMENT_FIELD3 = "Payment_Field3";
	private static final String PAID_IN_FULL = "Paid_In_Full";
	private static final String CANCELLED = "Cancelled";
	
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public ServiceAppointmentDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }

	public ServiceAppointment createServiceAppointment(ServiceAppointment sa)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "(" +  RESERVATION_DATE_TIME + ", " +
						 APPOINTMENT_DATE_TIME + ", " + SERVICE_TYPE_CATEGORY_ID + ", " +
						 SERVICE_DATE + ", " + SERVICE_START_TIME + ", " + RESERVATION_ID + ", " + 
						 PAYMENT_TYPE_ID + ", " + PAYMENT_FIELD1 + ", "+ PAYMENT_FIELD2 + ", " + 
						 PAYMENT_FIELD3 + ", " + PAID_IN_FULL + ", " + CANCELLED +")"  +
							 "VALUES ('" + sa.getReservation_date_time() + 
							 "', '" + sa.getAppointment_date_time() + "', '" + sa.getFk_service_type_category_id() +
							 "', '" + sa.getService_date() + "', '" + sa.getService_start_time() +
							 "', '" + sa.getFk_reservation_id() + "', '" + sa.getFk_payment_type_id() +
							 "', '" + sa.getPayment_field1() + "', '" + sa.getPayment_field2() +
							 "', '" + sa.getPayment_field3() + "', '" + sa.getPaid_in_full() +
							 "', '" + sa.getCancelled() + "');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				sa.setId(rs.getInt(1)); // 
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
           
                            
            return sa;
    }
	
	 public void deleteServiceAppointment(ServiceAppointment sa) 
	 {
         
         try {
				
        	 	int id = sa.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + SERVICE_APPOINTMENT_ID + " = " + id + "" ; 
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
	
	 
	 public List<ServiceAppointment> getAllServiceAppointment() {
         List<ServiceAppointment> saList = new ArrayList<ServiceAppointment>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					ServiceAppointment sa = new ServiceAppointment();
					sa.setId(rs.getInt(SERVICE_APPOINTMENT_ID));
					sa.setReservation_date_time(rs.getDate(RESERVATION_DATE_TIME));
					sa.setAppointment_date_time(rs.getDate(APPOINTMENT_DATE_TIME));
					sa.setFk_service_type_category_id(rs.getInt(SERVICE_TYPE_CATEGORY_ID));
					sa.setService_date(rs.getDate(SERVICE_DATE));
					sa.setService_start_time(rs.getDate(SERVICE_START_TIME));
					sa.setFk_reservation_id(rs.getInt(RESERVATION_ID));
					sa.setFk_payment_type_id(rs.getInt(PAYMENT_TYPE_ID));
					sa.setPayment_field1(rs.getString(PAYMENT_FIELD1));
					sa.setPayment_field2(rs.getString(PAYMENT_FIELD2));
					sa.setPayment_field3(rs.getString(PAYMENT_FIELD3));
					sa.setPaid_in_full(rs.getString(PAID_IN_FULL));
					sa.setCancelled(rs.getString(CANCELLED));
					saList.add(sa);
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
        

         return saList;
       }
}
