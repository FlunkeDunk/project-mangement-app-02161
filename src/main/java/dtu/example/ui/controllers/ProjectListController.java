package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.Map;

import dtu.example.ui.ActivityAware;
import dtu.example.ui.components.ActivityItem;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
    private Accordion activityListAccordion;
    @FXML
    private ListView<Project> projectList;

    // --- Buttons ---
    @FXML
    private Button addActivityButton;
    @FXML
    private Button editProjectButton;
    @FXML
    private Button addProjectButton;

    @FXML
    private void initialize() {
        setSelectedProjectButtonsDisabled(true);
        clearProjectDetails();
        clearActivityList();
        loadProjects();
        projectList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                onProjectSelected();
            } catch (IOException ex) {
                System.getLogger(ProjectListController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });


    }

    private void setSelectedProjectButtonsDisabled(Boolean enabled) {
        addActivityButton.setDisable(enabled);
        editProjectButton.setDisable(enabled);
    }

    
    private void loadProjects() {
        projectList.getItems().addAll(app.getAllProjects());
    }
    
    @FXML
    private void onProjectSelected() throws IOException {
        Project project = projectList.getSelectionModel().getSelectedItem();
        
        if (project == null || selectedProjectId == project.getId()) {
            return;
        }
        
        selectedProjectId = project.getId();
        String projectId = "" + project.getId();
        String projectStartDate = project.getStartDate().toString();
        setProjectDetails(project.getName(), projectId, projectStartDate, project.getProjectLeader());
        loadActivities(project.getActivityMap());
        setSelectedProjectButtonsDisabled(false);
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

    private void loadActivities(Map<Integer, Activity> activities) throws IOException {
        clearActivityList();
        if (activities == null) {
            return;
        }

        for (Map.Entry<Integer, Activity> entry : activities.entrySet()) {
            int activityId = entry.getKey();
            Activity activity = entry.getValue();
            ActivityItem activityItem = new ActivityItem(activity, activityId);
            activityListAccordion.getPanes().add(activityItem);
            activityItem.setOnRegisterTimeRequested(
                    () -> changeSceneWithActivity("register_time", RegisterTimeController.class, activityId));

            activityItem.setOnEditActivityRequested(
                    () -> changeSceneWithActivity("edit_activity", EditAcitvityController.class, activityId));

            activityItem.setOnAssignToActivityRequested(
                    () -> changeSceneWithActivity("assign_to_activity", AssignToActivityController.class, activityId));
        }
    }

    private <T extends ActivityAware> void changeSceneWithActivity(
            String sceneName,
            Class<T> controllerClass,
            int activityId) {
        try {
            navigator.changeScene(sceneName, controller -> {
                T typedController = controllerClass.cast(controller);
                typedController.setProjectId(selectedProjectId);
                typedController.setActivityId(activityId);
            });
        } catch (IOException e) {
            System.err.println("Failed loading " + sceneName + ": " + e.getMessage());
        }
    }

    // --- Clear UI ---
    private void clearProjectDetails() {
        selectedProjectNameLabel.setText("—");
        selectedProjectIdLabel.setText("—");
        selectedProjectStartDateLabel.setText("—");
        selectedProjectLeaderLabel.setText("—");
    }

    private void clearActivityList() {
        activityListAccordion.getPanes().clear();
    }

    // --- Button Handlers ---
    @FXML
    private void handleAddActivity() throws IOException {
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