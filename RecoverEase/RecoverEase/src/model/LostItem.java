package model;

public class LostItem extends Item {

    public LostItem(String itemId, String title, String description,
                    String location, String date, String imagePath, String postedBy) {
        super(itemId, title, description, location, date, imagePath, postedBy);
    }

    public String getType() {
        return "LOST";
    }

    // Build from a file line (called from ReportManager)
    public static LostItem fromFileLine(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length < 8) return null;
        // p[0] = type, already known
        return new LostItem(p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
    }
}
