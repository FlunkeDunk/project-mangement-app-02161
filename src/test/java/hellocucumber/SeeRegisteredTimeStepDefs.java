package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import dtu.superPlanner.ProjectManagementApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SeeRegisteredTimeStepDefs {
    ProjectManagementApp myApp;
    ErrorMessageHolder errorHolder;
    ProjectHolder projectHolder;
    double result;

    public SeeRegisteredTimeStepDefs(TestContext context, ErrorMessageHolder errorHolder, ProjectHolder projectHolder) {
        this.myApp = context.app;
        this.errorHolder = errorHolder;
        this.projectHolder = projectHolder;
    }

    @Given("a user {string} is logged in")
    public void aUserIsLoggedIn(String user) {
        myApp.createEmployee(user);
        myApp.login(user);
    }

    @Given("a user {string} has no time registered")
    public void hasNoTimeRegistered(String string) {
        boolean noRegisters = myApp.getAllProjects().stream().allMatch(p -> p.getActivitySet().stream().allMatch(a -> a.getTimeLedger(string) == null));
        assertTrue(noRegisters);
    }

    @When("the user checks time worked for the date {string}")
    public void theUserChecksTimeWorkedForTheDate(String string) {
        result = myApp.getTimeRegisteredForDate(getLocalDateByName(string));
    }

    @Then("{double} hours is returned")
    public void hoursIsReturned(Double expected) {
        assertEquals(expected, result);
    }

    private LocalDate getLocalDateByName(String string){
        String[] splitString = string.split("-");
        int day = Integer.parseInt(splitString[0]);
        int month = Integer.parseInt(splitString[1]);
        int year = Integer.parseInt(splitString[2]);
        return LocalDate.of(year, month, day);


    }
}


