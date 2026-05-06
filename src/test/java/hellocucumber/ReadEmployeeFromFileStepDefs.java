package hellocucumber;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadEmployeeFromFileStepDefs {
    public List<String> expectedInitials;
    public File fileWithInitials;
    public List<String> actualInitials;

    private EmployeeFileReaderHolder efrh;
    private ErrorMessageHolder errorHolder;

    public ReadEmployeeFromFileStepDefs(EmployeeFileReaderHolder efrh, ErrorMessageHolder errorHolder) {
        this.efrh = efrh;
        this.errorHolder = errorHolder;
    }

    private void createFile(List<String> list, String filename) throws IOException {
        fileWithInitials = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWithInitials))) {
            String fileData = "";
            for (int index = 0; index < list.size(); index++) {
                fileData += (list.get(index) + "\n");
            }
            writer.write(fileData);
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());

        }
    }

    private File getFile() {
        return fileWithInitials;
    }

    @Given("a file {string} exists")
    public void aFileExists(String filename) throws IOException {
        fileWithInitials = new File(filename);
        fileWithInitials.createNewFile();

        assertTrue(fileWithInitials.exists());
    }

    @Given("the file {string} contains the following initials")
    public void theFileContainsTheFollowingInitials(String string, List<String> names) throws IOException {
        expectedInitials = new ArrayList<>();
        for (String name : names) {
            expectedInitials.add(name);
        }
        createFile(expectedInitials, string);
        assertTrue(fileWithInitials.exists()); // I should probably remove this, but I'm unsure how to verify it in a
                                               // pretty way
    }
    private void deleteFileWithInitials(){
        fileWithInitials.delete();
    }
    @Given("a file {string} does not exist")
    public void aFileDoesNotExist(String filename) {
        fileWithInitials = new File(filename);
        deleteFileWithInitials();

        assertNull(fileWithInitials);
    }

    @When("a user start the program")
    public void aUserStartTheProgram() throws IOException {
        try {

            InputStream inputHolder = null;

            if ((fileWithInitials).exists()) {
                inputHolder = new FileInputStream(fileWithInitials);
            }
            actualInitials = efrh.loadEmployees(new FileInputStream(fileWithInitials));
        } catch (Exception e) {
            errorHolder.setError(e.getMessage());
        }

    }

    @Then("the program returns a list containing")
    public void theProgramReturnsAListContaining(List<String> expectedNames) {
        assertEquals(expectedNames, actualInitials);
    }

    @Then("an error is now thrown {string}")
    public void anErrorIsThrown(String string) {
        assertEquals(string, errorHolder.getError());
    }
}
