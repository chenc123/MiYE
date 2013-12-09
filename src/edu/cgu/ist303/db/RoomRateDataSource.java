package edu.cgu.ist303.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomRateDataSource {
	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "room_rate";
	private static final String ROOM_RATE_ID = "Room_Rate_Id";
	private static final String PEAK_WEEKDAY = "Peak_Weekday";
	private static final String PEAK_WEEKEND = "Peak_Weekend"; 
	private static final String OFF_SEASON_WEEKDAY = "Off_Season_Weekday";
	private static final String OFF_SEASON_WEEKEND = "Off_Season_Weekend";
	private static final String ROOM_TYPE_ID = "Room_Type_Id";

	private Statement stmt = null;
	private ResultSet rs = null;
	
	public RoomRateDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	public RoomRate createRoomRate(RoomRate rr)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "("+ PEAK_WEEKDAY + ", " + PEAK_WEEKEND + ", " + 
								OFF_SEASON_WEEKDAY + ", " + OFF_SEASON_WEEKDAY+ ", " + OFF_SEASON_WEEKEND + 
							  ", "+ ROOM_TYPE_ID +")" +
							 "VALUES ('" + rr.getPeak_weekday() + "', '" + rr.getPeak_weekend()  +
							  rr.getOff_season_weekday() + "', '" + rr.getOff_season_weekend()+ "', '" + 
							  rr.getFk_room_type_id() +"');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				rr.setId(rs.getInt(1)); // 
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
           
                            
            return rr;
    }
	public double room_total_cost(int room_id, java.sql.Date date_from, java.sql.Date date_to){
		double total_cost = 0;
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "select room_total_cost('" + date_from + "', '" + date_to + "', " + room_id + ");"; 
				
				rs = stmt.executeQuery(sql);
				rs.next();
				
				total_cost = rs.getDouble(1);
			
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
		 return total_cost;
		
	}
	public boolean updateRoomRate(RoomRate rr)
    {
		boolean success = false ; 
		try {
				
			 stmt = mySqlconn.conn.createStatement();
				String sql = "UPDATE " + TABLE_NAME + " SET "+ PEAK_WEEKDAY + " = '" + rr.getPeak_weekday() + "', " +
						PEAK_WEEKEND + " = '" + rr.getPeak_weekend() + "', " +
						OFF_SEASON_WEEKDAY + " = '" + rr.getOff_season_weekday() + "', " + 
						OFF_SEASON_WEEKEND + " = '" + rr.getOff_season_weekend() + "', " +
							" WHERE " + ROOM_TYPE_ID + " = " + rr.getFk_room_type_id() + ";";
				//System.out.println(sql);			 
				stmt.executeUpdate(sql); 
				success = true;
			   } catch (SQLException ex) {
				   System.out.println("SQLException: " + ex.getMessage());
				   System.out.println("SQLState: " + ex.getSQLState());
				   System.out.println("VendorError: " + ex.getErrorCode());
				   success = false;
			   }finally {
				   if (stmt != null) {
				        try {
				            stmt.close();
				        } catch (SQLException sqlEx) { } // ignore

				        stmt = null;
				    }
			   }
           
          return success;
    }
	 public void deleteRoom(RoomRate rr) 
	 {
         
         try {
				
        	 	int id = rr.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + ROOM_RATE_ID + " = " + id + "" ; 
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
	 
	 public List<RoomRate> getAllRoomRate() {
         List<RoomRate> roomRateList = new ArrayList<RoomRate>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					RoomRate rr = new RoomRate();
					rr.setId(rs.getInt(ROOM_RATE_ID));
					rr.setPeak_weekday(rs.getDouble(PEAK_WEEKDAY));
					rr.setPeak_weekend(rs.getDouble(PEAK_WEEKEND));
					rr.setOff_season_weekday(rs.getDouble(OFF_SEASON_WEEKDAY));
					rr.setOff_season_weekend(rs.getDouble(OFF_SEASON_WEEKEND));
					rr.setFk_room_type_id(rs.getInt(ROOM_TYPE_ID));
					
					roomRateList.add(rr);
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
        

         return roomRateList;
       }
	
}
