package dtu.superPlanner;

public class Project {
    private WeekBasedCalendar startDate;
    private int id;
    private String name;

    public Project(WeekBasedCalendar startDate, int id) {
        this.startDate = startDate;
        this.id = id;
    }

    public Project(WeekBasedCalendar startDate, int id, String name) {
        this(startDate, id);
        this.name = name;
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public int getId() {
        return id;
    }

    public String getProjectLeader() {
        return null;
    }

    public String getName() {
        return name;
    }
}
