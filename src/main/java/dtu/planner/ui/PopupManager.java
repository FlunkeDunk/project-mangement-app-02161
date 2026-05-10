package dtu.planner.ui;

import java.io.IOException;
import java.util.function.Consumer;

import dtu.planner.ui.interfaces.PopupDisplayer;
import dtu.planner.ui.interfaces.PopupService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * @author Arthur
 */

public class PopupManager implements PopupService {

    private final Navigator NAVIGATOR;
    private PopupDisplayer popupDisplayer;

    public PopupManager(PopupDisplayer popupDisplayer, Navigator navigator) {
        this.NAVIGATOR = navigator;
        this.popupDisplayer = popupDisplayer;
    }

    @Override
    public <T> void popUp(CustomScene scene, Consumer<T> initializer) throws IOException {
        popDown();
        FXMLLoader loader = NAVIGATOR.loadFXML(scene);
        Parent popup = loader.load();
        T controller = loader.getController();
        initializer.accept(controller);

        popupDisplayer.display(popup);
    }

    @Override
    public void popUp(CustomScene scene) throws IOException {
        popUp(scene, controller -> {});
    }

    @Override
    public void popDown() {
        popupDisplayer.removePopup();
    }


}
