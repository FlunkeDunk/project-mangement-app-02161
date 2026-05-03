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
        timeFrame.getStartDate().setYear(year);
        timeFrame.getStartDate().setWeek(week);
    }

    public void setEndDate(int year, int week) {
        LocalDate endDate = LocalDate.of(year, 1, 4).plusWeeks(week-1);

        if (endDate.getYear() < getStartYear() ||
                (endDate.getYear() == getStartYear() &&  getStartWeek() > endDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR))) {
            timeFrame.getEndDate().setYear(getStartYear());
            timeFrame.getEndDate().setWeek(getStartWeek());
            throw new IllegalArgumentException("End date must be after start date");
        } else {
            timeFrame.getEndDate().setYear(endDate.getYear());
            timeFrame.getEndDate().setWeek(endDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        }
    }
}
