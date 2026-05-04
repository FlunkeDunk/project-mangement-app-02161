package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TimeLedger {
    private Map<LocalDate, Double> entries;
    private double totalTime;

    public TimeLedger() {
        entries = new HashMap<>();
        totalTime = 0;
    }

    public void registerTime(LocalDate date, double time) {
        totalTime += time;
        entries.put(date, time);
    }

    public void editTime(LocalDate date, double newTime) {
        if (!entries.containsKey(date)) return;
        
        totalTime += newTime;
        totalTime -= entries.replace(date, newTime);
    }

    public double getTime(LocalDate date) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getTotalTime() {
        double totalTime = 0;
        for (double time : entries.values()) {
            totalTime += time;
        }
        return totalTime;
    }
}
