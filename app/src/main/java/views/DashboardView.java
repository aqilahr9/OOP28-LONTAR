package prak8.library.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.models.User;
import prak8.library.utils.StyleUtils;

public class DashboardView {

    private VBox root;
    private User currentUser;
    
    // Icon Buttons
    private Button btnProfil;
    private Button btnDaftarBuku;
    private Button btnBukuDipinjam;
    private Button btnLogout;

    public DashboardView(User currentUser) {
        this.currentUser = currentUser;
        
        // 1. Header
        Label lblWelcome = new Label("WELCOME TO LONTAR!");
        lblWelcome.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        lblWelcome.setStyle(StyleUtils.TEXT_WHITE + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 5, 5);");

        String titleText = currentUser.getRole().equalsIgnoreCase("Admin") ? "DASHBOARD ADMIN" : "MAIN MENU PERPUSTAKAAN";
        Label lblTitle = new Label(titleText);
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitle.setStyle(StyleUtils.TEXT_WHITE);

        VBox headerBox = new VBox(20, lblWelcome, lblTitle);
        headerBox.setAlignment(Pos.CENTER);

        // 2. Icon Grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(40);
        grid.setVgap(40);

        // Create Icons
        VBox boxProfil = StyleUtils.createIconButton("👤", "Profil");
        btnProfil = StyleUtils.getButtonFromIconBox(boxProfil);

        VBox boxDaftarBuku = StyleUtils.createIconButton("📚", "Daftar Buku");
        btnDaftarBuku = StyleUtils.getButtonFromIconBox(boxDaftarBuku);

        VBox boxBukuDipinjam = StyleUtils.createIconButton("📖", "Buku Pinjam");
        btnBukuDipinjam = StyleUtils.getButtonFromIconBox(boxBukuDipinjam);

        VBox boxLogout = StyleUtils.createIconButton("🚪", "Log Out");
        btnLogout = StyleUtils.getButtonFromIconBox(boxLogout);

        // Add to Grid - arranged in a single row (4 columns)
        grid.add(boxProfil, 0, 0);
        grid.add(boxDaftarBuku, 1, 0);
        grid.add(boxBukuDipinjam, 2, 0);
        grid.add(boxLogout, 3, 0);

        // Main Container
        root = new VBox(50);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30, 50, 70, 50));
        StyleUtils.applyBackground(root);
        root.getChildren().addAll(headerBox, grid);
    }
    
    public VBox getRoot() { return root; }
    public Button getBtnProfil() { return btnProfil; }
    public Button getBtnDaftarBuku() { return btnDaftarBuku; }
    public Button getBtnBukuDipinjam() { return btnBukuDipinjam; }
    public Button getBtnLogout() { return btnLogout; }
}

