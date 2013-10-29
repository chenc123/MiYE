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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ReservationV2 extends JPanel implements ActionListener, ItemListener, MouseListener {
	
	private static boolean ROOM_TYPE_POPULATE = false; 
	private JTextField tfSerach, tfFirstName, tfLastName;
	private JButton btnCheckCustomer, btnCheckRoom;
	private JComboBox comboBoxSearchByField, comboBoxRoomType, comboBoxRoom, abc;
	JPanel pnlCustomer, pnlReservation;
	HashMap<String, Integer> roomTypeMap, roomMap;
	private JDateChooser dateFrom, dateTo;
	private JCheckBox chckbxPreferredRoom;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblSelectedRoom;
	private JTextField tfSelectedRoom,tfService ,tfDateFrom ,tfDateTo ;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel labService;
	private SimpleDateFormat sdf;
	private JButton btnBook;
	
	/**
	 * Create the panel.
	 */
	public ReservationV2(JFrame f) {
		setLayout(null);
		
		 
		 
		pnlCustomer = new JPanel();
		pnlCustomer.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Customer Infomation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCustomer.setBounds(379, 11, 388, 365);
		add(pnlCustomer);
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
		
		
		String [] list= {"Phone", "Name"};
		comboBoxSearchByField =  new JComboBox(list);
		comboBoxSearchByField.setSelectedIndex(0);
		comboBoxSearchByField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(comboBoxSearchByField, "1, 1, right, center");
		
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
		
		lblSelectedRoom = new JLabel("Selected Room");
		lblSelectedRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomer.add(lblSelectedRoom, "1, 5, right, default");
		
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
		
		tfService = new JTextField();
		pnlCustomer.add(tfService, "3, 11, fill, default");
		tfService.setColumns(10);
		
		btnBook = new JButton("Submit");
		pnlCustomer.add(btnBook, "3, 15");
		
		pnlReservation = new JPanel();
		pnlReservation.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Room Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlReservation.setBounds(0, 11, 380, 170);
		add(pnlReservation);
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
		
		
		
		
		
		JLabel labDateFrom = new JLabel("From Date");
		labDateFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(labDateFrom, "4, 6, right, default");
		
		dateFrom = new JDateChooser();
		dateFrom.setDateFormatString("yyyy/MM/dd");
		pnlReservation.add(dateFrom, "6, 6, fill, fill");
		
		JLabel labDateTo = new JLabel("To Date");
		labDateTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlReservation.add(labDateTo, "4, 8, right, default");
		
		dateTo = new JDateChooser();
		dateTo.setDateFormatString("yyyy/MM/dd");
		pnlReservation.add(dateTo, "6, 8, fill, fill");
		
		btnCheckRoom = new JButton("Check Room");
		pnlReservation.add(btnCheckRoom, "8, 8, center, default");
		
		chckbxPreferredRoom.addItemListener(this);
		comboBoxRoomType.addActionListener(this);
		btnCheckRoom.addActionListener(this);
		
		
		panel = new JPanel();
		panel.setBounds(10, 191, 368, 185);
		add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(this);
		f.getContentPane().add(this);
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
		
		
		if(ae.getSource() == btnCheckRoom){
			ReservationDataSource rDS= new ReservationDataSource();
			
			//System.out.println(roomTypeMap.get(comboBoxRoomType.getSelectedItem()));
			int room_type_id = roomTypeMap.get(comboBoxRoomType.getSelectedItem());
		    sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date_from = sdf.format(dateFrom.getDate());
			String date_to = sdf.format(dateTo.getDate());
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
		}
		if(ae.getSource()==btnCheckCustomer  ){
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
		// TODO Auto-generated method stub
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

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setSize(600,500);
		new ReservationV2(frm);
		frm.setVisible(true);
		
		
	}
}
