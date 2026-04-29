module hellofx {
    requires transitive javafx.controls;
    requires javafx.fxml;
 
    opens dtu.example.ui to javafx.fxml; // Gives access to fxml files
    opens dtu.example.ui.controllers to javafx.fxml; // Gives access to fxml files
    opens dtu.example.ui.components to javafx.fxml; // Gives access to fxml files
    exports dtu.example.ui; // Exports the class inheriting from javafx.application.Application
    exports dtu.superPlanner; // Needed by Cucumber step definitions running from test classpath
}