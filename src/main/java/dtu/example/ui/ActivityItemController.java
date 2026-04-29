package dtu.example.ui;

import java.util.Set;

import dtu.superPlanner.Activity;
import dtu.superPlanner.TimeFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ActivityItemController {

    @FXML
    private VBox employeListVBox;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label titleLabel; // optional if you later give "Activity name" an fx:id

    @FXML
    public void initialize() {
        // Runs automatically after FXML is loaded
        // You can put setup logic here if needed
    }

    // --- Setters for activity data ---

    public void setActivity(Activity activity) {
        TimeFrame timeFrame = activity.getTimeFrame();
        setStartDate(timeFrame.getStartDate().toString());
        setEndDate(timeFrame.getEndDate().toString());
        setId(""+ 1);
        setName(activity.getName());
        setEmployees(activity.getEmployees());
    }

    public void setStartDate(String startDate) {
        startDateLabel.setText(startDate);
    }

    public void setEndDate(String endDate) {
        endDateLabel.setText(endDate);
    }

    public void setId(String id) {
        IdLabel.setText(id);
    }

    public void setName(String title) {
        if (titleLabel != null) {
            titleLabel.setText(title);
        }
    }

    // --- Employee list handling ---
    public void setEmployees(Set<String> employees) {
        employeListVBox.getChildren().clear();

        for (String name : employees) {
            Label label = new Label(name);
            employeListVBox.getChildren().add(label);
        }
    }

    public void addEmployee(String name) {
        Label label = new Label(name);
        employeListVBox.getChildren().add(label);
    }

    public void clearEmployees() {
        employeListVBox.getChildren().clear();
    }
}