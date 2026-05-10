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
            PopupService popupService,
            UiActionExecutor uiActionExecutor,
            UiState uiState) {

        List<ActivityItem> items = new ArrayList<>();

        for (var entry : project.getActivityMap().entrySet()) {
            int activityId = entry.getKey();
            Activity activity = entry.getValue();

            ActivityItem activityItem = new ActivityItem(activity, activityId);

            activityItem.setOnRegisterTimeRequested(
                    () -> {
                        uiState.setActivityId(activityId);
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.REGISTER_TIME,
                                ERROR_TITLE);
                    });

            activityItem.setOnEditActivityRequested(
                    () -> {
                        uiState.setActivityId(activityId);
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.EDIT_ACTIVITY,
                                ERROR_TITLE);
                    });

            activityItem.setOnAssignToActivityRequested(
                    () -> {
                        uiState.setActivityId(activityId);
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.ASSIGN_TO_ACTIVITY,
                                ERROR_TITLE);
                    });

            activityItem.setOnEditRegisteredTimeRequested(
                    () -> {
                        uiState.setActivityId(activityId);
                        uiActionExecutor.execute(
                                popupService::popUp, CustomScene.EDIT_REGISTERED_TIME,
                                ERROR_TITLE);
                    });

            items.add(activityItem);
        }

        return items;
    }
}
