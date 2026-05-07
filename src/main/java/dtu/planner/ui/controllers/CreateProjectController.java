package dtu.planner.ui.controllers;

import java.io.IOException;

import dtu.planner.ui.EmployeeStringConverter;
import dtu.superPlanner.Employee;
import dtu.superPlanner.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

    /**
    * @author Arthur, Emanuel, Mikkel, Ebbe and Benjamin with mob programming
    */

public class CreateProjectController extends ProjectManagementAwareController {

    @FXML
    TextField projectNameTextField;

    @FXML
    ChoiceBox<Employee> projectLeaderChoiceBox;

    @FXML
    private void initialize() {
        ObservableList<Employee> employees = FXCollections.observableArrayList(app.getEmployees());
        employees.add(0, null);
        projectLeaderChoiceBox.setItems(employees);

        projectLeaderChoiceBox.setConverter(new EmployeeStringConverter());
    }

    @FXML
    private void createProject() throws IOException, IllegalAccessException {
        Project project = app.createProject(projectNameTextField.getText());
        if (projectLeaderChoiceBox.getValue() != null) {
            app.setProjectLeader(project.getId(), projectLeaderChoiceBox.getValue().getInitials());
        }
        navigator.toProjectList();;
    }
}