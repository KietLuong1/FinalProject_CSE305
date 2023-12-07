/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gui.EmployeeAbility;
import gui.ManagerAbility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    private String salary;
    private String manager_id;
    private String user_id;

    public SecurityManager(String firstName, String lastName, String identityNumber, String password, String dob, String salary) {
        super(firstName, lastName, identityNumber, password, dob);
        this.salary = salary;
        connect();
        register();
        login();
        createDuty(user_id);
        getLeaveRequest(user_id);
        approveRequest(user_id);
        declineRequest(user_id);
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

    public void register() {
        int id;
        try {
            pst = con.prepareStatement("select * from manager;");
            rs = pst.executeQuery();

            String s = "";

            if (rs.next() == false) {
                id = 1;
            } else {
                do {
                    s = rs.getString(1);
                } while (rs.next() == true);

                String s1 = s.substring(0, s.length());
                id = Integer.parseInt(s1) + 1;
            }
            pst = con.prepareStatement("insert into manager (manager_id,name, password, salary) values (?,?,?,?)");
            pst.setString(1, String.valueOf(id));
            pst.setString(2, firstName.concat(" ").concat(lastName));
            pst.setString(4, password);
            pst.setString(5, salary);

            int k = pst.executeUpdate();

            pst = con.prepareStatement("insert into user (user_id, name, identity, password, dob) values (?,?,?,?,?);");
            pst.setString(1, String.valueOf(id));
            pst.setString(2, firstName.concat(" ").concat(lastName));
            pst.setString(3, identityNumber);
            pst.setString(4, password);
            pst.setString(5, dob);

            int k2 = pst.executeUpdate();

            if (k == 1 && k2 == 1) {
                JOptionPane.showMessageDialog(null, "Sign up successfully" + "Your Manager_ID is: " + id);
            } else {
                JOptionPane.showMessageDialog(null, "Sign up falied");
            }
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void login() {
        try {
            pst = con.prepareStatement("select * from manager where manager_id = '" + manager_id + "'and password = '" + password + "'");
            rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login successfully");
                ManagerAbility manager = new ManagerAbility();
                manager.setVisible(true);
                manager.pack();
            } else {
                JOptionPane.showMessageDialog(null, "Login failed");
            }
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void createDuty(String user_id) {
        try {
            pst = con.prepareStatement("select * from duty_schedule;");
            rs = pst.executeQuery();

            String s = "";
            int id;
            id = 0;
            String duty_id;

            if (rs.next() == false) {
                duty_id = "D1";
            } else {
                do {
                    System.out.println(rs.getString(1));
                    s = rs.getString(1);
                } while (rs.next() == true);

                String s1 = s.substring(0, 1);
                String s2 = s.substring(1, s.length());

                id = Integer.parseInt(s2) + 1;
                duty_id = s1.concat(Integer.toString(id));
            }

            pst = con.prepareStatement("insert into duty_schedule (schedule_id, user_id, place_id, duty_date, start_time, end_time) values (?,?,?,?,?,?);");
            pst.setString(1, duty_id);
//            khúc này hông biết lấy dữ liệu insert vô sao

            int k = pst.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void getLeaveRequest(String user_id) {
        try {
            pst = con.prepareStatement("select * from leave_request where user_id=?;");
            pst.setString(1, user_id);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                // khúc này show cho thằng manager coi mấy cái leave request
            }
        } catch (Exception e) {
            Logger.getLogger(SecurityStaff.class.getName()).log(Level.SEVERE, null, e);

        }
    }
    
    public void approveRequest(String user_id) {
        try {
            pst = con.prepareStatement("update from leave_request set is_approved = 1 where user_id =?;");
            pst.setString(1, user_id);
            
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void declineRequest(String user_id) {
        try {
            pst = con.prepareStatement("update from leave_request set is_approved = 0 where user_id =?;");
            pst.setString(1, user_id);
            
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
}
