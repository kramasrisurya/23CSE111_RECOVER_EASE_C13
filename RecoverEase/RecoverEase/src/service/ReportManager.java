package service;

import model.Item;
import model.LostItem;
import model.FoundItem;
import model.User;
import model.User.Role;
import util.FileHandler;
import util.IDGenerator;

import java.util.ArrayList;
import java.util.List;

public class ReportManager {

    private List<Item> items;

    public ReportManager() {
        items = FileHandler.loadItems();
    }

    // ─── POST ─────────────────────────────────────────────────────────────────

    public String postLost(String title, String description, String location,
                           String date, String imagePath, User user) {
        String id = IDGenerator.generate();
        String storedImage = FileHandler.saveImage(imagePath, id);
        LostItem li = new LostItem(id, title, description, location, date, storedImage, user.getEmail());
        items.add(li);
        FileHandler.saveItems(items);
        return id;
    }

    public String postFound(String title, String description, String location,
                            String date, String imagePath, User user) {
        String id = IDGenerator.generate();
        String storedImage = FileHandler.saveImage(imagePath, id);
        FoundItem fi = new FoundItem(id, title, description, location, date, storedImage, user.getEmail());
        items.add(fi);
        FileHandler.saveItems(items);
        return id;
    }

    // ─── VIEW ─────────────────────────────────────────────────────────────────

    public void viewAllLost() {
        boolean found = false;
        for (Item item : items) {
            if (item.getType().equals("LOST")) {
                System.out.println(item.getDetails());
                System.out.println("-------------------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No lost items reported.");
    }

    public void viewAllFound() {
        boolean found = false;
        for (Item item : items) {
            if (item.getType().equals("FOUND")) {
                System.out.println(item.getDetails());
                System.out.println("-------------------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No found items reported.");
    }

    public void viewMyPosts(User user) {
        boolean found = false;
        for (Item item : items) {
            if (item.getPostedBy().equalsIgnoreCase(user.getEmail())) {
                System.out.println(item.getDetails());
                System.out.println("-------------------------------");
                found = true;
            }
        }
        if (!found) System.out.println("You have no posts.");
    }

    // ─── EDIT ─────────────────────────────────────────────────────────────────

    // User can edit only their own posts; admin can edit any
    public boolean editItem(String itemId, String newTitle, String newDesc,
                            String newLocation, User user) {
        for (Item item : items) {
            if (item.getItemId().equalsIgnoreCase(itemId)) {
                if (!item.getPostedBy().equalsIgnoreCase(user.getEmail())
                        && user.getRole() != Role.INSTITUTE_HEAD) {
                    System.out.println("Access denied. You can only edit your own posts.");
                    return false;
                }
                if (!newTitle.isEmpty())    item.setTitle(newTitle);
                if (!newDesc.isEmpty())     item.setDescription(newDesc);
                if (!newLocation.isEmpty()) item.setLocation(newLocation);
                FileHandler.saveItems(items);
                System.out.println("Item updated successfully.");
                return true;
            }
        }
        System.out.println("Item not found: " + itemId);
        return false;
    }

    // ─── DELETE ───────────────────────────────────────────────────────────────

    // User deletes own post; admin can delete any post
    public boolean deleteItem(String itemId, User user) {
        Item target = null;
        for (Item item : items) {
            if (item.getItemId().equalsIgnoreCase(itemId)) {
                target = item;
                break;
            }
        }
        if (target == null) {
            System.out.println("Item not found: " + itemId);
            return false;
        }
        if (!target.getPostedBy().equalsIgnoreCase(user.getEmail())
                && user.getRole() != Role.INSTITUTE_HEAD) {
            System.out.println("Access denied. You can only delete your own posts.");
            return false;
        }
        items.remove(target);
        FileHandler.saveItems(items);
        System.out.println("Item deleted: " + itemId);
        return true;
    }

    // ─── SEARCH ───────────────────────────────────────────────────────────────

    public void search(String keyword) {
        String kw = keyword.toLowerCase();
        boolean found = false;
        for (Item item : items) {
            if (item.getTitle().toLowerCase().contains(kw)
                    || item.getDescription().toLowerCase().contains(kw)
                    || item.getLocation().toLowerCase().contains(kw)) {
                System.out.println(item.getDetails());
                System.out.println("-------------------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No items matched '" + keyword + "'.");
    }

    // ─── MATCH SUGGESTIONS ────────────────────────────────────────────────────

    // Shows found items that share keywords with a given lost item
    public void showMatches(String lostItemId) {
        LostItem lost = null;
        for (Item item : items) {
            if (item.getItemId().equalsIgnoreCase(lostItemId) && item.getType().equals("LOST")) {
                lost = (LostItem) item;
                break;
            }
        }
        if (lost == null) {
            System.out.println("Lost item not found.");
            return;
        }
        String[] keywords = (lost.getTitle() + " " + lost.getDescription()).toLowerCase().split("\\s+");
        boolean matched = false;
        System.out.println("Possible matches for: " + lost.getTitle());
        for (Item item : items) {
            if (!item.getType().equals("FOUND")) continue;
            String detail = (item.getTitle() + " " + item.getDescription()).toLowerCase();
            for (String kw : keywords) {
                if (kw.length() > 3 && detail.contains(kw)) {
                    System.out.println(item.getDetails());
                    System.out.println("-------------------------------");
                    matched = true;
                    break;
                }
            }
        }
        if (!matched) System.out.println("No matches found.");
    }

    // ─── ADMIN: VIEW ALL ──────────────────────────────────────────────────────

    public void viewAll() {
        if (items.isEmpty()) {
            System.out.println("No items in the system.");
            return;
        }
        for (Item item : items) {
            System.out.println(item.getDetails());
            System.out.println("-------------------------------");
        }
    }
}
