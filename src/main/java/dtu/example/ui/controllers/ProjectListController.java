package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.Map;

import dtu.example.ui.CustomScene;
import dtu.example.ui.PopupService;
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

/**
 * @author Arthur
 */

public class ProjectListController extends ProjectManagementAwareController {

    private PopupService popupService;

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
        popupService = new PopUpManager(popUpPane, popUpContainer, rootVBox, navigator);
        popupService.popDown();
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

        if (activities == null || activities.isEmpty()) {
            return;
        }

        for (Map.Entry<Integer, Activity> entry : activities.entrySet()) {
            int activityId = entry.getKey();
            Activity activity = entry.getValue();

            ActivityItem activityItem = new ActivityItem(activity, activityId);
            activityListAccordion.getPanes().add(activityItem);

            activityItem.setOnRegisterTimeRequested(
                    () -> {
                        try {
                            popupService.registerTime(getSelectedProjectId(), activityId);
                        } catch (IOException e) {
                            showAlert("Operation failed", e.getMessage());
                        }
                    });

            activityItem.setOnEditActivityRequested(
                    () -> {
                        try {
                            popupService.editActivity(getSelectedProjectId(), activityId);
                        } catch (IOException e) {
                            showAlert("Operation failed", e.getMessage());
                        }
                    });

            activityItem.setOnAssignToActivityRequested(
                    () -> {
                        try {
                            popupService.assignToActivity(getSelectedProjectId(), activityId);
                        } catch (IOException e) {
                            showAlert("Operation failed", e.getMessage());
                        }
                    });

            activityItem.setOnEditRegisteredTimeRequested(
                    () -> {
                        try {
                            popupService.editRegisteredTime(getSelectedProjectId(), activityId);
                        } catch (IOException e) {
                            showAlert("Operation failed", e.getMessage());
                        }
                    });
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
    private void handleAddActivity() {
        try {
            popupService.addActivity(getSelectedProjectId());
        } catch (IOException e) {
            showAlert("Operation failed", e.getMessage());
        }
    }

    @FXML
    private void handleEditProject() {
        try {
            popupService.editProject(getSelectedProjectId());
        } catch (IOException e) {
            showAlert("Operation failed", e.getMessage());
        }
    }

    @FXML
    private void handleAddProject() {
        try {
            popupService.popUp(CustomScene.CREATE_PROJECT);
        } catch (IOException e) {
            showAlert("Operation failed", e.getMessage());
        }
    }

    @FXML
    private void handleViewReport() {
        Report report = app.createReport(getSelectedProjectId());
        try {
            popupService.viewReport(report);
        } catch (IOException e) {
            showAlert("Failed loading", e.getMessage());
        }
    }

    @FXML
    private void onLogout() throws IOException {
        navigator.changeScene(CustomScene.LOGIN);
    }

    @FXML
    private void onAddFixedActivity() throws IOException {
        popupService.popUp(CustomScene.ADD_FIXED_ACTIVITY);
    }

    @FXML
    private void onUserRegisterTime() {
        try {
            navigator.changeScene(CustomScene.REGISTER_TIME_LIST);
        } catch (IOException ex) {
            showAlert("Failed loading", ex.getMessage());
            System.getLogger(AssignToActivityController.class.getName()).log(System.Logger.Level.ERROR, (String) null,
                    ex);
        }
    }

    @FXML
    private void checkClickedOutsidePopUp(MouseEvent event) {
        Node clickedNode = (Node) event.getTarget();

        // Check if the clicked node is NOT inside popUpPane
        if (!isDescendant(clickedNode, popUpPane)) {
            popupService.popDown();
        }
    }

    @FXML
    private void closePopUpClicked() {
        popupService.popDown();
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

    private int getSelectedProjectId() {
        Project project = projectList.getSelectionModel().getSelectedItem();
        return project != null ? project.getId() : 0;
    }

}