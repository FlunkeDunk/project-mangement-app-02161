package dtu.superPlanner;

public class WeekBasedCalendar {
    private int week;
    private int year;

    public WeekBasedCalendar(int week, int year) {
        this.week = week;
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public int getYear() {
        return year;
    }
}
