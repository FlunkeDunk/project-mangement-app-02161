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
        // Precondition
        assert true;

        boolean containedActivity = true;

        switch (activity) {
            case Activity a:
                containedActivity = activities.contains(a);
                if (!activities.add(a)) {
                    throw new IllegalArgumentException("The employee already has that activity");
                }
                break;
            case FixedActivity a:
                containedActivity = fixedActivities.contains(a);
                if (!fixedActivities.add(a)) {
                    throw new IllegalArgumentException("The employee already has that fixed activity");
                }
                break;
            default:
                throw new IllegalArgumentException("Not a valid type of activity");
        }

        // Postcondition: the activity is added to the correct Set
        assert !containedActivity && ((activity instanceof Activity && activities.contains(activity))
                || (activity instanceof FixedActivity && fixedActivities.contains(activity))) : "Postcondition";
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
            if (timeFrame.overlaps(fixedActivity.getTimeFrame())) {
                return false;
            }
        }
        return true;
    }

    public String getInitials() {
        return initials;
    }
}
