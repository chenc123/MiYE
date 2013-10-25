package edu.cgu.ist303.db;

public class Room {
	private int id;
	private String room_name;
	private int fk_room_type_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getFk_room_type_id() {
		return fk_room_type_id;
	}
	public void setFk_room_type_id(int fk_room_type_id) {
		this.fk_room_type_id = fk_room_type_id;
	}
	
}
