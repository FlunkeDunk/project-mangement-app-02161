package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReadEmployeeFromFileStepDefs {
    @Given("a file {string} exists")
    public void aFileExists(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the file contains the following initials")
    public void theFileContainsTheFollowingInitials(io.cucumber.datatable.DataTable dataTable) {
        throw new io.cucumber.java.PendingException();
    }

    @When("a user start the program")
    public void aUserStartTheProgram() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the program returns a list containing")
public void theProgramReturnsAListContaining(io.cucumber.datatable.DataTable dataTable) {
    throw new io.cucumber.java.PendingException();
}
}
