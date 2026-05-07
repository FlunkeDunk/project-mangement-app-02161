package dtu.example.ui.controllers;

import java.io.IOException;

import dtu.example.ui.Navigator;
import dtu.example.ui.NavigatorAware;
import dtu.example.ui.ProjectManagementAware;
import dtu.example.ui.interfaces.ThrowingBiConsumer;
import dtu.example.ui.interfaces.ThrowingConsumer;
import dtu.example.ui.interfaces.ThrowingRunnable;
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

    public void executeUiAction(ThrowingRunnable action, String errorTitle) {
        try {
            action.run();
        } catch (IOException e) {
            showAlert(errorTitle, e.getMessage());
        }
    }

    public <T> void executeUiAction(
            ThrowingConsumer<T> action,
            T arg,
            String errorTitle) {

        try {
            action.accept(arg);
        } catch (IOException e) {
            showAlert(errorTitle, e.getMessage());
        }
    }

    public <U, T> void executeUiAction(ThrowingBiConsumer<U, T> action, U arg, T arg2, String errorTitle) {
        try {
            action.accept(arg, arg2);
        } catch (IOException e) {
            showAlert(errorTitle, e.getMessage());
        }
    }
}
