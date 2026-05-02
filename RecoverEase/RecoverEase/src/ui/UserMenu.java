package ui;

import model.User;
import service.ReportManager;
import java.util.Scanner;

public class UserMenu {

    private User user;
    private ReportManager manager;
    private Scanner sc;

    public UserMenu(User user, ReportManager manager, Scanner sc) {
        this.user    = user;
        this.manager = manager;
        this.sc      = sc;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== USER MENU [ " + user.getName() + " ] =====");
            System.out.println("1. Report a Lost Item");
            System.out.println("2. Report a Found Item");
            System.out.println("3. View All Lost Items");
            System.out.println("4. View All Found Items");
            System.out.println("5. View My Posts");
            System.out.println("6. Edit My Post");
            System.out.println("7. Delete My Post");
            System.out.println("8. Search Items");
            System.out.println("9. Find Matches for My Lost Item");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    reportLost();
                    break;
                case "2":
                    reportFound();
                    break;
                case "3":
                    manager.viewAllLost();
                    break;
                case "4":
                    manager.viewAllFound();
                    break;
                case "5":
                    manager.viewMyPosts(user);
                    break;
                case "6":
                    editPost();
                    break;
                case "7":
                    deletePost();
                    break;
                case "8":
                    searchItems();
                    break;
                case "9":
                    findMatches();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void reportLost() {
        System.out.println("\n--- Report Lost Item ---");
        System.out.print("Title       : "); String title = sc.nextLine().trim();
        System.out.print("Description : "); String desc  = sc.nextLine().trim();
        System.out.print("Location    : "); String loc   = sc.nextLine().trim();
        System.out.print("Date Lost   : "); String date  = sc.nextLine().trim();
        System.out.print("Image Path (press Enter to skip): "); String img = sc.nextLine().trim();

        String id = manager.postLost(title, desc, loc, date, img, user);
        System.out.println("Lost item reported! ID: " + id);
    }

    private void reportFound() {
        System.out.println("\n--- Report Found Item ---");
        System.out.print("Title       : "); String title = sc.nextLine().trim();
        System.out.print("Description : "); String desc  = sc.nextLine().trim();
        System.out.print("Location    : "); String loc   = sc.nextLine().trim();
        System.out.print("Date Found  : "); String date  = sc.nextLine().trim();
        System.out.print("Image Path (press Enter to skip): "); String img = sc.nextLine().trim();

        String id = manager.postFound(title, desc, loc, date, img, user);
        System.out.println("Found item reported! ID: " + id);
    }

    private void editPost() {
        System.out.print("Enter Item ID to edit: "); String id = sc.nextLine().trim();
        System.out.print("New Title (Enter to keep): ");    String title = sc.nextLine().trim();
        System.out.print("New Description (Enter to keep): "); String desc = sc.nextLine().trim();
        System.out.print("New Location (Enter to keep): "); String loc  = sc.nextLine().trim();
        manager.editItem(id, title, desc, loc, user);
    }

    private void deletePost() {
        System.out.print("Enter Item ID to delete: "); String id = sc.nextLine().trim();
        manager.deleteItem(id, user);
    }

    private void searchItems() {
        System.out.print("Search keyword: "); String kw = sc.nextLine().trim();
        manager.search(kw);
    }

    private void findMatches() {
        System.out.print("Enter your Lost Item ID: "); String id = sc.nextLine().trim();
        manager.showMatches(id);
    }
}
