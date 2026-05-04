package version2;

public class LostItem extends Item {
    
    public LostItem(String title, String description, String location, String date, String contactDetails) {
        super(title, description, location, date, contactDetails);
    }

    @Override
    public void displayItem() {
        System.out.println("\n[LOST ALERT] ID: " + getId() + " | " + getTitle() + " at " + getLocation());
        System.out.println("             Desc: " + getDescription());
        System.out.println("             Date: " + getDate() + " | Contact: " + getContactDetails());
    }
}