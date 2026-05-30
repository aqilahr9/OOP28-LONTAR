package prak8.library.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.utils.StyleUtils;

public class TambahBukuView {

    private VBox root;
    private TextField txtId;
    private TextField txtJudul;
    private TextField txtPenulis;
    private TextField txtTahun;
    private Button btnSimpan;
    private Label lblStatus;

    public TambahBukuView() {
        Label lblTitle = new Label("TAMBAH BUKU BARU");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitle.setStyle(StyleUtils.TEXT_WHITE);

        txtId = new TextField();
        txtId.setPromptText("ID Buku (Contoh: BK-002)");
        txtId.setStyle(StyleUtils.NEUMORPHIC_INSET);
        txtId.setPrefHeight(40);

        txtJudul = new TextField();
        txtJudul.setPromptText("Judul Buku");
        txtJudul.setStyle(StyleUtils.NEUMORPHIC_INSET);
        txtJudul.setPrefHeight(40);

        txtPenulis = new TextField();
        txtPenulis.setPromptText("Penulis");
        txtPenulis.setStyle(StyleUtils.NEUMORPHIC_INSET);
        txtPenulis.setPrefHeight(40);

        txtTahun = new TextField();
        txtTahun.setPromptText("Tahun Terbit (Contoh: 2023)");
        txtTahun.setStyle(StyleUtils.NEUMORPHIC_INSET);
        txtTahun.setPrefHeight(40);

        btnSimpan = new Button("Simpan Data");
        btnSimpan.setMaxWidth(Double.MAX_VALUE);
        btnSimpan.setPrefHeight(45);
        StyleUtils.applyNeumorphicStyle(btnSimpan);

        lblStatus = new Label();
        lblStatus.setTextFill(Color.WHITE);
        lblStatus.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        StyleUtils.applyBackground(root);
        root.getChildren().addAll(lblTitle, txtId, txtJudul, txtPenulis, txtTahun, btnSimpan, lblStatus);
    }

    public VBox getRoot() { return root; }
    public TextField getTxtId() { return txtId; }
    public TextField getTxtJudul() { return txtJudul; }
    public TextField getTxtPenulis() { return txtPenulis; }
    public TextField getTxtTahun() { return txtTahun; }
    public Button getBtnSimpan() { return btnSimpan; }
    public Label getLblStatus() { return lblStatus; }
}
