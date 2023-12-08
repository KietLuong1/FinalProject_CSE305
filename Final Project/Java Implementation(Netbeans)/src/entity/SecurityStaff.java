/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gui.EmployeeAbility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JOptionpane;

/**
 *
 * @author Huong Duyen
 */
public class SecurityStaff extends Staff {

    private int totalLeaveAllowed = 5;
    private int leaveTaken;
    private String identityNumber;
    private String password;
    private String dob;
    private String firstName;
    private String lastName;
    private String staff_id;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public SecurityStaff(String firstName, String lastName, String identityNumber, String password, String dob, String staff_id) {
        super(firstName, lastName, identityNumber, password, dob);
        connect();
        this.staff_id = staff_id;

    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Hd301202");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean register() {
        try {
            pst = con.prepareStatement("select * from staff;");
            rs = pst.executeQuery();

            pst = con.prepareStatement("insert into staff (name, password) values (?,?,?)");
            pst.setString(2, firstName.concat(" ").concat(lastName));
            pst.setString(4, password);

            int k = pst.executeUpdate();

            if (k == 1) {
                JOptionPane.showMessageDialog(null, "Sign up successfully");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Sign up falied");
            }

        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean login() {
        try {
            pst = con.prepareStatement("select * from staff where staff_id = '" + staff_id + "'and staff_password = '" + password + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login successfully");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Login failed");
            }
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public ResultSet viewDuty() {
        try {
            pst = con.prepareStatement("select place_id, duty_date, start_time, end_time from duty_schdule where user_id =?;");
            pst.setString(1, staff_id);
            rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Successfully");
                return rs;
            } else {
                JOptionPane.showMessageDialog(null, "No record found");
            }

        } catch (Exception e) {
        }
        return null;
    }

    public boolean requestLeave() {
        try {
            pst = con.prepareStatement("insert into leave_request staff_id values (?)");
            pst.setString(1, staff_id);

            int k = pst.executeUpdate();

            if (k == 1) {
                if (leaveTaken < totalLeaveAllowed) {
                    JOptionPane.showMessageDialog(null, "Successfully!");
                    leaveTaken++;
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Failed!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (Exception e) {
        }
        return false;
    }

}
