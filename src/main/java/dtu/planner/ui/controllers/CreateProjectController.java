package dtu.planner.ui.controllers;

import java.io.IOException;

import dtu.planner.ui.EmployeeStringConverter;
import dtu.superPlanner.Employee;
import dtu.superPlanner.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

    /**
    * @author Arthur, Emanuel, Mikkel, Ebbe and Benjamin with mob programming
    */

public class CreateProjectController extends ProjectManagementAwareController {

    @FXML
    TextField projectNameTextField;

    @FXML
    ComboBox<Employee> projectLeaderComboBox;

    @FXML
    private void initialize() {
        ObservableList<Employee> employees = FXCollections.observableArrayList(app.getEmployees());
        employees.add(0, null);
        projectLeaderComboBox.setItems(employees);

        projectLeaderComboBox.setConverter(new EmployeeStringConverter());
    }

    @FXML
    private void createProject() throws IOException, IllegalAccessException {
        Project project = app.createProject(projectNameTextField.getText());
        if (projectLeaderComboBox.getValue() != null) {
            app.setProjectLeader(project.getId(), projectLeaderComboBox.getValue().getInitials());
        }
        navigator.toProjectList();;
    }
}