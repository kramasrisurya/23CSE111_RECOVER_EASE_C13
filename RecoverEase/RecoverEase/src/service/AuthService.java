package service;

import model.User;
import model.User.Role;
import util.FileHandler;

import java.util.List;

public class AuthService {

    private List<User> users;
    private User currentUser;

    public AuthService() {
        users = FileHandler.loadUsers();
        createDefaultAdminIfAbsent();
    }

    // Creates a default Institute Head on first run
    private void createDefaultAdminIfAbsent() {
        for (User u : users) {
            if (u.getRole() == Role.INSTITUTE_HEAD) return;
        }
        User admin = new User("Admin", "admin@recoverease.in", "Admin@123", Role.INSTITUTE_HEAD);
        users.add(admin);
        FileHandler.saveUsers(users);
        System.out.println("Default Institute Head created: admin@recoverease.in / Admin@123");
    }

    // Returns true if signup succeeded
    public boolean signup(String name, String email, String password) {
        // Check duplicate email
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered.");
                return false;
            }
        }
        User newUser = new User(name, email, password, Role.USER);
        users.add(newUser);
        FileHandler.saveUsers(users);
        System.out.println("Signup successful! You can now log in.");
        return true;
    }

    // Returns true if login succeeded
    public boolean login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Used by admin to delete a user account from users.txt
    public boolean deleteUser(String email) {
        if (email.equalsIgnoreCase(currentUser.getEmail())) {
            System.out.println("You cannot delete your own account.");
            return false;
        }
        boolean removed = users.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
        if (removed) {
            FileHandler.saveUsers(users);
            System.out.println("User account deleted: " + email);
        } else {
            System.out.println("No user found with email: " + email);
        }
        return removed;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
