package edu.cgu.ist303.db;

import java.sql.Date;

public class Reservation {
	private int id;
	private int fk_room_id;
	private Date reservation_Date;
	private int fk_customer_id;
	private int fk_service_category_id;
	private Date start_Date;
	private Date end_Date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFk_room_id() {
		return fk_room_id;
	}
	public void setFk_room_id(int fk_room_id) {
		this.fk_room_id = fk_room_id;
	}
	public Date getReservation_Date() {
		return reservation_Date;
	}
	public void setReservation_Date(Date reservation_Date) {
		this.reservation_Date = reservation_Date;
	}
	public int getFk_customer_id() {
		return fk_customer_id;
	}
	public void setFk_customer_id(int fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
	}
	public int getFk_service_category_id() {
		return fk_service_category_id;
	}
	public void setFk_service_category_id(int fk_service_category_id) {
		this.fk_service_category_id = fk_service_category_id;
	}
	public Date getStart_Date() {
		return start_Date;
	}
	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}
	public Date getEnd_Date() {
		return end_Date;
	}
	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}
	
	
	
	
}
