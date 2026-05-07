package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SetProjectLeaderStepDefs {
    public ProjectManagementApp pma;
    public Project project;
    public ProjectHolder projectHolder;
    public ErrorMessageHolder errorHolder;

    public SetProjectLeaderStepDefs(TestContext context, ProjectHolder projectHolder, ErrorMessageHolder errorHolder) {
        this.pma = context.app;
        this.projectHolder = projectHolder;
        this.errorHolder = errorHolder;
    }

    @Given("the project has a project leader {string}")
    public void theProjectHasAProjectLeader(String projectLeaderInitials) throws IllegalAccessException {
        project = projectHolder.getProject();
        try {
            if (project.isProjectLeader(projectLeaderInitials)) {
                pma.setProjectLeader(project.getId(), projectLeaderInitials);
            }
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());

        }

    }

    @When("an employee promotes an employee {string} to a project leader")
    public void anEmployeePromotesAnEmployeeToAProjectLeader(String initials) throws IllegalAccessException {
        project = projectHolder.getProject();

        project = projectHolder.getProject();
        try {
            if (project.isProjectLeader(initials)) {
                pma.setProjectLeader(project.getId(), initials);
            }
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());

        }
    }

    @Then("the promoted employee {string} is a project leader in the project")
    public void thePromotedEmployeeIsAProjectLeaderInTheProject(String initials) {
        assertEquals(initials, project.getProjectLeader());
    }

    @Then("the promoted employee {string} is not a project leader in the project")
    public void thePromotedEmployeeIsNotAProjectLeaderInTheProject(String initials) {

        assertNotEquals(initials, project.getProjectLeader());
    }

}
