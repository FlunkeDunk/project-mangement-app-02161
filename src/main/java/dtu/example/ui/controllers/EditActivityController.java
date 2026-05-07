package dtu.example.ui.controllers;

import dtu.example.ui.interfaces.ActivityAware;
import dtu.superPlanner.Activity;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

    /**
    * @author Arthur
    */
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


    @Override
    public void setProjectId(int projectId) {
        this.projectId = projectId;
        if (activityId != 0) {
            loadActivity();
        }
    }

    @Override
    public void setActivityId(int activityId) {
        this.activityId = activityId;
        if (projectId != 0) {
            loadActivity();
        }
    }

}
