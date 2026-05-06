package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Activity extends AbstractActivity {
    private final Set<String> employees = new HashSet<>();
    private Map<String, TimeLedger> employeeTimeLedgers;
    private double budgetedTime;
    private final int ID;

    public Activity(String name, TimeFrame timeFrame, int ID) {
        super(name, timeFrame);
        this.ID = ID;
        employeeTimeLedgers = new HashMap<>();
    }

    public void registerTime(String employeeInitials, LocalDate date, double time) {
        if (!employeeTimeLedgers.containsKey(employeeInitials)) {
            employeeTimeLedgers.put(employeeInitials, new TimeLedger());
        }
        employeeTimeLedgers.get(employeeInitials).registerTime(date, time);
    }

    public double getTotalTimeSpent() {
        double totalTimeSpent = 0;
        for (TimeLedger timeLedger : employeeTimeLedgers.values()) {
            totalTimeSpent = timeLedger.getTotalTime();
        }
        return totalTimeSpent;
    }

    public TimeLedger getTimeLedger(String employeeInitials) {
        return employeeTimeLedgers.get(employeeInitials);
    }

    public void editTime(String initials, LocalDate date, double newTime) {
        if (newTime < 0) {
            throw new IllegalArgumentException("Negative time not allowed for activities");
        }
        employeeTimeLedgers.get(initials).editTime(date, newTime);
    }

    public void addEmployee(String assignedInitials) {
        employees.add(assignedInitials);
    }

    public Set<String> getEmployees() {
        return employees;
    }

    public int getId() {
        return ID;
    }

    public Integer getDuration() {
        WeekFields wf = WeekFields.ISO;
        TimeFrame timeFrame = this.getTimeFrame();
        WeekBasedCalendar startDate = timeFrame.getStartDate();
        WeekBasedCalendar endDate = timeFrame.getEndDate();

        LocalDate startDateLocal = LocalDate
                .now()
                .with(wf.weekBasedYear(), startDate.getYear())
                .with(wf.weekOfWeekBasedYear(), startDate.getWeek())
                .with(ChronoField.DAY_OF_WEEK, 1);
        LocalDate endDateLocal = LocalDate
                .now()
                .with(wf.weekBasedYear(), endDate.getYear())
                .with(wf.weekOfWeekBasedYear(), endDate.getWeek())
                .with(ChronoField.DAY_OF_WEEK, 1);

        return (int) ChronoUnit.WEEKS.between(startDateLocal, endDateLocal);
    }

    public void setBudgetedTime(double budgetedTime) {
        this.budgetedTime = budgetedTime;
    }

    public double getBudgetedTime() {
        return budgetedTime;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
