package edu.cgu.ist303.functions;
import javax.swing.*;

import edu.cgu.ist303.db.*;

import java.awt.*;
import java.awt.event.*;


public class AddCustomer extends JFrame implements ActionListener{
	
	JLabel labFName, labLName, labAddress1, labAddress2, labCity, labState, labZipCode, labPhone;
	JTextField textFName, textLName, textAddress1, textAddress2, textCity, textState, textZipCode, textPhone;
	JPanel pnlFName, pnlLName, pnlAddress1, pnlAddress2, pnlCity, pnlState, pnlZipCode, pnlPhone;
	JButton btnAdd;
	
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/MIYE";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "admin";
	   
	
	    
	public AddCustomer()
	{
		Container cp  = this.getContentPane(); 
		cp.setLayout(new GridLayout(0,1));
		
		btnAdd = new JButton("Add");
		
		labFName = new JLabel("First Name");
		labLName = new JLabel("Last Name");
		labAddress1 = new JLabel("Address One");
		labAddress2 = new JLabel("Address Two");
		labCity = new JLabel("City"); 
		labState = new JLabel("State"); 
		labZipCode = new JLabel("Zip Code");
		labPhone = new JLabel("Phone");
		
		textFName = new JTextField(20); 
		textLName = new JTextField(20); 
		textAddress1 = new JTextField(20); 
		textAddress2 = new JTextField(20); 
		textCity = new JTextField(20);
		textState = new JTextField(20);
		textZipCode = new JTextField(20);
		textPhone = new JTextField(20);
		
		pnlFName = new JPanel();
		pnlLName = new JPanel(); 
		pnlAddress1 = new JPanel(); 
		pnlAddress2 = new JPanel(); 
		pnlCity = new JPanel(); 
		pnlState = new JPanel();
		pnlZipCode = new JPanel();
		pnlPhone = new JPanel();
		//pnlFName.setBorder(BorderFactory.createEmptyBorder(10,1,10,1));
		//pnlLName.setBorder(BorderFactory.createEmptyBorder(1,1,1,1)); 
		//pnlAddress1.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		//pnlAddress2.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		//pnlCity.setBorder(BorderFactory.createEmptyBorder(1,1,1,1)); 
		//pnlState.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		//pnlZipCode.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		
		pnlFName.add(labFName);
		pnlFName.add(textFName);
		pnlLName.add(labLName);
		pnlLName.add(textLName);
		pnlAddress1.add(labAddress1); 
		pnlAddress1.add(textAddress1); 
		pnlAddress2.add(labAddress2); 
		pnlAddress2.add(textAddress2);  
		pnlCity.add(labCity);
		pnlCity.add(textCity);
		pnlState.add(labState);
		pnlState.add(textState);
		pnlZipCode.add(labZipCode);
		pnlZipCode.add(textZipCode);
		pnlPhone.add(labPhone);
		pnlPhone.add(textPhone);
		cp.add(pnlFName);
		cp.add(pnlLName);
		cp.add(pnlAddress1);
		cp.add(pnlAddress2);
		cp.add(pnlCity);
		cp.add(pnlState);
		cp.add(pnlZipCode);
		cp.add(pnlPhone);
		cp.add(btnAdd);
		
		pack();
		this.setTitle("Add a new customer");
		//this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);
		
		btnAdd.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		Customer c = new Customer();
		
		c.setFirstName(textFName.getText());
		c.setLastName(textLName.getText());
		c.setAddress_one(textAddress1.getText());
		c.setAddress_two(textAddress2.getText());
		c.setCity(textCity.getText());
		c.setState(textState.getText());
		c.setZipCode(textZipCode.getText());
		c.setPhoneNumber(textPhone.getText());
		
		
		CustomerDataSource cusDS = new  CustomerDataSource();
		cusDS.createCustomer(c);
		System.out.println(c.getId());
		/***Connection conn = null;
		Statement stmt = null;
		//ResultSet rs = null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql = "INSERT INTO Customer (First_Name, Last_Name, Address_One, Address_Two, City, State, Zip_Code) " +
		    		  "VALUES ('" + textFName.getText() + "', '" + 
		    		  				textLName.getText() + "', '" +
		    		  				textAddress1.getText() + "', '" +
		    		  				textAddress2.getText() + "', '" +
		    		  				textCity.getText() + "', '" +
		    		  				textState.getText() + "', '" +
		    		  				textZipCode.getText() + "')";
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

        } ***/
		//Clear all textField
		textFName.setText(""); 
		textLName.setText(""); 
		textAddress1.setText(""); 
		textAddress2.setText(""); 
		textCity.setText("");
		textState.setText("");
		textZipCode.setText("");
        textPhone.setText("");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddCustomer();
	}

	
}
