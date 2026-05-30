package prak8.library.models;

public abstract class User {
    private String username;
    private String password;
    private String alamat;
    private String nomorHp;

    public User(String username, String password, String alamat, String nomorHp) {
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.nomorHp = nomorHp;
    }

    // Abstract method untuk Polymorphism nanti
    public abstract String getRole();

    // Getter & Setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNomorHp() { return nomorHp; }
    public void setNomorHp(String nomorHp) { this.nomorHp = nomorHp; }
}