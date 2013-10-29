package edu.cgu.ist303.functions;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;



import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import edu.cgu.ist303.db.*;

public class ComboboxTest extends JFrame implements ActionListener, ItemListener {

	JCheckBox chckbxNewCheckBox;
	JComboBox comboBox1, comboBox2;
	private JPanel contentPane;
	HashMap<String, Integer> roomTypeMap, roomMap;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboboxTest frame = new ComboboxTest();
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
	public ComboboxTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox1 = new JComboBox();
		comboBox1.setBounds(5, 5, 187, 31);
		contentPane.add(comboBox1);
		
		RoomTypeDataSource rtDS = new RoomTypeDataSource();
	    List<RoomType> roomTypeList = new ArrayList<RoomType>(); 
	    roomTypeList = rtDS.getAllRoom();
	    rtDS.close();
	    roomTypeMap = new HashMap<String, Integer>(); 
	    for(RoomType rt:roomTypeList){
	      roomTypeMap.put(rt.getRoom_type(), rt.getId());
	      comboBox1.addItem(rt.getRoom_type());
	    }
		
		
		comboBox2 = new JComboBox();
		comboBox2.setBounds(5, 81, 187, 31);
		contentPane.add(comboBox2);
		
		chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(47, 51, 97, 23);
		chckbxNewCheckBox.addItemListener(this);
		comboBox1.addActionListener(this);
		contentPane.add(chckbxNewCheckBox);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		 if(e.getStateChange() == ItemEvent.SELECTED){
			 comboBox2.setVisible(true);
			 //System.out.println(chckbxPreferredRoom.isSelected());
		 }
		 else
			 comboBox2.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
if(ae.getSource()==comboBox1  ){
			
			//System.out.println(comboBoxRoom);
			 RoomDataSource roomDS = new RoomDataSource();
			 List<edu.cgu.ist303.db.Room> roomSingle = new ArrayList<edu.cgu.ist303.db.Room>(); 
			 roomSingle = roomDS.getTypeRoom(1);
			 List<edu.cgu.ist303.db.Room> roomDouble = new ArrayList<edu.cgu.ist303.db.Room>(); 
			 roomDouble = roomDS.getTypeRoom(2);
			 List<edu.cgu.ist303.db.Room> roomQuadrupple = new ArrayList<edu.cgu.ist303.db.Room>(); 
			 roomQuadrupple = roomDS.getTypeRoom(3);
			 roomMap = new HashMap<String, Integer>();
			
			 
			 String s = comboBox1.getSelectedItem().toString();
			switch (s) {
				case "Single":
					comboBox2.removeAllItems();
					
					 for(edu.cgu.ist303.db.Room r:roomSingle){
						  roomMap.put(r.getRoom_name(), r.getId());
						 comboBox2.addItem(r.getRoom_name());
					 }  
					break;
					case "Double":
						 comboBox2.removeAllItems();
						 for(edu.cgu.ist303.db.Room r:roomDouble){
							  roomMap.put(r.getRoom_name(), r.getId());
							  comboBox2.addItem(r.getRoom_name());
						 }
						 break;
					case "Quadrupple":
						 comboBox2.removeAllItems();
						 for(edu.cgu.ist303.db.Room r:roomQuadrupple){
							  roomMap.put(r.getRoom_name(), r.getId());
							  comboBox2.addItem(r.getRoom_name());
						 }
						 break;
			}
	}
	
	
}
}
