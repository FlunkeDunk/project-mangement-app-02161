package dtu.example.ui;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.SpinnerValueFactory;

public class TimeSpinnerValueFactory
        extends SpinnerValueFactory.ListSpinnerValueFactory<LocalTime> {

    public TimeSpinnerValueFactory() {
        super(FXCollections.observableArrayList(createTimes()));
    }

    private static List<LocalTime> createTimes() {
        List<LocalTime> times = new ArrayList<>();
        times.add(LocalTime.of(0, 30));

        for (int h = 1; h < 24; h++) {
            times.add(LocalTime.of(h, 0));
            times.add(LocalTime.of(h, 30));
        }

        return times;
    }
}
