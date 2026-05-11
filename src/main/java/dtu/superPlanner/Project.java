package dtu.superPlanner;

import java.time.LocalDate;
import java.util.*;

public class Project {
    private Map<Integer, Activity> activities = new HashMap<Integer, Activity>();
    private WeekBasedCalendar startDate;
    private String name;
    private String projectLeader;
    private final int ID;

    public Project(WeekBasedCalendar startDate, int ID) {
        this.startDate = startDate;
        this.ID = ID;
    }

    public Project(WeekBasedCalendar startDate, int ID, String name) {
        this(startDate, ID);
        this.name = name;
    }
    
    /**
     * @author Ebbe
     */
    public void editName(String newName) {
        name = newName;
    }

    public void editTime(int activityId, String employeeInitials, LocalDate date, double newTime) throws IllegalArgumentException {
        getActivityById(activityId).editTime(employeeInitials, date, newTime);
    }

    /**
     * @author BenjaminEwe
     */
    public Activity createActivity(String name, TimeFrame timeFrame) {
        Activity myActivity = new Activity(name, timeFrame, activities.size() + 1);
        activities.put(myActivity.getId(), myActivity);
        return myActivity;
    }

    public void registerTime(int activityId, String employeeInitials, LocalDate date, double time) {
        getActivityById(activityId).registerTime(employeeInitials, date, time);
    }

    public Report createReport() {
        return new Report(this);
    }

    public void setBudgetedTime(int activityId, double budgetedTime) {
        getActivityById(activityId).setBudgetedTime(budgetedTime);
    }

    public void setActivityTimeFrame(int activityId, TimeFrame timeFrame) {
        getActivityById(activityId).setTimeFrame(timeFrame);
    }

    public void addEmployeeToActivity(int activityId, String employeeInitials) throws IllegalArgumentException {
        getActivityById(activityId).addEmployee(employeeInitials);
    }

    public TimeFrame getActivityTimeFrame(int activityId) {
        return getActivityById(activityId).getTimeFrame();
    }

    public Activity getActivityById(int activityId) {
        if (!activities.containsKey(activityId)) {
            throw new IllegalArgumentException("Activity with ID: " + activityId + " does not exist");
        }
        
        return activities.get(activityId);
    }

    public Set<Activity> getActivitySet() {
        return new HashSet<>(activities.values());
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public int getId() {
        return ID;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public boolean isProjectLeader(String initials) {
        return projectLeader == null || projectLeader.equals(initials);
    }

    public String getName() {
        return name;
    }

    public void setProjectLeader(String userIdentifier) {
        projectLeader = userIdentifier;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }

    public Map<Integer, Activity> getActivityMap() {
        return activities;
    }
}
