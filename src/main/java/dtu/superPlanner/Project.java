package dtu.superPlanner;

public class Project {
    private WeekBasedCalendar startDate;
    private final int ID;
    private String name;

    public Project(WeekBasedCalendar startDate, int ID) {
        this.startDate = startDate;
        this.ID = ID;
    }

    public Project(WeekBasedCalendar startDate, int ID, String name) {
        this(startDate, ID);
        this.name = name;
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public int getId() {
        return ID;
    }

    public String getProjectLeader() {
        return null;
    }

    public String getName() {
        return name;
    }
}
