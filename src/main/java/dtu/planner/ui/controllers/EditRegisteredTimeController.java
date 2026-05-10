package dtu.planner.ui.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

import dtu.planner.ui.UiState;
import dtu.planner.ui.components.TimeSpinner;
import dtu.planner.ui.interfaces.UiStateAware;
import dtu.superPlanner.Activity;
import dtu.superPlanner.TimeLedger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

    /**
    * @author Arthur
    */
public class EditRegisteredTimeController extends ProjectManagementAwareController implements UiStateAware {

    @FXML
    private GridPane dateGridPane;

    @FXML
    private Label activityLabel;

    private UiState uiState;

    @FXML
    private void initialize() {
        int projectId = uiState.getProjectId();
        int activityId = uiState.getActivityId();
        setActivityLabelName(projectId, activityId);
        loadLedger(projectId, activityId);

    }

    private void setActivityLabelName(int projectId, int activityId) {
        Activity activity = app.getActivity(projectId, activityId);
        if (activity != null) {
            activityLabel.setText(activity.getName());
        }
    }

    private void loadLedger(int projectId, int activityId) {
        TimeLedger timeLedger = app.getActivity(projectId, activityId)
                .getTimeLedger(app.getUserInitials());
        if (timeLedger == null) {
            return;
        }

        Set<LocalDate> sortedDates = new TreeSet<>(timeLedger.getAllDates());
        for (LocalDate date : sortedDates) {
            double timeValue = timeLedger.getTime(date);
            int hours = (int) timeValue;
            int minutes = (int) ((timeValue % 1) * 60);
            LocalTime time = LocalTime.of(hours, minutes);
            setupRow(date, time, projectId, activityId);
        }
    }

    private void setupRow(LocalDate date, LocalTime time, int projectId, int activityId) {
        TimeSpinner timeSpinner = new TimeSpinner(time);
        Label dateLabel = new Label(date.toString());
        dateGridPane.addRow(dateGridPane.getRowCount(), dateLabel, timeSpinner);

        timeSpinner.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            try {
                app.editTime(projectId, activityId, date, timeSpinner.getHours());
            } catch (IllegalArgumentException ex) {
                alertService.show("Invalid time", ex.getMessage());
            }
        }));
    }

    @Override
    public void setUiState(UiState uiState) {
        this.uiState = uiState;
    }

}