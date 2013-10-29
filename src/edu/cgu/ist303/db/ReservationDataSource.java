package edu.cgu.ist303.db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataSource {
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "reservation";
	private static final String RESERVATION_ID = "Reservation_Id";
	private static final String ROOM_ID = "Room_Id";
	private static final String RESERVATION_DATE = "Reservation_Date";
	private static final String CUSTOMER_ID = " Customer_Id";
	private static final String SERVICE_CATEGORY_ID = "Service_Category_Id";
	private static final String START_DATE = "Start_Date";
	private static final String END_DATE = "End_Date";
	private static final String[] allColumns = {RESERVATION_ID, ROOM_ID, RESERVATION_DATE,
		CUSTOMER_ID, SERVICE_CATEGORY_ID, START_DATE, END_DATE};
	
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public ReservationDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	
	public Reservation createReservation(Reservation r)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + " (" + ROOM_ID + ", " + RESERVATION_DATE + ", " + CUSTOMER_ID + ", " + SERVICE_CATEGORY_ID + ", "+
						START_DATE + ", " + END_DATE +")" +
							 " VALUES ('" + r.getFk_room_id() + "', '" + r.getReservation_Date() + "', '" + r.getFk_customer_id() + "', '" + 
							r.getFk_service_category_id() + "', '" + r.getStart_Date()+ "', '" + r.getEnd_Date() + "');" ; 
				
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				rs = stmt.getGeneratedKeys();
				rs.next();
				r.setId(rs.getInt(1));
				
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
           
                            
            return r;
    }
	
	public Reservation createReservationNoService(Reservation r)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + " (" + ROOM_ID + ", " + RESERVATION_DATE + ", " + CUSTOMER_ID  + ", "+
						START_DATE + ", " + END_DATE +")" +
							 " VALUES ('" + r.getFk_room_id() + "', '" + r.getReservation_Date() + "', '" + r.getFk_customer_id()  + "', '" + r.getStart_Date()+ "', '" + r.getEnd_Date() + "');" ; 
				
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				rs = stmt.getGeneratedKeys();
				rs.next();
				r.setId(rs.getInt(1));
				
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
           
                            
            return r;
    }
	
	 public void deleteReservation(Reservation r) 
	 {
         
         try {
				
        	 	int id = r.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + RESERVATION_ID + " = " + id + ";" ; 
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
	 public String getRoomNameById(int room_id){
		 String room_name = new String();
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT Room_Name FROM room  WHERE Room_Id = " + room_id + ";"; 
				
				rs = stmt.executeQuery(sql);
				rs.next();	
				room_name = rs.getString("Room_Name");
				
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
		 return room_name;
	 }
	public List<Integer> getAvailableRooms(int room_type_id, String date_from, String date_to){
		List<Integer> availableRoomsList = new ArrayList<Integer>();
		try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT room_id FROM room r WHERE room_type_id = " + room_type_id +
						" AND room_id NOT IN (SELECT room_id FROM reservation re WHERE r.room_id = re.room_id " + 
				" AND start_date <= '" + date_from + "' AND end_date >= '" + date_to +"');"; 
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next())
				{
					int room_id;
					room_id = rs.getInt(ROOM_ID);
					availableRoomsList.add(room_id);
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
		
		return availableRoomsList;
	}
	 
	 public List<Reservation> getAllReservation() {
         List<Reservation> reservationlList = new ArrayList<Reservation>();
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					Reservation r = new Reservation();
					
					r.setId(rs.getInt(RESERVATION_ID));
					r.setFk_room_id(rs.getInt(ROOM_ID));
					r.setReservation_Date(rs.getDate(RESERVATION_DATE));
					r.setFk_customer_id(rs.getInt(CUSTOMER_ID));
					r.setFk_service_category_id(rs.getInt(SERVICE_CATEGORY_ID));
					r.setStart_Date(rs.getDate(START_DATE));
					r.setEnd_Date(rs.getDate(END_DATE));
					reservationlList.add(r);
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

         return reservationlList;
       }
	
	
	 public String[] getAllColumns(){
		 return allColumns;
	 }
}
