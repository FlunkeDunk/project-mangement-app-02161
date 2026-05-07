package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.function.Consumer;

import dtu.example.ui.CustomScene;
import dtu.example.ui.Navigator;
import dtu.example.ui.interfaces.ActivityAware;
import dtu.example.ui.interfaces.PopupService;
import dtu.example.ui.interfaces.ProjectAware;
import dtu.superPlanner.Report;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Arthur
 */

public class PopUpManager implements PopupService {

    private AnchorPane popUpPane;
    private Parent popUpPaneContainer;
    private Node background;
    private Navigator navigator;
    private Parent currentPopUp;

    public PopUpManager(AnchorPane popUpPane, Pane popUpContainer, Node background, Navigator navigator) {
        this.popUpPane = popUpPane;
        this.background = background;
        this.navigator = navigator;
        this.popUpPaneContainer = popUpContainer;
    }

    @Override
    public <T> void popUp(CustomScene scene, Class<T> type, Consumer<T> initializer) throws IOException {
        if (currentPopUp != null) {
            popDown();
        }

        FXMLLoader loader = navigator.loadFXML(scene);

        currentPopUp = loader.load();

        if (initializer != null) {
            initializer.accept(loader.getController());
        }

        popUpPane.getChildren().add(currentPopUp);
        setup(true);

        AnchorPane.setTopAnchor(currentPopUp, 0.0);
        AnchorPane.setBottomAnchor(currentPopUp, 0.0);
        AnchorPane.setLeftAnchor(currentPopUp, 0.0);
        AnchorPane.setRightAnchor(currentPopUp, 0.0);
    }

    @Override
    public void popUp(CustomScene scene) throws IOException {
        popUp(scene, null, null);
    }

    public void popDown() {
        if (currentPopUp != null) {
            popUpPane.getChildren().remove(currentPopUp);
        }
        setup(false);
    }

    private void setup(boolean poppingUp) {
        popUpPaneContainer.setMouseTransparent(!poppingUp);
        popUpPaneContainer.setVisible(poppingUp);
        background.setDisable(poppingUp);
    }

    @Override
    public <T extends ActivityAware> void popUpWithActivity(CustomScene scene, Class<T> type, int projectId,
            int activityId) throws IOException {
        popUp(scene, type, controller -> {
            T typedController = type.cast(controller);
            typedController.setProjectId(projectId);
            typedController.setActivityId(activityId);
        });
    }

    @Override
    public void registerTime(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.REGISTER_TIME, RegisterTimeController.class, projectId, activityId);
    }

    @Override
    public void editActivity(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.EDIT_ACTIVITY, EditActivityController.class, projectId, activityId);
    }

    @Override
    public void editRegisteredTime(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.EDIT_REGISTERED_TIME, EditRegisteredTimeController.class, projectId, activityId);
    }

    @Override
    public void assignToActivity(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.ASSIGN_TO_ACTIVITY, AssignToActivityController.class, projectId, activityId);
    }

    @Override
    public <T extends ProjectAware> void popUpWithProject(CustomScene scene, Class<T> type, int projectId) throws IOException{
        popUp(scene, type, controller -> {
            T typedController = type.cast(controller);
            typedController.setProjectId(projectId);
        });
    }

    @Override
    public void editProject(int projectId) throws IOException{
        popUpWithProject(CustomScene.EDIT_PROJECT, EditProjectController.class, projectId);
    }
    
    @Override
    public void addActivity(int projectId) throws IOException{
        popUpWithProject(CustomScene.CREATE_ACTIVITY, CreateActivityController.class, projectId);
    }

    @Override
    public void viewReport(Report report) throws IOException{
        popUp(CustomScene.VIEW_REPORT, ViewReportController.class, c -> c.setReport(report));
    }

    @Override
    public void createProject() throws IOException {
        popUp(CustomScene.CREATE_PROJECT);
    }
    
    @Override
    public void addFixedActivity() throws IOException {
        popUp(CustomScene.ADD_FIXED_ACTIVITY);
    }

}
