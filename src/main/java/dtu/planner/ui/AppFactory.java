package dtu.planner.ui;

import java.io.IOException;
import java.io.InputStream;

import dtu.superPlanner.EmployeeRepository;
import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.MemoryProjectRepository;
import dtu.superPlanner.ProjectManagementApp;
import dtu.superPlanner.ProjectRepository;

/**
 * @author Arthur
 */

public class AppFactory {
    public ProjectManagementApp createApp() throws IOException {
        EmployeeRepository employeeRepository = createEmployeeRepository();
        ProjectRepository projectRepository = createProjectRepository();
        return new ProjectManagementApp(employeeRepository, projectRepository);
    }

    private EmployeeRepository createEmployeeRepository() throws IOException {
        InputStream input = getClass().getClassLoader()
                .getResourceAsStream("initials.txt");

        if (input == null) {
            throw new IOException("Resource file initials.txt not found");
        }

        return new FileEmployeeRepository(input);
    }

    private ProjectRepository createProjectRepository() throws IOException {
        return new MemoryProjectRepository();
    }
}