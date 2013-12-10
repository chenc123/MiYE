package edu.cgu.ist303.functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import edu.cgu.ist303.db.*;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

public class MgtServicePage extends JPanel implements ActionListener{
	private JTextField tfOldUnitCost;
	private JTextField tfNewUnitCost;
	private JComboBox cbbServiceType, cbbService, cbbServiceTime ;
	private JButton btnSubmit;
	private HashMap<String, Integer> serviceMap, serviceTypeMap;
	private HashMap<Integer, Integer> serviceTypeCategoryMap;
	/**
	 * Create the panel.
	 */
	public MgtServicePage() {
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(84, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)
					.addGap(76))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblServiceType = new JLabel("Service Type");
		lblServiceType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblServiceType, "6, 4, right, default");
		
		cbbServiceType = new JComboBox();
		
		cbbServiceType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ServiceTypeDataSource stDS = new ServiceTypeDataSource();
		List<ServiceType> stList = new ArrayList<ServiceType>(); 
		stList = stDS.getAllServiceType();
		stDS.close();
		cbbServiceType.addItem("--- Choose a service ---");
		serviceTypeMap = new HashMap<String, Integer>(); 
		for(ServiceType st:stList){
			serviceTypeMap.put(st.getService_type(), st.getId());
			cbbServiceType.addItem(st.getService_type());
		}
		panel.add(cbbServiceType, "8, 4, fill, default");
		
		
		
		JLabel lblService = new JLabel("Service");
		lblService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblService, "6, 6, right, default");
		
		cbbService = new JComboBox();
		cbbService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(cbbService, "8, 6, fill, default");
		
		JLabel lblServiceTime = new JLabel("Service Time");
		lblServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblServiceTime, "6, 8, right, default");
		
		cbbServiceTime = new JComboBox();
		cbbServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(cbbServiceTime, "8, 8, fill, default");
		
		JLabel lblUnitCost = new JLabel("Unit Cost");
		lblUnitCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblUnitCost, "6, 10, right, default");
		
		tfOldUnitCost = new JTextField();
		tfOldUnitCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfOldUnitCost.setEditable(false);
		panel.add(tfOldUnitCost, "8, 10, fill, top");
		tfOldUnitCost.setColumns(10);
		
		JLabel lblNewUnitCost = new JLabel("New Unit Cost");
		lblNewUnitCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewUnitCost, "6, 12, right, default");
		
		tfNewUnitCost = new JTextField();
		tfNewUnitCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(tfNewUnitCost, "8, 12, fill, default");
		tfNewUnitCost.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		panel.add(btnSubmit, "8, 14");
		
		cbbServiceType.addActionListener(this);
		cbbService.addActionListener(this);
		cbbServiceTime.addActionListener(this);
		btnSubmit.addActionListener(this);
		setLayout(groupLayout);

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == cbbServiceType){
			if(cbbServiceType.getSelectedIndex()!=0){
				ServiceDataSource sDS = new ServiceDataSource();
				int service_type_id = serviceTypeMap.get(cbbServiceType.getSelectedItem());
				List<Service> sList = new ArrayList<Service>();
				sList = sDS.getServiceByServiceTypeId(service_type_id);
			
				sDS.close();
				serviceMap = new HashMap<String, Integer>();
			//if(cbbServiceType.getSelectedItem()==null)
				//System.out.println("Before removeAllItems");
			//cbbServiceType.addItem("Test");
				cbbService.removeAllItems(); //if there are items in cbbServiceType , ae.getSource() == cbbServiceTypeCategory will trigger
			//if(cbbServiceType.getSelectedItem()==null)
				//System.out.println("After removeAllItems");
				for(Service s:sList){
					serviceMap.put(s.getService_name(), s.getId());
					cbbService.addItem(s.getService_name()); 
				}
			
			}else
				;
			
		}  
		
		if(ae.getSource() == cbbService){
			//System.out.println("In cbbServiceType:" + cbbServiceType.getSelectedItem());
			if(cbbService.getSelectedItem() != null){
				ServiceTypeCategoryDataSource stcDS = new ServiceTypeCategoryDataSource();
				int service_id = serviceMap.get(cbbService.getSelectedItem());
		
				List<ServiceTypeCategory> stcList = new ArrayList<ServiceTypeCategory>();
				stcList = stcDS.getServiceTypeCategoryByServiceId(service_id);
				stcDS.close();
				serviceTypeCategoryMap = new HashMap<Integer, Integer>();
				cbbServiceTime.removeAllItems();
				for(ServiceTypeCategory stc:stcList){
					serviceTypeCategoryMap.put(stc.getDuration(), stc.getId());
					cbbServiceTime.addItem(stc.getDuration());
				}
			}else //cbbServiceType.getSelectedItem()==null
				; //do nothing
		}
		
		if(ae.getSource() == cbbServiceTime){
			if(cbbServiceTime.getSelectedItem() != null){
				ServiceTypeCategoryDataSource stcDS = new ServiceTypeCategoryDataSource();
				int service_type_category_id = serviceTypeCategoryMap.get(cbbServiceTime.getSelectedItem());
				double price = stcDS.getUnitCostByServiceCategoryTypeId(service_type_category_id);
				stcDS.close();
				tfOldUnitCost.setText(String.valueOf(price));
			}else
				;
		}
		
		
		if(ae.getSource()==btnSubmit){
			if(!tfNewUnitCost.getText().isEmpty()){
				int service_type_category_id = serviceTypeCategoryMap.get(cbbServiceTime.getSelectedItem());
				ServiceTypeCategoryDataSource stcDS = new ServiceTypeCategoryDataSource();
				double new_unit_cost = Double.parseDouble(tfNewUnitCost.getText());
				stcDS.updateUnitCost(new_unit_cost, service_type_category_id);
				if(stcDS.updateUnitCost(new_unit_cost, service_type_category_id))
					JOptionPane.showMessageDialog(this, "Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(this, "Failed", "Failed", JOptionPane.INFORMATION_MESSAGE);
				
				tfNewUnitCost.setText("");
				tfOldUnitCost.setText(String.valueOf(stcDS.getUnitCostByServiceCategoryTypeId(service_type_category_id)));
			}else
				JOptionPane.showMessageDialog(this, "Please enter new unit cost", "Error", JOptionPane.INFORMATION_MESSAGE);;
		}
	}
	
	
}
