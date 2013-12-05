package edu.cgu.ist303.db;

public class ServiceAppointment {
	private int id;
	private java.sql.Date reservation_date_time;
	private java.sql.Date appointment_date_time;
	private int fk_service_type_category_id;
	private java.sql.Date service_date;
	private java.sql.Date service_start_time;
	private int fk_reservation_id;
	private int fk_payment_type_id;
	private String payment_field1;
	private String payment_field2;
	private String payment_field3;
	private String paid_in_full;
	private String cancelled;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.sql.Date getReservation_date_time() {
		return reservation_date_time;
	}
	public void setReservation_date_time(java.sql.Date reservation_date_time) {
		this.reservation_date_time = reservation_date_time;
	}
	public java.sql.Date getAppointment_date_time() {
		return appointment_date_time;
	}
	public void setAppointment_date_time(java.sql.Date appointment_date_time) {
		this.appointment_date_time = appointment_date_time;
	}
	public int getFk_service_type_category_id() {
		return fk_service_type_category_id;
	}
	public void setFk_service_type_category_id(int fk_service_type_category_id) {
		this.fk_service_type_category_id = fk_service_type_category_id;
	}
	public java.sql.Date getService_date() {
		return service_date;
	}
	public void setService_date(java.sql.Date service_date) {
		this.service_date = service_date;
	}
	public java.sql.Date getService_start_time() {
		return service_start_time;
	}
	public void setService_start_time(java.sql.Date service_start_time) {
		this.service_start_time = service_start_time;
	}
	public int getFk_reservation_id() {
		return fk_reservation_id;
	}
	public void setFk_reservation_id(int fk_reservation_id) {
		this.fk_reservation_id = fk_reservation_id;
	}
	public int getFk_payment_type_id() {
		return fk_payment_type_id;
	}
	public void setFk_payment_type_id(int fk_payment_type_id) {
		this.fk_payment_type_id = fk_payment_type_id;
	}
	public String getPayment_field1() {
		return payment_field1;
	}
	public void setPayment_field1(String payment_field1) {
		this.payment_field1 = payment_field1;
	}
	public String getPayment_field2() {
		return payment_field2;
	}
	public void setPayment_field2(String payment_field2) {
		this.payment_field2 = payment_field2;
	}
	public String getPayment_field3() {
		return payment_field3;
	}
	public void setPayment_field3(String payment_field3) {
		this.payment_field3 = payment_field3;
	}
	public String getPaid_in_full() {
		return paid_in_full;
	}
	public void setPaid_in_full(String paid_in_full) {
		this.paid_in_full = paid_in_full;
	}
	public String getCancelled() {
		return cancelled;
	}
	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}
	
	
	
	
	
	
}
