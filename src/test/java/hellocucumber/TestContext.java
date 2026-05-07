package hellocucumber;

import dtu.superPlanner.EmployeeRepository;
import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.ProjectManagementApp;

public class TestContext {
    public final EmployeeRepository repo =
        new FileEmployeeRepository("initials.txt");

    public final ProjectManagementApp app =
        new ProjectManagementApp(repo);
}