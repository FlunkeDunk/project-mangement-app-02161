package dtu.superPlanner;

import java.util.HashSet;
import java.util.Set;

public class Employee {
    private Set<Activity> activities;
    private Set<FixedActivity> fixedActivities;
    private String initials;

    /**
     * @author Emanuel
     */
    public Employee(String initials) {
        this.initials = initials;

        activities = new HashSet<>();
        fixedActivities = new HashSet<>();
    }

    /**
     * @author Emanuel
     */
    public void addActivity(AbstractActivity activity) throws IllegalArgumentException {
        switch (activity) {
            case Activity a:
                activities.add(a);
                break;
            case FixedActivity a:
                if (!fixedActivities.add(a)) {
                    throw new IllegalArgumentException("The employee already has that fixed activity");
                }
                break;
            default:
        }
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public Set<FixedActivity> getFixedActivities() {
        return fixedActivities;
    }

    /**
     * @author BenjaminEwe
     */
    public boolean isAvailable(TimeFrame timeFrame) {
        for (FixedActivity fixedActivity : fixedActivities) {
            if (TimeFrame.overlaps(timeFrame, fixedActivity.getTimeFrame())) {
                return true;
            }
        }
        return false;
    }

    public String getInitials() {
        return initials;
    }
}
