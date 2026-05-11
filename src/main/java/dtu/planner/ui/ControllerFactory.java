package dtu.planner.ui;

import dtu.planner.ui.interfaces.ActivityItemFactory;
import dtu.planner.ui.interfaces.ActivityItemFactoryAware;
import dtu.planner.ui.interfaces.AlertServiceAware;
import dtu.planner.ui.interfaces.PopupServiceFactory;
import dtu.planner.ui.interfaces.PopupServiceFactoryAware;
import dtu.planner.ui.interfaces.ProjectManagementAware;
import dtu.planner.ui.interfaces.ReportAware;
import dtu.planner.ui.interfaces.UiStateAware;
import dtu.superPlanner.ProjectManagementApp;
import javafx.util.Callback;

/**
 * @author Arthur
 */

public class ControllerFactory implements Callback<Class<?>, Object> {

    ProjectManagementApp app;
    AlertService alertService;
    Navigator navigator;
    UiState uiState;

    ActivityItemFactory activityItemFactory;
    PopupServiceFactory popupServiceFactory;

    public ControllerFactory(ProjectManagementApp app,
            AlertService alertService,
            Navigator navigator,
            UiState uiState,
            ActivityItemFactory activityItemFactory,
            PopupServiceFactory popupServiceFactory) {

        this.app = app;
        this.alertService = alertService;
        this.navigator = navigator;
        this.uiState = uiState;
        this.activityItemFactory = activityItemFactory;
        this.popupServiceFactory = popupServiceFactory;
    }

    @Override
    public Object call(Class<?> controllerClass) {
        try {
            Object controller = controllerClass.getDeclaredConstructor().newInstance();
            inject(controller);
            return controller;

        } catch (

        Exception e) {
            throw new RuntimeException(
                    "Failed to create controller: " + controllerClass.getName(),
                    e);
        }
    }

    private <T> void inject(T controller) {
        if (controller instanceof ProjectManagementAware aware) {
            aware.setProjectManagementApp(app);
        }

        if (controller instanceof NavigatorAware aware) {
            aware.setNavigator(navigator);
        }

        if (controller instanceof AlertServiceAware aware) {
            aware.setAlertService(alertService);
        }

        if (controller instanceof UiStateAware aware) {
            aware.setUiState(uiState);
        }
        if (controller instanceof ReportAware aware) {
            aware.setReport(app.createReport(uiState.getProjectId()));
        }
        if (controller instanceof ActivityItemFactoryAware  aware) {
        aware.setActivityItemFactory(activityItemFactory);
    }

    if (controller instanceof PopupServiceFactoryAware  aware) {
        aware.setPopupServiceFactory(popupServiceFactory);
    }
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
