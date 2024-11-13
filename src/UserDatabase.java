import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private Map<String, User> users;

    public UserDatabase() {
        users = new HashMap<>();
        // Default admin account
        users.put("admin", new User("admin", "adminpass", "Admin"));
    }

    public boolean authenticate(String username, String password, String role) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password) && user.getRole().equals(role);
    }

    public void addUser(String username, String password, String role) {
        users.put(username, new User(username, password, role));
    }
}

class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
