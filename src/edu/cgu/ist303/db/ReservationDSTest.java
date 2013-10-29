package edu.cgu.ist303.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReservationDSTest {

	public static void main(String[] args) throws ParseException{
		
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
		Reservation reser = new Reservation();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlToday = new java.sql.Date(new java.util.Date().getTime());

		java.util.Date utilDateFrom = sdf.parse("2013-10-30");
		java.sql.Date sqlDateFrom = new java.sql.Date(utilDateFrom.getTime());
		java.util.Date utilDateTo = sdf.parse("2013-11-1");
		java.sql.Date sqlDateTo = new java.sql.Date(utilDateTo.getTime());
		reser.setFk_customer_id(2);
		reser.setFk_room_id(1);
		reser.setFk_service_category_id(1);
		reser.setReservation_Date(sqlToday);
		reser.setStart_Date(sqlDateFrom);
		reser.setEnd_Date(sqlDateTo);
		rDS.createReservation(reser);
	}

}
