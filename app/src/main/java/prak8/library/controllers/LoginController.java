package prak8.library.controllers;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import prak8.library.dao.UserDAO;
import prak8.library.models.User;
import prak8.library.views.DashboardView;
import prak8.library.views.LoginView;
import prak8.library.views.RegisterView;

public class LoginController {

    private LoginView view;
    private UserDAO userDAO;

    public LoginController(LoginView view) {
        this.view = view;
        this.userDAO = new UserDAO();
        
        this.view.getBtnLogin().setOnAction(event -> handleLogin());
        this.view.getBtnRegister().setOnAction(event -> handleBukaRegister());
    }

    private void handleLogin() {
        String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();

        if (username.isEmpty() || password.isEmpty()) {
            view.getLblStatus().setTextFill(Color.WHITE);
            view.getLblStatus().setText("Username atau Password kosong!");
            return;
        }

        User userLogon = userDAO.verifikasiLogin(username, password);

        if (userLogon != null) {
            view.getLblStatus().setTextFill(Color.GREEN);
            view.getLblStatus().setText("Login Berhasil!");
            
            Stage stage = (Stage) view.getBtnLogin().getScene().getWindow();
            
            DashboardView dashboardView = new DashboardView(userLogon); 
            new DashboardController(dashboardView, userLogon); 
            
            stage.setScene(new Scene(dashboardView.getRoot(), 850, 600));
            stage.setTitle("Dashboard Perpustakaan - " + userLogon.getRole());
            
        } else {
            view.getLblStatus().setTextFill(Color.WHITE);
            view.getLblStatus().setText("Username atau Password salah!");
        }
    }

    private void handleBukaRegister() {
        Stage stage = (Stage) view.getBtnRegister().getScene().getWindow();
        RegisterView regView = new RegisterView();
        new RegisterController(regView);
        stage.setScene(new Scene(regView.getRoot(), 400, 550));
        stage.setTitle("Pendaftaran Akun Baru");
    }
}
