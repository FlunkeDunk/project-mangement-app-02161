package hellocucumber;

import dtu.superPlanner.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreateActivityStepDefs {

    private final ProjectManagementApp myApp;
    private final ErrorMessageHolder errorHolder;
    private final Project myProject;
    private final String user = "placeholder";

    public CreateActivityStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        this.myApp = myApp;
        this.errorHolder = errorHolder;

        Set<Project> projects = myApp.getAllProjects();
        if (projects.size() != 1) {
            throw new IllegalStateException("Expected exactly one project");
        }
        myProject = projects.iterator().next();
    }

    @When("an employee tries to add activity {string} with budgeted time {int} weeks")
    public void an_employee_tries_to_add_activity(String name, int weeks) {
        addActivityWithNameAndDuration(name, weeks);
    }

    @Given("the project has a project leader")
    public void theProjectHasAProjectLeader() {
        myProject.setProjectLeader("SomeOtherGuy");
    }

    @And("the user is not the project leader")
    public void theUserIsNotTheProjectLeader() {
        theProjectHasAProjectLeader();
    }

    @Then("the project should have the activities with the names and budgeted times")
    public void theProjectShouldHaveTheActivitiesWithTheNamesAndBudgetedTimes(List<List<String>> expectedActivities) {
        Set<Activity> actualActivities = myProject.getActivitySet();
        List<String> actual = actualActivities.stream()
                .map(a -> a.getName() + ":-:" + a.getDuration())
                .toList();
        List<String> expected = expectedActivities.stream()
                .map(row -> row.getFirst() + ":-:" + row.getLast())
                .toList();

        assertEquals(expected, actual, "Activities did not match");

    }

    @Given("the project has no activities")
    public void theProjectHasNoActivities() {
        assertEquals(0, myProject.getActivitySet().size());
    }


    @And("the project has the activities with the names and budgeted times")
    public void theProjectHasTheActivitiesWithTheNamesAndBudgetedTimes(List<List<String>> expectedActivities) {
        for (int i = 0; i < expectedActivities.size(); i++) {
            String name = expectedActivities.get(i).get(0);
            int duration = Integer.parseInt(expectedActivities.get(i).get(1));
            addActivityWithNameAndDuration(name, duration);
        }
    }

    private void addActivityWithNameAndDuration(String name, int weeks) {
        WeekBasedCalendar startWeek = new WeekBasedCalendar(1, 2026);
        WeekBasedCalendar endWeek = new WeekBasedCalendar(1+weeks, 2026);
        TimeFrame myTimeFrame = new TimeFrame(startWeek, endWeek);
        try {
            myProject.createActivity(name, myTimeFrame, user);
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());
        }

    }
}
