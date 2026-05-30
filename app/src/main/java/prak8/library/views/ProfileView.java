package prak8.library.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.models.User;
import prak8.library.utils.StyleUtils;

public class ProfileView {

    private VBox root;
    private PasswordField txtPassword;
    private TextField txtAlamat;
    private TextField txtNoHp;
    private Button btnUpdate;
    private Button btnBack;

    public ProfileView(User user) {
        Label lblTitle = new Label("EDIT PROFIL SAYA");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitle.setStyle(StyleUtils.TEXT_WHITE);

        Label lblUsername = new Label("Username: " + user.getUsername());
        lblUsername.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lblUsername.setStyle(StyleUtils.TEXT_WHITE);

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Password Baru");
        txtPassword.setText(user.getPassword());
        txtPassword.setStyle(StyleUtils.NEUMORPHIC_INSET);
        txtPassword.setPrefHeight(40);

        txtAlamat = new TextField();
        txtAlamat.setPromptText("Alamat");
        txtAlamat.setText(user.getAlamat());
        txtAlamat.setStyle(StyleUtils.NEUMORPHIC_INSET);
        txtAlamat.setPrefHeight(40);

        txtNoHp = new TextField();
        txtNoHp.setPromptText("Nomor HP");
        txtNoHp.setText(user.getNomorHp());
        txtNoHp.setStyle(StyleUtils.NEUMORPHIC_INSET);
        txtNoHp.setPrefHeight(40);

        btnUpdate = new Button("Simpan Perubahan");
        btnUpdate.setMaxWidth(Double.MAX_VALUE);
        btnUpdate.setPrefHeight(45);
        StyleUtils.applyNeumorphicStyle(btnUpdate);

        btnBack = new Button("Kembali");
        btnBack.setMaxWidth(Double.MAX_VALUE);
        btnBack.setPrefHeight(45);
        StyleUtils.applyNeumorphicStyle(btnBack);

        root = new VBox(20, lblTitle, lblUsername, txtPassword, txtAlamat, txtNoHp, btnUpdate, btnBack);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        StyleUtils.applyBackground(root);
    }

    public VBox getRoot() { return root; }
    public PasswordField getTxtPassword() { return txtPassword; }
    public TextField getTxtAlamat() { return txtAlamat; }
    public TextField getTxtNoHp() { return txtNoHp; }
    public Button getBtnUpdate() { return btnUpdate; }
    public Button getBtnBack() { return btnBack; }
}
