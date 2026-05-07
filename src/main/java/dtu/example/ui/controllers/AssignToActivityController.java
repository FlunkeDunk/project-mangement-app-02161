package dtu.example.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import dtu.example.ui.ActivityAware;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
    /**
    * @author Arthur
    */
public class AssignToActivityController extends ProjectManagementAwareController implements ActivityAware{

    @FXML
    private ListView<String> employeeListView;

    int projectId;

    int activityId;

    @FXML
    private void loadInitials() {
        employeeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<String> initialsList = new ArrayList<>();
        try {
            initialsList = app.getAvailableEmployees(projectId, activityId);
        } catch (IllegalAccessException ex) {
            showAlert("Invalid acces", ex.getMessage());
            System.getLogger(AssignToActivityController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        if (initialsList != null) {
            employeeListView.getItems().addAll(initialsList);
        }
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

    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
        if (activityId != 0) {
            loadInitials();
        }
    }
    
    @Override
    public void setActivityId(int activityId) {
        this.activityId = activityId;
        if (projectId != 0) {
            loadInitials();
        }
    }
}