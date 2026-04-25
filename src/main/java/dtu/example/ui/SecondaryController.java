package dtu.example.ui;

import java.io.IOException;

import javafx.fxml.FXML;

public class SecondaryController extends ProjectManagementAwareController {

    @FXML
    private void switchToPrimary() throws IOException {
        navigator.changeScene("create_project");
    }
}