package dtu.superPlanner;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private List<Project> projectList = new ArrayList<>();

    public List<Project> getProjectList() {
        return projectList;
    }

    public Project getProject() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
