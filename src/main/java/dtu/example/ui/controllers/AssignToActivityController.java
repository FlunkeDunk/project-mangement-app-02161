package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.List;

import dtu.example.ui.ActivityAware;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class AssignToActivityController extends ProjectManagementAwareController implements ActivityAware{

    @FXML
    private ListView<String> employeeListView;

    @FXML
    private Button assignButton;

    @FXML
    private Button exitButton;

    int projectId;

    int activityId;

    @FXML
    private void initialize() {
        employeeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Example data (replace with real data source)
        employeeListView.getItems().addAll(
                "Alice",
                "Bob",
                "Charlie",
                "Diana",
                "Eric",
                "Felix",
                "George"
        );
    }
    
    @FXML    
    private void handleAssign() {
        List<String> selectedEmployees = employeeListView.getSelectionModel().getSelectedItems();
        for (String selectedEmployee : selectedEmployees) {
            if (selectedEmployee != null) {
                try {
                    app.addEmployeeToActivity(projectId, activityId, selectedEmployee);
                } catch (IllegalAccessException e) {
                    showAlert("Not allowed", e.getMessage());
                }
            }
        }
        employeeListView.getItems().removeAll(selectedEmployees);
        employeeListView.refresh();
    }
    @FXML
    private void handleExit() throws  IOException{
        navigator.changeScene("project_list");
    }

    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    @Override
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}