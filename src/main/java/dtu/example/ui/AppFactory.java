package dtu.example.ui;

import java.io.IOException;
import java.io.InputStream;

import dtu.superPlanner.EmployeeRepository;
import dtu.superPlanner.FileEmployeeRepository;
import dtu.superPlanner.ProjectManagementApp;

public class AppFactory {
    public ProjectManagementApp createApp() throws IOException {
        EmployeeRepository repo = createEmployeeRepository();
        return new ProjectManagementApp(repo);
    }

    private EmployeeRepository createEmployeeRepository() throws IOException {
        InputStream input = getClass().getClassLoader()
                .getResourceAsStream("initials.txt");

        if (input == null) {
            throw new IOException("Resource file initials.txt not found");
        }

        return new FileEmployeeRepository(input);
    }
}