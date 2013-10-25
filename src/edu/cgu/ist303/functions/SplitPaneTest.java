package edu.cgu.ist303.functions;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
 
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class SplitPaneTest extends JPanel {
	 private JSplitPane splitPane;
	 private JPanel pnlMenu, pnlContent;
	 private JButton btnCustomer, btnReservation;
	
	 public SplitPaneTest(){
		 btnCustomer = new JButton("Customer Management");
		 btnReservation = new JButton("Reservation");
		 pnlMenu = new JPanel(new GridLayout(0,1));
		 pnlMenu.add(btnCustomer);
		 pnlMenu.add(btnReservation);
		 
		 splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				 pnlMenu, pnlContent);
		 splitPane.setOneTouchExpandable(true);
		 splitPane.setDividerLocation(150);
		 
		 
		 Dimension minimumSize = new Dimension(100, 50);
	     pnlMenu.setMinimumSize(minimumSize);
	    //pnlContent.setMinimumSize(minimumSize);
	
	        //Provide a preferred size for the split pane.
	        splitPane.setPreferredSize(new Dimension(400, 200));
	        
	 }
	 
	 public JSplitPane getSplitPane() {
	        return splitPane;
	    }
	 
	 private static void createAndShowGUI() {
		 
	        //Create and set up the window.
	        JFrame frame = new JFrame("SplitPaneTest");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        SplitPaneTest splitPaneTest = new SplitPaneTest();
	        frame.getContentPane().add(splitPaneTest.getSplitPane());
	 
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}
