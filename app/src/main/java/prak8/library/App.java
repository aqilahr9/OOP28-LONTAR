package prak8.library;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prak8.library.controllers.LoginController;
import prak8.library.views.LoginView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Inisialisasi tampilan LoginView berdesain Neumorphism
        LoginView loginView = new LoginView();
        
        // 2. Hubungkan View dengan LoginController untuk mengaktifkan tombol-tombolnya
        new LoginController(loginView);
        
        // 3. Set up resolusi jendela utama aplikasi
        Scene scene = new Scene(loginView.getRoot(), 400, 450);
        
        primaryStage.setTitle("LONTAR - Sistem Informasi Perpustakaan");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Mengunci ukuran layar agar desain Neumorphism tetap konsisten
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Eksekusi peluncuran aplikasi JavaFX
        launch(args);
    }
}