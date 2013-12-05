package edu.cgu.ist303.db;

public class Service {
	private int id;
	private String service_name;
	private int service_capacity;
	private int fk_service_type_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public int getService_capacity() {
		return service_capacity;
	}
	public void setService_capacity(int service_capacity) {
		this.service_capacity = service_capacity;
	}
	public int getFk_service_type_id() {
		return fk_service_type_id;
	}
	public void setFk_service_type_id(int fk_service_type_id) {
		this.fk_service_type_id = fk_service_type_id;
	}
	
	
	
	
}
