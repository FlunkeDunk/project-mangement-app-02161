package dtu.example.ui.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController extends ProjectManagementAwareController{

    @FXML
    private TextField userInitialsTextField;

    @FXML
    private Button loginButton;

    @FXML
    private void loginPressed() throws IOException {
        String initials = userInitialsTextField.getText();

        if (initials == null || initials.trim().isEmpty()) {
            showAlert("Login Error", "Please enter your user initials.");
            return;
        }

        initials = initials.trim();

        // Validate: only letters, length 3–4
        if (!initials.matches("[a-zA-Z]{4}")) {
            showAlert("Login Error", "Initials must be 4 letters (A–Z only).");
            return;
        }

        app.login(initials);

        navigator.changeScene("project_list");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}