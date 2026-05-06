package hellocucumber;

import dtu.superPlanner.ProjectManagementApp;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepDefs {

    private final ProjectManagementApp myApp;
    private final ErrorMessageHolder errorMessageHolder;

    public LoginStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorMessageHolder ) {
        this.myApp = myApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("the initials {string} exists in the database")
    public void a_user_with_username_initials_exists_in_the_database(String userID) {
        List<String> users = new ArrayList<>();
        users.add(userID);
        myApp.createEmployees(users);
    }

    @When("the user tries to log in as {string}")
    public void the_user_tries_to_log_in_as(String userID) {
        try {
            myApp.login(userID);
        } catch (Exception e) {
            errorMessageHolder.setError(e.getMessage());
        }
    }

    @Then("then the user is successfully logged in as {string}")
    public void then_the_user_is_successfully_logged_in(String userID) {
        assertNotNull(myApp.getUserInitials(), "User initials was null");
        assertEquals(userID, myApp.getUserInitials(), "User initials do not match");
    }

    @Then("the user is not logged in")
    public void theUserIsNotLoggedIn() {
        assertNull(myApp.getUserInitials());
    }
}
