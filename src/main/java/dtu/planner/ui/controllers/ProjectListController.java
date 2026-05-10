package dtu.planner.ui.controllers;

import dtu.planner.ui.ActivityItemFactoryAware;
import dtu.planner.ui.CustomScene;
import dtu.planner.ui.NodeUtils;
import dtu.planner.ui.ProjectDetailsView;
import dtu.planner.ui.UiState;
import dtu.planner.ui.interfaces.ActivityItemFactory;
import dtu.planner.ui.interfaces.PopupService;
import dtu.planner.ui.interfaces.PopupServiceFactory;
import dtu.planner.ui.interfaces.PopupServiceFactoryAware;
import dtu.planner.ui.interfaces.UiStateAware;
import dtu.superPlanner.Project;
import dtu.superPlanner.Report;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author Arthur
 */

public class ProjectListController extends ProjectManagementAwareController
        implements UiStateAware, ActivityItemFactoryAware, PopupServiceFactoryAware {

    private ActivityItemFactory activityItemFactory;
    private PopupServiceFactory popupServiceFactory;

    private PopupService popupService;

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
    private Button viewReportButton;

    private UiState uiState;

    @FXML
    private void initialize() {
        popupService = popupServiceFactory.create(popUpPane, popUpContainer, rootVBox, navigator);
        setSelectedProjectButtonsDisabled(true);
        clearProjectDetails();
        clearActivityList();
        popupService.popDown();

        projectList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showProject(newSelection);
            }
        });

        // Updates activity ui in uiState
        activityListAccordion.expandedPaneProperty().addListener((obs, oldPane, newPane) -> {
            int activityId = activityListAccordion.getPanes().indexOf(newPane) + 1;
            uiState.setActivityId(activityId);
        });

        loadProjects();
    }

    private void setSelectedProjectButtonsDisabled(boolean disabled) {
        addActivityButton.setDisable(disabled);
        editProjectButton.setDisable(disabled);
        viewReportButton.setDisable(disabled);
    }

    private void loadProjects() {
        int i = 0;
        projectList.getItems().addAll(app.getAllProjects());
        for (Project project : projectList.getItems()) {
            if (uiState.getProjectId() == project.getId()) {
                projectList.getSelectionModel().select(i);
                break;
            }
            i++;
        }
    }

    private void showProject(Project project) {
        if (project == null)
            return;
        boolean isUserLeader = project.isProjectLeader(app.getUserInitials());
        uiState.setProjectId(project.getId());
        setProjectDetails(new ProjectDetailsView(project));
        activityListAccordion.getPanes()
                .setAll(activityItemFactory.create(project, isUserLeader, popupService, this::executeUiAction));
        selectActivity();
                
        setSelectedProjectButtonsDisabled(!project.isProjectLeader(app.getUserInitials()));
    }

    private void selectActivity() {
        int paneIndex = uiState.getActivityId() - 1;
        if (0 <= paneIndex && paneIndex < activityListAccordion.getPanes().size()) {
            TitledPane pane = activityListAccordion.getPanes().get(paneIndex);
            if (pane != null) {
                activityListAccordion.expandedPaneProperty().set(pane);
            }

        }

    }

    private void setProjectDetails(ProjectDetailsView details) {
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

    private int getSelectedProjectId() {
        Project project = projectList.getSelectionModel().getSelectedItem();
        return project != null ? project.getId() : 0;
    }

    // --- Button Handlers ---
    @FXML
    private void handleAddActivity() {
        executeUiAction(
                popupService::popUp,
                CustomScene.CREATE_ACTIVITY,
                "Operation failed");
    }

    @FXML
    private void handleEditProject() {
        executeUiAction(
                popupService::popUp,
                CustomScene.EDIT_PROJECT,
                "Operation failed");
    }

    @FXML
    private void handleAddProject() {
        executeUiAction(
                popupService::popUp,
                CustomScene.CREATE_PROJECT,
                "Operation failed");
    }

    @FXML
    private void handleViewReport() {
        Report report = app.createReport(getSelectedProjectId());
        executeUiAction(
                navigator::changeScene,
                CustomScene.VIEW_REPORT,
                "Operation failed");
    }

    @FXML
    private void onLogout() {
        executeUiAction(
                navigator::changeScene,
                CustomScene.LOGIN,
                "Operation failed");
    }

    @FXML
    private void onAddFixedActivity() {
        executeUiAction(
                navigator::changeScene,
                CustomScene.ADD_FIXED_ACTIVITY,
                "Operation failed");
    }

    @FXML
    private void onUserRegisterTime() {
        executeUiAction(
                navigator::changeScene,
                CustomScene.REGISTER_TIME_LIST,
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

    @Override
    public void setUiState(UiState uiState) {
        this.uiState = uiState;
    }

    @Override
    public void setPopupServiceFactory(PopupServiceFactory popupServiceFactory) {
        this.popupServiceFactory = popupServiceFactory;
    }

    @Override
    public void setActivityItemFactory(ActivityItemFactory activityItemFactory) {
        this.activityItemFactory = activityItemFactory;
    }
}