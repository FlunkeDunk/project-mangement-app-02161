package dtu.planner.ui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class UiState {
    private final IntegerProperty selectedProjectId;
    private final IntegerProperty selectedActivityId;


    public UiState() {
        selectedProjectId = new SimpleIntegerProperty();
        selectedActivityId = new SimpleIntegerProperty();
    }

    public IntegerProperty selectedProjectIdProperty() {
        return selectedProjectId;
    }

    public int getProjectId() {
        return selectedProjectId.get();
    }

    public void setProjectId(int value) {
        selectedProjectId.set(value);
    }

    public IntegerProperty selectedActivityIdProperty() {
        return selectedActivityId;
    }

    public int getActivityId() {
        return selectedActivityId.get();
    }

    public void setActivityId(int value) {
        selectedActivityId.set(value);
    }
}
