package hellocucumber;

import dtu.superPlanner.Project;
import dtu.superPlanner.ProjectManagementApp;
import io.cucumber.java.en.*;

public class ChangeProjectNameStepDefs {
    public String user;
    public ProjectManagementApp myApp;
    public Project project;
    private ErrorMessageHolder errorHolder;

    public ChangeProjectNameStepDefs(ProjectManagementApp myApp, ErrorMessageHolder errorHolder) {
        this.myApp = myApp;
        this.errorHolder = errorHolder;
    }

    @When("the user changes the project name to {string}")
    public void the_user_changes_the_project_name_to(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
