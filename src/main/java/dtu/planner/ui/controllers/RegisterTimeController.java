package dtu.planner.ui.controllers;

import java.io.IOException;
import java.time.LocalTime;

import dtu.planner.ui.CustomScene;
import dtu.planner.ui.TimeSpinnerValueFactory;
import dtu.planner.ui.UiState;
import dtu.planner.ui.interfaces.UiStateAware;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

/**
 * @author Arthur
 */

public class RegisterTimeController extends ProjectManagementAwareController implements UiStateAware {

    @FXML
    private Spinner<LocalTime> timeSpinner;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button registerTimeButton;

    private UiState uiState;

    @FXML
    private void initialize() {
        registerTimeButton.setDisable(true);
        
        datePicker.valueProperty().addListener(((observableValue, oldDate, newDate) -> {
            updateButtonState();
        }));
        datePicker.setValue(app.getTimeServer().getCurrentDate());

        timeSpinner.setValueFactory(new TimeSpinnerValueFactory());
    }

    @FXML
    public void onRegisterTime() throws IOException {
        if (datePicker.getValue() == null) {
            return;
        }

        double hours = (double) timeSpinner.getValue().getHour() + (double) timeSpinner.getValue().getMinute() / 60.0;
        try {
            app.registerTime(uiState.getProjectId(), uiState.getActivityId(), hours, datePicker.getValue());
        } catch (IllegalArgumentException ex) {
            alertService.show("Failed to register", ex.getMessage());
            return;
        }
        navigator.changeScene(CustomScene.PROJECT_LIST);
    }



    private void updateButtonState() {
        boolean valid = datePicker.getValue() != null;
        registerTimeButton.setDisable(!valid);
    }

    @Override
    public void setUiState(UiState uiState) {
        this.uiState = uiState;
    }

}