package dtu.superPlanner;

/**
 * @author Emanuel
 */
public class FixedActivity extends AbstractActivity {
    private int hashCode;

    public FixedActivity(FixedActivityType type, TimeFrame timeFrame) {
        super(type.toString(), timeFrame);
        hashCode = timeFrame.hashCode() * 10 + type.ordinal(); 
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FixedActivity)) return false;

        FixedActivity other = (FixedActivity)obj;
        return other.getName().equals(this.getName()) && other.getTimeFrame().equals(this.getTimeFrame());
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
    
}
