package dtu.planner.ui.components;

import java.io.IOException;
import java.util.Set;

import dtu.planner.ui.App;
import dtu.superPlanner.Activity;
import dtu.superPlanner.TimeFrame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ActivityItem extends TitledPane {

    @FXML
    private VBox employeListVBox;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label IdLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private GridPane graphicGridPane;

    @FXML
    private Label timeSpentLabel;

    @FXML
    private Label budgetetTimeLabel;

    private Runnable onRegisterTimeRequested;
    private Runnable onEditActivityRequested;
    private Runnable onAssignToAcitvityRequested;
    private Runnable onEditRegisteredTimeRequested;

    public ActivityItem(Activity activity, int id) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("activity_item.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load ActivityItem FXML", e);
        }

        setActivity(activity, id);
        graphicGridPane.prefWidthProperty().bind(this.widthProperty().subtract(42));
    }

    @FXML
    public void onRegisterTime() {
        if (onRegisterTimeRequested != null) {
            onRegisterTimeRequested.run();
        }
    }

    @FXML
    public void onEditActivity() {
        if (onEditActivityRequested != null) {
            onEditActivityRequested.run();
        }
    }

    @FXML
    public void onAssignToActivity() {
        if (onAssignToAcitvityRequested != null) {
            onAssignToAcitvityRequested.run();
        }
    }

    @FXML
    public void onEditRegisteredTime() {
        if (onEditRegisteredTimeRequested != null) {
            onEditRegisteredTimeRequested.run();
        }
    }

    public void setOnRegisterTimeRequested(Runnable handler) {
        this.onRegisterTimeRequested = handler;
    }

    public void setOnEditActivityRequested(Runnable handler) {
        this.onEditActivityRequested = handler;
    }

    public void setOnAssignToActivityRequested(Runnable handler) {
        this.onAssignToAcitvityRequested = handler;
    }
    public void setOnEditRegisteredTimeRequested(Runnable handler) {
        this.onEditRegisteredTimeRequested = handler;
    }

    public void setActivity(Activity activity, int id) {
        TimeFrame timeFrame = activity.getTimeFrame();
        setStartDate(timeFrame.getStartDate().toString());
        setEndDate(timeFrame.getEndDate().toString());
        setTextId("" + id);
        setName(activity.getName());
        setEmployees(activity.getEmployees());
        setBudgetetTime(activity.getBudgetedTime());
        setTimeSpent(activity.getTotalTimeSpent());
    }

    public void setStartDate(String startDate) {
        startDateLabel.setText(startDate);
    }

    public void setEndDate(String endDate) {
        endDateLabel.setText(endDate);
    }

    public void setTextId(String id) {
        IdLabel.setText(id);
    }

    public void setName(String title) {
        nameLabel.setText(title);
    }

    // --- Employee list handling ---
    public void setEmployees(Set<String> employees) {
        clearEmployees();
        if (employees == null || employees.isEmpty()) {
            return;
        }
        for (String name : employees) {
            Label label = new Label(name);
            employeListVBox.getChildren().add(label);
        }
    }

    private void clearEmployees() {
        employeListVBox.getChildren().clear();
    }

    private void setTimeSpent(double timeSpent) {
        timeSpentLabel.setText(String.format("%.1f h", timeSpent));
    }

    private void setBudgetetTime(double budgetetTime) {
        budgetetTimeLabel.setText(String.format("%.1f h", budgetetTime));
    }
}