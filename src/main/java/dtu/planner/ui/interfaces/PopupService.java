package dtu.planner.ui.interfaces;


import java.io.IOException;
import java.util.function.Consumer;

import dtu.planner.ui.CustomScene;

public interface PopupService {

    public void popUp(CustomScene scene) throws IOException;

    public <T> void popUp(CustomScene scene, Consumer<T> controllerInitializer) throws IOException;

    public void popDown();

}