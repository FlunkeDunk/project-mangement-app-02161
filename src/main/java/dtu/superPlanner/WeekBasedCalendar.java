package dtu.superPlanner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;

public class WeekBasedCalendar {
    private int week;
    private int year;

    public WeekBasedCalendar(int week, int year) {
        this.week = week;
        this.year = year;
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

    @Override
    public String toString() {
        return "" + year + "-W" + week;
    }
}
