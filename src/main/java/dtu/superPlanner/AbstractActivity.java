package dtu.superPlanner;

/**
 * @author Emanuel
 */
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
}
