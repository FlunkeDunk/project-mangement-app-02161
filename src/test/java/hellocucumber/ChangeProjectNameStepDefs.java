package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.en.*;

public class ChangeProjectNameStepDefs {
    /*
    private final Project myProject; // Define this

    public ChangeProjectNameStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        
    }
    */

    @Given("the project has no project leader")
    public void the_project_has_no_project_leader() {
        assertNull(myProject.getProjectLeader());
    }

    @Given("the project has a project leader")
    public void the_project_has_project_leader() {
        assertNotNull(myProject.getProjectLeader());
    }

    @Given("the user is a project leader")
    public void the_user_is_a_project_leader(String employeeInitials) {
        assertEquals(employeeInitials, myProject.getProjectLeader());
    }

    @Given("the user is not a project leader")
    public void the_user_is_not_a_project_leader(String employeeInitials) {
        assertNotEquals(employeeInitials, myProject.getProjectLeader());
    }

    @When("the user changes the project name to {string}")
    public void the_user_changes_the_project_name_to(String employeeInitials, String newProjectName) {
        myProject.editName(employeeInitials, newProjectName);
    }

    @Then("the project has the name {string}")
    public void the_project_has_the_name(String newProjectName) {
        assertEquals(newProjectName, myProject.getName());
    }
}
