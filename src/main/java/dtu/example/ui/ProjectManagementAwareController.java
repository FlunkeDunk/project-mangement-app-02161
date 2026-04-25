package dtu.example.ui;

import dtu.superPlanner.ProjectManagementApp;

public abstract class ProjectManagementAwareController implements ProjectManagementAware, NavigatorAware {
    protected ProjectManagementApp app;
    protected Navigator navigator;
    
    @Override
    public void setProjectManagementApp(ProjectManagementApp app) {
        this.app = app;
    }
    
    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
