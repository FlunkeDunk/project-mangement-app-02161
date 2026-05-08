package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.print.PrintColor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dtu.superPlanner.Employee;
import dtu.superPlanner.FileEmployeeRepository;

public class ReadEmployeeFromFileStepDefs {
    private File fileWithInitials;
    private FileEmployeeRepository fer;
    private Set<String> actualInitials = new HashSet<>();
    private ErrorMessageHolder errorHolder;

    public ReadEmployeeFromFileStepDefs(ErrorMessageHolder errorHolder)
            throws FileNotFoundException, IOException {
        this.errorHolder = errorHolder;
    }

    private void createFile(String filename, List<String> list) {
        fileWithInitials = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWithInitials))) {
            String fileData = "";
            for (String init : list) {
                fileData += (init + "\n");
            }
            writer.write(fileData);
        } catch (Exception e) {
            errorHolder.setError("File not found!");
        }
    }

    @Given("a file {string} exists")
    public void aFileExists(String filename) throws IOException {
        File fileWithInitials = new File(filename);
        fileWithInitials.createNewFile();
        assertTrue(fileWithInitials.exists());
    }

    @Given("the file {string} contains the following initials")
    public void theFileContainsTheFollowingInitials(String filename, List<String> listOfInitials) {
        createFile(filename, listOfInitials);
    }

    @Given("a file {string} does not exist")
    public void aFileDoesNotExist(String string) {
        assertNull(fileWithInitials);
    }

    @When("the file is loaded")
    public void theFileIsLoaded() throws FileNotFoundException, IOException {
        try {
            this.fer = new FileEmployeeRepository(new FileInputStream(fileWithInitials));

            List<Employee> employees = fer.getAllEmployees();
            for (Employee init : employees) {
                actualInitials.add(init.getInitials());
            }
        } catch (Exception e) {
            errorHolder.setError("File not found!");
        }
    }

    @Then("the program returns a list containing")
    public void theProgramReturnsAListContaining(List<String> expectedInitialsList) {
        Set<String> expectedInitials = new HashSet<>();
        for (String init : expectedInitialsList) {
            expectedInitials.add(init);
        }
        assertEquals(expectedInitials, actualInitials);
    }

    @Then("a notification is given to the user")
    public void aNotificationIsGivenToTheUser() {
        assertTrue(fer.getSkippedLines());
    }

    @After
    public void cleanup() {
        if (fileWithInitials != null && fileWithInitials.exists()) {
            fileWithInitials.delete();
        }
    }
}
