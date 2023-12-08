/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import gui.EmployeeLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kietl
 */
public class SecurityConnection {
    Connection con;

    public SecurityConnection() {

    }

    public Connection Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_305", "root", "anhkiet2002");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SecurityConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SecurityConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
