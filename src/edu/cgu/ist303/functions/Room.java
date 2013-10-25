package edu.cgu.ist303.functions;
import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Room extends JFrame implements ActionListener{
        int room_Type_Id;
        String room_name;
        int room_id;
        
        
public static void main(String[] args) {
                // TODO Auto-generated method stub
                
                Room rm  = new Room();
                rm.addRoom();
                
        }        

        
        JLabel labRoomName, labRoomType;
        JTextField textRoomName;
        JPanel pnlRoom, pnlRoom_type;
        JButton btnSubmit;
        
        

         // JDBC driver name and database URL
           static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
           static final String DB_URL = "jdbc:mysql://localhost/MIYE";

           //  Database credentials
           static final String USER = "root";
           static final String PASS = "ist303@cgu";
           
        Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                JComboBox<String> comboRoomType = new JComboBox<String>();
                
        HashMap<String, Integer> roomType = new HashMap<String, Integer>();
       
                public void getRoomTypeId(){
                        try {
                                String getRoomTypeQuery = "SELECT Room_Type_Id, Room_Type FROM Room_Type";
                                Class.forName("com.mysql.jdbc.Driver");
                        
                                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                                stmt = conn.createStatement();
                        
                                rs = stmt.executeQuery(getRoomTypeQuery);
                                while (rs.next())
                                        {
                                        int room_type_id = rs.getInt("Room_Type_Id");
                    String room_type = rs.getString("Room_Type");
                    roomType.put(room_type, room_type_id);
                    comboRoomType.addItem(room_type);
                                        }
                        
                                        }catch (Exception e) {
                                                JOptionPane.showMessageDialog(labRoomType, e);
                                        } finally {
                                                try{
                                        rs.close();
                                        conn.close();
                                                }
                                                catch(Exception e){
                                                }
                
                                        }
                        
                }
                
                
        public void addRoom()
        {
                Container cp  = this.getContentPane(); 
                cp.setLayout(new GridLayout(0,1));
                
                btnSubmit = new JButton("Submit");
        
                
                //Create labels
                
                labRoomName = new JLabel("Room Name");
                labRoomType = new JLabel("Room Type"); 
                
                                
                //Create text fields
                textRoomName = new JTextField(20);
                        
                //Declare panel
                pnlRoom = new JPanel();
                pnlRoom = new JPanel(); 
                pnlRoom_type = new JPanel(); 
                pnlRoom_type = new JPanel(); 
                
                
                //Connect panel with contents
                
                pnlRoom.add(labRoomName);
                pnlRoom.add(textRoomName);
                pnlRoom_type.add(labRoomType);
                pnlRoom_type.add(comboRoomType);
                getRoomTypeId();
        
                
                

                //Add 
                cp.add(pnlRoom);
                cp.add(pnlRoom_type);
                

                cp.add(btnSubmit);
                pack();
                this.setTitle("Add a room");
                //this.setSize(800, 600);
                this.setResizable(false);
                this.setVisible(true);
                
                btnSubmit.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e)
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                //JComboBox comboList = new JComboBox();
                
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        stmt = conn.createStatement();
                
                        String sql = "INSERT INTO Room (Room_Type_Id, Room_Name) " +
                                      "VALUES ('" + roomType.get(comboRoomType.getSelectedItem()) + "', '" + 
                                                                        textRoomName.getText() + "')";

                        
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
                
                textRoomName.setText(""); 

        
        }
        
}