package dtu.superPlanner;

public class Project {
    private WeekBasedCalendar startDate;
    private int id;

    public Project(WeekBasedCalendar startDate, int id) {
        this.startDate = startDate;
        this.id = id;
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public int getId() {
        return 27001;
    }

    public String getProjectLeader() {
        return null;
    }
}
