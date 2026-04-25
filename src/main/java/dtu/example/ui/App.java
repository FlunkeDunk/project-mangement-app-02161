package dtu.example.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import dtu.superPlanner.ProjectManagementApp;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("create_project", new ProjectManagementApp()), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml, ProjectManagementApp app) throws IOException {
        scene.setRoot(loadFXML(fxml, app));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static Parent loadFXML(String fxml, ProjectManagementApp app) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(type -> {
            try {
                Object controller = type.getDeclaredConstructor().newInstance();
                if (controller instanceof ProjectManagementAware aware) {
                    aware.setProjectManagementApp(app);
                }
                return controller;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}