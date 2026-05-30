package prak8.library.models;

public class Admin extends User {

    public Admin(String username, String password, String alamat, String nomorHp) {
        super(username, password, alamat, nomorHp); // Memanggil constructor dari class induk (User)
    }

    @Override
    public String getRole() {
        return "Admin"; // Bentuk spesifik dari metode induk
    }
}