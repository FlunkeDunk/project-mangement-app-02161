package dtu.example.ui.controllers;

import java.io.IOException;

import dtu.example.ui.ProjectAware;
import dtu.superPlanner.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EditProjectController extends ProjectManagementAwareController implements ProjectAware {

    @FXML
    private TextField projectNameTextField;

    @FXML
    private ChoiceBox<Employee> projectLeaderChoiceBox;

    private int projectId;

    @FXML
    private void initialize() {
        projectLeaderChoiceBox.setItems(
                FXCollections.observableArrayList(app.getEmployees()));

        projectLeaderChoiceBox.setConverter(new EmployeeStringConverter());
    }

    @FXML
    private void onSave(ActionEvent event) throws IOException {
        String projectName = projectNameTextField.getText();
        if (projectLeaderChoiceBox.getValue() != null) {
            try {
                app.setProjectLeader(projectId, projectLeaderChoiceBox.getValue().getInitials());
            } catch (IllegalAccessException ex) {
                showAlert("Invalid access", ex.getMessage());
            }
        }
        app.getProject(projectId).editName("", projectName);
        navigator.changeScene("project_list");
    }

    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}