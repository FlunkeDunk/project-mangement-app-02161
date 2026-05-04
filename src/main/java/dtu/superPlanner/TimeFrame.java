package dtu.superPlanner;

public class TimeFrame {
    private WeekBasedCalendar startDate;
    private WeekBasedCalendar endDate;

    public TimeFrame(WeekBasedCalendar startDate, WeekBasedCalendar endDate) throws IllegalArgumentException {
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("DateError: End date must be after start date");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public WeekBasedCalendar getEndDate() {
        return endDate;
    }

    public void setStartDate(WeekBasedCalendar date) throws IllegalArgumentException {
        if (endDate.before(date)) {
            throw new IllegalArgumentException("DateError: End date must be after start date");
        }
        startDate = date;
    }

    public void setEndDate(WeekBasedCalendar date) {
        if (date.before(startDate)) {
            throw new IllegalArgumentException("DateError: End date must be after start date");
        }
        endDate = date;
    }

    public static boolean overlaps(TimeFrame timeFrame1, TimeFrame timeFrame2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toString() {
        return "(" + startDate.toString() + " to " + endDate.toString() + ")";
    }
}
