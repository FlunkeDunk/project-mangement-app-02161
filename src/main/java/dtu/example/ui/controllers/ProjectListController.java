package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.Map;

import dtu.example.ui.ActivityAware;
import dtu.example.ui.ProjectAware;
import dtu.example.ui.ReportAware;
import dtu.example.ui.components.ActivityItem;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import dtu.superPlanner.Report;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ProjectListController extends ProjectManagementAwareController {

    private int selectedProjectId = -1;
    private PopUpManager popUpManager;

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
    @FXML
    private AnchorPane popUpPane;
    @FXML
    private Pane popUpContainer;
    @FXML
    private VBox rootVBox;
    // --- Buttons ---
    @FXML
    private Button addActivityButton;
    @FXML
    private Button editProjectButton;
    @FXML
    private Button addProjectButton;
    @FXML
    private Button viewReportButton;
    @FXML
    private Button closePopUpButton;

    @FXML
    private void initialize() {
        setSelectedProjectButtonsDisabled(true);
        clearProjectDetails();
        clearActivityList();
        loadProjects();
        popUpManager = new PopUpManager(popUpPane, popUpContainer, rootVBox, navigator);
        popUpManager.popDown();
        projectList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                onProjectSelected();
            } catch (IOException ex) {
                System.getLogger(ProjectListController.class.getName()).log(System.Logger.Level.ERROR, (String) null,
                        ex);
            }
        });

    }

    private void setSelectedProjectButtonsDisabled(Boolean disabled) {
        addActivityButton.setDisable(disabled);
        editProjectButton.setDisable(disabled);
        viewReportButton.setDisable(disabled);
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
                    () -> popUpSceneWithActivity("register_time", RegisterTimeController.class, activityId));
            activityItem.setOnEditActivityRequested(
                    () -> popUpSceneWithActivity("edit_activity", EditActivityController.class, activityId));
            activityItem.setOnAssignToActivityRequested(
                    () -> popUpSceneWithActivity("assign_to_activity", AssignToActivityController.class, activityId));
            activityItem.setOnEditRegisteredTimeRequested(
                    () -> popUpSceneWithActivity("edit_registered_time", EditRegisteredTimeController.class,
                            activityId));
        }
    }

    private <T extends ActivityAware> void popUpSceneWithActivity(
            String sceneName,
            Class<T> controllerClass,
            int activityId) {
        try {
            popUpManager.popUp(sceneName, controller -> {
                T typedController = controllerClass.cast(controller);
                typedController.setProjectId(selectedProjectId);
                typedController.setActivityId(activityId);
            });
        } catch (IOException e) {
            showAlert("Failed loading", "Failed loading " + sceneName + ": " + e.getMessage());
        }
    }

    private <T extends ReportAware> void changeSceneWithReport(
            String sceneName,
            Class<T> controllerClass,
            Report report) {
        try {
            navigator.changeScene(sceneName, controller -> {
                T typedController = controllerClass.cast(controller);
                typedController.setReport(report);
            });
        } catch (IOException e) {
            System.err.println("Failed loading " + sceneName + ": " + e.getMessage());
        }
    }

    private <T extends ProjectAware> void changeSceneWithProject(
            String sceneName,
            Class<T> controllerClass,
            int projectId) {
        try {
            popUpManager.popUp(sceneName, controller -> {
                T typedController = controllerClass.cast(controller);
                typedController.setProjectId(projectId);
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
        popUpManager.popUp("create_activity", controller -> {
            ((CreateActivityController) controller).setProjectId(selectedProjectId);
        });
    }

    @FXML
    private void handleEditProject() {
        changeSceneWithProject("edit_project", EditProjectController.class, selectedProjectId);
    }

    @FXML
    private void handleAddProject() throws IOException {
        popUpManager.popUp("create_project");
    }

    @FXML
    private void handleViewReport() {
        changeSceneWithReport("view_report", ViewReportController.class,
                app.getProject(selectedProjectId).createReport());
    }

    @FXML
    private void onLogout() throws IOException {
        navigator.changeScene("login");
    }

    @FXML
    private void onAddFixedActivity() throws IOException {
        popUpManager.popUp("add_fixed_activity");
    }

    @FXML
    private void onUserRegisterTime() {
        System.out.println("User clicked user register time");
    }

    @FXML
    private void checkClickedOutsidePopUp(MouseEvent event) {
        Node clickedNode = (Node) event.getTarget();

        // Check if the clicked node is NOT inside popUpPane
        if (!isDescendant(clickedNode, popUpPane)) {
            popUpManager.popDown();
        }
    }

    @FXML
    private void closePopUpClicked() {
        popUpManager.popDown();
    }

    private boolean isDescendant(Node child, Node parent) {
        while (child != null) {
            if (child == parent) {
                return true;
            }
            child = child.getParent();
        }
        return false;
    }

}