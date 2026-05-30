package prak8.library.views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.models.Buku;
import prak8.library.models.User;
import prak8.library.utils.StyleUtils;

public class BookListView {

    private VBox root;
    private TableView<Buku> tabelBuku;
    private Button btnPinjamBuku;
    private Button btnKembalikanBuku;
    private Button btnTambahBuku;
    private Button btnBack;
    private Label lblPesan;

    private Label lblTitle;

    public BookListView(User currentUser) {
        // 1. Label Judul
        lblTitle = new Label("Daftar Buku Perpustakaan");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitle.setStyle(StyleUtils.TEXT_WHITE);

        btnBack = new Button("Kembali");
        StyleUtils.applyNeumorphicStyle(btnBack);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBox = new HBox(10, lblTitle, spacer, btnBack);
        topBox.setAlignment(Pos.CENTER_LEFT);

        // 2. Tabel Buku
        tabelBuku = new TableView<>();
        tabelBuku.setPrefHeight(380);
        tabelBuku.setStyle("-fx-background-color: #F5F5F5; -fx-selection-bar: #3B1C55; -fx-control-inner-background: #F5F5F5; -fx-text-fill: #000080; -fx-font-weight: bold;");

        TableColumn<Buku, String> colId = new TableColumn<>("ID Buku");
        colId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIdBuku()));
        
        TableColumn<Buku, String> colJudul = new TableColumn<>("Judul Buku");
        colJudul.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getJudul()));
        colJudul.setPrefWidth(250);

        TableColumn<Buku, String> colPenulis = new TableColumn<>("Penulis");
        colPenulis.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPenulis()));
        colPenulis.setPrefWidth(150);

        TableColumn<Buku, Integer> colTahun = new TableColumn<>("Tahun");
        colTahun.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTahunTerbit()));

        TableColumn<Buku, Boolean> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().isTersedia()));

        TableColumn<Buku, String> colPeminjam = new TableColumn<>("Dipinjam Oleh");
        colPeminjam.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPeminjam()));
        colPeminjam.setPrefWidth(120);
        
        colStatus.setCellFactory(column -> {
            return new TableCell<Buku, Boolean>() {
                @Override
                protected void updateItem(Boolean isTersedia, boolean empty) {
                    super.updateItem(isTersedia, empty);
                    if (empty || isTersedia == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        if (isTersedia) {
                            setText("Tersedia");
                            setStyle("-fx-text-fill: #10b981; -fx-font-weight: bold;");
                        } else {
                            setText("Dipinjam");
                            setStyle("-fx-text-fill: #ef4444; -fx-font-weight: bold;");
                        }
                    }
                }
            };
        });

        tabelBuku.getColumns().add(colId);
        tabelBuku.getColumns().add(colJudul);
        tabelBuku.getColumns().add(colPenulis);
        tabelBuku.getColumns().add(colTahun);
        tabelBuku.getColumns().add(colStatus);
        tabelBuku.getColumns().add(colPeminjam);
        
        // 3. Tombol Aksi
        btnPinjamBuku = new Button("Pinjam Buku");
        btnPinjamBuku.setPrefWidth(150);
        StyleUtils.applyNeumorphicStyle(btnPinjamBuku);

        btnKembalikanBuku = new Button("Kembalikan");
        btnKembalikanBuku.setPrefWidth(150);
        StyleUtils.applyNeumorphicStyle(btnKembalikanBuku);
        
        btnTambahBuku = new Button("Tambah Buku");
        btnTambahBuku.setPrefWidth(150);
        StyleUtils.applyNeumorphicStyle(btnTambahBuku);
        
        lblPesan = new Label();
        lblPesan.setStyle(StyleUtils.TEXT_WHITE + StyleUtils.FONT_BOLD);
        lblPesan.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        HBox bottomBox = new HBox(15, btnPinjamBuku, btnKembalikanBuku, btnTambahBuku, lblPesan);
        bottomBox.setAlignment(Pos.CENTER_LEFT);

        root = new VBox(20);
        root.setPadding(new Insets(25));
        StyleUtils.applyBackground(root);
        root.getChildren().addAll(topBox, tabelBuku, bottomBox);
    }

    public void setActionsVisible(boolean pinjam, boolean kembalikan, boolean tambah) {
        btnPinjamBuku.setVisible(pinjam); btnPinjamBuku.setManaged(pinjam);
        btnKembalikanBuku.setVisible(kembalikan); btnKembalikanBuku.setManaged(kembalikan);
        btnTambahBuku.setVisible(tambah); btnTambahBuku.setManaged(tambah);
    }

    public void setTitle(String title) {
        lblTitle.setText(title);
    }

    public VBox getRoot() { return root; }
    public TableView<Buku> getTabelBuku() { return tabelBuku; }
    public Button getBtnPinjamBuku() { return btnPinjamBuku; }
    public Button getBtnKembalikanBuku() { return btnKembalikanBuku; }
    public Button getBtnTambahBuku() { return btnTambahBuku; }
    public Button getBtnBack() { return btnBack; }
    public Label getLblPesan() { return lblPesan; }
}
