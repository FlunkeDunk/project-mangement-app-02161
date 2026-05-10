package dtu.planner.ui.controllers;

import java.io.IOException;

import dtu.planner.ui.CustomScene;
import dtu.planner.ui.EmployeeStringConverter;
import dtu.planner.ui.UiState;
import dtu.planner.ui.interfaces.UiStateAware;
import dtu.superPlanner.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

    /**
    * @author Arthur
    */
public class EditProjectController extends ProjectManagementAwareController implements UiStateAware {

    @FXML
    private TextField projectNameTextField;

    @FXML
    private ComboBox<Employee> projectLeaderComboBox;

    private int projectId;

    @FXML
    private void initialize() {
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
        String projectName = projectNameTextField.getText();
        Employee leader = projectLeaderComboBox.getValue();
        String leaderInitials = leader != null ? leader.getInitials() : null;
        try {
            app.setProjectName(projectId, projectName);
        } catch (IllegalAccessException ex) {
            alertService.show("Invalid access", ex.getMessage());
            System.getLogger(EditProjectController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return;
        }
        
        try {
            app.setProjectLeader(projectId, leaderInitials);
        } catch (IllegalAccessException ex) {
            alertService.show("Invalid access", ex.getMessage());
            System.getLogger(EditProjectController.class.getName()).log(System.Logger.Level.ERROR, (String) null,
            ex);
        }
        navigator.changeScene(CustomScene.PROJECT_LIST);
    }


    @Override
    public void setUiState(UiState uiState) {
        this.projectId = uiState.getProjectId();
    }
}