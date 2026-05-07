package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

/**
 * @author Emanuel
 */
public class TimeServer {
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public WeekBasedCalendar getCurrentWeekDate() {
        LocalDate date = getCurrentDate();
        int week = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year = date.get(IsoFields.WEEK_BASED_YEAR);
        return new WeekBasedCalendar(week, year);
    }
}
