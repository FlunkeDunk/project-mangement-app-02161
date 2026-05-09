package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dtu.superPlanner.Employee;
import dtu.superPlanner.FileEmployeeRepository;

public class ReadEmployeeFromFileStepDefs {
    private InputStream fileWithInitialsStream;
    private FileEmployeeRepository fer;
    private ErrorMessageHolder errorHolder;

    public ReadEmployeeFromFileStepDefs(ErrorMessageHolder errorHolder)
            throws FileNotFoundException, IOException {
        this.errorHolder = errorHolder;
    }

    private void createInputStream(List<String> list) {
        String fileData = "";
        for (String init : list) {
            fileData += (init + "\n");
        }
        fileWithInitialsStream = new ByteArrayInputStream(fileData.getBytes(StandardCharsets.UTF_8));
    }

    @Given("an initials file that contains the following initials")
    public void theFileContainsTheFollowingInitials(List<String> listOfInitials) {
        createInputStream(listOfInitials);
    }

    @Given("an initials file does not exist")
    public void aFileDoesNotExist() {
        assertNull(fileWithInitialsStream);
    }

    @When("the file is loaded")
    public void theFileIsLoaded() throws FileNotFoundException, IOException {
        try {
            this.fer = new FileEmployeeRepository(fileWithInitialsStream);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }
    }

    @Then("the program returns a list containing")
    public void theProgramReturnsAListContaining(List<String> expectedInitialsList) {
        Set<String> expectedInitials = new HashSet<>();
        for (String init : expectedInitialsList) {
            expectedInitials.add(init);
        }
        assertEquals(expectedInitials, fer.getEmployeeInitials());
    }

    @Then("a notification is given to the user")
    public void aNotificationIsGivenToTheUser() {
        assertTrue(fer.getSkippedLines());
    }
}
