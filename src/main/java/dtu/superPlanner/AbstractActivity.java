package dtu.superPlanner;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;

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

    public void setStartYear(int startYear) {
        timeFrame.getStartDate().setYear(startYear);
    }

    public void setEndYear(int endYear) {
        timeFrame.getEndDate().setYear(endYear);
    }

    public void setStartWeek(int startWeek) {

        if (getTimeFrame().getStartDate().getYear() == getTimeFrame().getEndDate().getYear()
        && startWeek > getTimeFrame().getEndDate().getWeek()) {

        }

        timeFrame.getStartDate().setWeek(startWeek);
    }

    public void setEndWeek(int endWeek) {
        // We find the number of weeks in the year we are working in to make sure roll-over works nicely
        // Due to how ISO calendars work we check Dec28.
        LocalDate lastDay = LocalDate.of(timeFrame.getEndDate().getYear(), 12, 28);
        int weeksInGivenYear = lastDay.get(WeekFields.ISO.weekOfWeekBasedYear());

        if (endWeek > 100) {
            throw new IllegalArgumentException("Too many weeks, ISO weeks are hard ):");
        } else if (endWeek > weeksInGivenYear) {
            setEndYear(getTimeFrame().getEndDate().getYear() + 1);
            LocalDate nextDate = lastDay
                    .minusWeeks(weeksInGivenYear)
                    .minusYears(getTimeFrame().getEndDate().getYear() - getTimeFrame().getStartDate().getYear())
                    .plusWeeks(endWeek);
            int nextWeek = nextDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

            timeFrame.getEndDate().setWeek(nextWeek);
        } else {
            timeFrame.getEndDate().setWeek(endWeek);
        }
    }


}
