package version2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecoverEaseSystem {
    private List<Item> database;
    private static final String FILE_NAME = "recover_ease_db.dat";

    public RecoverEaseSystem() {
        loadDatabase();
    }

    // --- FILE HANDLING: LOAD ---
    @SuppressWarnings("unchecked")
    private void loadDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            database = (List<Item>) ois.readObject();
            System.out.println(">>> SYSTEM: Database loaded successfully. (" + database.size() + " records)");
        } catch (FileNotFoundException e) {
            System.out.println(">>> SYSTEM: No existing database found. Creating a new one.");
            database = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(">>> SYSTEM ERROR: Corrupted database file. Starting fresh. Error: " + e.getMessage());
            database = new ArrayList<>();
        }
    }

    // --- FILE HANDLING: SAVE ---
    private void saveDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(database);
        } catch (IOException e) {
            System.out.println(">>> SYSTEM ERROR: Failed to save records to disk. Error: " + e.getMessage());
        }
    }

    public void addRecord(Item item) {
        database.add(item);
        System.out.println("\n>>> SYSTEM: Record added successfully. PLEASE SAVE THIS ID: " + item.getId() + " <<<");
        saveDatabase(); 
        quickMatch(item); 
    }

    // --- QUICK MATCHING LOGIC ---
    private void quickMatch(Item newItem) {
        System.out.println(">>> SYSTEM: Scanning for potential matches...");
        boolean matchFound = false;
        String newTitle = newItem.getTitle().toLowerCase();

        for (Item existingItem : database) {
            // Do not match with itself, and only cross-match Lost with Found
            if (!existingItem.getId().equals(newItem.getId()) && 
                newItem.getClass() != existingItem.getClass()) {
                
                String existingTitle = existingItem.getTitle().toLowerCase();
                
                // Basic matching threshold: Shared keywords in title
                if (existingTitle.contains(newTitle) || newTitle.contains(existingTitle)) {
                    System.out.println("    [!] POTENTIAL MATCH DETECTED [!]");
                    existingItem.displayItem();
                    matchFound = true;
                }
            }
        }
        if (!matchFound) {
            System.out.println("    No immediate matches found in the system.");
        }
    }

    public void displayAllRecords() {
        if (database.isEmpty()) {
            System.out.println("\n>>> SYSTEM: No records found in the database.");
            return;
        }
        for (Item item : database) {
            item.displayItem();
        }
    }

    public void displayFoundRecords() {
        boolean found = false;
        for (Item item : database) {
            if (item instanceof FoundItem) {
                item.displayItem();
                found = true;
            }
        }
        if (!found) System.out.println("\n>>> SYSTEM: No found items reported currently.");
    }

    public void search(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        boolean match = false;
        for (Item item : database) {
            if (item.getTitle().toLowerCase().contains(lowerKeyword) || 
                item.getLocation().toLowerCase().contains(lowerKeyword)) {
                item.displayItem();
                match = true;
            }
        }
        if (!match) System.out.println("\n>>> SYSTEM: No matching items found for '" + keyword + "'.");
    }

    public void deleteRecord(String id) {
        boolean removed = database.removeIf(item -> item.getId().equals(id.toUpperCase()));
        if (removed) {
            System.out.println("\n>>> SYSTEM: Record " + id + " has been marked as resolved and permanently deleted.");
            saveDatabase(); // Update the disk file after deletion
        } else {
            System.out.println("\n>>> SYSTEM ERROR: Record ID not found. Deletion failed.");
        }
    }
}