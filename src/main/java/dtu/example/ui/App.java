package dtu.example.ui;

import java.io.IOException;

import dtu.superPlanner.ProjectManagementApp;
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        AlertService alertService = new AlertService();
        ProjectManagementApp app;
        try {
            app = new AppFactory().createApp();
        } catch (IOException e) {
            alertService.show(AlertType.ERROR, "Failed creating app", e.getMessage());
            return;
        }
        
        Navigator navigator = buildNavigator(stage, app, alertService);
        configureStage(stage);
        try {
            navigator.toLogin();
        } catch (IOException e) {
            alertService.show(AlertType.ERROR, "Failed loading scene", e.getMessage());
        }
    }

    private Navigator buildNavigator(Stage stage, ProjectManagementApp app, AlertService alertService) {
        return new Navigator(
                stage,
                app,
                new DefaultPopupServiceFactory(),
                new ActivityItemFactory(),
                alertService);
    }

    private void configureStage(Stage stage) {
        stage.setTitle("Project Management App");
        stage.setWidth(640);
        stage.setHeight(540);
    }
}