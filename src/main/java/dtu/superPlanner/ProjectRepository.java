package dtu.superPlanner;

import java.util.Set;


public interface ProjectRepository {

    public Project get(int projectId);
    
    public boolean contains(int projectId);

    public Set<Project> getAllProjects();

    public int getProjectCount(int year);

    public Project createProject(String name, WeekBasedCalendar date);

}
