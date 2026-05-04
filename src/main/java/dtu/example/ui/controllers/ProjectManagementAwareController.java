package dtu.example.ui.controllers;

import dtu.example.ui.Navigator;
import dtu.example.ui.NavigatorAware;
import dtu.example.ui.ProjectManagementAware;
import dtu.superPlanner.ProjectManagementApp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class ProjectManagementAwareController implements ProjectManagementAware, NavigatorAware {
    protected ProjectManagementApp app;
    protected Navigator navigator;

    @Override
    public void setProjectManagementApp(ProjectManagementApp app) {
        this.app = app;
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
