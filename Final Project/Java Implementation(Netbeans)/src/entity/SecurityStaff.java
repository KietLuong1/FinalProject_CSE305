/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Huong Duyen
 */
public class SecurityStaff extends Staff{
    private int totalLeaveAllowed;
    private int leaveTaken;
    
    public SecurityStaff(String name, String identityNumber, String password) {
        super(name, identityNumber, password);
    }
    
}
