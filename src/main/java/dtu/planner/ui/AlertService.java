package dtu.planner.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Arthur
 */

public class AlertService {
    public void show(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void show(String title, String message) {
        show(AlertType.WARNING, title, message);
    }
}
