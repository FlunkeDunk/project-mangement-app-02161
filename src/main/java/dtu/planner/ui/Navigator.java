package dtu.planner.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {
    Stage stage;
    private ControllerFactory controllerFactory;

    public Navigator(Stage stage, ControllerFactory controllerFactory) {
        this.stage = stage;
        this.controllerFactory = controllerFactory;
    }

    public void changeScene(CustomScene fxmlScene) throws IOException {
        FXMLLoader loader = loadFXML(fxmlScene);
        Parent root = loader.load();


        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                App.class.getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }


    public FXMLLoader loadFXML(CustomScene scene) {
        var resource = App.class.getResource(scene.getFxmlFile() + ".fxml");
        if (resource == null) {
            throw new IllegalArgumentException("FXML file not found: " + scene.getFxmlFile());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        if (controllerFactory != null) {
            fxmlLoader.setControllerFactory(controllerFactory);
        }

        return fxmlLoader;
    }

}
