package prak8.library.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prak8.library.models.Buku;
import prak8.library.models.Transaksi;
import prak8.library.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransaksiDAO {

    // 1. Membuat tabel transaksi dengan Foreign Key
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS transaksi (" +
                     "id_transaksi TEXT PRIMARY KEY, " +
                     "peminjam_username TEXT NOT NULL, " +
                     "buku_id TEXT NOT NULL, " +
                     "tanggal_pinjam TEXT, " +
                     "tenggat_waktu TEXT, " +
                     "tanggal_kembali TEXT, " +
                     "status TEXT, " +
                     "FOREIGN KEY (peminjam_username) REFERENCES user(username), " +
                     "FOREIGN KEY (buku_id) REFERENCES buku(id_buku)" +
                     ");";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel transaksi: " + e.getMessage());
        }
    }

    // 2. Mencatat peminjaman (Insert Transaksi & Update Buku)
    public void pinjamBuku(Transaksi transaksi) {
        String sqlInsertTransaksi = "INSERT INTO transaksi(id_transaksi, peminjam_username, buku_id, tanggal_pinjam, tenggat_waktu, status) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsertTransaksi)) {

            pstmtInsert.setString(1, transaksi.getIdTransaksi());
            pstmtInsert.setString(2, transaksi.getPeminjam().getUsername());
            pstmtInsert.setString(3, transaksi.getBukuYangDipinjam().getIdBuku());
            pstmtInsert.setString(4, transaksi.getTanggalPinjam().toString());
            pstmtInsert.setString(5, transaksi.getTenggatWaktu().toString());
            pstmtInsert.setString(6, transaksi.getStatus());
            pstmtInsert.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Gagal catat transaksi: " + e.getMessage());
        }
    }

    public void updateStatusTransaksi(String bukuId, String username, String status) {
        String sql = "UPDATE transaksi SET status = ?, tanggal_kembali = DATE('now') WHERE buku_id = ? AND peminjam_username = ? AND status = 'Dipinjam'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setString(2, bukuId);
            pstmt.setString(3, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal update transaksi: " + e.getMessage());
        }
    }

    public ObservableList<Buku> getRiwayatBuku(String username) {
        ObservableList<Buku> list = FXCollections.observableArrayList();
        String sql = "SELECT b.*, t.status as trans_status FROM buku b JOIN transaksi t ON b.id_buku = t.buku_id WHERE t.peminjam_username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Buku buku = new Buku(
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getInt("tahun_terbit"),
                    rs.getString("trans_status").equals("Tersedia"), // Use transaction status for history context
                    rs.getString("trans_status") // Show status in the peminjam column for history
                );
                list.add(buku);
            }
        } catch (SQLException e) {
            System.out.println("Gagal ambil riwayat: " + e.getMessage());
        }
        return list;
    }
}
