package dtu.example.ui.components;

import java.time.LocalTime;

import dtu.example.ui.TimeSpinnerValueFactory;
import javafx.scene.control.Spinner;

public class TimeSpinner extends Spinner<LocalTime> {

    public TimeSpinner(LocalTime time) {
        super();
        setValueFactory(new TimeSpinnerValueFactory());
        getValueFactory().setValue(time);
    }
    public TimeSpinner() {
        super();
        setValueFactory(new TimeSpinnerValueFactory());
    }


    public double getHours() {
        return getValue().getHour() + (double) getValue().getMinute() / 60.0;
    }
}