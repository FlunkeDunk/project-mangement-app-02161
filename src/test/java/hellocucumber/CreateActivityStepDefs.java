package hellocucumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivityStepDefs {

    private final ProjectManagementApp myApp;
    private final ErrorMessageHolder errorHolder;
    private final Project myProject;
    private String user;

    public CreateActivityStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        this.myApp = myApp;
        this.errorHolder = errorHolder;

        Set<Project> projects = myApp.getAllProjects();
        if (projects.size() != 1) {
            throw new IllegalStateException("Expected exactly one project");
        }
        myProject = projects.iterator().next();

        user = myApp.getUserInitials();
    }

    @When("an employee tries to add activity {string} with budgeted time {int} weeks")
    public void an_employee_tries_to_add_activity(String name, int weeks) {
        addActivityWithNameAndDuration(name, weeks, false);
    }

    @Given("the project has a project leader")
    public void theProjectHasAProjectLeader() {
        myApp.setProjectLeader(myProject.getId(),"PROJECT_LEADER");
    }

    @Then("the project should have the activities with the names and budgeted times")
    public void theProjectShouldHaveTheActivitiesWithTheNamesAndBudgetedTimes(List<List<String>> expectedActivities) {
        Set<Activity> actualActivities = myProject.getActivitySet();
        List<String> actual = new ArrayList<>(actualActivities.stream()
                .map(a -> a.getName() + ":-:" + a.getDuration())
                .toList());
        List<String> expected = new ArrayList<>(expectedActivities.stream()
                .map(row -> row.getFirst() + ":-:" + row.getLast())
                .toList());

        Collections.sort(actual);
        Collections.sort(expected);

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
            addActivityWithNameAndDuration(name, duration, true);
        }
    }

    @And("the user is the project leader")
    public void theUserIsTheProjectLeader() {
        myApp.setProjectLeader(myProject.getId(),myApp.getUserInitials());
    }

    private void addActivityWithNameAndDuration(String projectName, int weeks, Boolean force) {
        WeekBasedCalendar startWeek = new WeekBasedCalendar(1, 2026);
        WeekBasedCalendar endWeek = new WeekBasedCalendar(1+weeks, 2026);
        TimeFrame myTimeFrame = new TimeFrame(startWeek, endWeek);
        String priorUser = myApp.getUserInitials();
        if (force) {
            myApp.login(myProject.getProjectLeader());
        }

        try {
            myApp.createActivity(myProject.getId(), projectName, myTimeFrame);
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());
            //System.out.println(e.getMessage());
        } finally {
            if (force) {
                myApp.login(priorUser);
            }
        }

    }
}
