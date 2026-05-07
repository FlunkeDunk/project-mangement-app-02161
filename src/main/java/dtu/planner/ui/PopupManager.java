package dtu.planner.ui;

import java.io.IOException;
import java.util.function.Consumer;

import dtu.planner.ui.interfaces.ActivityAware;
import dtu.planner.ui.interfaces.PopupDisplayer;
import dtu.planner.ui.interfaces.PopupService;
import dtu.planner.ui.interfaces.ProjectAware;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * @author Arthur
 */

public class PopupManager implements PopupService {

    private final Navigator NAVIGATOR;
    private PopupDisplayer popupDisplayer;

    public PopupManager(PopupDisplayer popupDisplayer, Navigator navigator) {
        this.NAVIGATOR = navigator;
        this.popupDisplayer = popupDisplayer;
    }

    @Override
    public <T> void popUp(CustomScene scene, Consumer<T> initializer) throws IOException {
        popDown();
        FXMLLoader loader = NAVIGATOR.loadFXML(scene);
        Parent popup = loader.load();
        T controller = loader.getController();

        initializer.accept(controller);

        popupDisplayer.display(popup);
    }

    @Override
    public void popUp(CustomScene scene) throws IOException {
        popUp(scene, controller -> {
        });
    }

    @Override
    public void popDown() {
        popupDisplayer.removePopup();
    }


    @Override
    public <T extends ActivityAware> void popUpWithActivity(
            CustomScene scene, int projectId,
            int activityId)
            throws IOException {
        popUp(scene, (T controller) -> {
            controller.setProjectId(projectId);
            controller.setActivityId(activityId);
        });
    }

    @Override
    public void registerTime(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.REGISTER_TIME, projectId, activityId);
    }

    @Override
    public void editActivity(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.EDIT_ACTIVITY, projectId, activityId);
    }

    @Override
    public void editRegisteredTime(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.EDIT_REGISTERED_TIME, projectId, activityId);
    }

    @Override
    public void assignToActivity(int projectId, int activityId) throws IOException {
        popUpWithActivity(CustomScene.ASSIGN_TO_ACTIVITY, projectId, activityId);
    }

    @Override
    public <T extends ProjectAware> void popUpWithProject(
            CustomScene scene,
            int projectId)
            throws IOException {
        popUp(scene, (T controller) -> {
            controller.setProjectId(projectId);
        });
    }

    @Override
    public void editProject(int projectId) throws IOException {
        popUpWithProject(CustomScene.EDIT_PROJECT, projectId);
    }

    @Override
    public void addActivity(int projectId) throws IOException {
        popUpWithProject(CustomScene.CREATE_ACTIVITY, projectId);
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
