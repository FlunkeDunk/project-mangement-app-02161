package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProjectManagementApp {
    private Map<Integer, Project> projects;
    private Map<String, Employee> employees;
    private String userInitials;
    private int projectIdNumerator;
    private TimeServer timeServer;

    public ProjectManagementApp() {
        projects = new HashMap<>();
        employees = new HashMap<>();
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
        // System.out.println("\nCreating activity for project " + projectId + "\nUser:"
        // + userInitials + "\nLeader:" + projects.get(projectId).getProjectLeader());
        Project myProject = getProject(projectId);
        if (!myProject.isLeader(userInitials)) {
            throw new IllegalAccessException("Only the project leader can create activities");
        }

        return myProject.createActivity(name, timeFrame);
    }

    public void registerTime(int projectId, int activityId, double time, LocalDate date) {
        getProject(projectId).registerTime(activityId, userInitials, date, time);
    }

    public void registerTime(int projectId, int activityId, double time) {
        getProject(projectId).registerTime(activityId, userInitials, timeServer.getCurrentDate(), time);
    }

    public void editTime(int projectId, int activityId, LocalDate date, double newTime) {
        getProject(projectId).editTime(activityId, userInitials, date, newTime);
    }

    public void setProjectLeader(int projectId, String newLeaderInitials) throws IllegalAccessException {
        if (!getProject(projectId).isProjectLeader(userInitials)) {
            throw new IllegalAccessException("Only the project leader can set a new project leader");
        }
        projects.get(projectId).setProjectLeader(newLeaderInitials);
    }

    public Report createReport(int projectId) {
        Project myProject = getProject(projectId);
        return myProject.createReport();
    }

    public FixedActivity createFixedActivity(String employeeInitials, String name, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setBudgetedTime(int projectId, int activityId, double budgetedTime) throws IllegalAccessException {
        Project project = getProject(projectId);
        if (!project.isLeader(userInitials))
            throw new IllegalAccessException("Only the project leader can set budgeted time for activities");
        projects.get(projectId).setBudgetedTime(activityId, budgetedTime);
    }

    public void setActivityTimeFrame(int projectId, int activityId, TimeFrame timeFrame) {
        getProject(projectId).setActivityTimeFrame(activityId, timeFrame);
    }

    public void addEmployeeToActivity(int projectId, int activityId, String employeeInitials) throws IllegalAccessException {
        Project proj = projects.get(projectId);
        if (proj.isLeader(userInitials)) {
            projects.get(projectId).addEmployeeToActivity(activityId, employeeInitials);
        } else {
            throw new IllegalAccessException("Only the Project Leader can add employees to an activity");
        }
    }

    public void setProjectName(int projectId, String name) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void login(String employeeInitials) {
        if (getEmployees()
                .stream()
                .anyMatch(employee -> employee.getInitials().equals(employeeInitials))) {
            userInitials = employeeInitials;
        } else {
            throw new IllegalArgumentException("Employee with initals " + employeeInitials + " does not exist.");
        }
    }

    public List<Employee> getAvailableEmployees(int projectId, int activityId) throws IllegalAccessException {
        if (getProject(projectId).isProjectLeader(userInitials)) {
            throw new IllegalAccessException("Only the project leader can see available employees.");
        }

        return employees.values().stream().toList();
    }

    public String getUserInitials() {
        return userInitials;
    }

    public Set<Employee> getEmployees() {
        return new HashSet<Employee>(employees.values());
    }

    public void createEmployees(List<String> initialsList) {
        for (String initials : initialsList) {
            createEmployee(initials);
        }
    }

    private void createEmployee(String initials) {
        employees.put(initials, new Employee(initials));
    }
}
