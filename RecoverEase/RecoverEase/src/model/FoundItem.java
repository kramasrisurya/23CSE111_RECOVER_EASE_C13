package model;

public class FoundItem extends Item {

    private boolean handedOver; // true if item has been claimed/returned

    public FoundItem(String itemId, String title, String description,
                     String location, String date, String imagePath, String postedBy) {
        super(itemId, title, description, location, date, imagePath, postedBy);
        this.handedOver = false;
    }

    public String getType() {
        return "FOUND";
    }

    public boolean isHandedOver()         { return handedOver; }
    public void setHandedOver(boolean val) { this.handedOver = val; }

    public String getDetails() {
        return super.getDetails() +
               "\n  Status     : " + (handedOver ? "Handed Over" : "Still with Finder");
    }

    public String toFileLine() {
        return super.toFileLine() + "|" + handedOver;
    }

    public static FoundItem fromFileLine(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length < 9) return null;
        FoundItem fi = new FoundItem(p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
        fi.setHandedOver(Boolean.parseBoolean(p[8]));
        return fi;
    }
}
