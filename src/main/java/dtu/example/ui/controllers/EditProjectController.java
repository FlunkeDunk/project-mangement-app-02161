package dtu.example.ui.controllers;

import java.io.IOException;

import dtu.example.ui.ProjectAware;
import dtu.superPlanner.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditProjectController extends ProjectManagementAwareController implements ProjectAware {

    @FXML
    private TextField projectNameTextField;

    @FXML
    private ComboBox<Employee> projectLeaderComboBox;

    private int projectId;

    private void setup() {
        ObservableList<Employee> employees = FXCollections.observableArrayList(app.getEmployees());
        employees.add(0, null);
        projectLeaderComboBox.setItems(employees);
        projectLeaderComboBox.setConverter(new EmployeeStringConverter());
        String leaderInitals = app.getProject(projectId).getProjectLeader();
        Employee leader = leaderInitals != null ? app.getEmployee(leaderInitals) : null;
        projectLeaderComboBox.setValue(leader);

        projectNameTextField.setText(app.getProject(projectId).getName());
    }

    @FXML
    private void onSave(ActionEvent event) throws IOException {
        try {
            String projectName = projectNameTextField.getText();
            Employee leader = projectLeaderComboBox.getValue();
            String leaderInitials = leader != null ? leader.getInitials() : null;
            try {
                app.setProjectLeader(projectId, leaderInitials);
            } catch (IllegalAccessException ex) {
                showAlert("Invalid access", ex.getMessage());
                System.getLogger(EditProjectController.class.getName()).log(System.Logger.Level.ERROR, (String) null,
                        ex);
            }
            app.setProjectName(projectId, projectName);
            navigator.changeScene("project_list");
        } catch (IllegalAccessException ex) {
            showAlert("Invalid access", ex.getMessage());
            System.getLogger(EditProjectController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
        setup();
    }
}