package dtu.example.ui.controllers;

import dtu.example.ui.ActivityItemFactory;
import dtu.example.ui.ProjectDetailsView;
import dtu.example.ui.interfaces.PopupService;
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
    private ActivityItemFactory activityItemFactory;

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
            if (newSelection != null) {
                showProject(newSelection);
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
    private void showProject(Project project) {
        if (project == null)
            return;

        setProjectDetails(new ProjectDetailsView(project));
        activityListAccordion.getPanes()
                .setAll(activityItemFactory.createActivityItems(project, popupService, this::executeUiAction));
        setSelectedProjectButtonsDisabled(false);
    }

    // --- Public method to update UI when a project is selected ---
    public void setProjectDetails(ProjectDetailsView details) {
        selectedProjectNameLabel.setText(details.getName());
        selectedProjectIdLabel.setText(details.getId());
        selectedProjectStartDateLabel.setText(details.getStartDate());
        selectedProjectLeaderLabel.setText(details.getLeader());
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
        executeUiAction(
            popupService::addActivity,
            getSelectedProjectId(),
            "Operation failed");
    }

    @FXML
    private void handleEditProject() {
        executeUiAction(
            popupService::editProject,
            getSelectedProjectId(),
            "Operation failed");
    }

    @FXML
    private void handleAddProject() {
        executeUiAction(
                popupService::createProject,
                "Operation failed");
    }

    @FXML
    private void handleViewReport() {
        Report report = app.createReport(getSelectedProjectId());
        executeUiAction(
            popupService::viewReport,
            report,
            "Operation failed");
    }

    @FXML
    private void onLogout() {
        executeUiAction(
                navigator::toLogin,
                "Operation failed");
    }

    @FXML
    private void onAddFixedActivity() {
        executeUiAction(
                popupService::addFixedActivity,
                "Operation failed");
    }

    @FXML
    private void onUserRegisterTime() {
        executeUiAction(
                navigator::toRegisterTimeList,
                "Operation failed");
    }

    @FXML
    private void checkClickedOutsidePopUp(MouseEvent event) {
        Node clickedNode = (Node) event.getTarget();

        // Check if the clicked node is NOT inside popUpPane
        if (!NodeUtils.isDescendant(clickedNode, popUpPane)) {
            popupService.popDown();
        }
    }

    @FXML
    private void closePopUpClicked() {
        popupService.popDown();
    }

    private int getSelectedProjectId() {
        Project project = projectList.getSelectionModel().getSelectedItem();
        return project != null ? project.getId() : 0;
    }

    public void setActivityItemFactory(ActivityItemFactory activityItemFactory) {
        this.activityItemFactory = activityItemFactory;
    }
}