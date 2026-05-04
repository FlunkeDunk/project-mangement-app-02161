package dtu.superPlanner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;

public class WeekBasedCalendar {
    private int week;
    private int year;

    public WeekBasedCalendar(int week, int year) {
        if (week == 0) {
            throw new IllegalArgumentException(String.format("DateError: Invalid week: %d", week));
        }
        else if (week < 0) {
            // Since no week 0 exists, we take negative weeks to mean weeks before first week of a given year
            week++;
        }

        LocalDate date = LocalDate.of(year, 1, 4).plusWeeks(week - 1);
        this.week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        this.year = date.getYear();
    }

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

    public void setWeek(int week) {
        this.week = week;
    }

    public LocalDate toLocalDate() {
        WeekFields weekFields = WeekFields.ISO;
        return LocalDate
                .now()
                .withYear(year)
                .with(weekFields.weekOfWeekBasedYear(), week)
                .with(weekFields.dayOfWeek(), DayOfWeek.MONDAY.getValue());
    }

    public boolean before(WeekBasedCalendar other) {
        return year < other.year || (year == other.year && week < other.week);
    }

    @Override
    public String toString() {
        return year + "-W" + week;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WeekBasedCalendar)) return false;
        WeekBasedCalendar wbc = (WeekBasedCalendar)other;
        return week == wbc.week && year == wbc.year;
    }
}
