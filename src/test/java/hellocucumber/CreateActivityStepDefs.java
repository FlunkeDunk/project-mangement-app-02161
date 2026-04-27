package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

import java.util.List;

public class CreateActivityStepDefs {

    @When("an employee tries to add activity {string} with budgeted time {int} weeks")
    public void an_employee_tries_to_add_activity(String string, int weeks) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("the project has a Project leader")
    public void theProjectHasAProjectLeader() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the user is not the project leader")
    public void theUserIsNotTheProjectLeader() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the project has the activities with the names and budgeted times")
    public void theProjectHasTheActivitiesWithTheNamesAndBudgetedTimes(List<List<String>> activities) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
