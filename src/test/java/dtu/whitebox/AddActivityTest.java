package dtu.whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import dtu.superPlanner.AbstractActivity;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Employee;
import dtu.superPlanner.FixedActivity;
import dtu.superPlanner.FixedActivityType;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;

public class AddActivityTest {
    private Employee employee;

    public AddActivityTest() {
        employee = new Employee("alan");
    }

    @Test
    public void testAddActivity() {
        // Set A
        TimeFrame timeFrame = new TimeFrame(new WeekBasedCalendar(6, 2026), new WeekBasedCalendar(7, 2026));
        Activity activity = new Activity("Test", timeFrame, 1);

        employee.addActivity(activity);

        Set<Activity> activities = employee.getActivities();
        assertEquals(1, activities.size());
        assertTrue(activities.contains(activity));
    }

    @Test
    public void testAddActivityTwiceThrows() {
        // Set B
        TimeFrame timeFrame = new TimeFrame(new WeekBasedCalendar(6, 2026), new WeekBasedCalendar(7, 2026));
        Activity activity = new Activity("Test", timeFrame, 1);

        String errMsg = "";
        employee.addActivity(activity);
        try {
            employee.addActivity(activity);
        } catch (Exception e) {
            errMsg = e.getMessage();
        }
        Set<Activity> activities = employee.getActivities();
        assertEquals(1, activities.size());
        assertEquals("The employee already has that activity", errMsg);
    }

    @Test
    public void testAddFixedActivity() {
        // Set C
        TimeFrame timeFrame = new TimeFrame(new WeekBasedCalendar(6, 2026), new WeekBasedCalendar(7, 2026));
        FixedActivity activity = new FixedActivity(FixedActivityType.Sick, timeFrame);

        employee.addActivity(activity);

        Set<FixedActivity> fixedActivities = employee.getFixedActivities();
        assertEquals(1, fixedActivities.size());
        assertTrue(fixedActivities.contains(activity));
    }

    @Test
    public void testAddFixedActivityTwiceThrows() {
        // Set D
        TimeFrame timeFrame = new TimeFrame(new WeekBasedCalendar(6, 2026), new WeekBasedCalendar(7, 2026));
        FixedActivity activity = new FixedActivity(FixedActivityType.Sick, timeFrame);

        String errMsg = "";
        employee.addActivity(activity);
        try {
            employee.addActivity(activity);
        } catch (Exception e) {
            errMsg = e.getMessage();
        }
        Set<FixedActivity> fixedActivities = employee.getFixedActivities();
        assertEquals(1, fixedActivities.size());
        assertEquals("The employee already has that fixed activity", errMsg);
    }

    @Test
    public void testAddWeirdActivityThrows() {
        // Set E
        TimeFrame timeFrame = new TimeFrame(new WeekBasedCalendar(6, 2026), new WeekBasedCalendar(7, 2026));
        AbstractActivity activity = new AbstractActivity("Weirdo", timeFrame) {};

        String errMsg = "";
        try {
            employee.addActivity(activity);
        } catch (Exception e) {
            errMsg = e.getMessage();
        }
        
        assertEquals("Not a valid type of activity", errMsg);
    }
}
