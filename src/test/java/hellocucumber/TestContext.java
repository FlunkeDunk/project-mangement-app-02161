package hellocucumber;

import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.MemoryProjectRepository;
import dtu.superPlanner.ProjectManagementApp;

public class TestContext {
    public final ProjectManagementApp app = new ProjectManagementApp(new FileEmployeeRepository(), new MemoryProjectRepository());
        
}