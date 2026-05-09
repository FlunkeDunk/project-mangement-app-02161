/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dtu.superPlanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;;

public class FileEmployeeRepository implements EmployeeRepository {

    private Map<String, Employee> employees;
    private boolean skippedLines;

    public FileEmployeeRepository(InputStream input) throws IOException {
        skippedLines = false;
        employees = loadEmployees(input);
    }

    public FileEmployeeRepository() {
        employees = new TreeMap<>();
    }

    public Set<String> getEmployeeInitials() {
        return employees.keySet();
    }

    private final Map<String, Employee> loadEmployees(InputStream input) throws IOException {
        Map<String, Employee> loadedEmployees = new TreeMap<>();

        if (input == null) {
            throw new IOException("Input file was null");

        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String initials;
            while ((initials = reader.readLine()) != null) {
                initials = initials.strip();
                if (initials.length() == 4) {
                    loadedEmployees.put(initials, new Employee(initials));
                } else {
                    skippedLines = true;
                }
            }
        } catch (IOException ex) {
            throw new IOException("Failed reading input file");
        }
        return loadedEmployees;
    }

    @Override
    public Employee get(String initials) {
        return employees.get(initials);
    }

    @Override
    public boolean contains(String initials) {
        return employees.containsKey(initials);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public void addEmployee(String initials) {
        employees.put(initials, new Employee(initials));
    }

    public boolean getSkippedLines() {
        return skippedLines;
    }

    @Override
    public List<String> findAvailableEmployeeInitials(Activity activity) {
        return findAvailableEmployees(activity).stream().map(Employee::getInitials).toList();
    }

    /**
     * @author Arthur
     */
    private List<Employee> findAvailableEmployees(Activity activity) {
        // Assert that acitvity and it's TimeFrame is not null and that no employees
        // have null initials
        assert activity != null
                && activity.getTimeFrame() != null
                && employees.values().stream().allMatch(e -> e.getInitials() != null)
                : "Preconditions not met";

        TimeFrame timeFrame = activity.getTimeFrame();

        List<Employee> result = employees.values().stream()                         // 1
                .filter(employee -> isEligibleForTimeActivity(employee, activity))  // 2
                .sorted(Comparator.comparingInt(
                        employee -> getActivitiesOverlapping(timeFrame, employee))) // 3
                .toList();

        // Assert that all employees are either
        // (in the result and available and not already assigned)
        // or (not in result and (not avaiable or already assigned the activity))
        assert getAllEmployees().stream().allMatch(
                (employee) -> (result.contains(employee) && employee.isAvailable(timeFrame)
                        && !employee.getActivities().contains(activity))
                        || (!result.contains(employee) && (!employee.isAvailable(timeFrame)
                                || employee.getActivities().contains(activity))))
                : "not all employees are either in the result or not avaiable or already assigned the activity";

        // Assert result is ordered post condition
        for (int i = 1; i < result.size(); i++) {
            Employee prev = result.get(i - 1);
            Employee curr = result.get(i);

            int prevLoad = getActivitiesOverlapping(timeFrame, prev);
            int currLoad = getActivitiesOverlapping(timeFrame, curr);

            assert prevLoad <= currLoad
                    : "Employees are not ordered by workload";
        }
        return result;
    }

    /**
     * @author BenjaminEwe
     */
    private int getActivitiesOverlapping(TimeFrame activityDuration, Employee employee) {
        Set<Activity> alreadyAssignedActivities = employee.getActivities();
        int activitiesOverlapping = 0;

        for (Activity activity : alreadyAssignedActivities) {
            TimeFrame existingActivityDuration = activity.getTimeFrame();

            if (TimeFrame.overlaps(existingActivityDuration, activityDuration)) {
                activitiesOverlapping++;
            }
        }

        return activitiesOverlapping;
    }

    private boolean isEligibleForTimeActivity(Employee employee, Activity activity) {
        return employee.isAvailable(activity.getTimeFrame()) && !employee.getActivities().contains(activity);
    }
}
