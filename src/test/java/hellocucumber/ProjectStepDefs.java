package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import dtu.superPlanner.*;
import org.junit.jupiter.api.Assertions;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectStepDefs {
    public String user;
    public String date;
    public ProjectManagementApp myApp;
    public Project myProject;

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
        int projectCount = myApp.getProjectCount();
        assert projectCount == 0;
    }
    @When("the user creates a project")
    public void the_user_creates_a_project() {
        myProject = myApp.createProject();
    }
    @Then("there is a project")
    public void there_is_a_project() {
        assertNotEquals(null, myProject);
    }
    @Then("project has the id {int}")
    public void projects_has_the_id(Integer expectedId) {
        assertEquals(expectedId, myProject.getId());
    }
    @Then("the project has no Project Manager")
    public void the_projects_has_no_project_manager() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("the project starts in week {int} and year {int}")
    public void theProjectStartsInWeekAndYear(int weekInput, int yearInput) {
        WeekBasedCalendar startDate = myProject.getStartDate();
        int week = startDate.getWeek();
        int year = startDate.getYear();

        assertEquals(week, weekInput);
        assertEquals(year, yearInput);

        throw new PendingException();
    }
}
