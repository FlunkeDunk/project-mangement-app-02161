package hellocucumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class ActivityStepDefs {

    private final ProjectManagementApp myApp;
    private final ErrorMessageHolder errorHolder;
    private final Project myProject;
    private Activity myActivity;
    private LocalDate currentDate;

    public ActivityStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        this.myApp = myApp;
        this.errorHolder = errorHolder;

        Set<Project> projects = myApp.getAllProjects();
        if (projects.size() > 1) {
            throw new IllegalStateException("Expected at most one project");
        }
        if (!projects.isEmpty()) {
            myProject = projects.iterator().next();
        } else {
            myProject = null;
        }
    }

    private Activity addActivityWithNameAndDuration(String projectName, int weeks, Boolean force) {
        WeekBasedCalendar startWeek = new WeekBasedCalendar(1, 1);
        WeekBasedCalendar endWeek = new WeekBasedCalendar(1 + weeks, 1);
        TimeFrame myTimeFrame = new TimeFrame(startWeek, endWeek);

        String prevUser = myApp.getUserInitials();
        if (force && myProject.getProjectLeader() != null) {
            myApp.login(myProject.getProjectLeader());
        }

        Activity activity = null;

        try {
            activity = myApp.createActivity(myProject.getId(), projectName, myTimeFrame);
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());
        } finally {
            if (force && prevUser != null) {
                myApp.login(prevUser);
            }
        }

        return activity;
    }

    private Activity getActivitybyName(String activityName) {
        Set<Activity> activities = null;
        activities = myApp.getProject(myProject.getId()).getActivitySet();

        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        assertNotNull(null);
        return null; // Has to have a return
    }

    private void addEmployee(String userID) {
        List<String> users = new ArrayList<>();
        users.add(userID);
        myApp.createEmployees(users);
    }

    @When("an employee tries to add activity {string} with budgeted time {int} weeks")
    public void anEmployeeTriesToAddActivity(String name, int weeks) {
        addActivityWithNameAndDuration(name, weeks, false);
    }

    @Given("the project has a project leader")
    public void theProjectHasAProjectLeader() throws IllegalAccessException {
        addEmployee("PROJECT_LEADER");
        myApp.setProjectLeader(myProject.getId(), "PROJECT_LEADER");
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

    @Then("the project has activities with the names")
    public void theProjectHasActivitiesWithTheNames(List<String> expectedActivityNames) {
        Set<Activity> activities = myProject.getActivitySet();
        List<String> activityNames = new ArrayList<>(activities.stream().map(a -> a.getName()).toList());
        List<String> expected = new ArrayList<>(expectedActivityNames);

        Collections.sort(activityNames);
        Collections.sort(expected);

        assertEquals(expected, activityNames);
    }

    @Given("the project has no activities")
    public void theProjectHasNoActivities() {
        assertTrue(myProject.getActivitySet().isEmpty());
    }

    @And("the project has the activities with the names and budgeted times")
    public void theProjectHasTheActivitiesWithTheNamesAndBudgetedTimes(List<List<String>> activities) {
        for (int i = 0; i < activities.size(); i++) {
            String name = activities.get(i).get(0);
            int duration = Integer.parseInt(activities.get(i).get(1));
            addActivityWithNameAndDuration(name, duration, true);
        }
    }

    @Given("the project has the activity {string}")
    public void theProjectHasTheActivity(String name) {
        myActivity = addActivityWithNameAndDuration(name, 0, true);
    }

    @When("the user changes the activity name to {string}")
    public void theUserChangesTheActivityNameTo(String name) {
        try {
            myApp.setActivityName(myProject.getId(), myActivity.getId(), name);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @When("the project leader sets the timeframe of activity {string} to")
    public void theProjectLeaderSetsTheTimeframeOfActivityTo(String activityName, List<List<Integer>> dates) {
        Activity activityToModify = getActivitybyName(activityName);
        List<Integer> tempDate = dates.get(0);
        TimeFrame timeFrame = null;
        try {
            WeekBasedCalendar startDate = new WeekBasedCalendar(tempDate.get(0), tempDate.get(1));
            tempDate = dates.get(1);
            WeekBasedCalendar endDate = new WeekBasedCalendar(tempDate.get(0), tempDate.get(1));
            timeFrame = new TimeFrame(startDate, endDate);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }

        if (timeFrame == null)
            return;
        myApp.setActivityTimeFrame(myProject.getId(), activityToModify.getId(), timeFrame);
    }

    @When("the project leader sets the activity {string} to end in week {int}, year {int}")
    public void theProjectLeaderSetsTheActivityToEndInWeekYear(String activityName, int week,
            int year) {
        Activity activityToModify = getActivitybyName(activityName);
        TimeFrame timeFrame = null;
        try {
            WeekBasedCalendar startDate = activityToModify.getTimeFrame().getStartDate();
            WeekBasedCalendar endDate = new WeekBasedCalendar(week, year);
            timeFrame = new TimeFrame(startDate, endDate);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }

        if (timeFrame == null)
            return;
        myApp.setActivityTimeFrame(myProject.getId(), activityToModify.getId(), timeFrame);
    }

    @Then("the activity starts in year {int} and week {int}")
    public void theActivityStartsInYearAndWeek(Integer year, Integer week) {
        WeekBasedCalendar expected = new WeekBasedCalendar(week, year);
        WeekBasedCalendar startDate = myProject.getActivityTimeFrame(myActivity.getId()).getStartDate();
        assertEquals(expected, startDate);
    }

    @Then("the activity ends in year {int} and week {int}")
    public void theActivityEndsInYearAndWeek(Integer year, Integer week) {
        WeekBasedCalendar endDate = myProject.getActivityTimeFrame(myActivity.getId()).getEndDate();
        assertEquals(year, endDate.getYear());
        assertEquals(week, endDate.getWeek());
    }

    @Then("an exception is thrown {string}")
    public void anExceptionIsThrown(String exception) {
        assertNotNull(errorHolder.getError());
        assertTrue(errorHolder.getError().contains(exception));
    }

    @Given("the activity {string} gets {double} hours budgeted")
    public void theActivityGetsHoursBudgeted(String name, double hours) {
        Activity currentActivity = getActivitybyName(name);
        try {
            myApp.setBudgetedTime(myProject.getId(), currentActivity.getId(), hours);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @Given("an employee has spent {double} hours on the activity {string}")
    public void anEmployeeHasSpentHoursOnTheActivity(double hours, String name) {
        Activity currentActivity = getActivitybyName(name);
        String debugUser = "Gandalf";

        String prevUser = myApp.getUserInitials();
        if (myApp.getProject(myProject.getId()).getProjectLeader() != null) {
            myApp.login(myProject.getProjectLeader());
        } // TODO

        try {
            myApp.addEmployeeToActivity(myProject.getId(), currentActivity.getId(), debugUser);
            addEmployee(debugUser);
            myApp.login(debugUser);
            myApp.registerTime(myProject.getId(), currentActivity.getId(), hours);
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());
        } finally {
            myApp.login(prevUser);
        }
    }

    @Given("the user has registered {double} hours on {string}")
    public void theUserHasRegisteredHoursOn(double hours, String activityName) {
        myApp.registerTime(myProject.getId(), myActivity.getId(), hours);
        currentDate = LocalDate.now();
    }

    @When("the user changes the registered time on activity {string} to {double}")
    public void theUserChangesTheRegisteredTimeOnActivityTo(String activityName, Double hours) {
        try {
            myApp.editTime(myProject.getId(), myActivity.getId(), currentDate, hours);
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
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
        myApp.login(prevUser);
    }

    @Then("{string} is not assigned to {string}")
    public void isNotAssignedTo(String assignee, String activityName) {
        Activity activity = getActivitybyName(activityName);
        assertFalse(activity.getEmployees().contains(assignee));
    }

    @Then("{string} is assigned to {string}")
    public void isAssignedTo(String assignee, String activityName) {
        assertTrue(myActivity.getEmployees().contains(assignee));
    }

    @And("{string} is the project leader") // Is this supposed to be a then or a given??? This is why we use @Given,
                                           // @When or @Then and not @And
    public void isTheProjectLeader(String projectLeader) {
        // Project leader reference needed below
        // assertEquals(projectLeader, myActivity.);
    }

    @Given("an activity {string} with a duration of {int} week(s)")
    public void anActivityWithADurationOfWeeks(String string, Integer int1) {
        WeekBasedCalendar startWeek = new WeekBasedCalendar(1, 2049);
        WeekBasedCalendar endWeek = new WeekBasedCalendar(1 + int1, 2049);
        TimeFrame timeFrame = new TimeFrame(startWeek, endWeek);

        try {
            myActivity = myApp.createActivity(myProject.getId(), string, timeFrame);
        } catch (IllegalAccessException e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @When("the user sets the budgeted time of the activity to {double} hour(s)")
    public void theUserSetsTheBudgetedTimeOfTheActivityToHours(double d) {
        try {
            myApp.setBudgetedTime(myProject.getId(), myActivity.getId(), d);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @Then("the activity has a budgeted time of {double} hour(s)")
    public void theActivityHasABudgetedTimeOfHours(double d) {
        assertEquals(d, myActivity.getBudgetedTime());
    }
}
