package edu.cgu.ist303.db;

import java.util.ArrayList;
import java.util.List;

public class ReservationDSTest {

	public static void main(String[] args){
		
		ReservationDataSource rDS = new ReservationDataSource();
		//it's your check in date 
		String date_from = "2013-10-20";
		//it's your check out date
		String date_to = "2013-10-21";
		// use Integer type List to store available room(in fact store it's id)
		List<Integer> r = new ArrayList<Integer>();
		//3 is Quadrupple type room 
		r = rDS.getAvailableRooms(3, date_from, date_to);
		for(int i = 0; i < r.size(); i++){
			System.out.println(r.get(i));
			System.out.println("Date from "+ date_from + " to " + date_to);
			System.out.println("\"" + rDS.getRoomNameById(r.get(i)) + "\" is available.");
			
		}
		
	}

}
