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

    public void editName(String employeeInitials, String newName) {
        if(employeeInitials != projectLeader && projectLeader != null) {
            //throw new Exception("Only the project leader can rename the activities");
        }

        name = newName;
        //Update UI?
    }

    public void editTime(String employeeInitials, LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Activity createActivity(String name, TimeFrame timeFrame) {
        Activity myActivity = new Activity(name, timeFrame, activities.size()+1);
        activities.put(myActivity.getId(), myActivity);
        return myActivity;
    }

    public void registerTime(String employeeInitials, LocalDate date, double time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Report createReport() {
        return new Report(this);
    }

    public void setBudgetedTime(int activityId, double budgetedTime) {
        activities.get(activityId).setBudgetedTime(budgetedTime);
    }

    public void setActivityTimeFrame(int activityId, TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void addEmployeeToActivity(int activityId, String employeeInitials) {
        activities.get(activityId).addEmployee(employeeInitials);
    }

    public void getActivityTimeFrame(int activityId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isLeader(String employeeInitials) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Activity getActivityById(int activityId) {
        return activities.get(activityId);
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
        return projectLeader;
    }

    public String getName() {
        return name;
    }

    public void setProjectLeader(String userIdentifier) {
        projectLeader = userIdentifier;
    }

    @Override
    public String toString() {
        return name;
    }
}
