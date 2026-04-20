package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectStepDefs {
    public String user;
    public String date;
    public ProjectManagementApp myApp;
    public Project project;

    public ProjectStepDefs(ProjectManagementApp myApp) {
        this.myApp = myApp;
    }

    @Given("a user is logged in")
    public void a_user_is_logged_in() {
        user = "huba";
    }
    @Given("the date is {string}")
    public void the_date_is(String string) {
        date =  string;
    }
    @When("there are no projects created this year")
    public void there_are_no_project_created_this_year() {
        int projectCount = myApp.getProjectIdNumerator();
        assert projectCount == 0;
    }
    @When("the user creates a project")
    public void the_user_creates_a_project() {
        project = myApp.createProject();
    }
    @Then("there is a project")
    public void there_is_a_project() {
        assertNotEquals(null, project);
    }
    @Then("project has the id {int}")
    public void projects_has_the_id(Integer expectedId) {
        assertEquals(expectedId, project.getId());
    }
    @Then("the project has no Project leader")
    public void the_projects_has_no_project_leader() {
        assertEquals(null, project.getProjectLeader());
    }

    @And("the project starts in week {int} and year {int}")
    public void theProjectStartsInWeekAndYear(int weekInput, int yearInput) {
        WeekBasedCalendar startDate = project.getStartDate();
        int week = startDate.getWeek();
        int year = startDate.getYear();

        assertEquals(week, weekInput);
        assertEquals(year, yearInput);

    }

    @Then("the project has the id {int}")
    public void the_project_has_the_id(int i) {
        assertEquals(i, project.getId());
    }
}
