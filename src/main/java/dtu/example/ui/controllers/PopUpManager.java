package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.function.Consumer;

import dtu.example.ui.Navigator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PopUpManager {

    private AnchorPane popUpPane;
    private Parent popUpPaneContainer;
    private Node background;
    private Navigator navigator;
    private Parent currentPopUp;

    public PopUpManager(AnchorPane popUpPane, Pane popUpContainer, Node background, Navigator navigator) {
        this.popUpPane = popUpPane;
        this.background = background;
        this.navigator = navigator;
        this.popUpPaneContainer = popUpContainer;
    }

    public <T> void popUp(String fxml, Consumer<T> initializer) throws IOException {
        FXMLLoader loader = navigator.loadFXML(fxml);
        currentPopUp = loader.load();

        if (initializer != null) {
            initializer.accept(loader.getController());
        }
        popUpPane.getChildren().add(currentPopUp);
        setup(true);
        AnchorPane.setTopAnchor(currentPopUp, 0.0);
        AnchorPane.setBottomAnchor(currentPopUp, 0.0);
        AnchorPane.setLeftAnchor(currentPopUp, 0.0);
        AnchorPane.setRightAnchor(currentPopUp, 0.0);
    }

    public void popUp(String fxml) throws IOException {
        popUp(fxml, null);
    }

    public void popDown() {
        if (currentPopUp != null) {
            popUpPane.getChildren().remove(currentPopUp);
        }
        setup(false);
    }

    private void setup(boolean poppingUp) {
        popUpPaneContainer.setMouseTransparent(!poppingUp);
        popUpPaneContainer.setVisible(poppingUp);
        background.setDisable(poppingUp);
    }

}
