package dtu.planner.ui;

import java.util.ArrayList;
import java.util.List;

import dtu.planner.ui.components.ActivityItem;
import dtu.planner.ui.interfaces.PopupService;
import dtu.planner.ui.interfaces.UiActionExecutor;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;

public class ActivityItemFactory {

    private final String ERROR_TITLE = "Operation failed";

    public List<ActivityItem> createActivityItems(
            Project project,
            PopupService popupService,
            UiActionExecutor uiActionExecutor) {

        List<ActivityItem> items = new ArrayList<>();
        int projectId = project.getId();

        for (var entry : project.getActivityMap().entrySet()) {
            int activityId = entry.getKey();
            Activity activity = entry.getValue();

            ActivityItem activityItem = new ActivityItem(activity, activityId);

            activityItem.setOnRegisterTimeRequested(
                () -> {
                    uiActionExecutor.execute(
                        popupService::registerTime, projectId, activityId, ERROR_TITLE);});

            activityItem.setOnEditActivityRequested(
                () -> {
                    uiActionExecutor.execute(
                        popupService::editActivity, projectId, activityId, ERROR_TITLE);});

            activityItem.setOnAssignToActivityRequested(
                () -> {
                    uiActionExecutor.execute(
                        popupService::assignToActivity, projectId, activityId, ERROR_TITLE);});

            activityItem.setOnEditRegisteredTimeRequested(
                () -> {
                    uiActionExecutor.execute(
                        popupService::editRegisteredTime, projectId, activityId, ERROR_TITLE);});

            items.add(activityItem);
        }

        return items;
    }
}
