package edu.cgu.ist303;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 *
 * @author Lin
 */
class javaconnect {

    static Connection ConnecrDb() {
          try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/MIYE", 
                        "root", "ist303@cgu");
        JOptionPane.showMessageDialog(null, "Connected");
        return con;

    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}








