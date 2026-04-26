package dtu.superPlanner;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Activity extends AbstractActivity {
    private Set<String> employees;
    private Map<String, TimeLedger> employeeTimeLedgers;
    private double expectedTime;

    public void registerTime(String employeeInitials, double time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getTotalTimeSpent() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void editTime(String initials, LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void addEmployee(String initials) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
