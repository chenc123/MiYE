package edu.cgu.ist303.functions;
import javax.swing.*;

import edu.cgu.ist303.db.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;


public class Reservation extends JFrame implements ActionListener{
       
        
public static void main(String[] args) {
                // TODO Auto-generated method stub
                
                Reservation reserv  = new Reservation();
                reserv.addReservation();
                
           
                
        }        
     
        
        JLabel labRoom, labReserv_Date, labCustomer, labService, labCheck_in_date, labCheck_out_date, labCustomerId;
        JTextField textRoom,textReserv_Date, textCustomer, textService, textCheck_in_date, textCheck_out_date;
        JPanel pnlRoom, pnlReserv_Date, pnlCustomer, pnlService, pnlCheck_in_date, pnlCheck_out_date,pnlCustomerId;
        JButton btnSubmit;
        //JComboBox comboList; 
 
        JComboBox<String> comboRoom = new JComboBox<String>();
        JComboBox<String> comboCustomer = new JComboBox<String>();
        JComboBox<String> comboService = new JComboBox<String>();
        HashMap<String, Integer> roomMap = new HashMap<String, Integer>();
        HashMap<String, Integer> customerMap = new HashMap<String, Integer>();
        HashMap<String, Integer> serviceMap = new HashMap<String, Integer>();
               
         
        public void addReservation()
        {
                Container cp  = this.getContentPane(); 
                cp.setLayout(new GridLayout(0,1));
                
                btnSubmit = new JButton("Submit");
        
                
                //Create labels
                
                labRoom = new JLabel("Room");
                labReserv_Date = new JLabel("Reservation Date"); 
                labCustomer = new JLabel("Customer");
                labService = new JLabel("Service");
                labCheck_in_date = new JLabel("Check In Date");
                labCheck_out_date = new JLabel("Check Out Date");
                labCustomerId = new JLabel("Select Customer");
                                
                //Create text fields
                textRoom = new JTextField(20);
                textReserv_Date = new JTextField(20);
                textCustomer = new JTextField(20);
                textService = new JTextField(20); 
                textCheck_in_date = new JTextField(20); 
                textCheck_out_date = new JTextField(20); 
                
                
                        
                //Declare panel
                pnlRoom = new JPanel();
                pnlReserv_Date = new JPanel(); 
                pnlCustomer = new JPanel(); 
                pnlService = new JPanel(); 
                pnlCheck_in_date = new JPanel(); 
                pnlCheck_out_date = new JPanel();
                pnlCustomerId = new JPanel();
                
                //Connect panel with contents
                
                pnlRoom.add(labRoom);
                pnlRoom.add(comboRoom);
                
                RoomDataSource roomDS = new RoomDataSource();
                List<edu.cgu.ist303.db.Room> roomList = new ArrayList<edu.cgu.ist303.db.Room>(); 
                roomList = roomDS.getAllRoom();
                roomDS.close();
                for(edu.cgu.ist303.db.Room room:roomList){
                   roomMap.put(room.getRoom_name(), room.getId());
                   comboRoom.addItem(room.getRoom_name());
                }
                
                
                pnlReserv_Date.add(labReserv_Date);
                pnlReserv_Date.add(textReserv_Date);
                pnlCustomer.add(labCustomer); 
                pnlCustomer.add(comboCustomer); 
               
                CustomerDataSource cusDS = new CustomerDataSource();
                List<Customer> cusList = new ArrayList<Customer>(); 
                cusList = cusDS.getAllCustomer();
                cusDS.close();
                for(Customer customer:cusList)
                {
                  customerMap.put(customer.getFirstName(), customer.getId());
                  comboCustomer.addItem(customer.getFirstName());
                }
                
                pnlService.add(labService); 
                pnlService.add(comboService);
                ServiceCategoryDataSource scDS = new ServiceCategoryDataSource();
                List<ServiceCategory> scList = new ArrayList<ServiceCategory>(); 
                scList = scDS.getAllServiceCategory();
                scDS.close();
                for(ServiceCategory serviceCategory:scList)
                {
                  comboService.addItem(serviceCategory.getCategory());
                  serviceMap.put(serviceCategory.getCategory(), serviceCategory.getId());
        		}
                pnlCheck_in_date.add(labCheck_in_date); 
                pnlCheck_in_date.add(textCheck_in_date); 
                pnlCheck_out_date.add(labCheck_out_date); 
                pnlCheck_out_date.add(textCheck_out_date);
                pnlCustomerId.add(labCustomerId);
                
                

                //Add 
                cp.add(pnlRoom);
                cp.add(pnlReserv_Date);
                cp.add(pnlCustomer);
                cp.add(pnlService);
                cp.add(pnlCheck_in_date);
                cp.add(pnlCheck_out_date);
                cp.add(pnlCustomerId);

                cp.add(btnSubmit);
                pack();
                this.setTitle("Add a user");
                //this.setSize(800, 600);
                this.setResizable(false);
                this.setVisible(true);
                
                btnSubmit.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e)
        {
        	
        	edu.cgu.ist303.db.Reservation r = new edu.cgu.ist303.db.Reservation();
        	edu.cgu.ist303.db.ReservationDataSource rDS = new edu.cgu.ist303.db.ReservationDataSource();
           
            r.setFk_room_id(roomMap.get(comboRoom.getSelectedItem())) ;        
            r.setReservation_Date(java.sql.Date.valueOf(textReserv_Date.getText())) ;
            r.setFk_customer_id(customerMap.get(comboCustomer.getSelectedItem())) ;
            r.setFk_service_category_id(serviceMap.get(comboService.getSelectedItem()));
            r.setStart_Date(java.sql.Date.valueOf(textCheck_in_date.getText())) ;
            r.setEnd_Date(java.sql.Date.valueOf(textCheck_out_date.getText())) ;
        	
        	rDS.createReservation(r);
        	rDS.close();
                //Clear all textField 
                textRoom.setText(""); 
                textReserv_Date.setText(""); 
                textCustomer.setText(""); 
                textService.setText(""); 
                textCheck_in_date.setText(""); 
                textCheck_in_date.setText(""); 
        
        }
       
}





