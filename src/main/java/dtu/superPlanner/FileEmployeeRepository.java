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
    public List<Employee> findAvailableEmployees(Activity activity) {
        // Assert preconditions
        assert activity != null                                         // activity is not null
                && activity.getTimeFrame() != null                      // activitys TimeFrame is not null
                && employees != null                                    // employees map is not null
                && employees.values().stream().allMatch(e -> e != null) // no employees are null
                : "Preconditions not met";

        TimeFrame timeFrame = activity.getTimeFrame();

        Comparator<Employee> bySmallestOverlap = Comparator.comparingInt(e -> getActivitiesOverlapping(timeFrame, e));

        List<Employee> result = getAllEmployees().stream()              // 1
                .filter(e -> isEligibleForTimeActivity(e, activity))    // 2
                .sorted(bySmallestOverlap)                              // 3
                .toList();

        // Assert that all employees are either
        // in the result and eligible
        // or not in the result and not eligible

        assert getAllEmployees().stream().allMatch(employee -> {
            boolean inResult = result.contains(employee);
            boolean eligible = isEligibleForTimeActivity(employee, activity);

            return inResult == eligible;
        }) : "Inconsistent employee selection: some employees are neither correctly included nor excluded";

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

            if (activityDuration.overlaps(existingActivityDuration)) {
                activitiesOverlapping++;
            }
        }

        return activitiesOverlapping;
    }

    private boolean isEligibleForTimeActivity(Employee employee, Activity activity) {
        return employee.isAvailable(activity.getTimeFrame()) && !employee.getActivities().contains(activity);
    }
}
