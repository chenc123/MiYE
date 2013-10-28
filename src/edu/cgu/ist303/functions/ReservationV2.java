package edu.cgu.ist303.functions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

import com.toedter.calendar.JDateChooser;

import edu.cgu.ist303.db.*;
import edu.cgu.ist303.db.Room;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationV2 extends JPanel implements ActionListener, ItemListener {
	
	
	private JTextField tfSerach, tfFirstName, tfLastName;
	private JButton btnCheckCustomer, btnCheckRoom;
	private JComboBox comboBoxSearchByField, comboBoxRoomType, comboBoxRoom;
	JPanel pnlCustomer, pnlReservation;
	HashMap<String, Integer> roomTypeMap, roomMap;
	
	private JCheckBox chckbxPreferredRoom;
	/**
	 * Create the panel.
	 */
	public ReservationV2(JFrame f) {
		setLayout(null);
		
		pnlCustomer = new JPanel();
		pnlCustomer.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Customer Infomation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCustomer.setBounds(186, 26, 313, 124);
		add(pnlCustomer);
		pnlCustomer.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("70px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("135px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("77px"),},
			new RowSpec[] {
				RowSpec.decode("31px"),
				RowSpec.decode("30px"),
				RowSpec.decode("34px"),}));
		
		
		String [] list= {"Phone", "Name"};
		comboBoxSearchByField =  new JComboBox(list);
		comboBoxSearchByField.setSelectedIndex(0);
		comboBoxSearchByField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(comboBoxSearchByField, "1, 1, fill, center");
		
		tfSerach = new JTextField();
		pnlCustomer.add(tfSerach, "3, 1, fill, center");
		tfSerach.setColumns(10);
		
		btnCheckCustomer = new JButton("Check");
		btnCheckCustomer.addActionListener(this);;
		pnlCustomer.add(btnCheckCustomer, "5, 1, left, center");
		
		JLabel labFirstName = new JLabel("First Name");
		labFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(labFirstName, "1, 2, right, center");
		
		tfFirstName = new JTextField();
		pnlCustomer.add(tfFirstName, "3, 2, fill, center");
		tfFirstName.setColumns(10);
		
		JLabel labLastName = new JLabel("Last Name");
		labLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(labLastName, "1, 3, right, center");
		
		tfLastName = new JTextField();
		pnlCustomer.add(tfLastName, "3, 3, fill, center");
		tfLastName.setColumns(10);
		
		pnlReservation = new JPanel();
		pnlReservation.setBounds(186, 161, 367, 178);
		add(pnlReservation);
		pnlReservation.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(73dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Room Type");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(lblNewLabel, "6, 2, right, default");
		
		comboBoxRoomType = new JComboBox();
		comboBoxRoomType.addActionListener(this);
		comboBoxRoomType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RoomTypeDataSource rtDS = new RoomTypeDataSource();
	    List<RoomType> roomTypeList = new ArrayList<RoomType>(); 
	    roomTypeList = rtDS.getAllRoom();
	    rtDS.close();
	    roomTypeMap = new HashMap<String, Integer>(); 
	    for(RoomType rt:roomTypeList){
	      roomTypeMap.put(rt.getRoom_type(), rt.getId());
	      comboBoxRoomType.addItem(rt.getRoom_type());
	    }
		pnlReservation.add(comboBoxRoomType, "8, 2, fill, default");
		
		comboBoxRoom = new JComboBox();
		comboBoxRoom.setVisible(false);
		comboBoxRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(comboBoxRoom, "10, 4, left, default");

		chckbxPreferredRoom = new JCheckBox("Preferred Room");
		chckbxPreferredRoom.setSelected(false);
		pnlReservation.add(chckbxPreferredRoom, "8, 4, left, default");
		chckbxPreferredRoom.addItemListener(this);
		
		
		
		
		JLabel labDateFrom = new JLabel("From Date");
		labDateFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(labDateFrom, "6, 6, right, default");
		
		JDateChooser dateFrom = new JDateChooser();
		dateFrom.setDateFormatString("yyyy/MM/dd");
		pnlReservation.add(dateFrom, "8, 6, fill, fill");
		
		JLabel labDateTo = new JLabel("To Date");
		labDateTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(labDateTo, "6, 8, right, default");
		
		JDateChooser dateTo = new JDateChooser();
		dateTo.setDateFormatString("yyyy/MM/dd");
		pnlReservation.add(dateTo, "8, 8, fill, fill");
		
		btnCheckRoom = new JButton("Check Room");
		btnCheckRoom.addActionListener(dateFrom);
		pnlReservation.add(btnCheckRoom, "8, 10, left, default");
		
		JLabel labService = new JLabel("Service");
		labService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(labService, "6, 12, right, default");
		
		
		f.getContentPane().add(this);
	}
	
	public void itemStateChanged(ItemEvent e){
		
		 if(e.getStateChange() == ItemEvent.SELECTED){
			 comboBoxRoom.setVisible(true);
			 //System.out.println(chckbxPreferredRoom.isSelected());
		 }
		 else
			 comboBoxRoom.setVisible(false);
		
	 }
	
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==btnCheckCustomer){
			String searchItem = (String)comboBoxSearchByField.getSelectedItem();
			Customer c = new Customer();	
			CustomerDataSource cusDS = new CustomerDataSource();
			switch (searchItem) {
				case "Phone": 
					c = cusDS.getCustomerByPhone(tfSerach.getText());
					System.out.println(c.getFirstName());
					tfFirstName.setText(c.getFirstName());
					tfLastName.setText(c.getLastName());
					
					if(c.getId()==0)
						JOptionPane.showMessageDialog(this, 
								"No such data exist", "Result", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "Name": JOptionPane.showMessageDialog(this,
						"Name");
				break;
		
		
			}
				
		}
		//comboBoxRoom = new JComboBox();
		
		
		if(ae.getSource()==comboBoxRoomType  ){
			
			//System.out.println(comboBoxRoom);
			 RoomDataSource roomDS = new RoomDataSource();
			 List<Room> roomSingle = new ArrayList<Room>(); 
			 roomSingle = roomDS.getTypeRoom(1);
			 List<Room> roomDouble = new ArrayList<Room>(); 
			 roomDouble = roomDS.getTypeRoom(2);
			 List<Room> roomQuadrupple = new ArrayList<Room>(); 
			 roomQuadrupple = roomDS.getTypeRoom(3);
			 roomMap = new HashMap<String, Integer>();
			
			
			 String s = comboBoxRoomType.getSelectedItem().toString();
			switch (s) {
				case "Single":
					comboBoxRoom.removeAllItems();
					 for(Room r:roomSingle){
						  roomMap.put(r.getRoom_name(), r.getId());
						  comboBoxRoom.addItem(r.getRoom_name());
					 }  
					break;
					case "Double":
						 comboBoxRoom.removeAllItems();
						 for(Room r:roomDouble){
							  roomMap.put(r.getRoom_name(), r.getId());
							  comboBoxRoom.addItem(r.getRoom_name());
						 }
						 break;
					case "Quadrupple":
						 comboBoxRoom.removeAllItems();
						 for(Room r:roomQuadrupple){
							  roomMap.put(r.getRoom_name(), r.getId());
							  comboBoxRoom.addItem(r.getRoom_name());
						 }
						 break;
			}
			}
			 /*if(comboBoxRoomType.getSelectedItem().equals("Single")){
				comboBoxRoom.removeAllItems();
				 for(Room r:roomSingle){
					  roomMap.put(r.getRoom_name(), r.getId());
					  comboBoxRoom.addItem(r.getRoom_name());
				 }
				 
				
			 } else if(comboBoxRoomType.getSelectedItem().equals("Double")){
				
				 comboBoxRoom.removeAllItems();
				 for(Room r:roomDouble){
					  roomMap.put(r.getRoom_name(), r.getId());
					  comboBoxRoom.addItem(r.getRoom_name());
				 }
				
			 }
			 else if(comboBoxRoomType.getSelectedItem().equals("Quadrupple")){
				 comboBoxRoom.removeAllItems();
				 for(Room r:roomQuadrupple){
					  roomMap.put(r.getRoom_name(), r.getId());
					  comboBoxRoom.addItem(r.getRoom_name());
				 }
				 
			 }	 
			 else
				 	;
				 
			 }*/
			
		}
		 
	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setSize(600,500);
		new ReservationV2(frm);
		frm.setVisible(true);
		
		
	}
}
