package dtu.example.ui;

import java.io.IOException;

import dtu.superPlanner.ProjectManagementApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Navigator {
    Stage stage;
    private Callback<Class<?>, Object> controllerFactory;

    public Navigator(Stage stage, ProjectManagementApp app) {
        this.stage = stage;
        this.controllerFactory = type -> {
            try {
                Object controller = type.getDeclaredConstructor().newInstance();
                if (controller instanceof ProjectManagementAware aware) {
                    aware.setProjectManagementApp(app);
                }
                if (controller instanceof NavigatorAware aware) {
                    aware.setNavigator(this);
                }
                return controller;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create controller: " + type.getName(), e);
            }
        };
    }

    public void setStage(Stage stage) {
        this.stage = stage;

    }

    public void changeScene(String fxml) throws IOException {
        stage.setScene(new Scene(loadFXML(fxml)));
        stage.show();
    }

    public Parent loadFXML(String fxml) throws IOException {
        var resource = App.class.getResource(fxml + ".fxml");
        if (resource == null) {
            throw new IllegalArgumentException("FXML file not found: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        if (controllerFactory != null) {
            fxmlLoader.setControllerFactory(controllerFactory);
        }

        return fxmlLoader.load();
    }
}
