package dtu.planner.ui;

import dtu.superPlanner.Project;

/**
 * @author Arthur
 */
public class ProjectDetailsView {
    private final String name;
    private final String id;
    private final String startDate;
    private final String leader;

    public ProjectDetailsView(Project project) {
        this.name = defaultValue(project.getName());
        this.id = String.valueOf(project.getId());
        this.startDate = project.getStartDate().toString();
        this.leader = defaultValue(project.getProjectLeader());
    }

    private String defaultValue(String value) {
        return value != null ? value : "None";
    }

    public String getId() {
        return id;
    }

    public String getLeader() {
        return leader;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

}
