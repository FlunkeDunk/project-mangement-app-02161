package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

public class TimeServer {
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public WeekBasedCalendar getCurrentWeekDate() {
        LocalDate date = getCurrentDate();
        System.out.println("Current date: " + date);
        int week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = date.get(IsoFields.WEEK_BASED_YEAR);
        return new WeekBasedCalendar(week, year);
    }
}
