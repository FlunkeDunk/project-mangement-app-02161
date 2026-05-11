package dtu.planner.ui;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.SpinnerValueFactory;

/**
 * @author Arthur
 */

public class TimeSpinnerValueFactory
        extends SpinnerValueFactory.ListSpinnerValueFactory<LocalTime> {

    public TimeSpinnerValueFactory() {
        super(FXCollections.observableArrayList(createTimes()));
    }

    private static List<LocalTime> createTimes() {
        List<LocalTime> times = new ArrayList<>();

        for (int h = 0; h < 24; h++) {
            times.add(LocalTime.of(h, 0));
            times.add(LocalTime.of(h, 30));
        }

        return times;
    }
}
