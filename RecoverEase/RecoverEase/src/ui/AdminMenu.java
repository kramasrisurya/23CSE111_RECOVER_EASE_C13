package ui;

import model.User;
import service.AuthService;
import service.ReportManager;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private User admin;
    private ReportManager manager;
    private AuthService auth;
    private Scanner sc;

    public AdminMenu(User admin, ReportManager manager, AuthService auth, Scanner sc) {
        this.admin   = admin;
        this.manager = manager;
        this.auth    = auth;
        this.sc      = sc;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== INSTITUTE HEAD PANEL [ " + admin.getName() + " ] =====");
            System.out.println("1.  View All Items (Lost + Found)");
            System.out.println("2.  View All Lost Items");
            System.out.println("3.  View All Found Items");
            System.out.println("4.  Delete Any Item by ID");
            System.out.println("5.  Edit Any Item by ID");
            System.out.println("6.  Search Items");
            System.out.println("7.  View All Registered Users");
            System.out.println("8.  Delete a User Account");
            System.out.println("9.  Report a Lost Item");
            System.out.println("10. Report a Found Item");
            System.out.println("0.  Logout");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    manager.viewAll();
                    break;
                case "2":
                    manager.viewAllLost();
                    break;
                case "3":
                    manager.viewAllFound();
                    break;
                case "4":
                    deleteItem();
                    break;
                case "5":
                    editItem();
                    break;
                case "6":
                    searchItems();
                    break;
                case "7":
                    viewUsers();
                    break;
                case "8":
                    deleteUser();
                    break;
                case "9":
                    reportLost();
                    break;
                case "10":
                    reportFound();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void deleteItem() {
        System.out.print("Enter Item ID to delete: ");
        String id = sc.nextLine().trim();
        manager.deleteItem(id, admin);
    }

    private void editItem() {
        System.out.print("Enter Item ID to edit: ");    String id    = sc.nextLine().trim();
        System.out.print("New Title (Enter to keep): "); String title = sc.nextLine().trim();
        System.out.print("New Description (Enter to keep): "); String desc = sc.nextLine().trim();
        System.out.print("New Location (Enter to keep): "); String loc  = sc.nextLine().trim();
        manager.editItem(id, title, desc, loc, admin);
    }

    private void searchItems() {
        System.out.print("Search keyword: ");
        String kw = sc.nextLine().trim();
        manager.search(kw);
    }

    private void viewUsers() {
        List<User> users = auth.getAllUsers();
        System.out.println("\n--- Registered Users ---");
        for (User u : users) {
            System.out.println("Name : " + u.getName() +
                               " | Email: " + u.getEmail() +
                               " | Role: " + u.getRole());
        }
    }

    private void deleteUser() {
        System.out.print("Enter email of user to delete: ");
        String email = sc.nextLine().trim();
        auth.deleteUser(email);
    }

    private void reportLost() {
        System.out.println("\n--- Report Lost Item (Admin) ---");
        System.out.print("Title       : "); String title = sc.nextLine().trim();
        System.out.print("Description : "); String desc  = sc.nextLine().trim();
        System.out.print("Location    : "); String loc   = sc.nextLine().trim();
        System.out.print("Date        : "); String date  = sc.nextLine().trim();
        System.out.print("Image Path (Enter to skip): "); String img = sc.nextLine().trim();
        String id = manager.postLost(title, desc, loc, date, img, admin);
        System.out.println("Lost item posted! ID: " + id);
    }

    private void reportFound() {
        System.out.println("\n--- Report Found Item (Admin) ---");
        System.out.print("Title       : "); String title = sc.nextLine().trim();
        System.out.print("Description : "); String desc  = sc.nextLine().trim();
        System.out.print("Location    : "); String loc   = sc.nextLine().trim();
        System.out.print("Date        : "); String date  = sc.nextLine().trim();
        System.out.print("Image Path (Enter to skip): "); String img = sc.nextLine().trim();
        String id = manager.postFound(title, desc, loc, date, img, admin);
        System.out.println("Found item posted! ID: " + id);
    }
}
