package prak8.library.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import prak8.library.dao.BukuDAO;
import prak8.library.models.Buku;
import prak8.library.views.TambahBukuView;
import prak8.library.views.SuccessView;

public class TambahBukuController {

    private TambahBukuView view;
    private BukuDAO bukuDAO;
    private DashboardController dashboardController;

    public TambahBukuController(TambahBukuView view, DashboardController dashboardController) {
        this.view = view;
        this.dashboardController = dashboardController;
        this.bukuDAO = new BukuDAO();

        this.view.getBtnSimpan().setOnAction(e -> handleSimpan());
    }

    private void handleSimpan() {
        try {
            String id = view.getTxtId().getText();
            String judul = view.getTxtJudul().getText();
            String penulis = view.getTxtPenulis().getText();
            String tahunStr = view.getTxtTahun().getText();

            if (id.isEmpty() || judul.isEmpty() || penulis.isEmpty() || tahunStr.isEmpty()) {
                view.getLblStatus().setText("Semua data harus terisi!");
                return;
            }

            int tahun = Integer.parseInt(tahunStr);

            Buku bukuBaru = new Buku(id, judul, penulis, tahun, true, "-");
            bukuDAO.tambahBuku(bukuBaru);

            // Me-refresh data tabel di jendela Dashboard
            if (dashboardController != null) {
                dashboardController.loadData();
                showSuccessNotification("Buku Ditambahkan!", "Buku " + judul + " telah masuk database.");
            }

            Stage stage = (Stage) view.getBtnSimpan().getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            view.getLblStatus().setText("Tahun harus berupa angka!");
        }
    }

    private void showSuccessNotification(String header, String subtext) {
        SuccessView successView = new SuccessView(header, subtext);
        Stage stage = new Stage();
        stage.setScene(new Scene(successView.getRoot(), 400, 300));
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.show();
    }
}
