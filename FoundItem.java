package version2;

public class FoundItem extends Item {
    
    public FoundItem(String title, String description, String location, String date, String contactDetails) {
        super(title, description, location, date, contactDetails);
    }

    @Override
    public void displayItem() {
        System.out.println("\n[FOUND ITEM] ID: " + getId() + " | " + getTitle() + " at " + getLocation());
        System.out.println("             Desc: " + getDescription());
        System.out.println("             Date: " + getDate() + " | Storage/Contact: " + getContactDetails());
    }
}