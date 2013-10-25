package edu.cgu.ist303.db;

import java.util.ArrayList;
import java.util.List;

public class PersonnelDSTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonnelDataSource pDS = new PersonnelDataSource();
		List<Personnel> pList = new ArrayList<Personnel>(); 
		pList = pDS.getAllPersonnel();
		for(Personnel person:pList)
		{
			System.out.println("ID: " + person.getId() + " First Name: " + person.getFirstName() +
					" Last Name: " + person.getLastName() + " Username: " + person.getUsername() + 
					" Password: " + person.getPassword());
		}
		
		String usernameFromInput = new String("orange");
		String passwordFromInput = new String("308");
		String getPassword = new String();
		getPassword = pDS.getPasswordByUsername(usernameFromInput);
		if(getPassword.equals(passwordFromInput) == true)
			System.out.println("Login successful");
		
			
		
		
	}
}
