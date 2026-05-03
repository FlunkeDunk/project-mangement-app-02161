package dtu.example.ui.controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dtu.example.ui.ActivityAware;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class RegisterTimeController extends ProjectManagementAwareController implements ActivityAware{

    @FXML
    private TextField initialsTextField;

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
        initialsTextField.textProperty().addListener(((observableValue, oldText,
                newText) -> {
            updateButtonState();
        }));

        List<LocalTime> times = new ArrayList<>();
        times.add(LocalTime.of(0, 30));
        for (int h = 1; h < 24; h++) {
            times.add(LocalTime.of(h, 0));
            times.add(LocalTime.of(h, 30));
        }

        timeSpinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(
                FXCollections.observableArrayList(times)));
    }

    @FXML
    public void onRegisterTime() {
        if (datePicker.getValue() == null || initialsTextField.getText() == null
                || initialsTextField.getText().isBlank()) {
            return;
        }

        double hours = (double) timeSpinner.getValue().getHour() + (double) timeSpinner.getValue().getMinute() / 60.0;
        app.registerTime(projectId, activityId, datePicker.getValue(), hours);
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
        boolean valid = datePicker.getValue() != null &&
                initialsTextField.getText() != null &&
                !initialsTextField.getText().isBlank();

        registerTimeButton.setDisable(!valid);
    }

}