package edu.cgu.ist303.functions;


import java.util.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


public class ReservationB extends JFrame{
        int Room_Type_Id;
        Date check_in_date;
        Date check_out_date;
        
    public static void main(String[] args) {
                // TODO Auto-generated method stub
                
	ReservationB reserv  = new ReservationB();
                reserv.addReserveRoom();
                
                
        }        
        
        
        JLabel  lab_reservation_date, labCheck_in_date, labCheck_out_date, labRoomTypeId, labFreeRoomId, labCustomer, labService;
        JTextField text_reservation_date, textCheck_in_date, textCheck_out_date;
        JPanel pnlReservationDate, pnlRoomType, pnlCheck_in_date, pnlCheck_out_date, pnlFreeRooms, pnlCustomer, pnlservice;
        JButton btnSubmit;
        //JComboBox comboList; 
        

         // JDBC driver name and database URL
           static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
           static final String DB_URL = "jdbc:mysql://localhost/MIYE";

           //  Database credentials
           static final String USER = "root";
           static final String PASS = "Ist303@cgu";
           
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                JComboBox<String> comboRoomType = new JComboBox<String>();
                HashMap<String, Integer> roomTypeMap = new HashMap<String, Integer>();
      
                JComboBox<String> comboFreeRooms = new JComboBox<String>();
                HashMap<String, Integer> freeRoomMap = new HashMap<String, Integer>();
                
                //JComboBox<String> comboRoom = new JComboBox<String>();
                JComboBox<String> comboCustomer = new JComboBox<String>();
                JComboBox<String> comboService = new JComboBox<String>();
        //HashMap<String, Integer> roomMap = new HashMap<String, Integer>();
        HashMap<String, Integer> customerMap = new HashMap<String, Integer>();
        HashMap<String, Integer> serviceMap = new HashMap<String, Integer>();
        
                public void getRoomTypeId(){
                        try {
                                String getRoomsQuery = "Select Room_Type_Id, Room_Type, Capacity FROM Room_Type";
                                Class.forName("com.mysql.jdbc.Driver");
                        
                                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                                stmt = conn.createStatement();
                        
                                rs = stmt.executeQuery(getRoomsQuery);
                                while (rs.next())
                                        {
                                        int room_id = rs.getInt("Room_Type_Id");
                    String room_type = rs.getString("Room_Type");
                    roomTypeMap.put(room_type, room_id);
                                        comboRoomType.addItem(room_type);
                                        }
                        
                                        }catch (Exception e) {
                                                JOptionPane.showMessageDialog(labRoomTypeId, e);
                                        } finally {
                                                try{
                                        rs.close();
                                        conn.close();
                                                }
                                                catch(Exception e){
                                                }
                
                                        }
                        
                }
                
                public void getCustomerId(){
                        try {
                                String getCustomerQuery = "Select Customer_Id, concat(First_Name,' ', Last_Name) AS customer FROM Customer";
                                Class.forName("com.mysql.jdbc.Driver");
                        
                                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                                stmt = conn.createStatement();
                        
                                rs = stmt.executeQuery(getCustomerQuery);
                                while (rs.next())
                                        {
                                        int customer_id = rs.getInt("Customer_Id");
                                        String customer_name = rs.getString("customer");  
                                        
                                        comboCustomer.addItem(customer_name);
                                        customerMap.put(customer_name, customer_id);
                                        
                                        }
                        
                                        }catch (Exception e) {
                                                JOptionPane.showMessageDialog(labCustomer, e);
                                        } finally {
                                                try{
                                        rs.close();
                                        conn.close();
                                                }
                                                catch(Exception e){
                                                }
                
                                        }
                        
                }
                public void getServiceId(){
                        try {
                                String getCustomerQuery = "Select Service_Category_Id, Category FROM Service_Category";
                                Class.forName("com.mysql.jdbc.Driver");
                        
                                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                                stmt = conn.createStatement();
                        
                                rs = stmt.executeQuery(getCustomerQuery);
                                while (rs.next())
                                        {
                                        int service_id = rs.getInt("Service_Category_Id");
                                        String service = rs.getString("Category");
                                        comboService.addItem(service);
                                        serviceMap.put(service, service_id);
                                        }
                        
                                        }catch (Exception e) {
                                                JOptionPane.showMessageDialog(labService, e);
                                        } finally {
                                                try{
                                        rs.close();
                                        conn.close();
                                                }
                                                catch(Exception e){
                                                }
                
                                        }
                        
                }
        public void addReserveRoom()
                {
                        Container cp  = this.getContentPane(); 
                        cp.setLayout(new GridLayout(0,1));
                        
                        btnSubmit = new JButton("Submit");
                
                        
                        //Create labels
                        lab_reservation_date = new JLabel("Reservation Date");
                        labRoomTypeId = new JLabel("Room Type");
                        labCheck_in_date = new JLabel("Check In Date");
                        labCheck_out_date = new JLabel("Check Out Date");
                        labFreeRoomId = new JLabel("Free Room");
                        labCustomer = new JLabel("Customer");
                        labService = new JLabel("Service");
                        
                        text_reservation_date = new JTextField(20);
                        textCheck_in_date = new JTextField(20); 
                        textCheck_out_date = new JTextField(20); 
                        
                        
                                
                        //Declare panel
                        pnlReservationDate = new JPanel();
                        pnlRoomType = new JPanel();
                        pnlCheck_in_date = new JPanel(); 
                        pnlCheck_out_date = new JPanel();
                        pnlFreeRooms = new JPanel();
                        pnlCustomer = new JPanel(); 
                        JPanel pnlService = new JPanel(); 
                        
                        //Connect panel with contents
                        pnlReservationDate.add(lab_reservation_date);
                        pnlReservationDate.add(text_reservation_date);
                        pnlCustomer.add(labCustomer); 
                        pnlCustomer.add(comboCustomer); 
                        getCustomerId();
                        pnlService.add(labService); 
                        pnlService.add(comboService);
                        getServiceId();
                        
                        pnlCheck_in_date.add(labCheck_in_date); 
                        pnlCheck_in_date.add(textCheck_in_date); 
                        pnlCheck_out_date.add(labCheck_out_date); 
                        pnlCheck_out_date.add(textCheck_out_date);
                        pnlRoomType.add(labRoomTypeId);
                        pnlRoomType.add(comboRoomType);
                        getRoomTypeId();
                        comboRoomType.addActionListener(new GetFreeRooms());
                        
                        pnlFreeRooms.add(labFreeRoomId);
                        pnlFreeRooms.add(comboFreeRooms);
                        //getFreeRoom();
                        //Add 
                        cp.add(pnlReservationDate);
                        cp.add(pnlCheck_in_date);
                        cp.add(pnlCheck_out_date);
                        cp.add(pnlRoomType);
                        cp.add(pnlFreeRooms);
                        cp.add(pnlCustomer);
                        cp.add(pnlService);
                        


                        cp.add(btnSubmit);
                        
                        this.setSize(800, 600);
                        this.setResizable(false);
                        this.setVisible(true);
                        btnSubmit.addActionListener(new SaveReservation());
                        pack();
                }
        class GetFreeRooms implements ActionListener {
                

        public void actionPerformed(ActionEvent e)
                //public void getFreeRoom()
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                //JComboBox comboList = new JComboBox();
                
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt = conn.createStatement();

                        String sql_sel = "SELECT Room_Id, Room_Name " +
                                "FROM Room " +
                                "WHERE Room_Id = Room_Id " +
                                "AND Room_Type_Id =  " + roomTypeMap.get(comboRoomType.getSelectedItem()) + 
                                " AND Room_Id NOT IN " +
                                "(SELECT Room_Id " +
                                "FROM Reservation " +
                                "WHERE Check_In_Date <= Check_Out_Date " +
                                " AND Check_In_Date BETWEEN '" + textCheck_in_date.getText() + "' AND ' " + textCheck_out_date.getText() + " ' AND Check_Out_Date >= ' " + textCheck_in_date.getText() + " ')";

                        ResultSet rss = stmt.executeQuery(sql_sel);
                        
                        while(rss.next()){
                                int roomTypeId = rss.getInt("Room_Id");
                                String roomName = rss.getString("Room_Name");
                                freeRoomMap.put(roomName, roomTypeId);
                                comboFreeRooms.addItem(roomName);
                                
                                
                                
                                                
                        }
                        
                        stmt.close();
                conn.close();
                } catch (SQLException ex) { 

            // String describing the error
           System.out.println("SQLException: " + ex.getMessage()); 
            // Vendor specific error code 
            System.out.println("VendorError: " + ex.getErrorCode()); 
        } catch (ClassNotFoundException e1) { 

            // Executes if the driver can't be found 
            e1.printStackTrace(); 

        } 
                //Clear all textField
                
        
                //textCheck_in_date.setText(""); 
                //textCheck_out_date.setText(""); 
        
        }
        /**
         * @param args
         */
        }

        class SaveReservation implements ActionListener {
            public void actionPerformed(ActionEvent e){
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                //JComboBox comboList = new JComboBox();
                
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt = conn.createStatement();
                        
                        String sql = "INSERT INTO Reservation (Room_Id, Reservation_Date, Customer_Id, Service_Category_Id, Check_In_Date, Check_Out_Date) " +
                                      "VALUES ('" + freeRoomMap.get(comboFreeRooms.getSelectedItem()) + "', '" + 
                                                                      text_reservation_date.getText() + "', '" +
                                                                      customerMap.get(comboCustomer.getSelectedItem()) + "', '" +
                                                                      serviceMap.get(comboService.getSelectedItem()) + "', '" +
                                                                      //comboList.getSelectedItem() + "', '" +
                                                                      textCheck_in_date.getText() + "', '" +
                                                                      textCheck_out_date.getText() + "')";
                        
                        
                        stmt.executeUpdate(sql);
                        stmt.close();
                conn.close();
                } catch (SQLException ex) { 

            // String describing the error
           System.out.println("SQLException: " + ex.getMessage()); 
            // Vendor specific error code 
            System.out.println("VendorError: " + ex.getErrorCode()); 
        } catch (ClassNotFoundException e1) { 

            // Executes if the driver can't be found 
            e1.printStackTrace(); 

        } 
                //Clear all textField
                
                 
                //textCheck_in_date.setText(""); 
                //textCheck_in_date.setText(""); 
        
        }

            }
        }

