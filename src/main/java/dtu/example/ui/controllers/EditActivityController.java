package dtu.example.ui.controllers;

import java.io.IOException;
import java.time.LocalDate;

import dtu.example.ui.ActivityAware;
import dtu.superPlanner.Activity;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class EditActivityController extends ProjectManagementAwareController implements ActivityAware {

    @FXML
    TextField activityNameTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;
    @FXML
    Spinner<Integer> budgetSpinner;

    private int projectId;
    private int activityId;

    private Activity activity;

    @FXML
    public void loadActivity() {
        activity = app.getProject(projectId).getActivityById(activityId);

        activityNameTextField.setText(activity.getName());

        startDatePicker.setValue(activity.getTimeFrame().getStartDate().toLocalDate());
        endDatePicker.setValue(activity.getTimeFrame().getEndDate().toLocalDate());

        budgetSpinner.getValueFactory().setValue((int) activity.getBudgetedTime());
    }

    @FXML
    private void onSave() throws IOException {
        if (activity == null) {
            navigator.changeScene("project_list");
        }

        activity.setName(activityNameTextField.getText());

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate != null) {
            try {
                activity.getTimeFrame().setStartDate(new WeekBasedCalendar(startDate));
            } catch (IllegalArgumentException e) {
                showAlert("Invalid date", e.getMessage());
            }
        }
        if (endDate != null) {
            try {
                activity.getTimeFrame().setEndDate(new WeekBasedCalendar(endDate));
            } catch (IllegalArgumentException e) {
                showAlert("Invalid date", e.getMessage());
            }
        }

        if (budgetSpinner.valueProperty().getValue() > 0) {
            activity.setBudgetedTime(budgetSpinner.valueProperty().getValue());
        }
        navigator.changeScene("project_list");
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
        if (activityId != 0) {
            loadActivity();
        }
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
        if (projectId != 0) {
            loadActivity();
        }
    }

}
