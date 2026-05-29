package prak8.library.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.utils.StyleUtils;

public class LoginView {
    
    private VBox root;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Label lblStatus;
    private Button btnRegister;

    public LoginView() {
        // 1. Inisialisasi Komponen
        Label lblTitle = new Label("LOGIN PERPUSTAKAAN");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitle.setStyle(StyleUtils.TEXT_WHITE);

        txtUsername = new TextField();
        txtUsername.setPromptText("Masukkan Username");
        txtUsername.setPrefWidth(250);
        txtUsername.setPrefHeight(40);
        txtUsername.setStyle(StyleUtils.NEUMORPHIC_INSET);

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Masukkan Password");
        txtPassword.setPrefWidth(250);
        txtPassword.setPrefHeight(40);
        txtPassword.setStyle(StyleUtils.NEUMORPHIC_INSET);

        btnLogin = new Button("Masuk");
        btnLogin.setPrefWidth(250);
        btnLogin.setPrefHeight(45);
        StyleUtils.applyNeumorphicStyle(btnLogin);

        btnRegister = new Button("Belum punya akun? Daftar");
        btnRegister.setStyle("-fx-background-color: transparent; " + StyleUtils.TEXT_WHITE + " -fx-underline: true; -fx-font-weight: bold;");
        btnRegister.setPrefWidth(250);

        lblStatus = new Label();
        lblStatus.setTextFill(Color.WHITE);
        lblStatus.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        // 2. Menyusun Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(10);
        
        grid.add(txtUsername, 0, 0);
        grid.add(txtPassword, 0, 1);
        grid.add(btnLogin, 0, 2);
        grid.add(lblStatus, 0, 3);
        grid.add(btnRegister, 0, 4);

        // Wadah Utama
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        StyleUtils.applyBackground(root);
        root.getChildren().addAll(lblTitle, grid);
    }

    public VBox getRoot() { return root; }
    public TextField getTxtUsername() { return txtUsername; }
    public PasswordField getTxtPassword() { return txtPassword; }
    public Button getBtnLogin() { return btnLogin; }
    public Label getLblStatus() { return lblStatus; }
    public Button getBtnRegister() { return btnRegister; }
}
