package dtu.planner.ui;

public enum CustomScene {

    ADD_FIXED_ACTIVITY("add_fixed_activity"),
    ASSIGN_TO_ACTIVITY("assign_to_activity"),
    CREATE_ACTIVITY("create_activity"),
    CREATE_PROJECT("create_project"),
    EDIT_ACTIVITY("edit_activity"),
    EDIT_PROJECT("edit_project"),
    EDIT_REGISTERED_TIME("edit_registered_time"),
    LOGIN("login"),
    PROJECT_LIST("project_list"),
    REGISTER_TIME_LIST("register_time_list"),
    REGISTER_TIME("register_time"),
    VIEW_REPORT("view_report");

    private final String fxmlFile;

    CustomScene(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }
}