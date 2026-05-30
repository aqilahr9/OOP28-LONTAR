package prak8.library.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL untuk SQLite. File database akan otomatis terbuat di folder utama proyek
    private static final String URL = "jdbc:sqlite:perpustakaan.db"; 

    private static Connection connection = null;

    // Private constructor agar tidak bisa di-instansiasi sembarangan dengan "new"
    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Membuka koneksi menggunakan JDBC
                connection = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            System.out.println("Gagal terhubung ke database: " + e.getMessage());
        }
        return connection;
    }
}
