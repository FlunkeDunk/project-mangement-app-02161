package dtu.planner.ui.controllers;

import java.time.LocalDate;

import dtu.planner.ui.CustomScene;
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
    private void addFixedActivity() {
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
            timeFrame = new TimeFrame(new WeekBasedCalendar(startDate), new WeekBasedCalendar(endDate));
        } catch (IllegalArgumentException ex) {
            alertService.show("Invalid dates", ex.getMessage());
            return;
        }
        try {
            app.createFixedActivity(type, timeFrame);
            
        } catch (IllegalArgumentException ex) {
            alertService.show("Illegal argument", ex.getMessage());
            return;

        }

        executeUiAction(
                navigator::changeScene,
                CustomScene.PROJECT_LIST,
                "Scene change failed");
    }
}