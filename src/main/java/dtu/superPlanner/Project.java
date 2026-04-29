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

    public void editTime(String employeeInitials, LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void createActivity(String name, TimeFrame timeFrame, String userID) throws IllegalAccessException {
        if (projectLeader != null && !projectLeader.equals(userID)) {
            throw new IllegalAccessException("Only the project leader can create activities");
        }
        Activity myActivity = new Activity(name, timeFrame, activities.size()+1);
        activities.put(myActivity.getId(), myActivity);
    }

    public void registerTime(String employeeInitials, LocalDate date, double time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Report createReport() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setExpectedTime(int activityId, double expectedTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setActivityTimeFrame(int activityId, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void addEmployeeToActivity(int activityId, String employeeInitials) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void getActivityTimeFrame(int activityId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isLeader(String employeeInitials) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Activity getActivityById(int activityId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Set<Activity> getActivitySet() {
        if (activities == null) {
            return null;
        }
        return new HashSet<>(activities.values());
    }

    public Map<Integer, Activity> getActivityMap() {
        return activities;
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public int getId() {
        return ID;
    }

    public String getProjectLeader() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setProjectLeader(String userIdentifier) {
        projectLeader = userIdentifier;
    }
}
