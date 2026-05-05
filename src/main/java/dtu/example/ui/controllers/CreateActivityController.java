package dtu.example.ui.controllers;

import java.io.IOException;
import java.time.LocalDate;

import dtu.superPlanner.Activity;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class CreateActivityController extends ProjectManagementAwareController {

    @FXML
    TextField activityNameTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;
    @FXML
    Spinner<Integer> budgetSpinner;

    private int projectId;


    @FXML
    private void onCreateActivity() throws IOException, IllegalAccessException {
        LocalDate startPickerDate = startDatePicker.getValue();
        LocalDate endPickerDate = endDatePicker.getValue();
        if (startPickerDate == null || endPickerDate == null) {
            showAlert("Invalid date", "Must choose both dates");
        }
        WeekBasedCalendar startDate = new WeekBasedCalendar(startPickerDate);
        WeekBasedCalendar endDate = new WeekBasedCalendar(endPickerDate);

        try {
            TimeFrame timeFrame = new TimeFrame(startDate, endDate);
            Activity activity = app.createActivity(projectId, activityNameTextField.getText(), timeFrame);
            if (budgetSpinner.valueProperty().getValue() > 0) {
                activity.setBudgetedTime(budgetSpinner.valueProperty().getValue());
            }
            navigator.changeScene("project_list");
        } catch (IllegalArgumentException e ) {
            showAlert("Invalid date", e.getMessage());
        } catch (IllegalAccessException e) {
            showAlert("Invalid acces", e.getMessage());
        }
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


}
