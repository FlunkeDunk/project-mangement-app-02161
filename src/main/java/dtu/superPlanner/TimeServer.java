package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.Calendar;

public class TimeServer {
    public WeekBasedCalendar getCurrentDate() {
        LocalDate date = LocalDate.now();
        int week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = date.get(IsoFields.WEEK_BASED_YEAR);
        return new WeekBasedCalendar(week, year);
    }
}
