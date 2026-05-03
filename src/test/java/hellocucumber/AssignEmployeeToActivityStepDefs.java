package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.en.*;

import io.cucumber.java.PendingException;

public class AssignEmployeeToActivityStepDefs {
    /* 
    private final Activity myActivity; // Define this

    public AssignEmployeeToActivityStepDefs() {
        
    }
    */
    
    @When("{string} assigns {string} to {string}")
    public void theEmployeeAssignsTo(String assigner, String assignee, String activityName) {
        myActivity.addEmployee(assigner, assignee);
    }

    @Then("{string} is not assigned to {string}")
    public void isNotAssignedTo(String assignee, String activityName) {
        assertFalse(myActivity.getEmployees().contains(assignee));
    }

    @Then("{string} is assigned to {string}")
    public void isAssignedTo(String assignee, String activityName) {
        assertTrue(myActivity.getEmployees().contains(assignee));
    }

    @And("{string} is the project Leader")
    public void isTheProjectLeader(String projectLeader) {
        // Project leader reference needed below
        //assertEquals(projectLeader, myActivity.);
    }
}
