package dtu.example.ui.controllers;

import java.io.IOException;
import java.time.LocalTime;

import dtu.example.ui.ActivityAware;
import dtu.example.ui.TimeSpinnerValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

public class RegisterTimeController extends ProjectManagementAwareController implements ActivityAware {

    @FXML
    private Spinner<LocalTime> timeSpinner;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button registerTimeButton;

    int projectId;
    int activityId;

    @FXML
    private void initialize() {
        registerTimeButton.setDisable(true);

        datePicker.valueProperty().addListener(((observableValue, oldDate, newDate) -> {
            updateButtonState();
        }));

        timeSpinner.setValueFactory(new TimeSpinnerValueFactory());
    }

    @FXML
    public void onRegisterTime() throws IOException {
        if (datePicker.getValue() == null) {
            return;
        }

        double hours = (double) timeSpinner.getValue().getHour() + (double) timeSpinner.getValue().getMinute() / 60.0;
        app.registerTime(projectId, activityId, hours, datePicker.getValue());
        navigator.changeScene("project_list");
    }

    @Override
    public void setActivityId(int id) {
        activityId = id;
    }

    @Override
    public void setProjectId(int id) {
        projectId = id;
    }

    private void updateButtonState() {
        boolean valid = datePicker.getValue() != null;
        registerTimeButton.setDisable(!valid);
    }

}