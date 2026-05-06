package hellocucumber;

import static org.mockito.ArgumentMatchers.doubleThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import dtu.superPlanner.Activity;
import dtu.superPlanner.Employee;
import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.TimeLedger;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTimeStepDefs {
    public Project project;
    public Activity activity;
    public TimeFrame timeFrame;
    public List<Activity> activities;
    public ProjectManagementApp myApp;
    public LocalDate date = LocalDate.now();
    public final String USER = "huba";
    private ErrorMessageHolder errorHolder;

    public RegisterTimeStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        this.myApp = myApp;
        this.errorHolder = errorHolder;
    }

    private Activity getActivityByName(String names) {
        for (Activity activity : activities) {
            if (activity.getName().equals(names)) {
                return activity;
            }
        }
        return null;
    }

    private LocalDate getLocalDateFromString(String string) {
        String[] splitString = string.split("-");
        int day = Integer.parseInt(splitString[0]);
        int month = Integer.parseInt(splitString[1]);
        int year = Integer.parseInt(splitString[2]);
        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

    @Given("the project has activities with the names:")
    public void theProjectHasActivitiesWithTheNames(List<String> activityNames) throws IllegalAccessException {
        project = myApp.createProject();
        activities = new ArrayList<>();

        WeekBasedCalendar startDate = new WeekBasedCalendar(4, 2026);
        WeekBasedCalendar endDate = new WeekBasedCalendar(7, 2026);
        TimeFrame timeFrame = new TimeFrame(startDate, endDate);

        for (int i = 0; i < activityNames.size(); i++) {
            Activity activity = myApp.createActivity(project.getId(), activityNames.get(i), timeFrame);
            activities.add(activity);
        }
    }

    @Given("the employee is assigned to the activity {string}")
    public void theEmployeeIsAssignedToTheActivity(String string) throws IllegalAccessException {
        myApp.addEmployeeToActivity(project.getId(), getActivityByName(string).getId(), USER);
        assertTrue(getActivityByName(string).getEmployees().contains(USER));
    }

    @Given("that the employee's total time on {string} is {double} hours")
    public void the_employee_s_total_time_on_is_hours(String string, Double double1) {
        Activity activity = getActivityByName(string);
        activity.registerTime(USER, myApp.getTimeServer().getCurrentDate().minusDays(3), double1);
        assertEquals(double1, activity.getTimeLedger(USER).getTotalTime());
    }

    @When("the employee registers {double} hours on {string}")
    public void theEmployeeRegistersHoursOn(Double double1, String string) {
        myApp.registerTime(project.getId(), getActivityByName(string).getId(), double1);
    }

    @Then("the employee's total time on {string} is {double} hours")
    public void theEmployeeSTotalTimeOnIsHours(String string, Double double1) {
        assertEquals(double1, getActivityByName(string).getTimeLedger(USER).getTotalTime());
    }

    @When("the employee registers {double} hours on {string} on the date {string}")
    public void theEmployeeRegistersHoursOnOnTheDate(Double double1, String string, String string2) {
        try {
            myApp.registerTime(project.getId(), getActivityByName(string).getId(), double1,
                    getLocalDateFromString(string2));

        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @Then("the employee's timeLedger for {string} has an entry with the date {string} for {double} hours")
    public void theEmployeeSTimeLedgerForHasAnEntryWithTheDateForHours(String string1, String string2, Double double1) {
        assertEquals(double1, getActivityByName(string1).getTimeLedger(USER).getTime(getLocalDateFromString(string2)));
    }

    @Given("the employee has no time registered on the activity {string}")
    public void theEmployeeHasNoTimeRegisteredOnTheActivity(String string) {
        assertFalse(getActivityByName(string).getEmployees().contains(USER));
    }

    @Given("the employee is not assigned to the activity {string}")
    public void theEmployeeIsNotAssignedToTheActivity(String string) {
        assertFalse(getActivityByName(string).getEmployees().contains(USER));
    }

    @Then("the employee's timeLedger for {string} has an entry for {double} hours")
    public void theEmployeeSTimeLedgerForHasAnEntryOnTheDateTodayForHours(String string, Double double1) {
        TimeLedger timeLedger = getActivityByName(string).getTimeLedger(USER);
        timeLedger.getAllDates().stream().anyMatch(date -> timeLedger.getTime(date) == double1);
        assertTrue(timeLedger.getAllDates().stream().anyMatch(date -> timeLedger.getTime(date) == double1));
    }

    @Then("the employee's total time for {string} is {double} hours")
    public void theEmployeeSTotalTimeForIsHours(String string, Double double1) {
        assertEquals(double1, getActivityByName(string).getTimeLedger(USER).getTotalTime());
    }

    @Then("the employee is still not assigned to the activity {string}")
    public void theEmployeeIsStillNotAssignedToTheActivity(String string) {
        assertFalse(getActivityByName(string).getEmployees().contains(USER));
    }

    @When("the employee registers a negative {double} hours on {string}")
    public void theEmployeeRegistersANegativeHoursOn(Double double1, String string) {
        try {
            myApp.registerTime(project.getId(), getActivityByName(string).getId(), double1);

        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @When("the employee registers a negative {double} hours on {string} on the date {string}")
    public void theEmployeeRegistersANegativeHoursOnOnTheDate(Double double1, String string, String string2) {
        try {
            myApp.registerTime(project.getId(), getActivityByName(string).getId(), double1,
                    getLocalDateFromString(string2));

        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }
}