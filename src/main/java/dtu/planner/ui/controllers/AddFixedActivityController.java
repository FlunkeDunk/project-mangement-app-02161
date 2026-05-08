package dtu.planner.ui.controllers;

import java.io.IOException;
import java.time.LocalDate;

import dtu.superPlanner.FixedActivityType;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
    /**
    * @author Arthur
    */
public class AddFixedActivityController extends ProjectManagementAwareController{

    @FXML
    private ComboBox<FixedActivityType> activityTypeComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    public void initialize() {
        // Populate choice box (example values)
        activityTypeComboBox.getItems().addAll(
                FixedActivityType.values()
        );

        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void addFixedActivity() throws IOException {
        FixedActivityType type = activityTypeComboBox.getValue();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        // Basic validation
        if (type == null || startDate == null || endDate == null) {
            alertService.show("Invalid input", "Please fill in all fields.");
            return;
        }
        

        TimeFrame timeFrame;
        try {
            timeFrame = new TimeFrame(new WeekBasedCalendar(startDate), new WeekBasedCalendar(startDate));
        } catch (IllegalArgumentException ex) {
            alertService.show("Invalid dates", ex.getMessage());
            return;
        }

        app.createFixedActivity(type, timeFrame);
        navigator.toProjectList();;
    }
}