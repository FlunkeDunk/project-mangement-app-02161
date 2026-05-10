package dtu.superPlanner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;

public class WeekBasedCalendar {
    private int week;
    private int year;

    /**
     * @author BenjaminEwe
     */
    public WeekBasedCalendar(int week, int year) {
        setWeekAndYear(week, year);
    }

    /**
     * @author Emanuel
     */
    public WeekBasedCalendar(LocalDate localDate) {
        this.week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        this.year = localDate.get(IsoFields.WEEK_BASED_YEAR);
    }

    public int getWeek() {
        return week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @author BenjaminEwe
     */
    public void setWeek(int week) {
        setWeekAndYear(week, this.year);
    }

    /**
     * @author Arthur
     */
    public LocalDate toLocalDate() {
        WeekFields weekFields = WeekFields.ISO;
        return LocalDate
                .now()
                .withYear(year)
                .with(weekFields.weekOfWeekBasedYear(), week)
                .with(weekFields.dayOfWeek(), DayOfWeek.MONDAY.getValue());
    }

    /**
     * @author Emanuel
     */
    public boolean before(WeekBasedCalendar other) {
        return year < other.year || (year == other.year && week < other.week);
    }

    /**
     * @author BenjaminEwe
     */
    private void setWeekAndYear(int week, int year) throws IllegalArgumentException {
        assert true;
        if (week == 0) {
            throw new IllegalArgumentException(String.format("DateError: Invalid week: %d", week));
        } else if (week < 0) {
            // Since no week 0 exists, we take negative weeks to mean weeks before first
            // week of a given year
            week++;
        }
        LocalDate date = LocalDate.of(year, 1, 4).plusWeeks(week - 1);
        this.week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        this.year = date.getYear();

        // The new week number must at most be the last week of the year we are in and at least 1 to be a valid week number
        assert(this.week <= LocalDate.of(this.year, 12, 28).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)
                && this.week > 0);
        // The new date is 'week' weeks after the input years first week
        assert(ChronoUnit.WEEKS.between(
                LocalDate.of(year, 1, 4).with(WeekFields.ISO.dayOfWeek(), 1),
                LocalDate.of(this.year, 1, 4)
                        .withYear(this.year)
                        .with(WeekFields.ISO.weekOfWeekBasedYear(), this.week)
                        .with(WeekFields.ISO.dayOfWeek(), 1)) == week - 1);
    }

    /**
     * @author Arthur
     */
    @Override
    public String toString() {
        return year + "-W" + week;
    }

    /**
     * @author Emanuel
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WeekBasedCalendar))
            return false;
        WeekBasedCalendar wbc = (WeekBasedCalendar) other;
        return week == wbc.week && year == wbc.year;
    }

    /**
     * @author Emanuel
     */
    @Override
    public int hashCode() {
        return year * 100 + week;
    }
}
