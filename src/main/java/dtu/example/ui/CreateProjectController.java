package dtu.example.ui;

import java.io.IOException;

import dtu.superPlanner.Project;
import dtu.superPlanner.WeekBasedCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateProjectController extends ProjectManagementAwareController {
    
    @FXML TextField projectNameTextField;

    @FXML
    private void createProject() throws IOException {
        Project project = app.createProject(projectNameTextField.getText());
        WeekBasedCalendar wbc = project.getStartDate();
        System.out.println("The project \"" + project.getName() + "\" (id: " + project.getId() + ") was created and starts: Week " + wbc.getWeek() + " of " + wbc.getYear());
        navigator.changeScene("project_list");
    }
}
