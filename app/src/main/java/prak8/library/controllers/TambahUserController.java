package prak8.library.controllers;

import javafx.stage.Stage;
import prak8.library.dao.UserDAO;
import prak8.library.models.Admin;
import prak8.library.models.Member;
import prak8.library.models.User;
import prak8.library.views.RegisterView;

public class TambahUserController {

    private RegisterView view;
    private UserDAO userDAO;

    public TambahUserController(RegisterView view) {
        this.view = view;
        this.userDAO = new UserDAO();

        // Memasang event listener pada tombol simpan di View
        this.view.getBtnSimpan().setOnAction(e -> handleSimpan());
    }

    private void handleSimpan() {
        String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();
        String alamat = view.getTxtAlamat().getText();
        String noHp = view.getTxtNoHp().getText();
        String role = view.getComboRole().getValue();

        if (username.isEmpty() || password.isEmpty() || alamat.isEmpty() || noHp.isEmpty() || role == null) {
            view.getLblStatus().setText("Semua data harus terisi!");
            return;
        }

        User userBaru;
        if (role.equals("Admin")) {
            userBaru = new Admin(username, password, alamat, noHp);
        } else {
            userBaru = new Member(username, password, alamat, noHp);
        }

        userDAO.tambahUser(userBaru);
        
        Stage stage = (Stage) view.getBtnSimpan().getScene().getWindow();
        stage.close();
    }
}