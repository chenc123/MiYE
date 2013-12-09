package edu.cgu.ist303.db;

public class RoomRate {
	private int id;
	private double peak_weekday;
	private double peak_weekend;
	private double off_season_weekday;
	private double off_season_weekend;
	private int fk_room_type_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPeak_weekday() {
		return peak_weekday;
	}
	public void setPeak_weekday(double peak_weekday) {
		this.peak_weekday = peak_weekday;
	}
	public double getPeak_weekend() {
		return peak_weekend;
	}
	public void setPeak_weekend(double peak_weekend) {
		this.peak_weekend = peak_weekend;
	}
	public double getOff_season_weekday() {
		return off_season_weekday;
	}
	public void setOff_season_weekday(double off_season_weekday) {
		this.off_season_weekday = off_season_weekday;
	}
	public double getOff_season_weekend() {
		return off_season_weekend;
	}
	public void setOff_season_weekend(double off_season_weekend) {
		this.off_season_weekend = off_season_weekend;
	}
	public int getFk_room_type_id() {
		return fk_room_type_id;
	}
	public void setFk_room_type_id(int fk_room_type_id) {
		this.fk_room_type_id = fk_room_type_id;
	}
	
	
	
}
