package dtu.superPlanner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ProjectManagementApp {
    private Map<Integer, Project> projects;
    private String userInitials;
    private int projectIdNumerator;
    private TimeServer timeServer;
    private final EmployeeRepository EMPLOYEE_REPOSITORY;

    public ProjectManagementApp(EmployeeRepository EMPLOYEE_REPOSITORY) {
        this.EMPLOYEE_REPOSITORY = EMPLOYEE_REPOSITORY;
        projects = new HashMap<>();
        timeServer = new TimeServer();
    }

    public Project createProject() throws RuntimeException {
        return createProject("");
    }

    /**
     * @author BenjaminEwe
     */
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

    public Activity getActivity(int projectId, int activityId) {
        return getProject(projectId).getActivityById(activityId);
    }

    public void setTimeServer(TimeServer timeServer) {
        this.timeServer = timeServer;
    }

    public Project getProject(int projectId) {
        if (!projects.containsKey(projectId)) {
            throw new IllegalArgumentException("Invalid project id");
        }
        return projects.get(projectId);
    }

    public Set<Project> getAllProjects() {
        return new HashSet<>(projects.values());
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
        projects.get(projectId).setProjectLeader(newLeaderInitials);
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
        projects.get(projectId).setBudgetedTime(activityId, budgetedTime);
    }

    public void setActivityTimeFrame(int projectId, int activityId, TimeFrame timeFrame) {
        getProject(projectId).setActivityTimeFrame(activityId, timeFrame);
    }

    /**
     * @author Ebbe
     */
    public void addEmployeeToActivity(int projectId, int activityId, String employeeInitials)
            throws IllegalAccessException, IllegalArgumentException {
        Project proj = projects.get(projectId);
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
    public List<String> getAvailableEmployees(int projectId, int activityId) throws IllegalAccessException {
        Project project = getProject(projectId);
        TimeFrame activityDuration = project.getActivityTimeFrame(activityId);

        // Aseert that Precondition that project and acitvityDuration is not null 
        assert project != null : "Project does not exist";
        assert activityDuration != null : "Activity does not exist";

        if (!project.isProjectLeader(userInitials)) {
            throw new IllegalAccessException("Only the project leader can see available employees.");
        }

        List<Employee> allEmployees = EMPLOYEE_REPOSITORY.getAllEmployees();
        PriorityQueue<priorityEmployee> leastBusyEmployees = new PriorityQueue<>();

        for (Employee employee : allEmployees) {
            if (!employee.isAvailable(activityDuration)) {
                continue;
            }
            if (employee.getActivities().contains(getActivity(projectId, activityId))) {
                continue;
            }

            int alreadyAssignedActivitiesInTimeFrame = getActivitiesOverlapping(activityDuration, employee);
            leastBusyEmployees.add(new priorityEmployee(employee.getInitials(), alreadyAssignedActivitiesInTimeFrame));
        }

        List<String> availableEmployeesInOrder = new ArrayList<>();
        while (!leastBusyEmployees.isEmpty()) {
            availableEmployeesInOrder.add(leastBusyEmployees.poll().userInitials());
        }

        List<String> result = availableEmployeesInOrder;
        // Assert that all employees are either in the result or not avaiable or already assigned the activity
        assert EMPLOYEE_REPOSITORY.getAllEmployees().stream().allMatch((employee) ->
            result.contains(employee.getInitials()) || !employee.isAvailable(activityDuration) || !employee.getActivities().contains(getActivity(projectId, activityId)))
                : "not all employees are either in the result or not avaiable or already assigned the activity";

        // Assert result is ordered post condition
        for (int i = 1; i < result.size(); i++) {
            Employee prev = EMPLOYEE_REPOSITORY.get(result.get(i - 1));
            Employee curr = EMPLOYEE_REPOSITORY.get(result.get(i));

            int prevLoad = getActivitiesOverlapping(activityDuration, prev);
            int currLoad = getActivitiesOverlapping(activityDuration, curr);

            assert prevLoad <= currLoad
                    : "Employees are not ordered by workload";
        }
        return result;
    }

    /**
     * @author BenjaminEwe
     */
    private int getActivitiesOverlapping(TimeFrame activityDuration, Employee employee) {
        Set<Activity> alreadyAssignedActivities = employee.getActivities();
        int activitiesOverlapping = 0;

        for (Activity activity : alreadyAssignedActivities) {
            TimeFrame existingActivityDuration = activity.getTimeFrame();

            if (TimeFrame.overlaps(existingActivityDuration, activityDuration)) {
                activitiesOverlapping++;
            }
        }

        return activitiesOverlapping;
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

    /**
     * @author BenjaminEwe
     */
    private record priorityEmployee(String userInitials, int priority) implements Comparable<priorityEmployee> {

        @Override
        public int compareTo(priorityEmployee other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public void createEmployee(String initials) {
        EMPLOYEE_REPOSITORY.addEmployee(initials);
    }
}
