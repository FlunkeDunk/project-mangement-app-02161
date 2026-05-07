package dtu.example.ui;

import java.io.IOException;
import java.io.InputStream;

import dtu.superPlanner.EmployeeRepository;
import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.ProjectManagementApp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {

        ProjectManagementApp app;
        try {
            app = buildApp();
        } catch (IOException e) {
            showAlert(AlertType.WARNING, "Failed building app model", e.getMessage());
            return;
        }
        Navigator navigator = buildNavigator(stage, app);
        
        stage.setTitle("Project Management App");
        stage.setWidth(640);
        stage.setHeight(540);
        
        try {
            navigator.toLogin();
        } catch (IOException e) {
            showAlert(AlertType.WARNING, "Failed loading scene", e.getMessage());
        }
    }

    private ProjectManagementApp buildApp() throws IOException {
        EmployeeRepository employeeRepository = buildEmployeeRepository();
        return new ProjectManagementApp(employeeRepository);
    }

    private Navigator buildNavigator(Stage stage, ProjectManagementApp app) {
        return new Navigator(
                stage,
                app,
                new DefaultPopupServiceFactory(),
                new ActivityItemFactory());
    }

    private EmployeeRepository buildEmployeeRepository() throws IOException{
        InputStream input = getClass().getClassLoader().getResourceAsStream("initials.txt");
        FileEmployeeRepository repo;
        try {
            repo = new FileEmployeeRepository(input);
        } catch (IOException e) {
            showAlert(
                    AlertType.ERROR,
                    "Failed reading file",
                    "Failed reading initials.txt");
            throw e;
        }

        if (repo.getSkippedLines()) {
            Platform.runLater(() -> showAlert(
                    AlertType.WARNING,
                    "Skipped lines",
                    "Some lines in employee text file were skipped"));
        }
        return repo;
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}