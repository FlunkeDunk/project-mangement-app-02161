package dtu.superPlanner;

public class ProjectManagementApp {
    private int projectIdNumerator;
    private TimeServer timeServer;

    public ProjectManagementApp() {
        timeServer = new TimeServer();
    }

    public Project createProject() {
        projectIdNumerator++;
        if (projectIdNumerator > 999) {
            throw new RuntimeException("Cannot create more than 999 projects a year");
        }
        return new Project(timeServer.getCurrentDate(), projectIdNumerator);
    }

    public int getProjectIdNumerator() {
        return projectIdNumerator;
    }
    public void setTimeServer(TimeServer timeServer){
        this.timeServer = timeServer;
    }
}
