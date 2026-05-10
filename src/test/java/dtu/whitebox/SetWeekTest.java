package dtu.whitebox;

import org.junit.jupiter.api.Test;

import dtu.superPlanner.WeekBasedCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class SetWeekTest {

    @Test
    public void testSetWeekZeroThrowsException() {
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
    public void testSetNegativeWeek() {
        // Set B
        WeekBasedCalendar cal = new WeekBasedCalendar(-3, 2027);
        assertEquals(51, cal.getWeek());
        assertEquals(2026, cal.getYear());
    }

    @Test
    public void testSetWeekNormal() {
        // Set C
        WeekBasedCalendar cal = new WeekBasedCalendar(4, 1996);
        assertEquals(4, cal.getWeek());
        assertEquals(1996, cal.getYear());
    }
}

