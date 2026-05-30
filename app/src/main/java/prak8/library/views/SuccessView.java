package prak8.library.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import prak8.library.utils.StyleUtils;

public class SuccessView {

    private VBox root;

    public SuccessView(String header, String subtext) {

        StackPane circleStack = new StackPane();
        Circle circle = new Circle(60);
        circle.setStyle("-fx-fill: rgba(255,255,255,0.1); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 5, 5);");
        
        Label checkmark = new Label("✓");
        checkmark.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        checkmark.setStyle(StyleUtils.TEXT_WHITE);
        
        circleStack.getChildren().addAll(circle, checkmark);

        Label lblHeader = new Label(header);
        lblHeader.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblHeader.setStyle(StyleUtils.TEXT_WHITE);

        Label lblSubtext = new Label(subtext);
        lblSubtext.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        lblSubtext.setStyle(StyleUtils.TEXT_WHITE);

        Button btnClose = new Button("OK");
        btnClose.setPrefWidth(100);
        StyleUtils.applyNeumorphicStyle(btnClose);
        btnClose.setOnAction(e -> btnClose.getScene().getWindow().hide());

        root = new VBox(20, circleStack, lblHeader, lblSubtext, btnClose);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        StyleUtils.applyBackground(root);
    }

    public VBox getRoot() { return root; }
}
