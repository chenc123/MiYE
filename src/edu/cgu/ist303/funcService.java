package edu.cgu.ist303;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.cgu.ist303.functions.BookService;
import edu.cgu.ist303.functions.MgtRoomPage;

public class funcService {
	final static String BOOK_SERVICE_PANEL = "Service Reservation";
    final static String SERVICE_RECORD_PANEL = "Service Record";
	
    
	public JTabbedPane tabbedPaneService(){
		 JTabbedPane tabbedPane = new JTabbedPane();
		 
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	      //  JPanel card2 = new JPanel();
	       
	        card1.add( new BookService(),BorderLayout.CENTER);
	       // card2.add(new BookRecord());
	        tabbedPane.addTab(BOOK_SERVICE_PANEL, card1);
	       // tabbedPane.addTab(SERVICE_RECORD_PANEL, card2);  
	        
	       return tabbedPane;
	}
}
