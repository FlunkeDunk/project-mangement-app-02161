package dtu.planner.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import dtu.planner.ui.UiState;
import dtu.planner.ui.interfaces.UiStateAware;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
    /**
    * @author Arthur
    */
public class AssignToActivityController extends ProjectManagementAwareController implements UiStateAware{

    @FXML
    private ListView<String> employeeListView;
    private UiState uiState;

    @FXML
    private void loadInitials() {
        employeeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<String> initialsList = new ArrayList<>();
        try {
            initialsList = app.getAvailableEmployees(uiState.getProjectId(), uiState.getActivityId());
        } catch (IllegalAccessException ex) {
            alertService.show("Invalid acces", ex.getMessage());
            System.getLogger(AssignToActivityController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        if (initialsList != null) {
            employeeListView.getItems().addAll(initialsList);
        }
    }
    
    @FXML    
    private void handleAssign() {
        int projectId = uiState.getProjectId();
        int activityId = uiState.getActivityId();
        List<String> selectedEmployees = employeeListView.getSelectionModel().getSelectedItems();
        for (String selectedEmployee : selectedEmployees) {
            if (selectedEmployee != null) {
                try {
                    app.addEmployeeToActivity(projectId, activityId, selectedEmployee);
                } catch (IllegalAccessException e) {
                    alertService.show("Not allowed", e.getMessage());
                }
            }
        }
        employeeListView.getItems().removeAll(selectedEmployees);
        employeeListView.refresh();
    }

    @Override
    public void setUiState(UiState uiState) {
        this.uiState = uiState;
    }
}