package dtu.superPlanner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TimeLedger {
    private Map<LocalDate, Entry> entries;
    private double totalTime;

    public TimeLedger() {
        entries = new HashMap<>();
    }

    public void registerTime(LocalDate date, double time) {
        entries.put(date, new Entry(date, time));
    }

    public void editTime(LocalDate date, double newTime) {
        registerTime(date, newTime);
    }

    public double getTime(LocalDate date) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getTotalTime() {
        double totalTime = 0;
        for (Entry entry : entries.values()) {
            totalTime += entry.getTime();
        }
        return totalTime;
    }

    private class Entry {
        private LocalDate date;
        private double time;

        private Entry(LocalDate date, double time) {
            this.date = date;
            this.time = time;
        }

        private void update(double newValue) {
            time = newValue;
        }

        private double getTime() {
            return time;
        }
    }
}
