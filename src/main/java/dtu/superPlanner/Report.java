package dtu.superPlanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Report {
    private final String PROJECT_NAME;
    private double timeSpent = 0;
    private double budgetedTime = 0;
    private double timeLeft = 0;
    private final Map<Integer, ReportEntry> entries = new HashMap<>();

    /**
     * @author BenjaminEwe
     */
    public Report(Project project) {
        PROJECT_NAME = project.getName();
        Set<Activity> activities = project.getActivitySet();
        for (Activity activity : activities) {
            double thisBudget = activity.getBudgetedTime();
            double spent = activity.getTotalTimeSpent();
            double estTimeLeft = spent > thisBudget ? 0 : thisBudget - spent;

            ReportEntry entry = new ReportEntry(thisBudget, estTimeLeft, spent, activity.getName());
            entries.put(activity.getId(), entry);

            budgetedTime += thisBudget;
            timeSpent += spent;
            timeLeft += budgetedTime - timeSpent;
        }
        timeLeft = timeLeft < 0 ? 0 : timeLeft;

    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public double getBudgetedTime() {
        return budgetedTime;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public Map<Integer, ReportEntry> getEntries() {
        return entries;
    }

    public String getProjectName() {
        return PROJECT_NAME;
    }

    /**
     * @author BenjaminEwe
     */
    public record ReportEntry(double budgetedTime, double timeLeft, double timeSpent, String name) {
        @Override
        public String toString() {
            return "[" + name + "] Time budgeted: " + budgetedTime + " hours, Time Spent: " + timeSpent + " hours, timeLeft: " + timeLeft + " hours.";
        }
    }
}
