package edu.cgu.ist303.functions;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import edu.cgu.ist303.db.Customer;
import edu.cgu.ist303.db.CustomerDataSource;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CustomerRecord extends JPanel implements ActionListener {
	private JTextField tfSearchString;
	private JTextField tfFName;
	private JTextField tfLName;
	private JTextField tfAddress1;
	private JTextField tfAddress2;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;
	private JTextField tfPhone;
	private JTable tblCustomer;
	private JButton btnSearch, btnModify;
	private JComboBox cbxSearchField;

	/**
	 * Create the panel.
	 */
	public CustomerRecord() {
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(7dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(115dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		String [] field = { "Phone", "Name" };
		cbxSearchField = new JComboBox(field);
		cbxSearchField.setSelectedIndex(0); //default: phone
		pnlSearch.add(cbxSearchField, "4, 2, right, default");
		
		tfSearchString = new JTextField();
		pnlSearch.add(tfSearchString, "6, 2, fill, default");
		tfSearchString.setColumns(10);
		
		btnSearch = new JButton("Search");
		pnlSearch.add(btnSearch, "8, 2");
		
		JPanel pnlCustomerModify = new JPanel();
		pnlCustomerModify.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(74dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(116dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel, "2, 2, right, default");
		
		tfFName = new JTextField();
		pnlCustomerModify.add(tfFName, "4, 2, fill, default");
		tfFName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel_1, "2, 4, right, default");
		
		tfLName = new JTextField();
		pnlCustomerModify.add(tfLName, "4, 4, fill, default");
		tfLName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address 1");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel_2, "2, 6, right, default");
		
		tfAddress1 = new JTextField();
		pnlCustomerModify.add(tfAddress1, "4, 6, fill, default");
		tfAddress1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address 2");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel_3, "2, 8, right, default");
		
		tfAddress2 = new JTextField();
		pnlCustomerModify.add(tfAddress2, "4, 8, fill, default");
		tfAddress2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("City");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel_4, "2, 10, right, default");
		
		tfCity = new JTextField();
		pnlCustomerModify.add(tfCity, "4, 10, fill, default");
		tfCity.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("State");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel_5, "2, 12, right, default");
		
		tfState = new JTextField();
		pnlCustomerModify.add(tfState, "4, 12, fill, default");
		tfState.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Zip Code");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel_6, "2, 14, right, default");
		
		tfZipCode = new JTextField();
		pnlCustomerModify.add(tfZipCode, "4, 14, fill, default");
		tfZipCode.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Phone Numer");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(lblNewLabel_7, "2, 16, right, default");
		
		tfPhone = new JTextField();
		tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerModify.add(tfPhone, "4, 16, fill, default");
		tfPhone.setColumns(10);
		
		btnModify = new JButton("Modify");
		pnlCustomerModify.add(btnModify, "4, 18, left, default");
		
		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlTable.add(scrollPane);
		
		tblCustomer = new JTable();
		scrollPane.setViewportView(tblCustomer);
		
		btnSearch.addActionListener(this);
		btnModify.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(pnlCustomerModify, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pnlSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 599, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlSearch, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(pnlCustomerModify, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if( ae.getSource() == btnSearch){
			Customer c =new Customer();
			CustomerDataSource cusDS = new CustomerDataSource();
			String searchItem = (String)cbxSearchField.getSelectedItem();
			
			switch (searchItem) {
				case "Phone": 
					c = cusDS.getCustomerByPhone(tfSearchString.getText());
					tfFName.setText(c.getFirstName());
					tfLName.setText(c.getLastName());
					tfAddress1.setText(c.getAddress_one()); 
					tfAddress2.setText(c.getAddress_two()); 
					tfCity.setText(c.getCity());
					tfState.setText(c.getState());
					tfZipCode.setText(c.getZipCode());
					tfPhone.setText(c.getPhoneNumber());
					if(c.getId()==0)
						JOptionPane.showMessageDialog(this, "No such data exist", "Result", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "Name": 
					List<Customer> cList = new ArrayList<Customer>();
					cList = cusDS.getCustomerByName(tfSearchString.getText());
					DefaultTableModel model = new DefaultTableModel();
					model.setColumnIdentifiers(cusDS.getAllColumns());
					tblCustomer.setModel(model);
					if(!cList.isEmpty()){
						for(Customer customer:cList){
							model.addRow(new Object[]{customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getAddress_one(), 
									customer.getAddress_two(), customer.getCity(),customer.getState(), customer.getZipCode(), customer.getPhoneNumber()});
									}
					}
					else
						JOptionPane.showMessageDialog(this, "No such data exist", "Result", JOptionPane.INFORMATION_MESSAGE);
				break;
			
		}
		
		if( ae.getSource() == btnModify){
			
			
		}
		
		
	}
	
	
	
	}	
	
}
