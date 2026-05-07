package hellocucumber;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.Report;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportStepDefs {
    private ProjectManagementApp myApp;
    private ErrorMessageHolder errorHolder;
    private Project project;
    private Report report;

    public ReportStepDefs(TestContext context, ErrorMessageHolder errorHolder) {
        this.myApp = context.app;
        this.errorHolder = errorHolder;
        this.project = myApp.getAllProjects().iterator().next(); // Assuming one project
    }

    @When("the project leader creates a report")
    public void the_project_leader_creates_a_report() {
        report = myApp.createReport(project.getId());
    }

    @Then("a report is created")
    public void a_report_is_created() {
        assertNotNull(report);
    }

    @Then("the activity {string} has {double} hours budgeted")
    public void theActivityHasHoursBudgeted(String activityName, double hoursBudgeted) {
        Map<Integer, Report.ReportEntry> entries = report.getEntries();
        Set<Activity> activities = myApp.getProject(project.getId()).getActivitySet();

        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                assertEquals(hoursBudgeted, entries.get(activity.getId()).budgetedTime());
            }
        }

        fail("Activity " + activityName + " was not found");
    }

    @And("the activity {string} has {int} hours spent")
    public void theActivityHasHoursSpent(String arg0, int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the report indicates the time budget is {int} hours")
    public void theReportIndicatesTheTimeBudgetIsHours(int budget) {
        assertEquals(budget, report.getBudgetedTime());
    }

    @Then("the report indicates the time spent is {double} hours")
    public void theReportIndicatesTheTimeSpentIsHours(double spent) {
        assertEquals(spent, report.getTimeSpent());
    }

    @Then("the report indicates the estimated time remaining is {int} hours")
    public void theReportIndicatesTheEstimatedTimeRemainingIsHours(int remaining) {
        assertEquals(remaining, report.getTimeLeft());
    }

    @Then("the report indecates the name of the project is {string}")
    public void theReportIndecatesTheNameOfTheProjectIs(String string) {
        assertEquals(string, report.getProjectName());
    }
}
