package edu.cgu.ist303.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDataSource {

	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "room";
	private static final String ROOM_ID = "Room_Id";
	private static final String ROOM_NAME = "Room_Name";
	private static final String ROOM_TYPE_ID = "Room_Type_Id";
	private static final String[] allColumns = { ROOM_ID, ROOM_NAME, ROOM_TYPE_ID};

	private Statement stmt = null;
	private ResultSet rs = null;
	
	public RoomDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	public Room createRoom(Room r)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "("+ ROOM_NAME + ", " + ROOM_TYPE_ID + ")" +
							 "VALUES ('" + r.getRoom_name() + "', '" + r.getFk_room_type_id()  +"');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				r.setId(rs.getInt(1)); // 
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
	
	 public void deleteRoom(Room r) 
	 {
         
         try {
				
        	 	int id = r.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + ROOM_ID + " = " + id + "" ; 
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
	 public List<Room> getTypeRoom(int roomTypeId) {
         List<Room> roomList = new ArrayList<Room>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT " + ROOM_ID + ", " + ROOM_NAME + 
							 " FROM " + TABLE_NAME + " WHERE " + ROOM_TYPE_ID + " = " + roomTypeId + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					Room r = new Room();
					r.setId(rs.getInt(ROOM_ID));
					r.setRoom_name(rs.getString(ROOM_NAME));
					//r.setFk_room_type_id(rs.getInt(ROOM_TYPE_ID));
					
					roomList.add(r);
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
        

         return roomList;
       }
	 
	 public List<Room> getAllRoom() {
         List<Room> roomList = new ArrayList<Room>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					Room r = new Room();
					r.setId(rs.getInt(ROOM_ID));
					r.setRoom_name(rs.getString(ROOM_NAME));
					r.setFk_room_type_id(rs.getInt(ROOM_TYPE_ID));
					
					roomList.add(r);
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
        

         return roomList;
       }
	 public String[] getAllColumns(){
		 return allColumns;
	 }
	
}
