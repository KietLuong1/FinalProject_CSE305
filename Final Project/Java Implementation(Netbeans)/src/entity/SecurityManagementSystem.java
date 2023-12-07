/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Huong Duyen
 */
public class SecurityManagementSystem {

    private List<Staff> users;

    public SecurityManagementSystem() {
        this.users = new ArrayList<>();
    }

    public void registerUser(UserFactory factory, String name, String identityNumber, String password) {
        
        Staff user = factory.creStaff(name, name, identityNumber, password, name);
        users.add(user);
    }
}
