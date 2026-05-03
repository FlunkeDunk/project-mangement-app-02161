package dtu.example.ui.controllers;

import java.io.IOException;

import dtu.example.ui.ActivityAware;
import dtu.superPlanner.Activity;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class EditActivityController extends ProjectManagementAwareController implements ActivityAware{

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
    private void onSave() throws IOException, IllegalAccessException {
        if (activity == null) {
            return;
        }

        activity.setName(activityNameTextField.getText());

        WeekBasedCalendar startDate = new WeekBasedCalendar(startDatePicker.getValue());
        WeekBasedCalendar endDate = new WeekBasedCalendar(endDatePicker.getValue());

        if (startDate != null) {
            activity.getTimeFrame().setStartDate(startDate);
        }
        if (endDate != null) {
            activity.getTimeFrame().setEndDate(endDate);
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
