package prak8.library.dao;

import prak8.library.models.User;
import prak8.library.utils.DatabaseConnection;
import prak8.library.models.Admin;
import prak8.library.models.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                     "username TEXT PRIMARY KEY, " +
                     "password TEXT NOT NULL, " +
                     "alamat TEXT, " +
                     "nomor_hp TEXT, " +
                     "role TEXT NOT NULL" +
                     ");";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel user: " + e.getMessage());
        }
    }

    public void tambahUser(User user) {
        String sql = "INSERT INTO user(username, password, alamat, nomor_hp, role) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getAlamat());
            pstmt.setString(4, user.getNomorHp());
            pstmt.setString(5, user.getRole()); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menambah user: " + e.getMessage());
        }
    }

    public User verifikasiLogin(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                String alamat = rs.getString("alamat");
                String nomorHp = rs.getString("nomor_hp");
                if (role.equalsIgnoreCase("Admin")) {
                    return new Admin(username, password, alamat, nomorHp);
                } else {
                    return new Member(username, password, alamat, nomorHp);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saat verifikasi login: " + e.getMessage());
        }
        return null;
    }

    public void updateUser(User user) {
        String sql = "UPDATE user SET password = ?, alamat = ?, nomor_hp = ? WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getAlamat());
            pstmt.setString(3, user.getNomorHp());
            pstmt.setString(4, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal update user: " + e.getMessage());
        }
    }
}
