import model.User;
import model.User.Role;
import service.AuthService;
import service.ReportManager;
import ui.AdminMenu;
import ui.UserMenu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc       = new Scanner(System.in);
        AuthService auth = new AuthService();
        ReportManager rm = new ReportManager();

        System.out.println("============================================");
        System.out.println("   Welcome to RecoverEase - Lost & Found    ");
        System.out.println("============================================");

        boolean appRunning = true;
        while (appRunning) {
            System.out.println("\n1. Login");
            System.out.println("2. Sign Up");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    handleLogin(sc, auth, rm);
                    break;
                case "2":
                    handleSignup(sc, auth);
                    break;
                case "0":
                    appRunning = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    private static void handleLogin(Scanner sc, AuthService auth, ReportManager rm) {
        System.out.print("Email    : "); String email    = sc.nextLine().trim();
        System.out.print("Password : "); String password = sc.nextLine().trim();

        if (auth.login(email, password)) {
            User user = auth.getCurrentUser();
            System.out.println("Login successful! Welcome, " + user.getName() + " [" + user.getRole() + "]");

            if (user.getRole() == Role.INSTITUTE_HEAD) {
                new AdminMenu(user, rm, auth, sc).show();
            } else {
                new UserMenu(user, rm, sc).show();
            }
            auth.logout();
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private static void handleSignup(Scanner sc, AuthService auth) {
        System.out.println("\n--- Sign Up ---");
        System.out.print("Name     : "); String name     = sc.nextLine().trim();
        System.out.print("Email    : "); String email    = sc.nextLine().trim();
        System.out.print("Password : "); String password = sc.nextLine().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }
        auth.signup(name, email, password);
    }
}
