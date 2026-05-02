package model;

public abstract class Item {

    private String itemId;
    private String title;
    private String description;
    private String location;
    private String date;
    private String imagePath;   // path to uploaded image file
    private String postedBy;    // email of the user who posted

    public Item(String itemId, String title, String description,
                String location, String date, String imagePath, String postedBy) {
        this.itemId      = itemId;
        this.title       = title;
        this.description = description;
        this.location    = location;
        this.date        = date;
        this.imagePath   = imagePath;
        this.postedBy    = postedBy;
    }

    public String getItemId()      { return itemId; }
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public String getLocation()    { return location; }
    public String getDate()        { return date; }
    public String getImagePath()   { return imagePath; }
    public String getPostedBy()    { return postedBy; }

    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location)       { this.location = location; }
    public void setImagePath(String imagePath)     { this.imagePath = imagePath; }

    // Each subclass defines its type label
    public abstract String getType();

    // Shared display method (no override needed in subclasses unless extra info)
    public String getDetails() {
        return "[" + getType() + "] ID: " + itemId +
               "\n  Title      : " + title +
               "\n  Description: " + description +
               "\n  Location   : " + location +
               "\n  Date       : " + date +
               "\n  Image      : " + (imagePath.isEmpty() ? "No image" : imagePath) +
               "\n  Posted By  : " + postedBy;
    }

    // CSV line for file storage  (pipe | separator to avoid conflicts)
    public String toFileLine() {
        return getType() + "|" + itemId + "|" + title + "|" + description +
               "|" + location + "|" + date + "|" + imagePath + "|" + postedBy;
    }
}
