package dtu.planner.ui.controllers;

import java.io.IOException;
import java.time.LocalDate;

import dtu.planner.ui.CustomScene;
import dtu.planner.ui.UiState;
import dtu.planner.ui.interfaces.UiStateAware;
import dtu.superPlanner.Activity;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * @author Arthur
 */
public class EditActivityController extends ProjectManagementAwareController implements UiStateAware {

    @FXML
    TextField activityNameTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;
    @FXML
    Spinner<Integer> budgetSpinner;

    private Activity activity;
    private UiState uiState;

    @FXML
    public void loadActivity() {
        activity = app.getActivity(uiState.getProjectId(), uiState.getActivityId());
        activityNameTextField.setText(activity.getName());
        startDatePicker.setValue(activity.getTimeFrame().getStartDate().toLocalDate());
        endDatePicker.setValue(activity.getTimeFrame().getEndDate().toLocalDate());

        budgetSpinner.getValueFactory().setValue((int) activity.getBudgetedTime());
    }

    @FXML
    private void onSave() throws IOException {
        if (activity == null) {
            navigator.changeScene(CustomScene.PROJECT_LIST);
            return;
        }

        activity.setName(activityNameTextField.getText());

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate != null) {
            try {
                activity.getTimeFrame().setStartDate(new WeekBasedCalendar(startDate));
            } catch (IllegalArgumentException e) {
                alertService.show("Invalid date", e.getMessage());
            }
        }
        if (endDate != null) {
            try {
                activity.getTimeFrame().setEndDate(new WeekBasedCalendar(endDate));
            } catch (IllegalArgumentException e) {
                alertService.show("Invalid date", e.getMessage());
            }
        }

        if (budgetSpinner.valueProperty().getValue() > 0) {
            activity.setBudgetedTime(budgetSpinner.valueProperty().getValue());
        }

        navigator.changeScene(CustomScene.PROJECT_LIST);
    }

    @Override
    public void setUiState(UiState uiState) {
        this.uiState = uiState;
    }

}
