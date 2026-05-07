package dtu.example.ui;


import java.io.IOException;
import java.util.function.Consumer;

import dtu.superPlanner.Report;

public interface PopupService {

    public void popUp(CustomScene scene) throws IOException;

    public <T> void popUp(CustomScene scene, Class<T> type, Consumer<T> controllerInitializer) throws IOException;

    public void popDown();

    public <T extends ActivityAware> void popUpWithActivity(CustomScene registerTime, Class<T> type,
            int projectId, int activityId) throws IOException;

    public void registerTime(int projectId, int activityId) throws IOException;

    public void editActivity(int projectId, int activityId) throws IOException;

    public void editRegisteredTime(int projectId, int activityId) throws IOException;

    public void assignToActivity(int projectId, int activityId) throws IOException;

    public <T extends ProjectAware> void popUpWithProject(CustomScene registerTime, Class<T> type, int projectId) throws IOException;

    public void editProject(int projectId) throws IOException;
    
    public void addActivity(int projectId) throws IOException;

    public void viewReport(Report report) throws IOException;
}