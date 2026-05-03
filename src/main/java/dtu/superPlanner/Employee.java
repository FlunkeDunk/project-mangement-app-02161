package dtu.superPlanner;

import java.util.Set;

public class Employee {
    private Set<Activity> activities;
    private Set<FixedActivity> fixedActivities;
    private double dailyWorkHours;

    public void addActivity(AbstractActivity activity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Set<Activity> getActivities() {
        return activities;
    }
    
    public Set<FixedActivity> getFixedActivities() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isAvailable(TimeFrame timeFrame) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
