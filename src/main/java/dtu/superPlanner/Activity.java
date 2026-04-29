package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Map;
import java.util.Set;

public class Activity extends AbstractActivity {
    private Set<String> employees;
    private Map<String, TimeLedger> employeeTimeLedgers;
    private double expectedTime;
    private final int ID;

    public Activity(String name, TimeFrame timeFrame, int ID) {
        super(name, timeFrame);
        this.ID = ID;
    }

    public void registerTime(String employeeInitials, double time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getTotalTimeSpent() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public TimeLedger getTimeLedger(String employeeInitials) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void editTime(String initials, LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void addEmployee(String initials) {
        throw new UnsupportedOperationException("Not implemented");
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
}
