package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Emanuel
 */
public class TimeLedger {
    private Map<LocalDate, Double> entries;
    private double totalTime;

    public TimeLedger() {
        entries = new HashMap<>();
        totalTime = 0;
    }

    public void registerTime(LocalDate date, double time) {
        totalTime += time;
        entries.put(date, time + entries.getOrDefault(date, 0.0));
    }

    public void editTime(LocalDate date, double newTime) throws IllegalArgumentException {
        if (!entries.containsKey(date))
            throw new IllegalArgumentException("Cannot edit time for a date where the employee has no time registered");

        totalTime += newTime - entries.get(date);
        entries.put(date, newTime);
    }

    /**
     * @author BenjaminEwe
     */
    public double getTime(LocalDate date) {
        return entries.containsKey(date) ? entries.get(date) : 0;
    }

    public double getTotalTime() {
        return totalTime;
    }
    public Set<LocalDate> getAllDates(){
        return entries.keySet();
    }
}
