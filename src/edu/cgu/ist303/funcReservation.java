package edu.cgu.ist303;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import edu.cgu.ist303.db.*;

import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout.Alignment;



public class funcReservation {
	final static String ADD_PANEL = "Add New Reservation";
    final static String QUERY_PANEL = "Find A Reservation";
	
    
	public JTabbedPane TabbedPaneReservation(){
		 JTabbedPane tabbedPane = new JTabbedPane();
		 
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        JPanel card2 = new JPanel();
	       
	        card1.add( new BookPage(),BorderLayout.CENTER);
	        card2.add(new BookRecord());
	        tabbedPane.addTab(ADD_PANEL, card1);
	        tabbedPane.addTab(QUERY_PANEL, card2);  
	        
	       return tabbedPane;
	}
	
	class BookPage extends JPanel implements ActionListener, ItemListener, MouseListener {
		
		private int customerId; 
		private JTextField tfSearch, tfFirstName, tfLastName, tfSelectedRoom ,tfDateFrom ,tfDateTo;
		private JButton btnCheckCustomer, btnCheckRoom, btnBook;
		private JComboBox comboBoxSearchByField, comboBoxRoomType, comboBoxRoom, comboBoxService;
		private JPanel pnlCustomer, pnlReservation;
		private HashMap<String, Integer> roomTypeMap, roomMap, serviceCategoryMap;
		private JDateChooser dateFrom, dateTo;
		private JCheckBox chckbxPreferredRoom;
		private JPanel pnlTable;
		private JTable table;
		private JScrollPane scrollPane;
		private JLabel lblSelectedRoom;
		private JLabel lblNewLabel_1;
		private JLabel lblNewLabel_2;
		private JLabel labService;
		private SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
		
		
		/**
		 * Create the panel.
		 */
		public BookPage() {
			pnlCustomer = new JPanel();
			pnlCustomer.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Booking Infomation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			
			labService = new JLabel("Service");
			labService.setFont(new Font("Tahoma", Font.PLAIN, 14));
			pnlCustomer.add(labService, "1, 11, right, default");
			
			comboBoxService = new JComboBox();
			pnlCustomer.add(comboBoxService, "3, 11, fill, default");
			ServiceCategoryDataSource scDS = new ServiceCategoryDataSource(); 
			List<ServiceCategory> scList = new ArrayList<ServiceCategory>(); 
			scList = scDS.getAllServiceCategory();
			scDS.close();
			serviceCategoryMap = new HashMap<String, Integer>(); 
			comboBoxService.addItem("");
			for(ServiceCategory sc:scList){
				serviceCategoryMap.put(sc.getCategory(), sc.getId());
			    comboBoxService.addItem(sc.getCategory());
			}
					
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
							if(comboBoxService.getSelectedItem() != ""){
								r.setFk_service_category_id(serviceCategoryMap.get(comboBoxService.getSelectedItem()));
								r = rDS.createReservation(r);
							}	
							else
								r = rDS.createReservationNoService(r);
						
							if(r.getId()!=0){
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
						}else
							JOptionPane.showMessageDialog(this, 
									"All "+ comboBoxRoomType.getSelectedItem() + " rooms are reserved.", "Result", JOptionPane.INFORMATION_MESSAGE);
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
							break;
					/*
					case "Name": 
						List<Customer> cList = new ArrayList<Customer>();
						cList = cusDS.getCustomerByName(tfSearchString.getText());
						DefaultTableModel model = new DefaultTableModel();
						String [] columns = {"First Name", "Last Name", "Address 1", "Address 2 ", "City", "State", "Zip Code", "Phone"};
						model.setColumnIdentifiers(columns);
						tblCustomer.setModel(model);
						if(!cList.isEmpty()){
							for(Customer customer:cList){
								model.addRow(new Object[]{customer.getFirstName(), customer.getLastName(), customer.getAddress_one(), 
										customer.getAddress_two(), customer.getCity(),customer.getState(), customer.getZipCode(), customer.getPhoneNumber()});
										}
						}
						else
							JOptionPane.showMessageDialog(this, "No such data exist", "Result", JOptionPane.INFORMATION_MESSAGE);
						break;
					*/
			
					}
				}else
					JOptionPane.showMessageDialog(this, 
							"Please enter the phone number", "Result", JOptionPane.INFORMATION_MESSAGE);
					
			}

			
			
			if(ae.getSource()==comboBoxRoomType && chckbxPreferredRoom.isSelected() ){
				
				
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
				
				
			}
			 
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			
			 if(table.getValueAt(row, col) != null){
				 String room_name = table.getValueAt(row, col).toString();
				 tfSelectedRoom.setText(room_name);
				 tfDateFrom.setText(sdf.format(dateFrom.getDate()));
				 tfDateTo.setText(sdf.format(dateTo.getDate()));
			 }else{
				 tfSelectedRoom.setText("This cell is empty");
				 tfDateFrom.setText("");
				 tfDateTo.setText("");
			 }
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
	
	}

	class BookRecord extends JPanel implements ActionListener{
		
		
		private JTable tblRecord;
		private JDateChooser dcDateFrom, dcDateTo;
		private JButton btnSearchAll, btnSearch;
		
		
		/**
		 * Create the panel.
		 */
		public BookRecord() {
			setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			add(panel, BorderLayout.NORTH);
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("78px"),
					ColumnSpec.decode("161px"),
					ColumnSpec.decode("78px"),
					ColumnSpec.decode("161px"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,},
				new RowSpec[] {
					RowSpec.decode("20px"),
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			
			JLabel lblNewLabel = new JLabel("Date From");
			panel.add(lblNewLabel, "2, 1, center, fill");
			
			dcDateFrom = new JDateChooser();
			dcDateFrom.setDateFormatString("yyyy/MM/dd");
			panel.add(dcDateFrom, "3, 1, fill, fill");
			
			JLabel lblNewLabel_1 = new JLabel("Date To");
			panel.add(lblNewLabel_1, "4, 1, center, fill");
			
			dcDateTo = new JDateChooser();
			dcDateTo.setDateFormatString("yyyy/MM/dd");
			panel.add(dcDateTo, "5, 1, fill, fill");
			
			btnSearch = new JButton("Submit");
			panel.add(btnSearch, "7, 1");
			
			btnSearchAll = new JButton("Search All");
			panel.add(btnSearchAll, "7, 3");
			
					//register buttons
					btnSearchAll.addActionListener(this);
					btnSearch.addActionListener(this);
					
			JScrollPane scrollPane = new JScrollPane();
			add(scrollPane, BorderLayout.CENTER);
			
			tblRecord = new JTable();
			scrollPane.setViewportView(tblRecord);
			
			
			
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			HashMap<Integer, String> roomMap = new HashMap<Integer, String>();
			RoomDataSource roomDS = new RoomDataSource();
			List<Room> rList = new ArrayList<Room>();
			rList = roomDS.getAllRoom();
			roomDS.close();
			for(Room room:rList){
				roomMap.put(room.getId(), room.getRoom_name());
				//System.out.println(room.getRoom_name() + " " + room.getId() );
			}
			HashMap<Integer, String> customerMap = new HashMap<Integer, String>();
			CustomerDataSource cusDS = new CustomerDataSource();
			List<Customer> cusList = new ArrayList<Customer>();
			cusList = cusDS.getAllCustomer();
			cusDS.close();
			for(Customer c:cusList){
				String fullname = c.getFirstName() + " " + c.getLastName();
				customerMap.put(c.getId(), fullname);
				
			}
			HashMap<Integer, String> serviceCategoryMap = new HashMap<Integer, String>();
			ServiceCategoryDataSource scDS = new ServiceCategoryDataSource(); 
			List<ServiceCategory> scList = new ArrayList<ServiceCategory>(); 
			scList = scDS.getAllServiceCategory();
			scDS.close();
			for(ServiceCategory sc:scList){
				serviceCategoryMap.put(sc.getId(), sc.getCategory());	   
			}
			
			if(ae.getSource() == btnSearch){
				if(dcDateTo.getDate() != null && dcDateFrom.getDate() != null){
					if(dcDateTo.getDate().after(dcDateFrom.getDate()) ){
						java.sql.Date sqlDateFrom = new java.sql.Date(dcDateFrom.getDate().getTime());
						
						java.sql.Date sqlDateTo = new java.sql.Date(dcDateTo.getDate().getTime());
						//java.sql.Date sqlToday = new java.sql.Date(new java.util.Date().getTime());
						ReservationDataSource rDS= new ReservationDataSource();
						List<edu.cgu.ist303.db.Reservation> reList = new ArrayList<edu.cgu.ist303.db.Reservation>();
						reList = rDS.getReservation(sqlDateFrom, sqlDateTo);
						rDS.close();
						DefaultTableModel model = new DefaultTableModel();
						model.setColumnIdentifiers(rDS.getAllColumns());
						tblRecord.setModel(model);
						if(!rList.isEmpty()){
							for(edu.cgu.ist303.db.Reservation r:reList){
							//RESERVATION_ID, ROOM_ID, RESERVATION_DATE, CUSTOMER_ID, SERVICE_CATEGORY_ID, START_DATE, END_DATE
							model.addRow(new Object[]{r.getId(), roomMap.get(r.getFk_room_id()), r.getReservation_Date(), customerMap.get(r.getFk_customer_id()), 
											serviceCategoryMap.get(r.getFk_service_category_id()), r.getStart_Date(), r.getEnd_Date()});				
							}
						}
					}else
						JOptionPane.showMessageDialog(this,  "\"Date to\" must be after \"Date from\"");
					
					
				}else
					JOptionPane.showMessageDialog(this,  "Please Pick Dates");
			}
			
			if(ae.getSource() == btnSearchAll){
				

				ReservationDataSource rDS= new ReservationDataSource();
				List<edu.cgu.ist303.db.Reservation> reList = new ArrayList<edu.cgu.ist303.db.Reservation>();
				reList = rDS.getAllReservation();
				rDS.close();
				DefaultTableModel model = new DefaultTableModel();
				String [] allcolumns = {"ID", "Room", "Book Date", "Customer", "Service", "Check in Date", "Check out Date"};
				model.setColumnIdentifiers(allcolumns);
				tblRecord.setModel(model);
				if(!rList.isEmpty()){
					for(edu.cgu.ist303.db.Reservation r:reList){
						//RESERVATION_ID, ROOM_ID, RESERVATION_DATE, CUSTOMER_ID, SERVICE_CATEGORY_ID, START_DATE, END_DATE
						model.addRow(new Object[]{r.getId(), roomMap.get(r.getFk_room_id()), r.getReservation_Date(), customerMap.get(r.getFk_customer_id()), 
										serviceCategoryMap.get(r.getFk_service_category_id()), r.getStart_Date(), r.getEnd_Date()});				
					}
					
					
					
				}
			}
			
		}

		
	}
	
	
}
