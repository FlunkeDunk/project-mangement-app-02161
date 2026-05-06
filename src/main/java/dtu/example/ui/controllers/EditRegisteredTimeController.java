package dtu.example.ui.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

import dtu.example.ui.ActivityAware;
import dtu.example.ui.TimeSpinnerValueFactory;
import dtu.superPlanner.TimeLedger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;

public class EditRegisteredTimeController extends ProjectManagementAwareController implements ActivityAware {

    @FXML
    private GridPane dateGridPane;

    private int projectId;

    private int activityId;

    @FXML
    private void onBack() throws IOException {
        navigator.changeScene("project_list");
    }

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
        Spinner<LocalTime> timeSpinner = new Spinner<>();
        timeSpinner.setValueFactory(new TimeSpinnerValueFactory());
        timeSpinner.getValueFactory().setValue(time);
        Label dateLabel = new Label(date.toString());
        dateGridPane.addRow(dateGridPane.getRowCount(), dateLabel, timeSpinner);

        timeSpinner.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            double hours = newValue.getHour() + (double) newValue.getMinute() / 60.0;
            try {
                app.editTime(projectId, activityId, date, hours);
            } catch (IllegalArgumentException ex) {
                showAlert("Invalid time", ex.getMessage());
            }
        }));
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
        if (activityId != 0) {
            loadLedger();
        }
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
        if (projectId != 0) {
            loadLedger();
        }
    }

}