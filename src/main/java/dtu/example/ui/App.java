package dtu.example.ui;

import java.io.IOException;

import dtu.superPlanner.ProjectManagementApp;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private Navigator navigator;

    private final int HEIGHT = 480;
    private final int WIDTH = 640;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Project Management App");
        ProjectManagementApp app = new ProjectManagementApp();
        app.createEmployees(new EmployeeFileReader().loadEmployees());
        navigator = new Navigator(stage, app);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        navigator.changeScene("login");
    }

    public static void main(String[] args) {
        launch();
    }

}