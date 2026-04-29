package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.Map;

import dtu.example.ui.components.ActivityItem;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ProjectListController extends ProjectManagementAwareController {

    private int selectedProjectId = -1;

    // --- Project Details Labels ---
    @FXML
    private Label selectedProjectNameLabel;
    @FXML
    private Label selectedProjectIdLabel;
    @FXML
    private Label selectedProjectStartDateLabel;
    @FXML
    private Label selectedProjectLeaderLabel;
    @FXML
    private VBox projectListVBox;
    @FXML
    private Accordion activityListAccordion;

    // --- Buttons ---
    @FXML
    private Button addActivityButton;
    @FXML
    private Button editProjectButton;
    @FXML
    private Button editActivityButton;
    @FXML
    private Button addProjectButton;

    @FXML
    private void initialize() {
        setSelectedProjectButtonsDisabled(true);
        clearProjectDetails();
        clearProjectList();
        clearActivityList();
        loadProjects();

    }

    private void setSelectedProjectButtonsDisabled(Boolean enabled) {
        addActivityButton.setDisable(enabled);
        editProjectButton.setDisable(enabled);
        editActivityButton.setDisable(enabled);
    }

    // --- Public method to update UI when a project is selected ---
    public void setProjectDetails(String name, String id, String startDate, String leader) {
        String displayName = name != null ? name : "none";
        String displayLeader = leader != null ? leader : "none";
        selectedProjectNameLabel.setText(displayName);
        selectedProjectIdLabel.setText(id);
        selectedProjectStartDateLabel.setText(startDate);
        selectedProjectLeaderLabel.setText(displayLeader);
    }

    private void loadProjects() {
        app.getAllProjects().forEach((project) -> {
            Label label = new Label(project.getName());
            label.setOnMouseClicked((MouseEvent event) -> {
                try {
                    onProjectClicked(project);
                } catch (IOException ex) {
                    System.getLogger(ProjectListController.class.getName()).log(System.Logger.Level.ERROR,
                            (String) null, ex);
                }
            });
            projectListVBox.getChildren().add(label);
        });
    }

    private void onProjectClicked(Project project) throws IOException {
        if (selectedProjectId == project.getId()) {
            return;
        }
        selectedProjectId = project.getId();
        String projectId = "" + project.getId();
        String projectStartDate = project.getStartDate().toString();
        setProjectDetails(project.getName(), projectId, projectStartDate, project.getProjectLeader());
        loadActivities(project.getActivityMap());
        setSelectedProjectButtonsDisabled(false);
    }

    private void loadActivities(Map<Integer, Activity> activities) throws IOException {
        clearActivityList();
        if (activities == null) {
            return;
        }

        for (Map.Entry<Integer, Activity> entry : activities.entrySet()) {
            ActivityItem activityItem = new ActivityItem(entry.getValue(), entry.getKey());
            activityListAccordion.getPanes().add(activityItem);
        }
    }

    // --- Clear UI ---
    private void clearProjectDetails() {
        selectedProjectNameLabel.setText("—");
        selectedProjectIdLabel.setText("—");
        selectedProjectStartDateLabel.setText("—");
        selectedProjectLeaderLabel.setText("—");
    }

    private void clearProjectList() {
        projectListVBox.getChildren().clear();
    }

    private void clearActivityList() {
        activityListAccordion.getPanes().clear();
    }

    // --- Button Handlers ---
    @FXML
    private void handleAddActivity() throws IOException{
        navigator.changeScene("create_activity", controller -> {
            ((CreateActivityController) controller).setProjectId(selectedProjectId);
        });
    }

    @FXML
    private void handleEditProject() {
        System.out.println("Edit Project clicked");
        // TODO: open edit project dialog
    }

    @FXML
    private void handleEditActivity() {
        System.out.println("Edit Activity clicked");
        // TODO: open edit activity dialog
    }

    @FXML
    private void handleAddProject() throws IOException {
        navigator.changeScene("create_project");
    }
}