package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProjectManagementApp {
    private Map<Integer, Project> projects;
    private String userInitials;
    private int projectIdNumerator;
    private TimeServer timeServer;

    public ProjectManagementApp() {
        projects = new HashMap<>();
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
        int id = getProjectId();
        Project project = new Project(timeServer.getCurrentWeekDate(), id, name);
        projects.put(id, project);

        return project;
    }

    private int getProjectId() {
        WeekBasedCalendar date = timeServer.getCurrentWeekDate();
        return (date.getYear() % 100) * 1000 + projectIdNumerator;
    }

    public int getProjectIdNumerator() {
        return projectIdNumerator;
    }

    public void setTimeServer(TimeServer timeServer) {
        this.timeServer = timeServer;
    }

    public Project getProject(int projectId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Set<Project> getAllProjects() {
        return new HashSet<>(projects.values());
    }

    public Activity createActivity(int projectId, String name, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void registerTime(int projectId, int activityId, LocalDate date, double time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void editTime(int projectId, int activityId, LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setProjectLeader(int projectId, String employeeInitials) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Report createReport(int projectId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public FixedActivity createFixedActivity(String employeeInitials, String name, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setExpectedTime(int projectId, int activityId, double expectedTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setActivityTimeFrame(int projectId, int activityId, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void addEmployeeToActivity(int projectId, int activityId, String employeeInitials) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setProjectName(int projectId, String name) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean login(String employeeInitials) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Set<Employee> getAvailableEmployees(String employeeInitials, int projectId, int activityId) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
