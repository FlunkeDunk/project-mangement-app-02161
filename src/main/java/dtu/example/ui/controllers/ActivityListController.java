package dtu.example.ui.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ActivityListController {

    @FXML
    private VBox employeListVBox;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private void initialize() {
        
    }

    // Example method to dynamically add employees
    public void addEmployee(String name) {
        Label label = new Label(name);
        employeListVBox.getChildren().add(label);
    }

    // Example method to set activity info
    public void setActivityData(String startDate, String endDate, int id) {
        startDateLabel.setText(startDate);
        endDateLabel.setText(endDate);
        IdLabel.setText(String.valueOf(id));
    }
}