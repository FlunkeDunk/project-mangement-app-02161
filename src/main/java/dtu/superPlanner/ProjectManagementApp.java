package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
        return projects.get(projectId);
    }

    public Set<Project> getAllProjects() {
        return new HashSet<>(projects.values());
    }

    public Activity createActivity(int projectId, String name, TimeFrame timeFrame) throws IllegalAccessException {
        //System.out.println("\nCreating activity for project " + projectId + "\nUser:" + userInitials + "\nLeader:" + projects.get(projectId).getProjectLeader());
        Project myProject = getProject(projectId);
        String projectLeader = myProject.getProjectLeader();
        if (projectLeader != null && !projectLeader.equals(userInitials)) {
            throw new IllegalAccessException("Only the project leader can create activities");
        }

        return myProject.createActivity(name, timeFrame);
    }

    public void registerTime(int projectId, int activityId, LocalDate date, double time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void editTime(int projectId, int activityId, LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setProjectLeader(int projectId, String employeeInitials) {
        //String projectLeader = projects.get(projectId).getProjectLeader();
        //if (projectLeader != null && !projectLeader.equals(userInitials)) {
        //    throw new IllegalAccessException("Only the project leader can set a new project leader");
        //}
        projects.get(projectId).setProjectLeader(employeeInitials);
    }

    public Report createReport(int projectId) {
        Project myProject = getProject(projectId);
        return myProject.createReport();
    }

    public FixedActivity createFixedActivity(String employeeInitials, String name, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setBudgetedTime(int projectId, int activityId, double budgetedTime) {
        projects.get(projectId).setBudgetedTime(activityId, budgetedTime);
    }

    public void setActivityTimeFrame(int projectId, int activityId, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void addEmployeeToActivity(int projectId, int activityId, String employeeInitials) {
        projects.get(projectId).addEmployeeToActivity(activityId, employeeInitials);
    }

    public void setProjectName(int projectId, String name) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void login(String employeeInitials) {
        userInitials = employeeInitials;
    }

    public List<Employee> getAvailableEmployees(String employeeInitials, int projectId, int activityId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public String getUserInitials() {
        return userInitials;
    }
}
