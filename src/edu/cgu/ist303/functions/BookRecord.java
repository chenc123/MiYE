package edu.cgu.ist303.functions;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import edu.cgu.ist303.db.Customer;
import edu.cgu.ist303.db.CustomerDataSource;
import edu.cgu.ist303.db.ReservationDataSource;
import edu.cgu.ist303.db.Room;
import edu.cgu.ist303.db.RoomDataSource;
import edu.cgu.ist303.db.ServiceCategory;
import edu.cgu.ist303.db.ServiceCategoryDataSource;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class BookRecord extends JPanel implements ActionListener{
	
	
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
			model.setColumnIdentifiers(rDS.getAllColumns());
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frm = new JFrame();
					JPanel p = new BookRecord();
					frm.getContentPane().add(p);
					frm.pack();
					frm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
}
