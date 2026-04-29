package dtu.superPlanner;

public class TimeFrame {
    private WeekBasedCalendar startDate;
    private WeekBasedCalendar endDate;

    public TimeFrame(WeekBasedCalendar startDate, WeekBasedCalendar endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public WeekBasedCalendar getEndDate() {
        return endDate;
    }

    public void setStartDate(WeekBasedCalendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(WeekBasedCalendar endDate) {
        this.endDate = endDate;
    }

    public static boolean overlaps(TimeFrame timeFrame1, TimeFrame timeFrame2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toString() {
        return "(" + startDate.toString() + " to " + endDate.toString() + ")";
    }
}
