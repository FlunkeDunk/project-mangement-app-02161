package dtu.example.ui.controllers;

import java.util.List;

import dtu.example.ui.ActivityAware;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

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
                System.out.println("Assigned employee: " + selectedEmployee);
            }
        }
        employeeListView.getItems().removeAll(selectedEmployees);
        employeeListView.refresh();
    }
    @FXML
    private void handleExit() {
        // Close the window
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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