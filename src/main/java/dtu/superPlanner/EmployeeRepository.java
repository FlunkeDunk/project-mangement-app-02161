package dtu.superPlanner;

import java.util.List;
import java.util.Set;

public interface EmployeeRepository {
    public Set<String> getEmployeeInitials();    

    public Employee get(String userInitials);

    public boolean contains(String initials);

    public List<Employee> getAllEmployees();

    public void addEmployee(String initials);

}
