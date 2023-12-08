/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huong Duyen
 */
public class SecurityManager extends Staff {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String firstName;
    private String lastName;
    private String dob;
    private String identityNumber;
    private String password;
    private String username;
    private String manager_id;

    public SecurityManager(String firstName, String lastName, String identityNumber, String username, String password, String dob, String salary) {
        super(firstName, lastName, identityNumber, password, dob);
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "Hd301202");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SecurityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SecurityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean register() {
        try {
            pst = con.prepareStatement("select * from manager;");
            rs = pst.executeQuery();

            pst = con.prepareStatement("insert into manager (name, password, salary) values (?,?,?)");
            pst.setString(2, firstName.concat(" ").concat(lastName));
            pst.setString(4, password);
            pst.setString(5, "0");

            int k = pst.executeUpdate();

            if (k == 1) {
                return true;
            }
        } catch (Exception e) { 
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean login() {
        try {
            pst = con.prepareStatement("select * from manager where username = '" + username + "'and password = '" + password + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);

        }
        return false;
    }

    public boolean createDuty(String user_id, String place_id, String duty_date, String start_time, String end_time) {
        try {
            pst = con.prepareStatement("select * from duty_schedule;");
            rs = pst.executeQuery();
          
            pst = con.prepareStatement("insert into duty_schedule (user_id, place_id, duty_date, start_time, end_time) values (?,?,?,?,?,?);");
            pst.setString(2, user_id);
            pst.setString(3, place_id);
            pst.setString(4, duty_date);
            pst.setString(5, start_time);
            pst.setString(6, end_time);

            int k = pst.executeUpdate();

            if (k == 1) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public ResultSet getLeaveRequest() {
        try {
            pst = con.prepareStatement("select * from leave_request");
            rs = pst.executeQuery();

            return rs;
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public ResultSet getLeaveRequest(String user_id) {
        try {
            pst = con.prepareStatement("select * from leave_request where staff_id =?");
            pst.setString(1, user_id);
            rs = pst.executeQuery();

            return rs;
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public boolean setRequest(String user_id, boolean isAccept) {
        try {
            int option = (isAccept) ? 1 : 0;
            pst = con.prepareStatement("update from leave_request set is_approved = " + option + " where user_id =?;");
            pst.setString(1, user_id);

            int k = pst.executeUpdate();
            if (k == 1) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
