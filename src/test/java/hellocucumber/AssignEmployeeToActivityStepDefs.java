package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

public class AssignEmployeeToActivityStepDefs {
    @When("{string} assigns {string} to {string}")
    public void theEmployeeAssignsTo(String assigner, String assignee, String activity) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("{string} is not assigned to {string}")
    public void isNotAssignedTo(String assignee, String activity) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("{string} is assigned to {string}")
    public void isAssignedTo(String assignee, String activity) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("{string} is the project Leader")
    public void isTheProjectLeader(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
