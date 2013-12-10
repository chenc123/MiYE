package edu.cgu.ist303;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.cgu.ist303.funcReservation.BookPage;
import edu.cgu.ist303.funcReservation.BookRecord;
import edu.cgu.ist303.functions.MgtRoomPage;
import edu.cgu.ist303.functions.MgtServicePage;

public class funcManagement {
	final static String MGT_ROOM_PANEL = "Manage Room";
    final static String MGT_SERVICE_PANEL = "Manage Service";
	
    
	public JTabbedPane tabbedPaneManagement(){
		 JTabbedPane tabbedPane = new JTabbedPane();
		 
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        JPanel card2 = new JPanel();
	       
	        card1.add( new MgtRoomPage(),BorderLayout.CENTER);
	        card2.add(new MgtServicePage(),BorderLayout.CENTER);
	        tabbedPane.addTab(MGT_ROOM_PANEL, card1);
	        tabbedPane.addTab(MGT_SERVICE_PANEL, card2);  
	        
	       return tabbedPane;
	}
}
