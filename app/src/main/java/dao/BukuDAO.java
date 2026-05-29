package prak8.library.dao;

import prak8.library.models.Buku;
import prak8.library.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BukuDAO {

    public static void createTable() {
        // Menambahkan kolom peminjam di akhir
        String sql = "CREATE TABLE IF NOT EXISTS buku ("
                   + "id_buku TEXT PRIMARY KEY,"
                   + "judul TEXT NOT NULL,"
                   + "penulis TEXT NOT NULL,"
                   + "tahun_terbit INTEGER,"
                   + "is_tersedia BOOLEAN,"
                   + "peminjam TEXT" 
                   + ");";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel buku: " + e.getMessage());
        }
    }

    public ObservableList<Buku> getAllBuku() {
        ObservableList<Buku> listBuku = FXCollections.observableArrayList();
        String sql = "SELECT * FROM buku";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Menarik data peminjam dari database
                Buku buku = new Buku(
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getInt("tahun_terbit"),
                    rs.getBoolean("is_tersedia"),
                    rs.getString("peminjam") // Data baru ditarik
                );
                listBuku.add(buku);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data buku: " + e.getMessage());
        }
        return listBuku;
    }

    public void tambahBuku(Buku buku) {
        String sql = "INSERT INTO buku(id_buku, judul, penulis, tahun_terbit, is_tersedia, peminjam) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, buku.getIdBuku());
            pstmt.setString(2, buku.getJudul());
            pstmt.setString(3, buku.getPenulis());
            pstmt.setInt(4, buku.getTahunTerbit());
            pstmt.setBoolean(5, buku.isTersedia());
            pstmt.setString(6, buku.getPeminjam()); // Menyimpan string "-" saat awal ditambah
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menambah buku: " + e.getMessage());
        }
    }

    // FUNGSI INI DIUBAH TOTAL: Menambahkan parameter namaPeminjam
    public void updateStatusPinjam(String idBuku, boolean isTersedia, String namaPeminjam) {
        String sql = "UPDATE buku SET is_tersedia = ?, peminjam = ? WHERE id_buku = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isTersedia);
            pstmt.setString(2, namaPeminjam); // Update nama orangnya
            pstmt.setString(3, idBuku);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal mengubah status buku: " + e.getMessage());
        }
    }
}
