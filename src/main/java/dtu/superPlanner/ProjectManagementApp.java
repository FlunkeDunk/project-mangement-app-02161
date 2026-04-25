package dtu.superPlanner;

public class ProjectManagementApp {
    private int projectIdNumerator;
    private TimeServer timeServer;

    public ProjectManagementApp() {
        timeServer = new TimeServer();
    }

    public Project createProject() throws RuntimeException {
        return createProject("");
    }

    public Project createProject(String name) throws RuntimeException {
        if (projectIdNumerator >= 999) {
            throw new RuntimeException("Cannot create more than 999 projects a year");
        }
        projectIdNumerator++;
        return new Project(timeServer.getCurrentDate(), getProjectId(), name);
    }

    private int getProjectId() {
        WeekBasedCalendar date = timeServer.getCurrentDate();
        return (date.getYear()%100)*1000 + projectIdNumerator;
    }

    public int getProjectIdNumerator() {
        return projectIdNumerator;
    }
    public void setTimeServer(TimeServer timeServer){
        this.timeServer = timeServer;
    }
}
