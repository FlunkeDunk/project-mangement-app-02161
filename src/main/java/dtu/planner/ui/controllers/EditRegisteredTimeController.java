package dtu.planner.ui.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

import dtu.planner.ui.components.TimeSpinner;
import dtu.planner.ui.interfaces.ActivityAware;
import dtu.superPlanner.TimeLedger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

    /**
    * @author Arthur
    */
public class EditRegisteredTimeController extends ProjectManagementAwareController implements ActivityAware {

    @FXML
    private GridPane dateGridPane;

    private int projectId;

    private int activityId;

    private void loadLedger() {
        TimeLedger timeLedger = app.getProject(projectId).getActivityById(activityId)
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
            setupRow(date, time);
        }
    }

    private void setupRow(LocalDate date, LocalTime time) {
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
    public void setProjectId(int projectId) {
        this.projectId = projectId;
        if (activityId != 0) {
            loadLedger();
        }
    }

    @Override
    public void setActivityId(int activityId) {
        this.activityId = activityId;
        if (projectId != 0) {
            loadLedger();
        }
    }

}