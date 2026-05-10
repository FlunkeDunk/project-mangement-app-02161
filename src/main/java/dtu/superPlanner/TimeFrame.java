package dtu.superPlanner;

public class TimeFrame {
    private WeekBasedCalendar startDate;
    private WeekBasedCalendar endDate;

    /**
     * @author Emanuel
     */
    public TimeFrame(WeekBasedCalendar startDate, WeekBasedCalendar endDate) throws IllegalArgumentException {
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("DateError: End date must be after start date");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WeekBasedCalendar getStartDate() {
        return startDate;
    }

    public WeekBasedCalendar getEndDate() {
        return endDate;
    }

    /**
     * @author Emanuel
     */
    public void setStartDate(WeekBasedCalendar date) throws IllegalArgumentException {
        if (endDate.before(date)) {
            throw new IllegalArgumentException("DateError: End date must be after start date");
        }
        startDate = date;
    }

    /**
     * @author Emanuel
     */
    public void setEndDate(WeekBasedCalendar date) {
        if (date.before(startDate)) {
            throw new IllegalArgumentException("DateError: End date must be after start date");
        }
        endDate = date;
    }

    /**
     * @author BenjaminEwe
     */
    public boolean overlaps(TimeFrame other) {
        WeekBasedCalendar firstStartDate = getStartDate();
        WeekBasedCalendar firstEndDate = getEndDate();
        WeekBasedCalendar secondStartDate = other.getStartDate();
        WeekBasedCalendar secondEndDate = other.getEndDate();

        /* If the second activity starts before the first one ends, but does not end before the first one starts,
         * that means that some part of it overlaps.
         * If the second activity starts after the first one ends, it is impossible to overlap
         * If the second activity end before the first one starts, it is impossible to overlap
         */

        return secondStartDate.before(firstEndDate) && !secondEndDate.before(firstStartDate);
    }

    @Override
    public String toString() {
        return "(" + startDate.toString() + " to " + endDate.toString() + ")";
    }
    
    /**
     * @author Emanuel
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeFrame))
            return false;

        TimeFrame other = (TimeFrame) obj;
        return other.startDate.equals(this.startDate) && other.endDate.equals(this.endDate);
    }

    /**
     * @author Emanuel
     */
    @Override
    public int hashCode() {
        int h1 = startDate.hashCode();
        int h2 = endDate.hashCode();
        return (h1 * h1 + h1 + 2 * h1 * h2 + 3 * h2 + h2 * h2) / 2;
    }
}
