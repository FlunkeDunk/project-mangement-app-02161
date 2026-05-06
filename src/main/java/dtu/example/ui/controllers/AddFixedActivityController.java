package dtu.example.ui.controllers;

import java.time.LocalDate;

import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class AddFixedActivityController extends ProjectManagementAwareController{

    @FXML
    private ChoiceBox<String> activityTypeChoiceBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    public void initialize() {
        // Populate choice box (example values)
        activityTypeChoiceBox.getItems().addAll(
                "Work",
                "Exercise",
                "Study",
                "Leisure"
        );

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void addFixedActivity() {
        String type = activityTypeChoiceBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        // Basic validation
        if (type == null || startDate == null || endDate == null) {
            showAlert("Invalid input", "Please fill in all fields.");
            return;
        }
        

        TimeFrame timeFrame;
        try {
            timeFrame = new TimeFrame(new WeekBasedCalendar(startDate), new WeekBasedCalendar(startDate));
        } catch (IllegalArgumentException ex) {
            showAlert("Invalid dates", ex.getMessage());
            return;
        }

        app.createFixedActivity(null, type, timeFrame);
    }
}