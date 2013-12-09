package edu.cgu.ist303.functions;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import edu.cgu.ist303.db.PaymentType;
import edu.cgu.ist303.db.PaymentTypeDataSource;
import edu.cgu.ist303.db.ServiceType;
import edu.cgu.ist303.db.ServiceTypeDataSource;
import edu.cgu.ist303.db.Service;
import edu.cgu.ist303.db.ServiceTypeCategory;
import edu.cgu.ist303.db.ServiceTypeCategoryDataSource;
import edu.cgu.ist303.db.ServiceDataSource;

import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;

public class BookService extends JPanel implements ActionListener {

	private JPanel contentPane;
	private JTextField tfRoomName;
	private JTextField tfPrice;
	private JTextField tfPaymentField1, tfPaymentField2, tfPaymentField3;
	private HashMap<String, Integer> serviceMap, serviceTypeMap, paymentTypeMap;
	private HashMap<Integer, Integer> serviceTypeCategoryMap;
	private JComboBox cbbServiceType, cbbService, cbbServiceTypeCategory;
	private JComboBox cbbPaymentType;
	private JLabel labPaymentField1, labPaymentField2, labPaymentField3;
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookService frame = new BookService();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public BookService() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		
		JPanel pnlServiceAppointment = new JPanel();
		
		JPanel pnlPaymentInfo = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlServiceAppointment, GroupLayout.PREFERRED_SIZE, 592, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlPaymentInfo, GroupLayout.PREFERRED_SIZE, 593, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(pnlServiceAppointment, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlPaymentInfo, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		pnlPaymentInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(105dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel labPaymentType = new JLabel("Payment Type");
		labPaymentType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPaymentInfo.add(labPaymentType, "2, 2, right, default");
		
		cbbPaymentType = new JComboBox();
		PaymentTypeDataSource ptDS = new PaymentTypeDataSource();
		List<PaymentType> ptList = new ArrayList<PaymentType>(); 
		ptList = ptDS.getAllPaymentType();
		ptDS.close();
		cbbPaymentType.addItem("-- Choose Payment Type --");
		paymentTypeMap = new HashMap<String, Integer>(); 
		for(PaymentType pt:ptList){
			paymentTypeMap.put(pt.getPayment_type(), pt.getId());
			cbbPaymentType.addItem(pt.getPayment_type());
		}
		cbbPaymentType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPaymentInfo.add(cbbPaymentType, "4, 2, left, default");
		
		labPaymentField1 = new JLabel("Field1");
		labPaymentField1.setVisible(false);
		labPaymentField1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPaymentInfo.add(labPaymentField1, "2, 4, right, default");
		
		tfPaymentField1 = new JTextField();
		tfPaymentField1.setVisible(false);
		tfPaymentField1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPaymentInfo.add(tfPaymentField1, "4, 4, left, default");
		tfPaymentField1.setColumns(16);
		
		labPaymentField2 = new JLabel("Field2");
		labPaymentField2.setVisible(false);
		labPaymentField2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPaymentInfo.add(labPaymentField2, "2, 6, right, default");
		
		tfPaymentField2 = new JTextField();
		tfPaymentField2.setVisible(false);
		tfPaymentField2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPaymentInfo.add(tfPaymentField2, "4, 6, left, default");
		tfPaymentField2.setColumns(16);
		
		labPaymentField3 = new JLabel("Field3");
		labPaymentField3.setVisible(false);
		labPaymentField3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlPaymentInfo.add(labPaymentField3, "2, 8, right, default");
		
		tfPaymentField3 = new JTextField();
		tfPaymentField3.setVisible(false);
		tfPaymentField3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPaymentField3.setText("");
		pnlPaymentInfo.add(tfPaymentField3, "4, 8, left, default");
		tfPaymentField3.setColumns(16);
		pnlServiceAppointment.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(103dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel labRoomName = new JLabel("Room Name");
		labRoomName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(labRoomName, "2, 2, right, default");
		
		tfRoomName = new JTextField();
		tfRoomName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(tfRoomName, "4, 2, left, default");
		tfRoomName.setColumns(10);
		
		JLabel labServiceDate = new JLabel("Service Date");
		labServiceDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(labServiceDate, "2, 4, right, default");
		
		JDateChooser dcServiceDate = new JDateChooser();
		pnlServiceAppointment.add(dcServiceDate, "4, 4, left, fill");
		
		JLabel labService = new JLabel("Service Type");
		labService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(labService, "2, 6, right, default");
		
		cbbServiceType = new JComboBox();
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
		cbbServiceType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(cbbServiceType, "4, 6, left, default");
		
		JLabel labServiceType = new JLabel("Service");
		labServiceType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(labServiceType, "2, 8, right, default");
		
		cbbService = new JComboBox();
		cbbService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(cbbService, "4, 8, left, default");
		
		JLabel labServiceTime = new JLabel("Service Time");
		labServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(labServiceTime, "2, 10, right, default");
		
		cbbServiceTypeCategory = new JComboBox();
		cbbServiceTypeCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(cbbServiceTypeCategory, "4, 10, left, default");
		
		JLabel labPrice = new JLabel("Price");
		labPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(labPrice, "2, 12, right, default");
		
		tfPrice = new JTextField();
		tfPrice.setEditable(false);
		tfPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlServiceAppointment.add(tfPrice, "4, 12, left, default");
		tfPrice.setColumns(10);
		contentPane.setLayout(gl_contentPane);
		
		cbbServiceType.addActionListener(this);
		cbbService.addActionListener(this);
		cbbServiceTypeCategory.addActionListener(this);
		cbbPaymentType.addActionListener(this);
		
		setLayout(gl_contentPane);
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
				cbbServiceTypeCategory.removeAllItems();
				for(ServiceTypeCategory stc:stcList){
					serviceTypeCategoryMap.put(stc.getDuration(), stc.getId());
					cbbServiceTypeCategory.addItem(stc.getDuration());
				}
			}else //cbbServiceType.getSelectedItem()==null
				; //do nothing
		}
		
		if(ae.getSource() == cbbServiceTypeCategory){
			if(cbbServiceTypeCategory.getSelectedItem() != null){
				ServiceTypeCategoryDataSource stcDS = new ServiceTypeCategoryDataSource();
				int service_type_category_id = serviceTypeCategoryMap.get(cbbServiceTypeCategory.getSelectedItem());
				double price = stcDS.getPriceByServiceCategoryTypeId(service_type_category_id);
				stcDS.close();
				tfPrice.setText(String.valueOf(price));
			}else
				;
		}
		if(ae.getSource() == cbbPaymentType){
			if(cbbPaymentType.getSelectedIndex() != 0){
				if(cbbPaymentType.getSelectedItem().toString().equals("Credit Card")){
					labPaymentField1.setText("Name on Card");
					labPaymentField2.setText("Card Number");
					labPaymentField3.setText("Valid Thru");
					labPaymentField1.setVisible(true);
					labPaymentField2.setVisible(true);
					labPaymentField3.setVisible(true);
					tfPaymentField1.setVisible(true);
					tfPaymentField2.setVisible(true);
					tfPaymentField3.setVisible(true);
				}else if (cbbPaymentType.getSelectedItem().toString().equals("Check")){
					labPaymentField1.setText("Bank");
					labPaymentField2.setText("Account Number");
					labPaymentField3.setText("Routing Number");
					labPaymentField1.setVisible(true);
					labPaymentField2.setVisible(true);
					labPaymentField3.setVisible(true);
					tfPaymentField1.setVisible(true);
					tfPaymentField2.setVisible(true);
					tfPaymentField3.setVisible(true);
				}else if(cbbPaymentType.getSelectedItem().toString().equals("Cash")){
					labPaymentField1.setVisible(false);
					labPaymentField2.setVisible(false);
					labPaymentField3.setVisible(false);
					tfPaymentField1.setVisible(false);
					tfPaymentField2.setVisible(false);
					tfPaymentField3.setVisible(false);
				}else{
					labPaymentField1.setText("Code");
					labPaymentField1.setVisible(true);
					labPaymentField2.setVisible(false);
					labPaymentField3.setVisible(false);
					tfPaymentField1.setVisible(true);
					tfPaymentField2.setVisible(false);
					tfPaymentField3.setVisible(false);
				}
					
				
			}else
				;
		}
	}
	
	
	
	
}
