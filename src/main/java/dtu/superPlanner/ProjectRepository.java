package dtu.superPlanner;

import java.util.Set;

public class ProjectRepository {
    private Set<Project> projectSet;

    public Set<Project> getProjectSet() {
        return projectSet;
    }

    public Project getProject() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
