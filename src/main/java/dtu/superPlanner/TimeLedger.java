package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public void editTime(LocalDate date, double newTime) {
        if (!entries.containsKey(date))
            return;

        totalTime += newTime - entries.get(date);
        entries.put(date, newTime);
    }

    public double getTime(LocalDate date) {
        return entries.get(date);
    }

    public double getTotalTime() {
        return totalTime;
    }
    public Set<LocalDate> getAllDates(){
        return entries.keySet();
    }
}
