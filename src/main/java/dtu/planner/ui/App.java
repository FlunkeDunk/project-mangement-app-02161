package dtu.planner.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 * @author Arthur
 */
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        configureStage(stage);
        AppBootstrap appBootstrap = new AppBootstrap();
        appBootstrap.start(stage);
    }

    private void configureStage(Stage stage) {
        stage.setTitle("Project Management App");
        stage.setWidth(640);
        stage.setHeight(540);
    }
}