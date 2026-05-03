package dtu.superPlanner;

public class ReportEntry {
    private final double budgeted;
    private final double spent;
    private final double timeLeft;
    private final String name;

    public ReportEntry(double duration, double spent, double timeLeft, String name) {
        this.budgeted = duration;
        this.spent = spent;
        this.timeLeft = timeLeft;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Time budgeted: " + budgeted + " hours, Time Spent: " + spent + " hours, timeLeft: " + timeLeft + " hours.";
    }

    public double getBudgeted() {
        return budgeted;
    }

    public double getSpent() {
        return spent;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public String getName() {
        return name;
    }
}
