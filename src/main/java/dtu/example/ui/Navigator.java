package dtu.example.ui;

import java.io.IOException;
import java.util.function.Consumer;

import dtu.example.ui.controllers.ProjectListController;
import dtu.example.ui.controllers.ViewReportController;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.Report;
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

    private void setStage(Stage stage) {
        this.stage = stage;

    }

    private <T> T changeScene(CustomScene fxmlScene, Consumer<T> initializer) throws IOException {
        FXMLLoader loader = loadFXML(fxmlScene);
        Parent root = loader.load();
        
        if (initializer != null) {
            initializer.accept(loader.getController());
        }
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
            App.class.getResource("style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.show();

        return loader.getController();
    }

    private <T> T changeScene(CustomScene scene) throws IOException {
        return changeScene(scene, null);
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

    public void toRegisterTimeList() throws IOException{
        changeScene(CustomScene.REGISTER_TIME_LIST);
    }

    public void toLogin() throws IOException{
        changeScene(CustomScene.LOGIN);
    }

    public void toProjectList() throws IOException{
        ProjectListController controller = changeScene(CustomScene.PROJECT_LIST);
        controller.setActivityItemFactory(new ActivityItemFactory());
    }

public void toViewReport(Report report) throws IOException{
    changeScene(CustomScene.VIEW_REPORT, controller -> {
        ViewReportController typedController = (ViewReportController) controller;
        typedController.setReport(report);
    });
}

}
