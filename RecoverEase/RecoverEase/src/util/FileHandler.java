package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import model.User;
import model.Item;
import model.LostItem;
import model.FoundItem;

public class FileHandler {

    private static final String DATA_DIR    = "data/";
    private static final String USERS_FILE  = DATA_DIR + "users.txt";
    private static final String ITEMS_FILE  = DATA_DIR + "items.txt";
    private static final String IMAGES_DIR  = "images/";

    // ─── USER FILE ───────────────────────────────────────────────────────────

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File f = new File(USERS_FILE);
        if (!f.exists()) return users;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    User u = User.fromFileLine(line);
                    if (u != null) users.add(u);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        new File(DATA_DIR).mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE, false))) {
            for (User u : users) {
                bw.write(u.toFileLine());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users file: " + e.getMessage());
        }
    }

    // ─── ITEM FILE ────────────────────────────────────────────────────────────

    public static List<Item> loadItems() {
        List<Item> items = new ArrayList<>();
        File f = new File(ITEMS_FILE);
        if (!f.exists()) return items;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String type = line.split("\\|")[0];
                if (type.equals("LOST")) {
                    LostItem li = LostItem.fromFileLine(line);
                    if (li != null) items.add(li);
                } else if (type.equals("FOUND")) {
                    FoundItem fi = FoundItem.fromFileLine(line);
                    if (fi != null) items.add(fi);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading items file: " + e.getMessage());
        }
        return items;
    }

    public static void saveItems(List<Item> items) {
        new File(DATA_DIR).mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ITEMS_FILE, false))) {
            for (Item item : items) {
                bw.write(item.toFileLine());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving items file: " + e.getMessage());
        }
    }

    // ─── IMAGE HANDLING ───────────────────────────────────────────────────────

    // Copies image from source path into images/ folder and returns stored path
    public static String saveImage(String sourcePath, String itemId) {
        if (sourcePath == null || sourcePath.trim().isEmpty()) return "";
        File src = new File(sourcePath.trim());
        if (!src.exists()) {
            System.out.println("  Image file not found: " + sourcePath);
            return "";
        }
        new File(IMAGES_DIR).mkdirs();
        String ext = sourcePath.contains(".") ?
                     sourcePath.substring(sourcePath.lastIndexOf('.')) : ".jpg";
        String destPath = IMAGES_DIR + itemId + ext;
        try {
            Files.copy(src.toPath(), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("  Image saved to: " + destPath);
            return destPath;
        } catch (IOException e) {
            System.out.println("  Could not copy image: " + e.getMessage());
            return "";
        }
    }
}
