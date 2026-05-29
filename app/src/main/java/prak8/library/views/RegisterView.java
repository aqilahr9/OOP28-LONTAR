package prak8.library.views;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.utils.StyleUtils;

public class RegisterView {
    private VBox root;
    private TextField txtUsername, txtAlamat, txtNoHp;
    private PasswordField txtPassword;
    private ComboBox<String> comboRole;
    private Button btnSimpan, btnBatal;
    private Label lblStatus;

    public RegisterView() {
        Label lblTitle = new Label("BUAT AKUN BARU");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitle.setStyle(StyleUtils.TEXT_WHITE);

        txtUsername = new TextField(); txtUsername.setPromptText("Username");
        txtUsername.setStyle(StyleUtils.NEUMORPHIC_INSET); txtUsername.setPrefHeight(40);

        txtPassword = new PasswordField(); txtPassword.setPromptText("Password");
        txtPassword.setStyle(StyleUtils.NEUMORPHIC_INSET); txtPassword.setPrefHeight(40);

        txtAlamat = new TextField(); txtAlamat.setPromptText("Alamat");
        txtAlamat.setStyle(StyleUtils.NEUMORPHIC_INSET); txtAlamat.setPrefHeight(40);

        txtNoHp = new TextField(); txtNoHp.setPromptText("Nomor HP");
        txtNoHp.setStyle(StyleUtils.NEUMORPHIC_INSET); txtNoHp.setPrefHeight(40);

        comboRole = new ComboBox<>(FXCollections.observableArrayList("Admin", "Member"));
        comboRole.setPromptText("Pilih Role");
        comboRole.setPrefWidth(Double.MAX_VALUE);
        comboRole.setPrefHeight(40);

        comboRole.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Pilih Role");
                    setStyle("-fx-text-fill: #F5F5F5; -fx-font-weight: bold;");
                } else {
                    setText(item);
                    setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
                }
            }
        });

        comboRole.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 10; -fx-border-color: rgba(255,255,255,0.2); -fx-border-radius: 10;");

        btnSimpan = new Button("Daftarkan Akun");
        btnSimpan.setPrefWidth(Double.MAX_VALUE); btnSimpan.setPrefHeight(45);
        StyleUtils.applyNeumorphicStyle(btnSimpan);

        btnBatal = new Button("Kembali");
        btnBatal.setPrefWidth(Double.MAX_VALUE); btnBatal.setPrefHeight(45);
        StyleUtils.applyNeumorphicStyle(btnBatal);

        lblStatus = new Label();
        lblStatus.setTextFill(Color.WHITE);
        lblStatus.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        root = new VBox(20, lblTitle, txtUsername, txtPassword, txtAlamat, txtNoHp, comboRole, btnSimpan, btnBatal, lblStatus);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        StyleUtils.applyBackground(root);
    }

    public VBox getRoot() { return root; }
    public TextField getTxtUsername() { return txtUsername; }
    public PasswordField getTxtPassword() { return txtPassword; }
    public TextField getTxtAlamat() { return txtAlamat; }
    public TextField getTxtNoHp() { return txtNoHp; }
    public ComboBox<String> getComboRole() { return comboRole; }
    public Button getBtnSimpan() { return btnSimpan; }
    public Button getBtnBatal() { return btnBatal; }
    public Label getLblStatus() { return lblStatus; }
}
