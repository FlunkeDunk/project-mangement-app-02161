package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

public abstract class AbstractActivity {
    private String name;
    private TimeFrame timeFrame;

    public AbstractActivity(String name, TimeFrame timeFrame) {
        this.name = name;
        this.timeFrame = timeFrame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    private int getStartWeek() {
        return timeFrame.getStartDate().getWeek();
    }

    private int getEndWeek() {
        return timeFrame.getEndDate().getWeek();
    }

    private int getStartYear() {
        return timeFrame.getStartDate().getYear();
    }

    private int getEndYear() {
        return timeFrame.getEndDate().getYear();
    }

    public void setStartDate(int year, int week) {
        timeFrame.setStartDate(year, week);
    }

    public void setEndDate(int year, int week) {
        timeFrame.setEndDate(year, week);
    }
}
