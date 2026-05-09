package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectManagementApp {
    private String userInitials;
    private TimeServer timeServer;
    private final EmployeeRepository EMPLOYEE_REPOSITORY;
    private final ProjectRepository PROJECT_REPOSITORY;

    public ProjectManagementApp(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.EMPLOYEE_REPOSITORY = employeeRepository;
        this.PROJECT_REPOSITORY = projectRepository;
        timeServer = new TimeServer();
    }

    public Project createProject() throws RuntimeException {
        return createProject("");
    }

    /**
     * @author BenjaminEwe
     */
    public Project createProject(String name) throws RuntimeException {
        return PROJECT_REPOSITORY.createProject(name, timeServer.getCurrentWeekDate());
    }

    public int getProjectCount(int year) {
        return PROJECT_REPOSITORY.getProjectCount(year);
    }

    public Activity getActivity(int projectId, int activityId) {
        return getProject(projectId).getActivityById(activityId);
    }

    public void setTimeServer(TimeServer timeServer) {
        this.timeServer = timeServer;
    }

    public Project getProject(int projectId) {
        if (!PROJECT_REPOSITORY.contains(projectId)) {
            throw new IllegalArgumentException("Invalid project id");
        }
        return PROJECT_REPOSITORY.get(projectId);
    }

    public Set<Project> getAllProjects() {
        return PROJECT_REPOSITORY.getAllProjects();
    }

    public Activity createActivity(int projectId, String name, TimeFrame timeFrame) throws IllegalAccessException {
        Project myProject = getProject(projectId);
        if (!myProject.isProjectLeader(userInitials)) {
            throw new IllegalAccessException("Only the project leader can create activities");
        }

        return myProject.createActivity(name, timeFrame);
    }

    public void registerTime(int projectId, int activityId, double time, LocalDate date) {
        if (time < 0) {
            throw new IllegalArgumentException("Cannot register negative time");
        }
        if (date.isAfter(timeServer.getCurrentDate())) {
            throw new IllegalArgumentException("Cannot register time in the future");
        }
        getProject(projectId).registerTime(activityId, userInitials, date, time);
    }

    public void registerTime(int projectId, int activityId, double time) {
        if (time < 0) {
            throw new IllegalArgumentException("Cannot register negative time");
        }
        getProject(projectId).registerTime(activityId, userInitials, timeServer.getCurrentDate(), time);
    }

    public void editTime(int projectId, int activityId, LocalDate date, double newTime)
            throws IllegalArgumentException {
        getProject(projectId).editTime(activityId, userInitials, date, newTime);
    }

    public void setProjectLeader(int projectId, String newLeaderInitials) throws IllegalAccessException {
        if (!getProject(projectId).isProjectLeader(userInitials)) {
            throw new IllegalAccessException("Only the project leader can set a new project leader");
        }
        PROJECT_REPOSITORY.get(projectId).setProjectLeader(newLeaderInitials);
    }

    public Report createReport(int projectId) {
        Project myProject = getProject(projectId);
        return myProject.createReport();
    }

    public FixedActivity createFixedActivity(FixedActivityType type, TimeFrame timeFrame)
            throws IllegalArgumentException {
        FixedActivity activity = new FixedActivity(type, timeFrame);
        Employee user = EMPLOYEE_REPOSITORY.get(userInitials);
        user.addActivity(activity);

        return activity;
    }

    public void setBudgetedTime(int projectId, int activityId, double budgetedTime) throws IllegalAccessException {
        Project project = getProject(projectId);
        if (!project.isProjectLeader(userInitials))
            throw new IllegalAccessException("Only the project leader can set budgeted time for activities");
        PROJECT_REPOSITORY.get(projectId).setBudgetedTime(activityId, budgetedTime);
    }

    public void setActivityTimeFrame(int projectId, int activityId, TimeFrame timeFrame) {
        getProject(projectId).setActivityTimeFrame(activityId, timeFrame);
    }

    /**
     * @author Ebbe
     */
    public void addEmployeeToActivity(int projectId, int activityId, String employeeInitials)
            throws IllegalAccessException, IllegalArgumentException {
        Project proj = PROJECT_REPOSITORY.get(projectId);
        Employee employee = getEmployee(employeeInitials);
        Activity activity = proj.getActivityById(activityId);
        if (!proj.isProjectLeader(userInitials)) {
            throw new IllegalAccessException("Only the project leader can assign employees to activities");
        }
        if (activity.getEmployees().contains(employeeInitials)) {
            throw new IllegalArgumentException("Employee is already added to the activity");
        }
        proj.addEmployeeToActivity(activityId, employeeInitials);
        employee.addActivity(proj.getActivityById(activityId));
    }

    /**
     * @author Ebbe
     */
    public void setProjectName(int projectId, String name) throws IllegalAccessException {
        Project project = getProject(projectId);
        if (!project.isProjectLeader(userInitials))
            throw new IllegalAccessException("Only the project leader can change project names");
        project.editName(name);
    }

    public void setActivityName(int projectId, int activityId, String name) throws IllegalAccessException {
        Project project = getProject(projectId);
        if (!project.isProjectLeader(userInitials))
            throw new IllegalAccessException("Only the project leader can change activity names");
        project.getActivityById(activityId).setName(name);
    }

    public Employee getEmployee(String initials) throws IllegalArgumentException {
        if (!EMPLOYEE_REPOSITORY.contains(initials)) {
            throw new IllegalArgumentException("Invalid employee initials");
        }
        return EMPLOYEE_REPOSITORY.get(initials);
    }

    public void login(String employeeInitials) {
        if (getEmployees()
                .stream()
                .anyMatch(employee -> employee.getInitials().equals(employeeInitials))) {
            userInitials = employeeInitials;
        } else {
            throw new IllegalArgumentException("Employee with initials " + employeeInitials + " does not exist.");
        }
    }

    /**
     * @author BenjaminEwe
     */

    public List<String> getAvailableEmployees(int projectId, int activityId)
            throws IllegalAccessException {

        Project project = getProject(projectId);

        if (!project.isProjectLeader(userInitials)) {
            throw new IllegalAccessException(
                    "Only the project leader can see available employees");
        }

        Activity activity = getActivity(projectId, activityId);

        return EMPLOYEE_REPOSITORY.findAvailableEmployeeInitials(activity);
    }


    public String getUserInitials() {
        return userInitials;
    }

    public Set<Employee> getEmployees() {
        return new HashSet<>(EMPLOYEE_REPOSITORY.getAllEmployees());
    }

    public TimeServer getTimeServer() {
        return timeServer;
    }

    /**
     * @author Emanuel
     */
    public Set<FixedActivity> getFixedActivities() {
        Employee user = EMPLOYEE_REPOSITORY.get(userInitials);
        return user.getFixedActivities();
    }

    public void createEmployee(String initials) {
        EMPLOYEE_REPOSITORY.addEmployee(initials);
    }
}
