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

    public Report(Project project) {
        PROJECT_NAME = project.getName();
        Set<Activity> activities = project.getActivitySet();
        for (Activity activity : activities) {
            double thisBudget = activity.getBudgetedTime();
            double spent = activity.getTotalTimeSpent();
            double estTimeLeft = spent > thisBudget ? 0 : thisBudget - spent;

            ReportEntry entry = new ReportEntry(thisBudget, spent, estTimeLeft, activity.getName());
            entries.put(activity.getId(), entry);

            budgetedTime += thisBudget;
            timeSpent += spent;
            timeLeft += spent > thisBudget ? 0 : budgetedTime - timeSpent;
            
        }

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
}
