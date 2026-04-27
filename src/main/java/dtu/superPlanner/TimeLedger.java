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
        throw new UnsupportedOperationException("Not implemented");
    }

    public void editTime(LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getTime(LocalDate date) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double getTotalTime() {
        throw new UnsupportedOperationException("Not implemented");
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
    }
}
