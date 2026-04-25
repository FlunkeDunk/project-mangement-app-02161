package dtu.example.ui;

import dtu.superPlanner.ProjectManagementApp;

public abstract class ProjectManagementAwareController implements ProjectManagementAware {
    protected ProjectManagementApp app;
    
    public void setProjectManagementApp(ProjectManagementApp app) {
        this.app = app;
    }
}
