package edu.cgu.ist303.functions;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import edu.cgu.ist303.db.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class TableDemo extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	JButton btnCustomer,btnPersonnel, btnRoom;
	private JLabel lblCellYouChoose;
	private JTextField tfFromTable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableDemo frame = new TableDemo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TableDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCustomer = new JButton("Show Customer Table");
		btnCustomer.setBounds(32, 303, 149, 23);
		contentPane.add(btnCustomer);
		
		btnPersonnel = new JButton("Show Personnel Table");
		btnPersonnel.setBounds(215, 303, 149, 23);
		contentPane.add(btnPersonnel);
		
		btnRoom = new JButton("Show Rooms Table");
		btnRoom.setBounds(404, 303, 149, 23);
		contentPane.add(btnRoom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 31, 533, 206);
		contentPane.add(scrollPane);
	
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		lblCellYouChoose = new JLabel("Cell you choose");
		lblCellYouChoose.setHorizontalAlignment(SwingConstants.CENTER);
		lblCellYouChoose.setBounds(32, 269, 149, 23);
		contentPane.add(lblCellYouChoose);
		
		tfFromTable = new JTextField();
		tfFromTable.setBounds(215, 270, 149, 22);
		contentPane.add(tfFromTable);
		tfFromTable.setColumns(10);
		
		table.addMouseListener(this);
		//table.getSelectionModel().addListSelectionListener(this);
		btnCustomer.addActionListener(this);
		btnPersonnel.addActionListener(this);
		btnRoom.addActionListener(this);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent evt) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		
		 if(table.getValueAt(row, col) != null){
			 String fname = table.getValueAt(row, col).toString();
			 tfFromTable.setText(fname);
		 }else
			 tfFromTable.setText("This cell is empty");
	}
	/* public void valueChanged(ListSelectionEvent event) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(table.getValueAt(row, col)== null)
			System.out.println("empty");
		
		 if(table.getValueAt(row, col) != null){
			 String fname = table.getValueAt(row, col).toString();
			 tfFromTable.setText(fname);
		 }else
			 tfFromTable.setText("This cell is empty");
	 }*/
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btnCustomer){
		
			CustomerDataSource cDS= new CustomerDataSource();
			List<Customer> cusList = new ArrayList<Customer>();
			cusList = cDS.getAllCustomer();
			cDS.close();
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(cDS.getAllColumns());
			table.setModel(model);
			if(!cusList.isEmpty()){
				for(Customer c:cusList){
					model.addRow(new Object[]{c.getId(), c.getFirstName(), c.getLastName(), c.getAddress_one(), 
							c.getAddress_two(), c.getCity(),c.getState(), c.getZipCode(), c.getPhoneNumber()});
					
					
				}
				
				
				
			}
			
		}
			
		if(e.getSource()==btnRoom){
			
			RoomDataSource rDS= new RoomDataSource();
			List<edu.cgu.ist303.db.Room> roomList = new ArrayList<edu.cgu.ist303.db.Room>();
			roomList = rDS.getAllRoom();
			rDS.close();
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(rDS.getAllColumns());
			table.setModel(model);
			if(!roomList.isEmpty()){
				for(edu.cgu.ist303.db.Room r:roomList){
					model.addRow(new Object[]{ r.getId(), r.getRoom_name(), r.getFk_room_type_id()});
					
					
				}
				
				
				
			}
		}
			
	}
}
