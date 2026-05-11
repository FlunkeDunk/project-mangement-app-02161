package dtu.planner.ui;

import java.io.IOException;

import dtu.planner.ui.interfaces.ActivityItemFactory;
import dtu.planner.ui.interfaces.PopupServiceFactory;
import dtu.superPlanner.ProjectManagementApp;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Arthur
 */


//
public class AppBootstrap {

    void start(Stage stage) {
        AlertService alertService = new AlertService();
        ProjectManagementApp app;
        UiState uiState = new UiState();
        try {
            app = new AppFactory().createApp();
        } catch (IOException e) {
            alertService.show(AlertType.ERROR, "Failed creating app", e.getMessage());
            System.getLogger(AppBootstrap.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return;
        }
        
        ActivityItemFactory activityItemFactory = new ActivityItemFactoryImplementation();
        PopupServiceFactory popupServiceFactory = new DefaultPopupServiceFactory();

        ControllerFactory controllerFactory = new ControllerFactory(
                app,
                alertService,
                null,
                uiState,
                activityItemFactory,
                popupServiceFactory);
        Navigator navigator = new Navigator(stage, controllerFactory);
        controllerFactory.setNavigator(navigator);
        try {
            navigator.changeScene(CustomScene.LOGIN);
        } catch (IOException e) {
            System.getLogger(AppBootstrap.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            alertService.show(AlertType.ERROR, "Failed loading scene", e.getMessage());
        }
    }
}


