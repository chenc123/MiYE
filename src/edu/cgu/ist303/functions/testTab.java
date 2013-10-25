package edu.cgu.ist303.functions;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;

import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;

import edu.cgu.ist303.db.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.FlowLayout;


public class testTab extends JFrame implements ActionListener {
	private JButton btnOne, btnTwo, btnThree;
	private JPanel contentPane;
	JPanel pnlDeck;
	JTabbedPane tabOne, tabTwo, tabThree;
	CardLayout cardManager = new CardLayout();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testTab frame = new testTab();
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
	public testTab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnOne = new JButton("One");
		panel.add(btnOne);
		btnOne.addActionListener(this);
		btnTwo = new JButton("Two");
		panel.add(btnTwo);
		btnTwo.addActionListener(this);
		btnThree = new JButton("Three");
		panel.add(btnThree);
		btnThree.addActionListener(this);
		pnlDeck = new JPanel();
		splitPane.setRightComponent(pnlDeck);
		pnlDeck.setLayout(cardManager);
		
		tabOne = new JTabbedPane(JTabbedPane.TOP);
		tabOne.addTab("A",new JPanel());
		tabOne.addTab("B",new JPanel());
		pnlDeck.add(tabOne, "tabOne");
		
		tabTwo = new JTabbedPane(JTabbedPane.TOP);
		tabTwo.addTab("C",new JPanel());
		tabTwo.addTab("D",new JPanel());
		pnlDeck.add(tabTwo, "tabTwo");
		
		tabThree = new JTabbedPane(JTabbedPane.TOP);
		tabThree.addTab("E",new JPanel());
		tabThree.addTab("F",new JPanel());
		pnlDeck.add(tabThree, "tabThree");
	}
	
	public void actionPerformed( ActionEvent e )
	{
	    
	    
	    
	    
		if(e.getSource() == btnOne)
		{
			cardManager = (CardLayout) pnlDeck.getLayout();
			cardManager.show(pnlDeck, (String)"tabOne");
			
		}else if (e.getSource()== btnTwo)
		{
			cardManager = (CardLayout) pnlDeck.getLayout();
			cardManager.show(pnlDeck, (String)"tabTwo");
			
		}else if (e.getSource()== btnThree)
		{
			cardManager = (CardLayout) pnlDeck.getLayout();
			cardManager.show(pnlDeck, (String)"tabThree");
		}

}
}
