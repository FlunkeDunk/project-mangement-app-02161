package dtu.superPlanner;

public class ProjectManagementApp {
    private int projectIdNumerator;

    public ProjectManagementApp() {

    }

    public Project createProject() {
        projectIdNumerator++;
        return new Project(new WeekBasedCalendar(3,2027), projectIdNumerator);
    }

    public int getProjectIdNumerator() {
        return projectIdNumerator;
    }
}
