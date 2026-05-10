package dtu.planner.ui;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Arthur
 */
public class Navigator {
    Stage stage;
    private ControllerFactory controllerFactory;

    public Navigator(Stage stage, ControllerFactory controllerFactory) {
        this.stage = stage;
        this.controllerFactory = controllerFactory;
    }

    private void setStage(Stage stage) {
        this.stage = stage;

    }

    public <T> T changeScene(CustomScene fxmlScene, Consumer<T> initializer) throws IOException {
        FXMLLoader loader = loadFXML(fxmlScene);
        Parent root = loader.load();

        if (initializer != null) {
            initializer.accept(loader.getController());
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                App.class.getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();

        return loader.getController();
    }

    public <T> T changeScene(CustomScene scene) throws IOException {
        return changeScene(scene, controller -> {});
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
