package sorry.aldan.ti3a_8_12_tugasbesar.Model;

public class User {
    int id;
    String nama;
    String email;
    String username;
    String level;

    public User() {
    }

    public User(int id, String nama, String email, String username, String level) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.username = username;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
