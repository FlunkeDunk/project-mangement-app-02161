package dtu.planner.ui.controllers;

import java.io.IOException;
import java.time.LocalDate;

import dtu.planner.ui.CustomScene;
import dtu.planner.ui.UiState;
import dtu.planner.ui.interfaces.UiStateAware;
import dtu.superPlanner.Activity;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

    /**
    * @author Arthur
    */
public class CreateActivityController extends ProjectManagementAwareController implements UiStateAware{

    @FXML
    TextField activityNameTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;
    @FXML
    Spinner<Integer> budgetSpinner;

    private UiState uiState;

    @FXML
    private void initialize() {
        startDatePicker.setValue(app.getTimeServer().getCurrentDate());
        endDatePicker.setValue(app.getTimeServer().getCurrentDate());
    }


    @FXML
    private void onCreateActivity() throws IOException, IllegalAccessException {
        LocalDate startPickerDate = startDatePicker.getValue();
        LocalDate endPickerDate = endDatePicker.getValue();
        if (startPickerDate == null || endPickerDate == null) {
            alertService.show("Invalid date", "Must choose both dates");
        }
        WeekBasedCalendar startDate = new WeekBasedCalendar(startPickerDate);
        WeekBasedCalendar endDate = new WeekBasedCalendar(endPickerDate);

        try {
            TimeFrame timeFrame = new TimeFrame(startDate, endDate);
            Activity activity = app.createActivity(uiState.getProjectId(), activityNameTextField.getText(), timeFrame);
            if (budgetSpinner.valueProperty().getValue() > 0) {
                activity.setBudgetedTime(budgetSpinner.valueProperty().getValue());
            }
            navigator.changeScene(CustomScene.PROJECT_LIST);
        } catch (IllegalArgumentException e ) {
            alertService.show("Invalid date", e.getMessage());
        } catch (IllegalAccessException e) {
            alertService.show("Invalid access", e.getMessage());
        }
    }

    @Override
    public void setUiState(UiState uiState) {
        this.uiState = uiState;
    }


}
