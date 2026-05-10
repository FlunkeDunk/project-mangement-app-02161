package dtu.planner.ui.interfaces;

import java.util.List;

import dtu.planner.ui.UiState;
import dtu.planner.ui.components.ActivityItem;
import dtu.superPlanner.Project;

public interface ActivityItemFactory {
        
    public List<ActivityItem> create(
            Project project,
            PopupService popupService,
            UiActionExecutor uiActionExecutor,
            UiState uiState);
}
