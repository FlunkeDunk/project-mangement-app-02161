package hellocucumber;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectStepDefs {
    public String user;
    // public String date;
    public ProjectManagementApp myApp;
    public Project project;
    private ErrorMessageHolder errorHolder;

    public ProjectStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        this.myApp = myApp;
        this.errorHolder = errorHolder;

    }

    @Given("a user is logged in")
    public void aUserIsLoggedIn() {
        user = "huba";
    }

    @Given("the date is {string}")
    public void the_date_is(String string) {
        String[] splitString = string.split("-");
        int day = Integer.parseInt(splitString[0]);
        int month = Integer.parseInt(splitString[1]);
        int year = Integer.parseInt(splitString[2]);
        LocalDate date = LocalDate.of(year, month, day);

        MockTimeHolder mth = new MockTimeHolder(myApp);
        mth.setDate(date);
    }

    @When("there are no projects created this year")
    public void thereAreNoProjectCreatedThisYear() {
        int projectCount = myApp.getProjectIdNumerator();
        assert projectCount == 0;
    }

    @Given("a project")
    public void aProject() {
        theUserCreatesAProject();
    }

    @When("the user creates a project")
    public void theUserCreatesAProject() {
        try {
            project = myApp.createProject();
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }

    }

    @Then("there is a project")
    public void thereIsAProject() {
        assertNotNull(project);
    }

    @Then("project has the id {int}")
    public void projects_has_the_id(Integer expectedId) {
        assertEquals(expectedId, project.getId());
    }

    @Then("the project has no project leader")
    public void the_projects_has_no_project_leader() {
        assertNull(project.getProjectLeader());
    }

    @Then("the project starts in week {int} and year {int}")
    public void theProjectStartsInWeekAndYear(int weekInput, int yearInput) {
        WeekBasedCalendar startDate = project.getStartDate();
        int week = startDate.getWeek();
        int year = startDate.getYear();

        assertEquals(weekInput, week);
        assertEquals(yearInput, year);

    }

    @Then("the project has the id {int}")
    public void the_project_has_the_id(int i) {
        assertEquals(i, project.getId());
    }

    @When("there are {int} project(s) created this year")
    public void there_are_project_created_this_year(Integer int1) {
        for (int i = 0; i < int1; i++) {
            myApp.createProject();
        }
        assertEquals(myApp.getProjectIdNumerator(), int1);
    }

    @Then("an error is thrown {string}")
    public void an_error_is_thrown(String string) {
        assertEquals(string, errorHolder.getError());
    }

    @Then("under {int} project(s) have been created this year")
    public void underProjectsHaveBeenCreatedThisYear(Integer int1) {
        assertTrue(myApp.getProjectIdNumerator() < int1);
    }

    @Given("no projects have been created")
    public void noProjectsHaveBeenCreated() {
        assertEquals(0, myApp.getAllProjects().size());
    }

    @When("the user creates a project {string}")
    public void theUserCreatesAProject(String string) {
        project = myApp.createProject(string);
    }

    @Then("the project has the name {string}")
    public void theProjectHasTheName(String string) {
        assertEquals(string, project.getName());
    }

    @When("the user creates {int} project(s)")
    public void theUserCreatesProject(Integer int1) {
        for (int i = 0; i < int1; i++) {
            myApp.createProject();
        }
    }

    @Then("{int} project(s) exist(s)")
    public void projectExists(Integer int1) {
        Set<Project> projects = myApp.getAllProjects();
        assertEquals(int1, projects.size());
    }
}
