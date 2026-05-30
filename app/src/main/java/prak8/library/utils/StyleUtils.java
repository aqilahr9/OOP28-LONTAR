package prak8.library.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StyleUtils {
    
    public static final String GALAXY_VIBRANT = "-fx-background-color: linear-gradient(to bottom right, #1A1A40, #3B1C55, #6A0572, #A3423C);";
    public static final String TEXT_NAVY = "-fx-text-fill: #000080;";
    public static final String TEXT_WHITE = "-fx-text-fill: white;";
    public static final String FONT_BOLD = "-fx-font-weight: bold;";
    
    public static final String NEUMORPHIC_RAISED = 
        "-fx-background-color: #F5F5F5; " +
        "-fx-background-radius: 15; " +
        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 15, 0, 8, 8);";
        
    public static final String NEUMORPHIC_PRESSED = 
        "-fx-background-color: #E0E0E0; " +
        "-fx-background-radius: 15; " +
        "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 4, 4);";

    public static final String NEUMORPHIC_INSET = 
        "-fx-background-color: #FFFFFF; " +
        "-fx-background-radius: 10; " +
        "-fx-text-fill: #000080; " +
        "-fx-prompt-text-fill: rgba(0,0,128,0.5); " +
        "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 3, 3); " +
        "-fx-padding: 10;";

    public static void applyNeumorphicStyle(Button button) {
        button.setStyle(NEUMORPHIC_RAISED + TEXT_NAVY + FONT_BOLD);
        
        button.setOnMouseEntered(e -> button.setStyle(NEUMORPHIC_PRESSED + TEXT_NAVY + FONT_BOLD));
        button.setOnMouseExited(e -> button.setStyle(NEUMORPHIC_RAISED + TEXT_NAVY + FONT_BOLD));
        button.setOnMousePressed(e -> button.setStyle(NEUMORPHIC_PRESSED + TEXT_NAVY + FONT_BOLD));
        button.setOnMouseReleased(e -> button.setStyle(NEUMORPHIC_PRESSED + TEXT_NAVY + FONT_BOLD));
    }

    public static void applyBackground(Region region) {
        region.setStyle(GALAXY_VIBRANT);
    }

    public static VBox createIconButton(String icon, String labelText) {
        Button btn = new Button(icon);
        btn.setPrefSize(100, 100);
        btn.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        applyNeumorphicStyle(btn);
        
        Label lbl = new Label(labelText);
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lbl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;"); // Labels stay white for contrast
        
        VBox box = new VBox(10, btn, lbl);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    public static Button getButtonFromIconBox(VBox box) {
        if (box.getChildren().get(0) instanceof Button) {
            return (Button) box.getChildren().get(0);
        }
        return null;
    }
}
