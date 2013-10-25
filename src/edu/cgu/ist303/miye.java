package edu.cgu.ist303;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import edu.cgu.ist303.db.*;
import java.awt.*;
import java.awt.event.*;


public class miye extends JFrame implements ActionListener {

	private JPanel mainPane, pnlCardDeck, pnlCustomer;
	private JTextField textFieldId;
	private JPasswordField textFieldPassword;
	private JButton btnLogin, btnShowReservationCard, btnShowCustomerCard,btnShowServiceCard;
	private CardLayout cardManager;
	private JSplitPane splitPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					miye frame = new miye();
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
	public miye() {
		setResizable(false);
		setTitle("MiYE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 800, 600);
		mainPane = new JPanel(); //card 1
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		cardManager = new CardLayout();
		mainPane.setLayout(cardManager);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(153, 153, 255));
		panelLogin.setForeground(SystemColor.desktop);
		mainPane.add(panelLogin, "Login"); // add card 1
		panelLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 150));
		
	
		
		JPanel panelLoginInput = new JPanel();
		panelLoginInput.setBackground(new Color(204, 204, 255));
		
		panelLoginInput.setBorder(new EmptyBorder(20, 15, 30, 15));
		panelLogin.add(panelLoginInput);
		panelLoginInput.setLayout(new GridLayout(5, 0, 0, 5));
		//panelLoginInput.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLoginInput.add(lblID);
		
		textFieldId = new JTextField();
		textFieldId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLoginInput.add(textFieldId);
		textFieldId.setColumns(15);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLoginInput.add(lblPassword);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLoginInput.add(textFieldPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelLoginInput.add(btnLogin);
		btnLogin.addActionListener(this);
		
		
		
		splitPane = new JSplitPane();
		mainPane.add(splitPane, "Menu"); // add card2
		
		JPanel panelButtons = new JPanel();
		splitPane.setLeftComponent(panelButtons);
		panelButtons.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnShowReservationCard = new JButton("Reservation");
		panelButtons.add(btnShowReservationCard);
		btnShowReservationCard.addActionListener(this);
		
		
		btnShowCustomerCard = new JButton("Customer");
		panelButtons.add(btnShowCustomerCard);
		btnShowCustomerCard.addActionListener(this);
		
		btnShowServiceCard = new JButton("Services");
		panelButtons.add(btnShowServiceCard);
		btnShowServiceCard.addActionListener(this);
		
		pnlCardDeck = new JPanel();
		splitPane.setRightComponent(pnlCardDeck);
		pnlCardDeck.setLayout(new CardLayout(0, 0));
		
		
		funcCustomer fCustomer = new funcCustomer();
		pnlCardDeck.add(fCustomer.tabPaneCustomer(),"Customer");
		
		funcReservation fReservation = new funcReservation();
		pnlCardDeck.add(fReservation.TabbedPaneReservation(),"Reservation");
		
		JPanel pnlService = new JPanel();
		pnlCardDeck.add(pnlService, "Service");
		
		JLabel lblService = new JLabel("This Pane is for Service");
		pnlService.add(lblService);
		
		
	}

	 public void actionPerformed( ActionEvent e )
	   {
	     String username = textFieldId.getText();
	     String password = String.valueOf(textFieldPassword.getPassword()); //transfer char[] type to String
	    
		if(e.getSource() == btnShowReservationCard)
		{
			cardManager = (CardLayout) pnlCardDeck.getLayout();
			cardManager.show(pnlCardDeck, (String)"Reservation");
			
		}else if (e.getSource()== btnShowCustomerCard)
		{
			cardManager = (CardLayout) pnlCardDeck.getLayout();
			cardManager.show(pnlCardDeck, (String)"Customer");
			
		}else if (e.getSource()== btnShowServiceCard)
		{
			cardManager = (CardLayout) pnlCardDeck.getLayout();
			cardManager.show(pnlCardDeck, (String)"Service");
		}
		
	    
	     
	     if ( e.getSource() == btnLogin )    
				 if( username != "" && password != "" && loginValidation(username,password) == true)
				 {
					JOptionPane.showMessageDialog(this, "Login Success!");
					cardManager = (CardLayout) mainPane.getLayout();
					cardManager.show(mainPane, (String)"Menu");
				 }
				 else
				 {	
					  JOptionPane.showMessageDialog(this, "Invalid ID or Password. Please Try again.", "Login Failed",
			                    JOptionPane.ERROR_MESSAGE);
				 } 	
	     	textFieldId.setText("");
			textFieldPassword.setText("");
			 
	   }
	 
	 public boolean loginValidation(String usernameInput, String passwordInput){
		 PersonnelDataSource pDS = new PersonnelDataSource();
		 String password = pDS.getPasswordByUsername(usernameInput);
		 pDS.close();
		 return password.equals(passwordInput); 
	 }
	
}
