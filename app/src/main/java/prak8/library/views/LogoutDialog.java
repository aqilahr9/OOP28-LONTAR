package prak8.library.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.utils.StyleUtils;

public class LogoutDialog {

    private VBox root;
    private Button btnIya;
    private Button btnTidak;

    public LogoutDialog() {
        Label lblMessage = new Label("Anda yakin ingin log out?");
        lblMessage.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        lblMessage.setStyle(StyleUtils.TEXT_WHITE);

        btnIya = new Button("Iya");
        btnIya.setPrefWidth(100);
        StyleUtils.applyNeumorphicStyle(btnIya);

        btnTidak = new Button("Tidak");
        btnTidak.setPrefWidth(100);
        StyleUtils.applyNeumorphicStyle(btnTidak);

        HBox buttonBox = new HBox(20, btnIya, btnTidak);
        buttonBox.setAlignment(Pos.CENTER);

        root = new VBox(30, lblMessage, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        StyleUtils.applyBackground(root);
        root.setStyle(root.getStyle() + "-fx-border-color: #D2B48C; -fx-border-width: 2; -fx-border-radius: 15;");
    }

    public VBox getRoot() { return root; }
    public Button getBtnIya() { return btnIya; }
    public Button getBtnTidak() { return btnTidak; }
}
