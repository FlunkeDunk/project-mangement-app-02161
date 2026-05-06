package dtu.example.ui.controllers;

import java.io.IOException;

import dtu.superPlanner.Employee;
import dtu.superPlanner.Project;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreateProjectController extends ProjectManagementAwareController {

    @FXML
    TextField projectNameTextField;

    @FXML
    ChoiceBox<Employee> projectLeaderChoiceBox;

    @FXML
    private void initialize() {
        projectLeaderChoiceBox.setItems(
                FXCollections.observableArrayList(app.getEmployees()));

        projectLeaderChoiceBox.setConverter(new EmployeeStringConverter());
    }

    @FXML
    private void createProject() throws IOException, IllegalAccessException {
        Project project = app.createProject(projectNameTextField.getText());
        if (projectLeaderChoiceBox.getValue() != null) {
            app.setProjectLeader(project.getId(), projectLeaderChoiceBox.getValue().getInitials());
        }
        WeekBasedCalendar wbc = project.getStartDate();
        navigator.changeScene("project_list");
    }
}