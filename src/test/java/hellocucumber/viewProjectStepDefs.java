package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

public class viewProjectStepDefs {

    @Given("{int} project(s) exist(s)")
    public void project_exists(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user views the list of projects")
    public void the_user_views_the_list_of_projects() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the project is shown")
    public void the_project_is_shown() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("all the {int} projects are shown")
    public void allTheProjectsAreShown(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the user is told no projects exist")
    public void theUserIsToldNoProjectsExist() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
