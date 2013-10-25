package edu.cgu.ist303;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import edu.cgu.ist303.db.*;


public class funcCustomer implements ActionListener {
	
	final static String ADD_PANEL = "Add New Customer";
    final static String QUERY_PANEL = "Find A Customer";
    
    private JLabel labFName, labLName, labAddress1, labAddress2, labCity, labState, labZipCode, labPhone;
	private JTextField textFName, textLName, textAddress1, textAddress2, textCity, textState, textZipCode, textPhone;
	private JPanel pnlCustomerForm; 
    private JButton btnSubmitCustomer; 
	
    //object for FindEditCustomer method
    private JPanel pnlSearch;
    private JComboBox<String> searchByField;
    private JTextField textSearch;
    private JButton	btnSearch;
    
	public JTabbedPane tabPaneCustomer() {
	        JTabbedPane tabbedPane = new JTabbedPane();
	 
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        JPanel card2 = new JPanel();
	   
	        //create card1 layout for customer-adding form
	        card1.setLayout(new GridLayout(2,1));
	       
	        funcCustomer fCus = new funcCustomer();
	        fCus.AddCustomerPanel(card1);
	        fCus.FindEditCustomer(card2);
	        
	        tabbedPane.addTab(ADD_PANEL, card1);
	        tabbedPane.addTab(QUERY_PANEL, card2);  
	        
	       return tabbedPane;
    }   
	
	public void AddCustomerPanel(Container pane){
		
        pnlCustomerForm = new JPanel(new GridLayout(9,2)); 
        btnSubmitCustomer = new JButton("Submit"); 
        labFName = new JLabel("First Name", SwingConstants.RIGHT);
		labLName = new JLabel("Last Name", SwingConstants.RIGHT);
		labAddress1 = new JLabel("Address One", SwingConstants.RIGHT);
		labAddress2 = new JLabel("Address Two", SwingConstants.RIGHT);
		labCity = new JLabel("City", SwingConstants.RIGHT); 
		labState = new JLabel("State", SwingConstants.RIGHT); 
		labZipCode = new JLabel("Zip Code", SwingConstants.RIGHT);
		labPhone = new JLabel("Phone", SwingConstants.RIGHT);
		
		textFName = new JTextField(20); 
		textLName = new JTextField(20); 
		textAddress1 = new JTextField(20); 
		textAddress2 = new JTextField(20); 
		textCity = new JTextField(20);
		textState = new JTextField(20);
		textZipCode = new JTextField(20);
		textPhone = new JTextField(20);
		
		pnlCustomerForm.add(labFName);
		pnlCustomerForm.add(textFName);
		pnlCustomerForm.add(labLName);
		pnlCustomerForm.add(textLName);
		pnlCustomerForm.add(labAddress1);
		pnlCustomerForm.add(textAddress1);
		pnlCustomerForm.add(labAddress2);
		pnlCustomerForm.add(textAddress2);
		pnlCustomerForm.add(labCity);
		pnlCustomerForm.add(textCity);
		pnlCustomerForm.add(labState);
		pnlCustomerForm.add(textState);
		pnlCustomerForm.add(labZipCode);
		pnlCustomerForm.add(textZipCode);
		pnlCustomerForm.add(labPhone);
		pnlCustomerForm.add(textPhone);
		
		pnlCustomerForm.add(new JLabel());//do nothing
		
		pnlCustomerForm.add(btnSubmitCustomer);
		
		btnSubmitCustomer.setActionCommand("submit");
		btnSubmitCustomer.addActionListener(this);
		pane.add(pnlCustomerForm);
	}
    
	public void FindEditCustomer(Container pane)
	{
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//
		pnlSearch = new JPanel(new FlowLayout());
		
		String [] list= {"Phone", "Name"};
		searchByField =  new JComboBox<String>(list);
		searchByField.setSelectedIndex(1);
		
		
		textSearch = new JTextField(20);
		btnSearch = new JButton("Submit");
		pnlSearch.add(searchByField);
		pnlSearch.add(textSearch);
		pnlSearch.add(btnSearch);
		pnlSearch.setBorder(BorderFactory.createLineBorder(Color.black));
		
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(pnlSearch, c);
		
		JPanel p= new JPanel();
		funcCustomer.this.AddCustomerPanel(p); //pane =card2
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		btnSubmitCustomer.setText("Modify");
		btnSubmitCustomer.setActionCommand("modify");
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight=10;
		c.anchor = GridBagConstraints.CENTER;
		pane.add(p, c);
		//searchByField.addActionListener(this);
		btnSearch.setActionCommand("search");
		btnSearch.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		Customer c = new Customer();	
		CustomerDataSource cusDS = new CustomerDataSource();
		if("search".equals(btnSearch.getActionCommand()))
		{
			
				String searchItem = (String)searchByField.getSelectedItem();
			
				switch (searchItem) {
					case "Phone": 
						c = cusDS.getCustomerByPhone(textSearch.getText());
						textFName.setText(c.getFirstName());
						textLName.setText(c.getLastName());
						textAddress1.setText(c.getAddress_one()); 
						textAddress2.setText(c.getAddress_two()); 
						textCity.setText(c.getCity());
						textState.setText(c.getState());
						textZipCode.setText(c.getZipCode());
						textPhone.setText(c.getPhoneNumber());
						if(c.getId()==0)
							JOptionPane.showMessageDialog(pnlSearch, 
									"No such data exist", "Result", JOptionPane.INFORMATION_MESSAGE);
						break;
					case "Name": JOptionPane.showMessageDialog(pnlSearch,
							"Name");
					break;
			
			
				}
			
		}
		if("modify".equals(btnSubmitCustomer.getActionCommand()))
		{
			
		}
		
		if ("submit".equals(btnSubmitCustomer.getActionCommand()))
		{
		   
		   
		   c.setFirstName(textFName.getText());
		   c.setLastName(textLName.getText());
		   c.setAddress_one(textAddress1.getText());
		   c.setAddress_two(textAddress2.getText());
		   c.setCity(textCity.getText());
		   c.setState(textState.getText());
		   c.setZipCode(textZipCode.getText());
		   c.setPhoneNumber(textPhone.getText());
		   cusDS.createCustomer(c);
		   cusDS.close();
		   
		   if(c.getId()!=0)
			   JOptionPane.showMessageDialog(pnlCustomerForm,
                    "Adding " + c.getFirstName() + " " + c.getLastName() + " successful!");
		   else
			   JOptionPane.showMessageDialog(pnlCustomerForm,
	                   "Adding " + c.getFirstName() + " " + c.getLastName() + " failed!");
		}
		
		
	}
    
	
}
