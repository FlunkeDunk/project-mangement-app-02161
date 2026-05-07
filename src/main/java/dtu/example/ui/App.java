package dtu.example.ui;

import java.io.IOException;

import dtu.superPlanner.EmployeeRepository;
import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.ProjectManagementApp;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ProjectManagementApp app = buildApp();
        Navigator navigator = buildNavigator(stage, app);

        stage.setTitle("Project Management App");
        stage.setWidth(640);
        stage.setHeight(540);

        navigator.toLogin();
    }

    private ProjectManagementApp buildApp() {
        EmployeeRepository repo = new FileEmployeeRepository("initials.txt");
        return new ProjectManagementApp(repo);
    }

    private Navigator buildNavigator(Stage stage, ProjectManagementApp app) {
        return new Navigator(
            stage,
            app,
            new DefaultPopupServiceFactory(),
            new ActivityItemFactory()
        );
    }
}