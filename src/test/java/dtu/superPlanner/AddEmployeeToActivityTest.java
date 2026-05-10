package dtu.superPlanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddEmployeeToActivityTest {
    private ProjectManagementApp app = new ProjectManagementApp(new FileEmployeeRepository(), new MemoryProjectRepository());
    private Project testProject1 = app.createProject("Test Project1");
    private Project testProject2 = app.createProject("Test Project2");
    private Project testProject3 = app.createProject("Test Project3");
    private Activity testActivity1;
    private Activity testActivity2;
    private Activity testActivity3;

    String errMessage = "";

    public AddEmployeeToActivityTest() throws IllegalAccessException {
        WeekBasedCalendar startWeek = new WeekBasedCalendar(1, 2049);
        WeekBasedCalendar endWeek = new WeekBasedCalendar(2, 2049);
        TimeFrame timeFrame = new TimeFrame(startWeek, endWeek);
        this.testActivity1 = app.createActivity(testProject1.getId(), "Test Activity1", timeFrame);
        this.testActivity2 = app.createActivity(testProject2.getId(), "Test Activity2", timeFrame);
        this.testActivity3 = app.createActivity(testProject3.getId(), "Test Activity3", timeFrame);

        app.createEmployee("adut");
        app.createEmployee("mtie");
        app.createEmployee("jdat");
        app.login("adut");
    }

    @Test
    public void testProjectLeaderOnly() throws IllegalAccessException {
        // Set A
        app.setProjectLeader(testProject1.getId(), "mtie");
        try {
            app.addEmployeeToActivity(testProject1.getId(), testActivity1.getId(), "jdat");
        } catch (Exception e) {
            errMessage = e.getMessage();
        }
        
        assertEquals("Only the project leader can assign employees to activities", errMessage);
    }

    @Test
    public void testEmployeeAlreadyAssigned() throws IllegalAccessException {
        // Set B
        app.addEmployeeToActivity(testProject2.getId(), testActivity2.getId(), "jdat");

        try {
            app.addEmployeeToActivity(testProject2.getId(), testActivity2.getId(), "jdat");
        } catch (Exception e) {
            errMessage = e.getMessage();
        }
        
        assertEquals("Employee is already added to the activity", errMessage);
    }

    @Test
    public void testEmployeeAssigned() throws IllegalAccessException {
        // Set C
        app.addEmployeeToActivity(testProject3.getId(), testActivity3.getId(), "jdat");

        assertTrue(testActivity3.getEmployees().contains("jdat"));
    }
}