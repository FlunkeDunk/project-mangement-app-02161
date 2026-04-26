package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

public class registerTimeStepDefs {
    @Given("the employee is assigned to the activity {string}")
    public void the_employee_is_assigned_to_the_activity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the employee's total time on {string} is {int} hours")
    public void the_employee_s_total_time_on_is_hours(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the employee registers {int} hours on {string}")
    public void the_employee_registers_hours_on(Integer int1, String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the employee's timeLedger for {string} has an entry for {int} hours")
    public void the_employee_s_time_ledger_for_has_an_entry_for_hours(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the employee is not assigned to the activity {string}")
    public void theEmployeeIsNotAssignedToTheActivity(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the employee has no time registered on the activity {string}")
    public void theEmployeeHasNoTimeRegisteredOnTheActivity(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the employee's total time for {string} is {int} hours")
    public void theEmployeeSTotalTimeForIsHours(String arg0, int arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
