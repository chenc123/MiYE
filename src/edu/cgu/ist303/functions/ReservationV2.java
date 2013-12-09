package edu.cgu.ist303.functions;

import javax.swing.JComponent;
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
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import edu.cgu.ist303.db.*;
import edu.cgu.ist303.db.Room;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ReservationV2 extends JPanel implements ActionListener, ItemListener, MouseListener {
	
	private int customerId; 
	private JTextField tfSearch, tfFirstName, tfLastName, tfSelectedRoom ,tfDateFrom ,tfDateTo;
	private JButton btnCheckCustomer, btnCheckRoom, btnBook;
	private JComboBox comboBoxSearchByField, comboBoxRoomType, comboBoxRoom;
	private JPanel pnlCustomer, pnlReservation;
	private HashMap<String, Integer> roomTypeMap, roomMap;
	private JDateChooser dateFrom, dateTo;
	private JCheckBox chckbxPreferredRoom;
	private JPanel pnlTable;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblSelectedRoom;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel labTotalCost;
	private SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
	private JTextField tfTotalCost;
	
	
	/**
	 * Create the panel.
	 */
	public ReservationV2() {
		
		 
		 
		pnlCustomer = new JPanel();
		pnlCustomer.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Customer Infomation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCustomer.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("113px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("161px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("77px"),},
			new RowSpec[] {
				RowSpec.decode("31px"),
				RowSpec.decode("30px"),
				RowSpec.decode("34px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(21dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(21dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(19dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(22dlu;default)"),}));
		
		
		String [] list= {"Phone"};
		comboBoxSearchByField =  new JComboBox(list);
		comboBoxSearchByField.setSelectedIndex(0);
		comboBoxSearchByField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(comboBoxSearchByField, "1, 1, right, center");
		
		tfSearch = new JTextField();
		pnlCustomer.add(tfSearch, "3, 1, fill, center");
		tfSearch.setColumns(10);
		
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
		
		
		lblSelectedRoom = new JLabel("Selected Room");
		lblSelectedRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(lblSelectedRoom, "1, 5, right, fill");
		
		tfSelectedRoom = new JTextField();
		pnlCustomer.add(tfSelectedRoom, "3, 5, fill, default");
		tfSelectedRoom.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Check-in Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(lblNewLabel_1, "1, 7, right, default");
		
		tfDateFrom = new JTextField();
		pnlCustomer.add(tfDateFrom, "3, 7, fill, default");
		tfDateFrom.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Check-out Date");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(lblNewLabel_2, "1, 9, right, default");
		
		tfDateTo = new JTextField();
		pnlCustomer.add(tfDateTo, "3, 9, fill, default");
		tfDateTo.setColumns(10);
		
		labTotalCost = new JLabel("Total Cost");
		labTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(labTotalCost, "1, 11, right, default");
		ServiceTypeCategoryDataSource scDS = new ServiceTypeCategoryDataSource(); 
		List<ServiceTypeCategory> stcList = new ArrayList<ServiceTypeCategory>(); 
		stcList = scDS.getAllServiceTypeCategory();
		scDS.close();
		
		
		tfTotalCost = new JTextField();
		tfTotalCost.setEditable(false);
		pnlCustomer.add(tfTotalCost, "3, 11, fill, top");
		tfTotalCost.setColumns(10);
		
		
		btnBook = new JButton("Submit");
		pnlCustomer.add(btnBook, "3, 15");
		
		pnlReservation = new JPanel();
		pnlReservation.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Room Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlReservation.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Room Type");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(lblNewLabel, "4, 2, right, default");
		
		comboBoxRoomType = new JComboBox();
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
		pnlReservation.add(comboBoxRoomType, "6, 2, fill, default");
		
		comboBoxRoom = new JComboBox();
		comboBoxRoom.setVisible(false);
		comboBoxRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(comboBoxRoom, "8, 4, left, default");

		chckbxPreferredRoom = new JCheckBox("Preferred Room");
		chckbxPreferredRoom.setSelected(false);
		pnlReservation.add(chckbxPreferredRoom, "6, 4, left, default");
		
		
		JLabel labDateFrom = new JLabel("Date From");
		labDateFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(labDateFrom, "4, 6, right, default");
		
		dateFrom = new JDateChooser();
		dateFrom.setDateFormatString("yyyy/MM/dd");
		pnlReservation.add(dateFrom, "6, 6, fill, fill");
		
		JLabel labDateTo = new JLabel("Date To");
		labDateTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(labDateTo, "4, 8, right, default");
		
		dateTo = new JDateChooser();
		dateTo.setDateFormatString("yyyy/MM/dd");
		pnlReservation.add(dateTo, "6, 8, fill, fill");
		
		btnCheckRoom = new JButton("Check Room");
		pnlReservation.add(btnCheckRoom, "8, 8, center, default");
		
		
		
		pnlTable = new JPanel();
		pnlTable.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		pnlTable.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(this);
		chckbxPreferredRoom.addItemListener(this);
		comboBoxRoomType.addActionListener(this);
		btnCheckRoom.addActionListener(this);
		btnBook.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pnlReservation, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(379)
					.addComponent(pnlCustomer, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pnlReservation, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlCustomer, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);
		
		
	}
	
	public void itemStateChanged(ItemEvent e){
		
		 if(e.getStateChange() == ItemEvent.SELECTED){
			 comboBoxRoom.setVisible(true);
			 RoomDataSource roomDS = new RoomDataSource();
			 List<Room> roomSingle = new ArrayList<Room>(); 
			 roomSingle = roomDS.getTypeRoom(1);
			 List<Room> roomDouble = new ArrayList<Room>(); 
			 roomDouble = roomDS.getTypeRoom(2);
			 List<Room> roomQuadrupple = new ArrayList<Room>(); 
			 roomQuadrupple = roomDS.getTypeRoom(3);
			 roomDS.close();
			
			
			if(comboBoxRoomType.getSelectedItem().toString().equals("Single")){
				comboBoxRoom.removeAllItems();
				 for(Room r:roomSingle){
					 comboBoxRoom.addItem(r.getRoom_name());
				 }  
			}else if(comboBoxRoomType.getSelectedItem().toString().equals("Double")){
				 comboBoxRoom.removeAllItems();
				 for(Room r:roomDouble){
					  comboBoxRoom.addItem(r.getRoom_name());
				 }
			}else{
				 comboBoxRoom.removeAllItems();
				 for(Room r:roomQuadrupple){
					  comboBoxRoom.addItem(r.getRoom_name());
				 }
			}
			 
		 }
		 else
			 comboBoxRoom.setVisible(false);
		
	 }
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()== btnBook){
			
			roomMap = new HashMap<String, Integer>();
			RoomDataSource roomDS = new RoomDataSource();
			List<Room> rList = new ArrayList<Room>();
			rList = roomDS.getAllRoom();
			for(Room room:rList){
				roomMap.put(room.getRoom_name(), room.getId());
			}
			
			
			//System.out.println(roomMap.get(tfSelectedRoom.getText()));
			//System.out.println(serviceCategoryMap.get(comboBoxService.getSelectedItem()));
			
				try {
					
					if( !tfSearch.getText().isEmpty() && !tfFirstName.getText().isEmpty() && !tfLastName.getText().isEmpty() && !tfSelectedRoom.getText().isEmpty() && !tfDateFrom.getText().isEmpty() && !tfDateTo.getText().isEmpty())
					{
						java.util.Date utilDateFrom = sdf.parse(tfDateFrom.getText());
						java.sql.Date sqlDateFrom = new java.sql.Date(utilDateFrom.getTime());
						java.util.Date utilDateTo = sdf.parse(tfDateTo.getText());
						java.sql.Date sqlDateTo = new java.sql.Date(utilDateTo.getTime());
						java.sql.Date sqlToday = new java.sql.Date(new java.util.Date().getTime());
					
						//System.out.println("Customer_ID: "+ customerId + " Room_ID: " + roomMap.get(tfSelectedRoom.getText()) + " Today: " + sqlToday + " From: " +sqlDateFrom + " To "+ sqlDateTo + " Service_Category_ID: " + serviceCategoryMap.get(comboBoxService.getSelectedItem()));
						edu.cgu.ist303.db.Reservation r = new edu.cgu.ist303.db.Reservation();
						r.setFk_customer_id(customerId);
						r.setFk_room_id(roomMap.get(tfSelectedRoom.getText()));
						r.setReservation_Date(sqlToday);
						r.setStart_Date(sqlDateFrom);
						r.setEnd_Date(sqlDateTo);
					
						ReservationDataSource rDS = new ReservationDataSource();

						r = rDS.createReservationNoService(r);
					
						if(r.getId() != 0){
							JOptionPane.showMessageDialog(this, "Booking successful", "Message", JOptionPane.INFORMATION_MESSAGE);
							tfSearch.setText("");
							tfFirstName.setText("");
							tfLastName.setText("");
							tfSelectedRoom.setText("");
							tfDateFrom.setText("");
							tfDateTo.setText("");
						
						}
						else
							;
					}else
						JOptionPane.showMessageDialog(this, "Please fill all fields ", "Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
			
		
			
			
		}
		
		if(ae.getSource() == btnCheckRoom){
			if(dateTo.getDate() != null && dateFrom.getDate() != null){
				if(dateTo.getDate().after(dateFrom.getDate()) ){
					ReservationDataSource rDS= new ReservationDataSource();
					//System.out.println(roomTypeMap.get(comboBoxRoomType.getSelectedItem()));
					int room_type_id = roomTypeMap.get(comboBoxRoomType.getSelectedItem());

					java.sql.Date date_from = new java.sql.Date(dateFrom.getDate().getTime());
					java.sql.Date date_to = new java.sql.Date(dateTo.getDate().getTime());
					//System.out.println("From: " + date_from + " To: " + date_to);
			
					List<Integer> rList = new ArrayList<Integer>();
					rList = rDS.getAvailableRooms(room_type_id, date_from, date_to);
			
					DefaultTableModel model = new DefaultTableModel();
					String[] column = {"Available Room"};
					model.setColumnIdentifiers(column);
					table.setModel(model);
					if(!rList.isEmpty()){
						for(int i=0; i < rList.size(); i++){
							model.addRow(new Object[]{rDS.getRoomNameById(rList.get(i))});
						}
						rDS.close();
					}
				}else
					JOptionPane.showMessageDialog(this,  "\"Date to\" must be after \"Date from\"");
			}else
				JOptionPane.showMessageDialog(this,  "Please Pick Dates");
		}
		if(ae.getSource()==btnCheckCustomer  ){
			if( !tfSearch.getText().isEmpty()){
				String searchItem = (String)comboBoxSearchByField.getSelectedItem();
				Customer c = new Customer();	
				CustomerDataSource cusDS = new CustomerDataSource();
				switch (searchItem) {
					case "Phone": 
						c = cusDS.getCustomerByPhone(tfSearch.getText());
						//System.out.println(c.getFirstName());
						tfFirstName.setText(c.getFirstName());
						tfLastName.setText(c.getLastName());
						customerId = c.getId();
						if(c.getId()==0)
							JOptionPane.showMessageDialog(this, 
									"Phone number \"" + tfSearch.getText() + "\" is not found", "Result", JOptionPane.INFORMATION_MESSAGE);
		
		
				}
			}else
				JOptionPane.showMessageDialog(this, 
						"Please enter the phone number", "Result", JOptionPane.INFORMATION_MESSAGE);
				
		}
		//comboBoxRoom = new JComboBox();
		
		
		if(ae.getSource() == comboBoxRoomType && chckbxPreferredRoom.isSelected() ){
			
			
			 RoomDataSource roomDS = new RoomDataSource();
			 List<Room> roomSingle = new ArrayList<Room>(); 
			 roomSingle = roomDS.getTypeRoom(1);
			 List<Room> roomDouble = new ArrayList<Room>(); 
			 roomDouble = roomDS.getTypeRoom(2);
			 List<Room> roomQuadrupple = new ArrayList<Room>(); 
			 roomQuadrupple = roomDS.getTypeRoom(3);
			 roomDS.close();
			
			
			if(comboBoxRoomType.getSelectedItem().toString().equals("Single")){
				comboBoxRoom.removeAllItems();
				 for(Room r:roomSingle){
					 comboBoxRoom.addItem(r.getRoom_name());
				 }  
			}else if(comboBoxRoomType.getSelectedItem().toString().equals("Double")){
				 comboBoxRoom.removeAllItems();
				 for(Room r:roomDouble){
					  comboBoxRoom.addItem(r.getRoom_name());
				 }
			}else{
				 comboBoxRoom.removeAllItems();
				 for(Room r:roomQuadrupple){
					  comboBoxRoom.addItem(r.getRoom_name());
				 }
			}
				
			   /*
			 String s = comboBoxRoomType.getSelectedItem().toString();
			 switch (s) {
				case "Single":
					comboBoxRoom.removeAllItems();
					 for(Room r:roomSingle){
						 comboBoxRoom.addItem(r.getRoom_name());
					 }  
					break;
					case "Double":
						 comboBoxRoom.removeAllItems();
						 for(Room r:roomDouble){
							  comboBoxRoom.addItem(r.getRoom_name());
						 }
						 break;
					case "Quadrupple":
						 comboBoxRoom.removeAllItems();
						 for(Room r:roomQuadrupple){
							  comboBoxRoom.addItem(r.getRoom_name());
						 }
						 break;
			 }*/
			 
			
		
			}
			
			
		}
		 
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		String room_name = null;
		 if(table.getValueAt(row, col) != null){
			 room_name = table.getValueAt(row, col).toString();
			 tfSelectedRoom.setText(room_name);
			 tfDateFrom.setText(sdf.format(dateFrom.getDate()));
			 tfDateTo.setText(sdf.format(dateTo.getDate()));
		 }else{
			 tfSelectedRoom.setText("This cell is empty");
			 tfDateFrom.setText("");
			 tfDateTo.setText("");
		 }
		 RoomRateDataSource rrDS = new RoomRateDataSource();
		double total_cost = 0;
		roomMap = new HashMap<String, Integer>();
		RoomDataSource roomDS = new RoomDataSource();
		List<Room> rList = new ArrayList<Room>();
		rList = roomDS.getAllRoom();
		for(Room room:rList){
			roomMap.put(room.getRoom_name(), room.getId());
		}
		
		java.sql.Date date_from = new java.sql.Date(dateFrom.getDate().getTime());
		java.sql.Date date_to = new java.sql.Date(dateTo.getDate().getTime());
		
		total_cost = rrDS.room_total_cost(roomMap.get(room_name), date_from, date_to);
		
		tfTotalCost.setText(String.valueOf(total_cost));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		//frm.setSize(600,500);
		 JComponent newContentPane = new ReservationV2();
		 newContentPane.setOpaque(false);
		 frm.setContentPane(newContentPane);
		 frm.pack();
		frm.setVisible(true);
		
		
	}
}
