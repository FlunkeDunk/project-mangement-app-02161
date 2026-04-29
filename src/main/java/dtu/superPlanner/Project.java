package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Project {
    private Map<Integer, Activity> activities;
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

    public Activity createActivity(String name, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
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

    public Set<Activity> getAllActivities() {
        if (activities == null) {
            return null;
        }
        return new HashSet<>(activities.values());
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
}
