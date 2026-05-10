package dtu.superPlanner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MemoryProjectRepository implements ProjectRepository {
    private Map<Integer, Project> projects;
    private Map<Integer, Integer> projectIdNumerators;

    public MemoryProjectRepository() {
        projects = new TreeMap<>();
        projectIdNumerators = new HashMap<>();
    }

    @Override
    public boolean contains(int projectId) {
        return projects.containsKey(projectId);
    }

    @Override
    public Project get(int projectId) {
        return projects.get(projectId);
    }

    @Override
    public Set<Project> getAllProjects() {
        return new HashSet<>(projects.values());
    }

    @Override
    public Project createProject(String name, WeekBasedCalendar date) throws RuntimeException {
        int year = date.getYear();
        int projectYearCount = getProjectCount(year);
        if (projectYearCount >= 999) {
            throw new RuntimeException("Cannot create more than 999 projects a year");
        }
        projectIdNumerators.put(date.getYear(), 1 + projectYearCount);
        int id = getProjectId(year);
        Project project = new Project(date, id, name);
        projects.put(id, project);

        return project;
    }

    private int getProjectId(int year) {
        return (year % 100) * 1000 + getProjectCount(year);
    }

    @Override
    public int getProjectCount(int year) {
        return projectIdNumerators.getOrDefault(year, 0);
    }


}
