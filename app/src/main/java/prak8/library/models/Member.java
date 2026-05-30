package prak8.library.models;

public class Member extends User {

    public Member(String username, String password, String alamat, String nomorHp) {
        super(username, password, alamat, nomorHp);
    }

    @Override
    public String getRole() {
        return "Member"; 
    }
}