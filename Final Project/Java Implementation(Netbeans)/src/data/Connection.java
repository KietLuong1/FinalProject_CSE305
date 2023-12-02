/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huong Duyen
 */
public class Connection {
    private Connection con;

    public Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/campus_security_management", "root", "root");
            System.out.println("Connected");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCon() {
        return con;
    }
    
}
