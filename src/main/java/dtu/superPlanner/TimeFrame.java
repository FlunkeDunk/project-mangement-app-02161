package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

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

    public void setStartDate(int year, int week) {
        int weeksInGivenYear = LocalDate.of(year, 12, 28).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        if (week < 1 || week > weeksInGivenYear) {
            throw new IllegalArgumentException(String.format("DateError: Invalid start week: %d", week));
        } else {
            this.startDate = new WeekBasedCalendar(week, year);
        }
    }

    public void setStartDate(WeekBasedCalendar date) {
        setStartDate(date.getYear(), date.getWeek());
    }

    public void setEndDate(WeekBasedCalendar date) {
        setEndDate(date.getYear(), date.getWeek());
    }


    public void setEndDate(int yearEnd, int weekEnd) {
        int yearStart = getStartDate().getYear();
        int weekStart = getStartDate().getWeek();

        if (weekEnd == 0) {
            throw new IllegalArgumentException(String.format("DateError: Invalid end week: %d", weekEnd));
        }
        else if (weekEnd < 0) {
            // Since no week 0 exists, we take negative weeks to mean weeks before first week of a given year
            weekEnd++;
        }

        LocalDate endDateLocal = LocalDate.of(yearEnd, 1, 4).plusWeeks(weekEnd-1);
        yearEnd = endDateLocal.getYear();
        weekEnd = endDateLocal.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        if (yearEnd < yearStart || (yearEnd == yearStart && weekStart > weekEnd)) {
            setEndDate(startDate.getYear(), startDate.getWeek());
            throw new IllegalArgumentException("DateError: End date must be after start date");
        } else {
            this.endDate = new WeekBasedCalendar(weekEnd, yearEnd);
        }
    }

    public static boolean overlaps(TimeFrame timeFrame1, TimeFrame timeFrame2) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toString() {
        return "(" + startDate.toString() + " to " + endDate.toString() + ")";
    }
}
