package edu.cgu.ist303.db;

public class ServiceCategory {
	private int id;
	private String category;
	private double unit_cost;
	private int duration;
	private int fk_service_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(double unit_cost) {
		this.unit_cost = unit_cost;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getFk_service_id() {
		return fk_service_id;
	}
	public void setFk_service_id(int fk_service_id) {
		this.fk_service_id = fk_service_id;
	}
	
	
}
