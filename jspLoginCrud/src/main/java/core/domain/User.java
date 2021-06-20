package core.domain;

public class User {
    String username;
    int id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public User(String username, int id) {
        this.username = username;
        this.id = id;
    }
}
