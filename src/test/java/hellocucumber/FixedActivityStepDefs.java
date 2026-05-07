package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dtu.superPlanner.FixedActivity;
import dtu.superPlanner.FixedActivityType;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.TimeFrame;
import dtu.superPlanner.WeekBasedCalendar;
import io.cucumber.java.en.*;

public class FixedActivityStepDefs {
    private final ProjectManagementApp app;
    private final ErrorMessageHolder errorHolder;
    private FixedActivity activity;
    private String user;
    private LocalDate currentDate;

    public FixedActivityStepDefs(TestContext context, ErrorMessageHolder errorHolder) {
        this.app = context.app;
        this.errorHolder = errorHolder;

        user = app.getUserInitials();
    }

    private TimeFrame getTimeFrameFromIntegerLists(List<List<Integer>> dates) {
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

        return timeFrame;
    }

    @When("sets {string} to end in year {int}")
    public void setsToEndInYear(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("sets {string} to start in week {int}")
    public void setsToStartInWeek(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("sets {string} to end in week {int}")
    public void setsToEndInWeek(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a fixed activity {string} with timeframe")
    public void aFixedActivityWithTimeframe(String string, List<List<Integer>> dates) {
        theUserCreatesAFixedActivityWithTimeframe(string, dates);
    }

    @When("the user creates a fixed activity {string} with timeframe")
    public void theUserCreatesAFixedActivityWithTimeframe(String string, List<List<Integer>> dates) {
        TimeFrame timeFrame = getTimeFrameFromIntegerLists(dates);

        if (timeFrame == null)
            return;

        try {
            activity = app.createFixedActivity(FixedActivityType.valueOf(string), timeFrame);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @Then("the user has the fixed activity {string} with timeframe")
    public void theUserHasTheFixedActivityWithTimeframe(String string, List<List<Integer>> dates) {
        List<FixedActivity> fixedActivities = new ArrayList<>(app.getFixedActivities());
        TimeFrame timeFrame = getTimeFrameFromIntegerLists(dates);
        assertNotNull(timeFrame);

        FixedActivity expectedActivity = new FixedActivity(FixedActivityType.valueOf(string), timeFrame);

        assertTrue(fixedActivities.contains(expectedActivity));
    }

    @Then("the user has {int} fixed activities")
    public void theUserHasFixedActivities(Integer int1) {
        assertEquals(int1, app.getFixedActivities().size());
    }
}
