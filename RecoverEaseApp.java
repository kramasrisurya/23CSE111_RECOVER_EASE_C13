package version2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RecoverEaseApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecoverEaseSystem system = new RecoverEaseSystem();
        boolean running = true;

        System.out.println("=========================================");
        System.out.println("      RECOVER EASE: TERMINAL SYSTEM      ");
        System.out.println("=========================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Report a Lost Item");
            System.out.println("2. Report a Found Item");
            System.out.println("3. View All Database Records");
            System.out.println("4. View 'Found' Items Only");
            System.out.println("5. Search Database by Keyword");
            System.out.println("6. Resolve & Delete an Item (Requires ID)");
            System.out.println("7. Terminate System");
            System.out.print("Select an operation (1-7): ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("\n>>> SYSTEM ERROR: Invalid input type. Please enter a numerical digit (1-7).");
                scanner.nextLine(); 
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Item Title (e.g., Wallet): ");
                        String lTitle = scanner.nextLine();
                        System.out.print("Enter Description: ");
                        String lDesc = scanner.nextLine();
                        System.out.print("Enter Location Lost: ");
                        String lLoc = scanner.nextLine();
                        System.out.print("Enter Date (DD-MM-YYYY): ");
                        String lDate = scanner.nextLine();
                        System.out.print("Enter Your Contact Details: ");
                        String lContact = scanner.nextLine();
                        system.addRecord(new LostItem(lTitle, lDesc, lLoc, lDate, lContact));
                        break;
                    case 2:
                        System.out.print("Enter Item Title (e.g., Keys): ");
                        String fTitle = scanner.nextLine();
                        System.out.print("Enter Description: ");
                        String fDesc = scanner.nextLine();
                        System.out.print("Enter Location Found: ");
                        String fLoc = scanner.nextLine();
                        System.out.print("Enter Date (DD-MM-YYYY): ");
                        String fDate = scanner.nextLine();
                        System.out.print("Enter Storage/Contact Details: ");
                        String fContact = scanner.nextLine();
                        system.addRecord(new FoundItem(fTitle, fDesc, fLoc, fDate, fContact));
                        break;
                    case 3:
                        system.displayAllRecords();
                        break;
                    case 4:
                        system.displayFoundRecords();
                        break;
                    case 5:
                        System.out.print("Enter keyword to search (Title or Location): ");
                        String keyword = scanner.nextLine();
                        system.search(keyword);
                        break;
                    case 6:
                        System.out.print("Enter the exact 6-character Item ID to delete: ");
                        String id = scanner.nextLine();
                        system.deleteRecord(id);
                        break;
                    case 7:
                        running = false;
                        System.out.println("\nShutting down Recover Ease. Data is safely stored.");
                        break;
                    default:
                        System.out.println("\n>>> SYSTEM ERROR: Invalid selection. Please choose 1-7.");
                }
            } catch (Exception e) {
                System.out.println("\n>>> UNEXPECTED SYSTEM ERROR: " + e.getMessage());
                System.out.println("Returning to main menu to prevent data loss.");
            }
        }
        scanner.close();
    }
}