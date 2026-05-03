package dtu.superPlanner;

public class ReportEntry {
    private final double budgeted;
    private final double spent;
    private final double estTimeLeft;

    public ReportEntry(double duration, double spent, double estTimeLeft) {
        this.budgeted = duration;
        this.spent = spent;
        this.estTimeLeft = estTimeLeft;
    }

    @Override
    public String toString() {
        return "Time budgeted: " + budgeted + " hours, Time Spent: " + spent + " hours, EstTimeLeft: " + estTimeLeft + " hours.";
    }

    public double getBudgeted() {
        return budgeted;
    }
}
