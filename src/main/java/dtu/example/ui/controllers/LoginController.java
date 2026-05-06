package dtu.example.ui.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController extends ProjectManagementAwareController{

    @FXML
    private TextField userInitialsTextField;

    @FXML
    private Button loginButton;

    @FXML
    private void loginPressed() throws IOException {
        String initials = userInitialsTextField.getText().toLowerCase();

        if (initials == null || initials.trim().isEmpty()) {
            showAlert("Login Error", "Please enter your user initials.");
            return;
        }

        initials = initials.trim();

        // Validate: only letters, length 4
        if (!initials.matches("[a-zA-Z]{4}")) {
            showAlert("Login Error", "Initials must be 4 letters (A–Z only).");
            return;
        }

        try {
            app.login(initials);
        } catch (IllegalArgumentException ex) {
            showAlert("Invalid initials", ex.getMessage());
            return;
        }

        navigator.changeScene("project_list");
    }
}