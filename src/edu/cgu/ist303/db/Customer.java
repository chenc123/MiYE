package edu.cgu.ist303.db;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String address_one;
	private String address_two;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress_one() {
		return address_one;
	}
	public void setAddress_one(String address_one) {
		this.address_one = address_one;
	}
	public String getAddress_two() {
		return address_two;
	}
	public void setAddress_two(String address_two) {
		this.address_two = address_two;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
