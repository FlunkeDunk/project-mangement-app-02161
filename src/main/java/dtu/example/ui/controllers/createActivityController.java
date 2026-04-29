package dtu.example.ui.controllers;

import java.io.IOException;
import java.time.LocalDate;

import dtu.superPlanner.Activity;
import dtu.superPlanner.TimeFrame;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class createActivityController extends ProjectManagementAwareController {

    @FXML
    TextField activityNameTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;

    private int projectId;


    @FXML
    private void createActivity() throws IOException {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue().get;
        TimeFrame timeFrame = new TimeFrame();
        Activity activity = app.createActivity(projectId, activityNameTextField.getText(), timeFrame);
        System.out.println("The activity \"" + activity.getName() + "\" (id: " + activity.getId()
                + ") has the timeframe: " + activity.getTimeFrame());
        
        navigator.changeScene("project_list");
    }
}
