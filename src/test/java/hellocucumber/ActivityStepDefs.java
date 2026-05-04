package hellocucumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import dtu.superPlanner.Activity;
import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityStepDefs {

    private final ProjectManagementApp myApp;
    private final ErrorMessageHolder errorHolder;
    private final Project myProject;
    private String user;
    private Activity myActivity;
    private LocalDate currentDate;

    public ActivityStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        this.myApp = myApp;
        this.errorHolder = errorHolder;

        Set<Project> projects = myApp.getAllProjects();
        if (projects.size() != 1) {
            throw new IllegalStateException("Expected exactly one project");
        }
        myProject = projects.iterator().next();

        user = myApp.getUserInitials();
    }

    private void addActivityWithNameAndDuration(String projectName, int weeks, Boolean force) {
        WeekBasedCalendar startWeek = new WeekBasedCalendar(1, 1);
        WeekBasedCalendar endWeek = new WeekBasedCalendar(1+weeks, 1);
        TimeFrame myTimeFrame = new TimeFrame(startWeek, endWeek);
        String priorUser = myApp.getUserInitials();
        if (force) {
            myApp.login(myProject.getProjectLeader());
        }

        try {
            myApp.createActivity(myProject.getId(), projectName, myTimeFrame);
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());
        } finally {
            if (force) {
                myApp.login(priorUser);
            }
        }
    }

    private Activity getActivitybyName(String activityName) {
        Set<Activity> activities = null;
        activities = myApp.getProject(myProject.getId()).getActivitySet();
        Activity activityToModify = null;

        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                activityToModify = activity;
                break;
            }
        }
        assertNotNull(activityToModify);
        return activityToModify;
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

    @Given("the project has the activity {string}")
    public void the_project_has_the_activity(String name) {
        addActivityWithNameAndDuration(name, 0, true);
        myActivity = getActivitybyName(name);
    }

    @When("the user changes the activity name to {string}")
    public void theUserChangesTheActivityNameTo(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("the project leader sets the activity {string} to start in week {int}, year {int}")
    public void theProjectLeaderSetsTheActivityStringToStartInWeekStartWeekYearStartYear(String activityName, int week, int year) {
        Activity activityToModify = getActivitybyName(activityName);
        try {
            activityToModify.setStartDate(year, week);
        } catch (IllegalArgumentException e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @When("the project leader sets the activity {string} to end in week {int}, year {int}")
    public void theProjectLeaderSetsTheActivityStringToEndInWeekEndWeekYearEndYear(String activityName, int week, int year) {
        Activity activityToModify = getActivitybyName(activityName);
        try {
            activityToModify.setEndDate(year, week);
        } catch (Exception e){
            errorHolder.setError(e.getMessage());
        }
    }

    @Then("the activity starts in year {int}")
    public void the_activity_starts_in_year(Integer year) {
        assertEquals(year, myActivity.getTimeFrame().getStartDate().getYear());
    }
    @Then("the activity starts in week {int}")
    public void the_activity_starts_in_week(Integer week) {
        assertEquals(week, myActivity.getTimeFrame().getStartDate().getWeek());
    }
    @Then("the activity ends in year {int}")
    public void the_activity_ends_in_year(Integer year) {
        assertEquals(year, myActivity.getTimeFrame().getEndDate().getYear());
    }
    @Then("the activity ends in week {int}")
    public void the_activity_ends_in_week(Integer week) {
        assertEquals(week, myActivity.getTimeFrame().getEndDate().getWeek());
    }

    @Then("an exception is thrown {string}")
    public void an_exception_is_thrown(String exception) {
        assertTrue(errorHolder.getError().contains(exception));
    }

    @Given("the activity {string} gets {double} hours budgeted")
    public void theActivityGetsHoursBudgeted(String name, double hours) {
        Activity currentActivity = getActivitybyName(name);
        myApp.setBudgetedTime(myProject.getId(), currentActivity.getId(), hours);
    }

    @Given("an employee has spent {double} hours on the activity {string}")
    public void anEmployeeHasSpentHoursOnTheActivity(double hours, String name) {
        Activity currentActivity = getActivitybyName(name);
        System.out.println(currentActivity.getName());
        String debugUser = "Gandalf";

        String prevUser = myApp.getUserInitials();
        myApp.login(myProject.getProjectLeader());

        try {
            myApp.addEmployeeToActivity(myProject.getId(), currentActivity.getId(), debugUser);
            currentActivity.registerTime(debugUser, hours);
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());
        } finally {
            myApp.login(prevUser);
        }
    }

    @Given("the user has registered {double} hours on {string}")
    public void theUserHasRegisteredHoursOn(double hours, String activityName) {
        myActivity.registerTime(user, hours);
        currentDate = LocalDate.now();
    }
    @When("the user changes the time of the prior registration on activity {string} to {double}")
    public void theUserChangesTheRegisteredHoursOnTheActivityInWeek(String activityName, Double hours) {
        try {
            myActivity.editTime(user, currentDate, hours);
        } catch (IllegalArgumentException e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @Then("the activity {string} has {double} hours worked")
    public void theActivityHasHoursWorked(String activityName, Double hours) {
        assertEquals(hours, getActivitybyName(activityName).getTotalTimeSpent());
    }

    @When("{string} assigns {string} to {string}")
    public void theEmployeeAssignsTo(String assigner, String assignee, String activityName) {
        String prevUser = myApp.getUserInitials();

        myApp.login(assigner);
        try {
            myApp.addEmployeeToActivity(myProject.getId(), myActivity.getId(), assignee);
        } catch (Exception e){
            errorHolder.setError(e.getMessage());
        }
        myApp.login(prevUser);
    }

    @Then("{string} is not assigned to {string}")
    public void isNotAssignedTo(String assignee, String activityName) {
        assertFalse(myActivity.getEmployees().contains(assignee));
    }

    @Then("{string} is assigned to {string}")
    public void isAssignedTo(String assignee, String activityName) {
        assertTrue(myActivity.getEmployees().contains(assignee));
    }

    @And("{string} is the project Leader")
    public void isTheProjectLeader(String projectLeader) {
        // Project leader reference needed below
        //assertEquals(projectLeader, myActivity.);
    }
}
