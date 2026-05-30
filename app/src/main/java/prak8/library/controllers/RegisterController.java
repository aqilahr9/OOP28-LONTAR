package prak8.library.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import prak8.library.dao.UserDAO;
import prak8.library.models.Admin;
import prak8.library.models.Member;
import prak8.library.models.User;
import prak8.library.views.LoginView;
import prak8.library.views.RegisterView;

public class RegisterController {
    private RegisterView view;
    private UserDAO userDAO;

    public RegisterController(RegisterView view) {
        this.view = view;
        this.userDAO = new UserDAO();

        this.view.getBtnSimpan().setOnAction(e -> handleRegister());
        this.view.getBtnBatal().setOnAction(e -> kembaliKeLogin());
    }

    private void handleRegister() {
        String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();
        String alamat = view.getTxtAlamat().getText();
        String noHp = view.getTxtNoHp().getText();
        String role = view.getComboRole().getValue();

        if (username.isEmpty() || password.isEmpty() || alamat.isEmpty() || noHp.isEmpty() || role == null) {
            view.getLblStatus().setText("Semua data harus terisi!");
            return;
        }

        User userBaru = role.equals("Admin") ? 
            new Admin(username, password, alamat, noHp) : 
            new Member(username, password, alamat, noHp);

        userDAO.tambahUser(userBaru);
        kembaliKeLogin();
    }

    private void kembaliKeLogin() {
        Stage stage = (Stage) view.getBtnBatal().getScene().getWindow();
        LoginView loginView = new LoginView();
        new LoginController(loginView);
        stage.setScene(new Scene(loginView.getRoot(), 400, 480));
    }
}
