package model;

public class User {

    public enum Role {
        USER, INSTITUTE_HEAD
    }

    private String name;
    private String email;
    private String password; // plain text as requested (simple)
    private Role role;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public Role   getRole()     { return role; }

    // Convert to a single CSV line for txt file storage
    public String toFileLine() {
        return name + "," + email + "," + password + "," + role.name();
    }

    // Build a User from a CSV line read from the txt file
    public static User fromFileLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        return new User(parts[0], parts[1], parts[2], Role.valueOf(parts[3]));
    }
}
