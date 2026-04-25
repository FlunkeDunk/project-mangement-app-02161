package dtu.example.ui;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController extends ProjectManagementAwareController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("create_project", app);
    }
}