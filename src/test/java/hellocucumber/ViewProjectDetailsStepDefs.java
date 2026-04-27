package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

import java.util.List;

public class ViewProjectDetailsStepDefs {
    @When("the user views the details of the project")
    public void the_user_views_the_details_of_the_project() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} is shown for the field {string}")
    public void is_shown_for_the_field(String field_item, String field_name) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("the project has the project leader {string}")
    public void theProjectHasTheProjectLeader(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the project has no name")
    public void theProjectHasNoName() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the project has activities with the names")
    public void theProjectHasTheseActivities(List<String> acitivities) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("activities with these names are shown")
    public void theseActivitiesAreShown(List<String> activities) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("an option to cycle shown activities is given")
    public void anOptionToCycleShownActivitiesIsGiven() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the project has no activties")
    public void theProjectHasNoActivties() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
