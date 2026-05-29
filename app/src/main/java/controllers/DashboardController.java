package prak8.library.controllers;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import prak8.library.dao.BukuDAO;
import prak8.library.dao.TransaksiDAO;
import prak8.library.dao.UserDAO;
import prak8.library.models.Buku;
import prak8.library.models.Member;
import prak8.library.models.Transaksi;
import prak8.library.models.User;
import prak8.library.views.BookListView;
import prak8.library.views.DashboardView;
import prak8.library.views.LoginView;
import prak8.library.views.LogoutDialog;
import prak8.library.views.ProfileView;
import prak8.library.views.SuccessView;
import prak8.library.views.TambahBukuView;

import java.time.LocalDate;

public class DashboardController {

    private DashboardView view;
    private BukuDAO bukuDAO;
    private User currentUser;

    private BookListView currentBookListView;

    public DashboardController(DashboardView view, User currentUser) {
        this.view = view;
        this.currentUser = currentUser;
        this.bukuDAO = new BukuDAO();

        // Bind Icon Buttons
        this.view.getBtnProfil().setOnAction(e -> handleShowProfile());
        this.view.getBtnDaftarBuku().setOnAction(e -> handleShowBookList());
        this.view.getBtnBukuDipinjam().setOnAction(e -> handleShowBorrowedBooks());
        this.view.getBtnLogout().setOnAction(e -> handleLogout());
    }

    private void handleShowProfile() {
        ProfileView profileView = new ProfileView(currentUser);
        Stage stage = (Stage) view.getRoot().getScene().getWindow();
        
        profileView.getBtnBack().setOnAction(e -> {
            stage.getScene().setRoot(view.getRoot());
            stage.setWidth(850);
            stage.setHeight(650);
        });
        profileView.getBtnUpdate().setOnAction(e -> {
            currentUser.setPassword(profileView.getTxtPassword().getText());
            currentUser.setAlamat(profileView.getTxtAlamat().getText());
            currentUser.setNomorHp(profileView.getTxtNoHp().getText());
            new UserDAO().updateUser(currentUser);
            showSuccessNotification("Profil Diperbarui", "Data Anda telah disimpan.");
        });

        stage.getScene().setRoot(profileView.getRoot());
        stage.setWidth(450);
        stage.setHeight(650);
    }

    private void handleShowBorrowedBooks() {
        BookListView blView = new BookListView(currentUser);
        blView.setTitle("Buku yang Sedang Dipinjam");
        // Hide all action buttons in "Borrowed Books" view for both roles
        blView.setActionsVisible(false, false, false);
        
        Stage stage = (Stage) view.getRoot().getScene().getWindow();
        setupBookListHandlers(blView);
        
        refreshBorrowedBooks(blView);
        
        stage.getScene().setRoot(blView.getRoot());
        stage.setWidth(900);
        stage.setHeight(650);
    }
    
    private void refreshBorrowedBooks(BookListView blView) {
        ObservableList<Buku> allBuku = bukuDAO.getAllBuku();
        ObservableList<Buku> borrowed = allBuku.filtered(b -> b.getPeminjam().equals(currentUser.getUsername()));
        blView.getTabelBuku().setItems(borrowed);
    }

    public void loadData() {
        if (currentBookListView != null) {
            currentBookListView.getTabelBuku().setItems(bukuDAO.getAllBuku());
        }
    }

    private void handleShowBookList() {
        BookListView bookListView = new BookListView(currentUser);
        currentBookListView = bookListView;
        
        // Members can now also return books in the Book List view
        bookListView.setActionsVisible(true, true, currentUser.getRole().equalsIgnoreCase("Admin"));

        Stage stage = (Stage) view.getRoot().getScene().getWindow();
        setupBookListHandlers(bookListView);
        
        stage.getScene().setRoot(bookListView.getRoot());
        stage.setWidth(900);
        stage.setHeight(650);
    }

    private void setupBookListHandlers(BookListView blView) {
        blView.getTabelBuku().setItems(bukuDAO.getAllBuku());

        blView.getBtnBack().setOnAction(e -> {
            currentBookListView = null;
            Stage stage = (Stage) blView.getRoot().getScene().getWindow();
            stage.getScene().setRoot(view.getRoot());
            stage.setWidth(850);
            stage.setHeight(650);
        });

        blView.getBtnPinjamBuku().setOnAction(e -> handlePinjamBuku(blView));
        blView.getBtnTambahBuku().setOnAction(e -> handleTambahBuku());
        blView.getBtnKembalikanBuku().setOnAction(e -> handleKembalikanBuku(blView));
    }

    private void handlePinjamBuku(BookListView blView) {
        Buku bukuTerpilih = blView.getTabelBuku().getSelectionModel().getSelectedItem();
        if (bukuTerpilih == null || !bukuTerpilih.isTersedia()) {
            blView.getLblPesan().setTextFill(Color.WHITE);
            blView.getLblPesan().setText("Pilih buku yang tersedia!");
            return;
        }

        // 1. Record Transaction (Only if Member, or cast if Admin)
        if (currentUser instanceof Member) {
            Transaksi trans = new Transaksi(
                "TRX-" + System.currentTimeMillis(),
                (Member) currentUser,
                bukuTerpilih,
                LocalDate.now(),
                7 // Default 7 days
            );
            new TransaksiDAO().pinjamBuku(trans);
        }

        // 2. Update Book Status
        bukuDAO.updateStatusPinjam(bukuTerpilih.getIdBuku(), false, currentUser.getUsername());
        
        showSuccessNotification("Peminjaman Berhasil!", "Buku berhasil dipinjam.");
        refreshViewData(blView);
    }

    private void handleKembalikanBuku(BookListView blView) {
        Buku bukuTerpilih = blView.getTabelBuku().getSelectionModel().getSelectedItem();
        if (bukuTerpilih == null || bukuTerpilih.isTersedia()) {
            blView.getLblPesan().setTextFill(Color.WHITE);
            blView.getLblPesan().setText("Pilih buku yang sedang dipinjam!");
            return;
        }

        if (!currentUser.getRole().equalsIgnoreCase("Admin") && !bukuTerpilih.getPeminjam().equals(currentUser.getUsername())) {
            blView.getLblPesan().setTextFill(Color.WHITE);
            blView.getLblPesan().setText("Akses Ditolak! Ini bukan buku Anda.");
            return;
        }
        
        // Update Transaction Status to "Dikembalikan"
        new TransaksiDAO().updateStatusTransaksi(bukuTerpilih.getIdBuku(), currentUser.getUsername(), "Dikembalikan");

        // Update Book to available and set borrower to '-'
        bukuDAO.updateStatusPinjam(bukuTerpilih.getIdBuku(), true, "-");
        
        showSuccessNotification("Pengembalian Berhasil!", "Buku berhasil dikembalikan.");
        refreshViewData(blView);
    }

    private void refreshViewData(BookListView blView) {
        // Logic to refresh based on current view title/filter
        ObservableList<Buku> all = bukuDAO.getAllBuku();
        blView.getTabelBuku().setItems(all);
    }

    private void handleTambahBuku() {
        TambahBukuView tambahView = new TambahBukuView();
        new TambahBukuController(tambahView, this); 
        
        Stage stage = new Stage();
        stage.setTitle("Form Tambah Buku - Admin");
        stage.setScene(new Scene(tambahView.getRoot(), 350, 450));
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    private void handleLogout() {
        LogoutDialog logoutDialog = new LogoutDialog();
        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(logoutDialog.getRoot(), 350, 200));
        dialogStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Konfirmasi Logout");

        logoutDialog.getBtnTidak().setOnAction(e -> dialogStage.close());
        logoutDialog.getBtnIya().setOnAction(e -> {
            dialogStage.close();
            Stage stage = (Stage) view.getRoot().getScene().getWindow();
            LoginView loginView = new LoginView();
            new LoginController(loginView); 
            stage.setScene(new Scene(loginView.getRoot(), 400, 480));
        });

        dialogStage.showAndWait();
    }

    private void showSuccessNotification(String header, String subtext) {
        SuccessView successView = new SuccessView(header, subtext);
        Stage stage = new Stage();
        stage.setScene(new Scene(successView.getRoot(), 400, 300));
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.show();
    }
}
