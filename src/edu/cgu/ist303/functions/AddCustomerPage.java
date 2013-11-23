package edu.cgu.ist303.functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.factories.FormFactory;

import edu.cgu.ist303.db.Customer;
import edu.cgu.ist303.db.CustomerDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class AddCustomerPage extends JPanel implements ActionListener{
	private JTextField tfFName, tfLName, tfAddress1, tfAddress2, tfCity, tfPhone, tfState, tfZipCode;
	private JButton btnSubmit;
	
	/**
	 * Create the panel.
	 */
	public AddCustomerPage() {
		
		JPanel pnlCustomerForm = new JPanel();
		pnlCustomerForm.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(95)
					.addComponent(pnlCustomerForm, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addComponent(pnlCustomerForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		pnlCustomerForm.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(67dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
		
		JLabel labFName = new JLabel("First Name");
		labFName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(labFName, "2, 2, right, default");
		
		tfFName = new JTextField();
		tfFName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(tfFName, "4, 2, fill, default");
		tfFName.setColumns(10);
		
		JLabel labLName = new JLabel("Last Name");
		labLName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(labLName, "2, 4, right, default");
		
		tfLName = new JTextField();
		tfLName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(tfLName, "4, 4, fill, default");
		tfLName.setColumns(10);
		
		JLabel labAddress1 = new JLabel("Address 1");
		labAddress1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(labAddress1, "2, 6, right, default");
		
		tfAddress1 = new JTextField();
		tfAddress1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(tfAddress1, "4, 6, fill, default");
		tfAddress1.setColumns(10);
		
		JLabel labAddress2 = new JLabel("Address 2");
		labAddress2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(labAddress2, "2, 8, right, default");
		
		tfAddress2 = new JTextField();
		tfAddress2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(tfAddress2, "4, 8, fill, default");
		tfAddress2.setColumns(10);
		
		JLabel labCity = new JLabel("City");
		labCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(labCity, "2, 10, right, default");
		
		tfCity = new JTextField();
		tfCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(tfCity, "4, 10, fill, default");
		tfCity.setColumns(10);
		
		JLabel labState = new JLabel("State");
		labState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(labState, "2, 12, right, default");
		
		tfState = new JTextField();
		tfState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(tfState, "4, 12, fill, default");
		tfState.setColumns(10);
		
		JLabel lblZipCode = new JLabel("Zip Code");
		lblZipCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(lblZipCode, "2, 14, right, default");
		
		tfZipCode = new JTextField();
		pnlCustomerForm.add(tfZipCode, "4, 14, fill, top");
		tfZipCode.setColumns(10);
		
		JLabel labPhone = new JLabel("Phone");
		labPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(labPhone, "2, 16, right, default");
		
		tfPhone = new JTextField();
		tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(tfPhone, "4, 16, fill, default");
		tfPhone.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlCustomerForm.add(btnSubmit, "4, 18, left, default");
		setLayout(groupLayout);
		//add listener
		btnSubmit.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == btnSubmit){
			CustomerDataSource cusDS = new CustomerDataSource();
			
			if(!tfFName.getText().isEmpty() && !tfLName.getText().isEmpty() && !tfPhone.getText().isEmpty())
			{
				Customer c = new Customer();
			
				c.setFirstName(tfFName.getText());
				c.setLastName(tfLName.getText());
				c.setAddress_one(tfAddress1.getText());
				c.setAddress_two(tfAddress2.getText());
				c.setCity(tfCity.getText());
				c.setState(tfState.getText());
				c.setZipCode(tfZipCode.getText());
				c.setPhoneNumber(tfPhone.getText());
				
				cusDS.createCustomer(c);
				
			
				if(c.getId()!=0)
					JOptionPane.showMessageDialog(this,
							"Adding " + c.getFirstName() + " " + c.getLastName() + " successful!");
				else
					JOptionPane.showMessageDialog(this,
		                   "Adding " + c.getFirstName() + " " + c.getLastName() + " failed!");
			
			}else if(cusDS.checkCustomerByPhone(tfPhone.getText())){
				JOptionPane.showMessageDialog(this,
		                   "Phone \""+ tfPhone.getText() +"\" is already existed ");
			}else
				JOptionPane.showMessageDialog(this,
		                   "\"First Name\" or \"Last Name\" or \"Phone\" cannot be empty ");
			cusDS.close();
		}
		
	}
	
	
	
}
