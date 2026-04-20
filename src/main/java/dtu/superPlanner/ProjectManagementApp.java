package dtu.superPlanner;

public class ProjectManagementApp {
    private int projectIdNumerator;

    public ProjectManagementApp() {

    }

    public Project createProject() {
        projectIdNumerator++;
        if (projectIdNumerator > 999) {
            throw new RuntimeException("Cannot create more than 999 projects a year");
        }
        return new Project(new WeekBasedCalendar(3,2027), projectIdNumerator);
    }

    public int getProjectIdNumerator() {
        return projectIdNumerator;
    }
}
