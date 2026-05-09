package dtu.superPlanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeekBasedCalendarTest {

    @Test
    public void testNormalizeWeekZeroThrowsException() {
        // Set A
        String errMessage = "";
        String errType = "";

        try {
            WeekBasedCalendar myCal = new WeekBasedCalendar(0, 2026);
        } catch (Exception e) {
            errMessage = e.getMessage();
            errType = e.getClass().toString();
        }

        assertEquals("DateError: Invalid week: 0", errMessage);
        assertEquals("class java.lang.IllegalArgumentException", errType);
    }

    @Test
    public void testNormalizeNegativeWeek() {
        // Set B
        WeekBasedCalendar cal = new WeekBasedCalendar(-3, 2027);
        assertEquals(51, cal.getWeek());
        assertEquals(2026, cal.getYear());
    }

    @Test
    public void testNormalizeWeekOverflow() {
        // Set B
        WeekBasedCalendar cal = new WeekBasedCalendar(4, 1996);
        assertEquals(4, cal.getWeek());
        assertEquals(1996, cal.getYear());
    }
}

