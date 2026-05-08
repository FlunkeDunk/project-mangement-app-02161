package dtu.planner.ui.controllers;

import java.io.IOException;

import dtu.planner.ui.components.TimeSpinner;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class RegisterTimeListController extends ProjectManagementAwareController {

    @FXML
    private GridPane activityGridPane;

    @FXML
    private HBox timeHBox;

    private DatePicker datePicker;
    private TimeSpinner timeSpinner;

    @FXML
    private void initialize() {
        activityGridPane.getChildren().clear();
        datePicker = new DatePicker(app.getTimeServer().getCurrentDate());
        datePicker.setPrefWidth(120);
        timeSpinner = new TimeSpinner();
        timeSpinner.setPrefWidth(120);

        timeHBox.getChildren().addAll(datePicker, timeSpinner);

        if (app.getAllProjects().isEmpty()) {
            return;
        }

        boolean addedProjectLabel;
        for (Project project : app.getAllProjects()) {
            addedProjectLabel = false;
            for (Activity activity : project.getActivitySet()) {
                if (activity.getEmployees().contains(app.getUserInitials())) {
                    if (!addedProjectLabel) {
                        setupProjectLabel(project);
                        addedProjectLabel = true;
                    }
                    setupRow(project, activity);
                }
            }
        }
    }

    @FXML
    private void onBack() throws IOException {
        navigator.toProjectList();
    }

    private void setupProjectLabel(Project project) {
        addSeparator();
        Label projectLabel = new Label(project.toString());
        projectLabel.getStyleClass().add("subheading");
        activityGridPane.addRow(activityGridPane.getRowCount(), projectLabel);
        GridPane.setColumnSpan(projectLabel, 2);
        addSeparator();
        projectLabel.setAlignment(Pos.CENTER);
    }

    private void addSeparator() {
        Separator separator = new Separator(Orientation.HORIZONTAL);

        separator.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(separator, Priority.ALWAYS);

        activityGridPane.addRow(activityGridPane.getRowCount(), separator);
        GridPane.setColumnSpan(separator, 2);
    }

    private void setupRow(Project project, Activity activity) {
        Label activityLabel = new Label(activity.toString());
        Button registerButton = new Button("Register");
        activityGridPane.addRow(activityGridPane.getRowCount(), activityLabel, registerButton);
        activityLabel.setAlignment(Pos.BASELINE_RIGHT);
        registerButton.setOnAction(event -> {
            try {
                app.registerTime(project.getId(), activity.getId(), timeSpinner.getHours(), datePicker.getValue());
            } catch (IllegalArgumentException ex) {
                alertService.show("Failed to register", ex.getMessage());
            }
        });
    }

}
