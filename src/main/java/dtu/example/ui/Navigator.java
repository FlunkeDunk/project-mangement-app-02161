package dtu.example.ui;

import java.io.IOException;
import java.util.function.Consumer;

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

    public <T> void changeScene(String fxml, Consumer<T> initializer) throws IOException {
        FXMLLoader loader = loadFXML(fxml);
        Parent root = loader.load();
        
        if (initializer != null) {
            initializer.accept(loader.getController());
        }

        stage.setScene(new Scene(root));
        stage.show();
    }

    public <T> void changeScene(String fxml) throws IOException {
        FXMLLoader loader = loadFXML(fxml);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public FXMLLoader loadFXML(String fxml) throws IOException {
        var resource = App.class.getResource(fxml + ".fxml");
        if (resource == null) {
            throw new IllegalArgumentException("FXML file not found: " + fxml);
        }
        
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        if (controllerFactory != null) {
            fxmlLoader.setControllerFactory(controllerFactory);
        }

        return fxmlLoader;
    }
}
