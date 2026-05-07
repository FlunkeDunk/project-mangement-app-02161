package dtu.planner.ui;

import dtu.superPlanner.Employee;
import javafx.util.StringConverter;

    /**
    * @author Arthur
    */
   
public class EmployeeStringConverter extends StringConverter<Employee> {
    @Override
    public String toString(Employee e) {
        return e == null ? "None" : e.getInitials();
    }

    @Override
    public Employee fromString(String s) {
        return null; // not needed for ChoiceBox
    }
}
