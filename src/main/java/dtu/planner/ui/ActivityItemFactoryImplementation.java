package dtu.planner.ui;

import java.util.ArrayList;
import java.util.List;

import dtu.planner.ui.components.ActivityItem;
import dtu.planner.ui.interfaces.ActivityItemFactory;
import dtu.planner.ui.interfaces.PopupService;
import dtu.planner.ui.interfaces.UiActionExecutor;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;

/**
 * @author Arthur
 */
public class ActivityItemFactoryImplementation implements ActivityItemFactory {

    public ActivityItemFactoryImplementation() {
    }

    private final String ERROR_TITLE = "Operation failed";

    public List<ActivityItem> create(
            Project project,
            boolean hasAcces,
            PopupService popupService,
            UiActionExecutor uiActionExecutor) {

        List<ActivityItem> items = new ArrayList<>();

        for (var entry : project.getActivityMap().entrySet()) {
            int activityId = entry.getKey();
            Activity activity = entry.getValue();

            ActivityItem activityItem = new ActivityItem(activity, activityId, hasAcces);
            activityItem.expandedProperty().set(false);
            items.add(activityItem);


            activityItem.setOnRegisterTimeRequested(
                    () -> {
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.REGISTER_TIME,
                                ERROR_TITLE);
                    });

            activityItem.setOnEditRegisteredTimeRequested(
                    () -> {
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.EDIT_REGISTERED_TIME,
                                ERROR_TITLE);
                    });

            activityItem.setOnEditActivityRequested(
                    () -> {
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.EDIT_ACTIVITY,
                                ERROR_TITLE);
                    });

            activityItem.setOnAssignToActivityRequested(
                    () -> {
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.ASSIGN_TO_ACTIVITY,
                                ERROR_TITLE);
                    });

        }

        return items;
    }
}
