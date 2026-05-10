package dtu.whitebox;

import org.junit.jupiter.api.Test;

import dtu.superPlanner.Activity;
import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.MemoryProjectRepository;
import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class AddEmployeeToActivityTest {
    private ProjectManagementApp app = new ProjectManagementApp(new FileEmployeeRepository(), new MemoryProjectRepository());
    private Project project;
    private Activity activity;

    public AddEmployeeToActivityTest() throws IllegalAccessException {
        WeekBasedCalendar startWeek = new WeekBasedCalendar(1, 2049);
        WeekBasedCalendar endWeek = new WeekBasedCalendar(2, 2049);
        TimeFrame timeFrame = new TimeFrame(startWeek, endWeek);
        project = app.createProject("Test Project");
        activity = app.createActivity(project.getId(), "Test Activity", timeFrame);

        app.createEmployee("adut");
        app.createEmployee("mtie");
        app.createEmployee("jdat");
        app.login("adut");
    }

    @Test
    public void testNotProjectLeader() throws IllegalAccessException {
        // Set A
        app.setProjectLeader(project.getId(), "mtie");
        String errMessage = "";
        try {
            app.addEmployeeToActivity(project.getId(), activity.getId(), "jdat");
        } catch (Exception e) {
            errMessage = e.getMessage();
        }
        
        assertEquals("Only the project leader can assign employees to activities", errMessage);
    }

    @Test
    public void testEmployeeAlreadyAssigned() throws IllegalAccessException {
        // Set B
        app.addEmployeeToActivity(project.getId(), activity.getId(), "jdat");
        String errMessage = "";
        try {
            app.addEmployeeToActivity(project.getId(), activity.getId(), "jdat");
        } catch (Exception e) {
            errMessage = e.getMessage();
        }
        
        assertEquals("Employee is already added to the activity", errMessage);
    }

    @Test
    public void testEmployeeAssigned() throws IllegalAccessException {
        // Set C
        app.addEmployeeToActivity(project.getId(), activity.getId(), "jdat");

        assertTrue(activity.getEmployees().contains("jdat"));
    }
}