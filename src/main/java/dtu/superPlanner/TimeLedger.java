package dtu.superPlanner;

import java.time.LocalDate;
import java.util.List;

public class TimeLedger {
    private List<Entry> entries;
    private double totalTime;

    public void registerTime(double time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void editTime(LocalDate date, double newTime) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private class Entry {
        private LocalDate date;
        private double time;

        private void update(double newValue) {
            time = newValue;
        }
    }
}
