module hellofx {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens dtu.planner.ui to javafx.fxml; // Gives access to fxml files
    opens dtu.planner.ui.controllers to javafx.fxml; // Gives access to fxml files
    opens dtu.planner.ui.components to javafx.fxml; // Gives access to fxml files
    exports dtu.planner.ui; // Exports the class inheriting from javafx.application.Application
    exports dtu.superPlanner; // Needed by Cucumber step definitions running from test classpath
}