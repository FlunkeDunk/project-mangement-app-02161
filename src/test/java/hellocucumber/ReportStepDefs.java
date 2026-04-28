package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

public class ReportStepDefs {
    @When("the project leader creates a report")
    public void the_project_leader_creates_a_report() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("a report is created")
    public void a_report_is_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("the activity {string} has {int} hours budgeted")
    public void theActivityHasHoursBudgeted(String arg0, int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the activity {string} has {int} hours spent")
    public void theActivityHasHoursSpent(String arg0, int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the report indicates the time budget is {int} hours")
    public void theReportIndicatesTheTimeBudgetIsHours(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the report indicates the time spent is {int} hours")
    public void theReportIndicatesTheTimeSpentIsHours(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
