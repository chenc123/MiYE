package edu.cgu.ist303.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDataSource {

	private MySQLConnector mySqlconn;
	private static final String TABLE_NAME = "room_type";
	private static final String ROOM_TYPE_ID = "Room_Type_Id";
	private static final String ROOM_TYPE = "Room_Type";
	private static final String CAPACITY = "Capacity"; 
	

	private Statement stmt = null;
	private ResultSet rs = null;
	
	public RoomTypeDataSource() //constructor, link to DB and choose personnel table
	{
		mySqlconn = new MySQLConnector();
	}
	
	public void close()
    {
		mySqlconn.close();
    }
	
	public RoomType createRoom(RoomType rt)
    {
		 try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "INSERT INTO " + TABLE_NAME + "("+ ROOM_TYPE + ", " + CAPACITY + ")" +
							 "VALUES ('" + rt.getRoom_type() + "', '" + rt.getCapacity()  +"');" ; 
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // auto-generated keys made available for retrieval 
				rs = stmt.getGeneratedKeys();  //ResultSet object containing the auto-generated key(s)
				rs.next();
				rt.setId(rs.getInt(1)); // 
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
           
                            
            return rt;
    }
	
	 public void deleteRoom(RoomType rt) 
	 {
         
         try {
				
        	 	int id = rt.getId();
        	 	stmt = mySqlconn.conn.createStatement();
				String sql = "DELETE FROM" + TABLE_NAME + 
							 "WHERE " + ROOM_TYPE_ID + " = " + id + "" ; 
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
	
	 
	 public List<RoomType> getAllRoom() {
         List<RoomType> roomTypeList = new ArrayList<RoomType>();

         
         try {
				stmt = mySqlconn.conn.createStatement();
				String sql = "SELECT * " + 
							 "FROM "+ TABLE_NAME + ";" ; 
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					RoomType rt = new RoomType();
					rt.setId(rs.getInt(ROOM_TYPE_ID));
					rt.setRoom_type(rs.getString(ROOM_TYPE));
					rt.setCapacity(rs.getInt(CAPACITY));
					
					roomTypeList.add(rt);
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
        

         return roomTypeList;
       }
}
