package version2;

import java.io.Serializable;
import java.util.UUID;

public abstract class Item implements Serializable {
    // serialVersionUID ensures compatibility during deserialization
    private static final long serialVersionUID = 1L; 
    
    private String id;
    private String title;
    private String description;
    private String location;
    private String date;
    private String contactDetails;

    public Item(String title, String description, String location, String date, String contactDetails) {
        this.id = UUID.randomUUID().toString().substring(0, 6).toUpperCase(); 
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.contactDetails = contactDetails;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getContactDetails() { return contactDetails; }

    public abstract void displayItem(); 
}