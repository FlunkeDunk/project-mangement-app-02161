package dtu.example.ui.controllers;

import java.io.IOException;

import dtu.example.ui.ProjectAware;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditProjectController extends ProjectManagementAwareController implements ProjectAware{

    @FXML
    private TextField projectNameTextField;

    private int projectId;

    @FXML
    private void onSave(ActionEvent event) throws IOException {
        String projectName = projectNameTextField.getText();
        app.getProject(projectId).editName("", projectName);
        navigator.changeScene("project_list");
    }

    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}