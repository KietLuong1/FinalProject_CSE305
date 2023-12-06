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

    Staff creStaff(String name, String identity, String password);

    class SecurityFactory implements UserFactory {

        private int totalLeaveAllowed;

        public SecurityFactory(int totalLeaveAllowed) {
            this.totalLeaveAllowed = totalLeaveAllowed;
        }

        public Staff createUser(String name, String identityNumber, String password) {
            return new SecurityStaff(name, identityNumber, password);
        }

        @Override
        public Staff creStaff(String name, String identity, String password) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    class ManagerFactory implements UserFactory {

        public Staff createUser(String name, String identityNumber, String password) {
            return new SecurityManager(name, identityNumber, password);
        }

        @Override
        public Staff creStaff(String name, String identity, String password) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
