/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package entity;

/**
 *
 * @author Huong Duyen
 */
public interface UserFactory {

    Staff creStaff(String firstName, String lastName, String identity, String password, String dob);

    class SecurityFactory implements UserFactory {

        private int totalLeaveAllowed;

        public SecurityFactory(int totalLeaveAllowed) {
            this.totalLeaveAllowed = totalLeaveAllowed;
        }

        public Staff createUser(String firstName, String lastName, String identityNumber, String password, String dob) {
            return new SecurityStaff(firstName, lastName, identityNumber, password, dob, password);
        }

        @Override
        public Staff creStaff(String firstName, String lastName, String identity, String password, String dob) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    class ManagerFactory implements UserFactory {

        public Staff createUser(String firstName, String lastName, String identityNumber, String password, String dob) {
            return new SecurityManager(firstName, lastName, identityNumber, password, dob, dob);
        }

        @Override
        public Staff creStaff(String firstName, String lastName, String identity, String password, String dob) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
